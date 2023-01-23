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

public class LearnObjectNode {
    private static final ObjectMapper mapper;
    static  {
        mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
    public static void main(String[] args) throws JsonProcessingException {
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

        var siva = "1";

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
        System.out.println(ans);

        ArrayNode arr1 = mapper.createArrayNode().add(mapper.createObjectNode().put("sai", "siva"));
        System.out.println(StreamSupport.stream(arr1.spliterator(), false).anyMatch(w1->w1.has("sai")));


    }
}
