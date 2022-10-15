package ca.siva.ds.generics;

//import javax.annotation.;

import java.util.ArrayList;
import java.util.List;

public class GenericsInfo {
//    public static final Logger logger = L
    public static <T> T getValueOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static <T> T first(List<? extends T> list) {
        return list.get(0);
    }

    public static void main(String[] args) {
        List<String> x = new ArrayList<String>(){
            {
                add("abc");
            }
        };
        System.out.println(first(x));
    }

}
