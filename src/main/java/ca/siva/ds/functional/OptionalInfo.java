package ca.siva.ds.functional;

import java.util.Objects;
import java.util.Optional;

public class OptionalInfo {

    public static void main(String[] args) {
        String y = "abc";
        String z = null;
        Optional.ofNullable(z).filter(Objects::nonNull).map(w -> w.equalsIgnoreCase(y)).map(x -> y + "a").orElse(null);

    }
}
