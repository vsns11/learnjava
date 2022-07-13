package ca.siva.ds.functional;

import com.sun.tools.javac.util.List;

import java.util.Optional;
import java.util.stream.Stream;

public class StreamsInfo {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 12, 10, 4);

//        List<Integer> list = null;
        Stream<Integer> stream = list == null ? Stream.empty() : list.stream();

        Optional<String> opt = stream.filter(x -> x == 4).findFirst().map((x) -> {
            return "sai";
        });

        System.out.println(opt);
        String z = opt.orElse(null);
        System.out.println("z".equalsIgnoreCase("Z"));
        System.out.println(z);


    }
}
