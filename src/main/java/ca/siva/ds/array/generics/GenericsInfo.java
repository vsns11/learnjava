package ca.siva.ds.array.generics;

//import javax.annotation.;

public class GenericsInfo {

    public static <T> T getValueOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static void main(String[] args) {

    }
}
