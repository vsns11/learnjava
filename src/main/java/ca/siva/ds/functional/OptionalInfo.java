package ca.siva.ds.functional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class OptionalInfo {

    public static <T> Optional<T> testFindValue(JsonNode node, String key, Function<JsonNode, T> converter) {
        return Optional.of(node)
                        .map(x -> x.findValue(key))
                        .map(converter);
    }

    public static void main(String[] args) {
        ObjectNode wx = new ObjectMapper().createObjectNode();
        wx.put("abc", 90.5);
        System.out.println(testFindValue(wx, "abc", JsonNode::asDouble));

        String y = "abc";
        String z = null;
        Optional.ofNullable(z).filter(Objects::nonNull).map(w -> w.equalsIgnoreCase(y)).map(x -> y + "a").orElse(null);

        /*Instance Methods.
            1) get()
            2) ifPresent(Consumer c) ->
            3) orElse(T other) ->
            4) orElseGet(Supplier s) -> Return
            5) orElseThrow() -> NoSuchElementException
            6) orElseThrow(Supplier s) -> Throws exception supplied by the supplier

         */
        // #1 get()
        Optional<Integer> _x = Optional.of(2);
        Integer _y = _x.get();
        int _w = 1;
        Integer _z = _w;



    }
}
