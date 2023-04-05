package ca.siva.ds.misc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


class MyClass2 {
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

public class LearnObjectNode {
    private static final ObjectMapper mapper;
    static  {
        mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public static void testObjectWithMapper() throws JsonProcessingException {
        String json = "{\"data\": {\"key1\": \"value1\", \"key2\": \"value2\", \"key3\": \"value3\"}}";
//        String json = "{\"data\": [{\"key1\": \"value1\", \"key2\": \"value2\", \"key3\": \"value3\"}]}";
        MyClass2 myClass = mapper.readValue(json, MyClass2.class);

        Object data = mapper.readTree(mapper.writeValueAsString(myClass.getData()));

        if (data instanceof List) {
            List<Integer> list = (List<Integer>) data;
            System.out.println("List: " + list);
        } else if (data instanceof ArrayNode) {
            ArrayNode arrayNode = (ArrayNode) data;
            System.out.println("ArrayNode: " + arrayNode);
        } else if (data instanceof String) {
            String string = (String) data;
            System.out.println("String: " + string);
        } else if (data instanceof Number) {
            Number number = (Number) data;
            System.out.println("Number: " + number);
        } else {
            System.out.println("Unexpected data type: " + data.getClass().getName());
        }
    }
    public static void main(String[] args) throws JsonProcessingException {
        testObjectWithMapper();
        String json = "{\"data\":\"[1, 2, 3]\"}";

        MyClass2 myClass = mapper.readValue(json, MyClass2.class);

        String x = "[{\"1\": \"1\"}]";

        ArrayNode w =  mapper.readValue(x, ArrayNode.class);
        ObjectNode jsonNode = (ObjectNode) w.get(0);

        System.out.println(jsonNode);
//        String x = "a", y = "b";

        JsonNode arrayNode = mapper.createArrayNode().add("one").add("two");
        String dummyString = "[{\"value\":\"dE121_siva\"},{\"value\":\"dE142_siva\"},{\"value\":\"dE129_siva\"},\"\"]";
        JsonNode node = mapper.readTree(dummyString);

        List<DummyItemValue> list =  mapper.readValue(dummyString, new TypeReference<List<DummyItemValue>>(){});
        System.out.println(list);
        System.out.println("--");
        System.out.println(mapper.convertValue(list, ArrayNode.class));
        System.out.println(list.stream()
                .sorted((DummyItemValue l, DummyItemValue m)->{
                    try {

                        Integer value1 = Integer.valueOf(l.value.split("_")[0].substring(2));
                        Integer value2 = Integer.valueOf(m.value.split("_")[0].substring(2));

                        return value2.compareTo(value1);
                    } catch (StringIndexOutOfBoundsException e) {
                        return 1;
                    }
                }).collect(Collectors.toList()));

        String ans = list.stream()
                    .filter(Objects::nonNull)
                    .sorted((DummyItemValue l, DummyItemValue m)->{
                           try {

                               Integer value1 = Integer.valueOf(l.value.split("_")[0].substring(2));
                               Integer value2 = Integer.valueOf(m.value.split("_")[0].substring(2));

                               return value2.compareTo(value1);
                           } catch (StringIndexOutOfBoundsException e) {
                               return 1;
                           }
                    }).findFirst().map(DummyItemValue::getValue).orElse(null);


        ArrayNode arr1 = mapper.createArrayNode().add(mapper.createObjectNode().put("sai", "siva"));
        JsonNode payload = mapper.createObjectNode().putArray("data").addAll(arr1);

        System.out.println(payload);

        StreamSupport.stream(arr1.spliterator(), false).forEach(
                result -> {
                    if ("siva".equalsIgnoreCase(result.get("sai").asText())) {
                        ((ObjectNode)result).put("sai", "siva1");
                    }
                }
        );

        System.out.println(payload);

//        System.out.println(node2.get("key2").toString());
//      Use as Text to get string representation (without "\" in the representation), i.e, needed for the conversion, whereas toString() does the opposite
//        Object o = mapper.readValue(node2.get("key2").asText(), DummyItemValue.class);
//        System.out.println(o);
// convert the object to jsonNode
//        System.out.println(mapper.convertValue(o, JsonNode.class));



    }
}
