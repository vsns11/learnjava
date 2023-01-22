package ca.siva.ds.misc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
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
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

public class LearnApacheHttpClient {
    private static final ObjectMapper mapper;

    static  {
        mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
    public static void apiInvocation(RequestBuilder requestBuilder) {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(20000)
                .build();
        String acceptCharSet = "UTF-8";

        try (final CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {

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

