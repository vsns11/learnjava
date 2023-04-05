package ca.siva.ds.misc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

public class LearnApacheHttpClient {
    private static final ObjectMapper mapper;
    private static final int MAX_RETRIES = 3;
    private static final long DELAY = 1000;
    private static RequestConfig config;

    static  {
        mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(20000)
                .build();

    }
    public static void apiInvocation(RequestBuilder requestBuilder) {

        String acceptCharSet = "UTF-8";

        try (final CloseableHttpClient httpclient = HttpClients.custom()
                                                .addInterceptorLast((HttpResponseInterceptor) (response, context) -> {
                                                    if (response.getStatusLine().getStatusCode() >= 400) {
                                                        // Throw an IOException to force a retry via HttpRequestRetryHandler.retryRequest()
                                                        throw new IOException("Invalid code returned: " + response.getStatusLine().getStatusCode());
                                                    }
                                                })
                                                .setRetryHandler((exception, executionCount, context) -> {
                                                    if (executionCount > MAX_RETRIES) { // MAX_RETRIES = 3
                                                        return false;
                                                    } else {
                                                        try {
                                                            // Sleep before retrying
                                                            Thread.sleep(DELAY); // DELAY = 1000 MS
                                                        } catch (InterruptedException ex) {
                                                            // ... Log or silently swallow
                                                            Thread.currentThread().interrupt();
                                                        }
                                                        return true;
                                                    }
                                                })

                .setDefaultRequestConfig(config)
                                    .build()) {


            //request
            HttpUriRequest request = requestBuilder.build();

            //targetHost
            URI requestUri = requestBuilder.getUri();
            HttpHost targetHost = new HttpHost(requestUri.getHost(), requestUri.getPort(), requestUri.getScheme());

            //clientContext
            AuthCache authCache = new BasicAuthCache();
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(targetHost, basicAuth);
            HttpClientContext clientContext = HttpClientContext.create();
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(new AuthScope(requestUri.getHost(), requestUri.getPort(), AuthScope.ANY_REALM), new UsernamePasswordCredentials("username", "password"));
            clientContext.setCredentialsProvider(credsProvider);
            clientContext.setAuthCache(authCache);

            // final step use httpClient to execute the api invocation
            CloseableHttpResponse response = httpclient.execute(targetHost, request, clientContext);
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity, acceptCharSet);

            System.out.println(responseBody);
        } catch (IOException e) {

        }
    }


    public static void performGetOperation() {
        RequestBuilder requestBuilder = RequestBuilder.get().setUri("https://dummyjson.com/products");
        requestBuilder.addHeader(HttpHeaders.ACCEPT, "application/json");
        requestBuilder.addHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8");
        apiInvocation(requestBuilder);
    }

    public static void postApiInvocation() {
        RequestBuilder requestBuilder = RequestBuilder.post().setUri("https://dummyjson.com/posts/add");
        ObjectNode objectNode = mapper.createObjectNode().put("title", "Hello world").put("userId", 1);
        StringEntity entity = new StringEntity(objectNode.toString(), ContentType.parse("application/json;charset=utf-8"));
        requestBuilder.addHeader(HttpHeaders.ACCEPT, "application/json");
        requestBuilder.addHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8");
        requestBuilder.setEntity(entity);
        apiInvocation(requestBuilder);

    }

    public static void patchApiInvocation() {
        RequestBuilder requestBuilder = RequestBuilder.patch().setUri("https://dummyjson.com/posts/1");
        ObjectNode objectNode = mapper.createObjectNode().put("title", "I'm a patch request");
        StringEntity entity = new StringEntity(objectNode.toString(), ContentType.parse("application/json;charset=utf-8"));
        requestBuilder.addHeader(HttpHeaders.ACCEPT, "application/json");
        requestBuilder.addHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8");
        requestBuilder.setEntity(entity);
        apiInvocation(requestBuilder);
    }

    public static void deleteApiInvocation() {
        RequestBuilder requestBuilder = RequestBuilder.delete().setUri("https://dummyjson.com/posts/1");
        requestBuilder.addHeader(HttpHeaders.ACCEPT, "application/json");
        requestBuilder.addHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8");
        apiInvocation(requestBuilder);
    }

    public static void main(String[] args) {
        performGetOperation();
        postApiInvocation();
        patchApiInvocation();
        deleteApiInvocation();
    }
}

