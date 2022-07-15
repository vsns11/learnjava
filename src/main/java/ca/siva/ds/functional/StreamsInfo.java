package ca.siva.ds.functional;

import com.sun.tools.javac.util.List;

import java.util.ArrayList;
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
        System.out.println(opt); // Empty optional if it doesn't match
        String z = opt.orElse(null);

        //Finite streams
        Stream<Integer> s1 = Stream.of(1, 2, 3);
        Stream<Integer> s2 = Stream.empty();
        ArrayList<Integer> a1 = new ArrayList<Integer>() {
            {
                add(2);
                add(3);
            }
        };
        Stream<Integer> s3 = a1.stream();


        //Infinite streams
        Stream<Double> s4 = Stream.generate(Math::random);
        Stream<Integer> s5 = Stream.iterate(1, n -> n + 2);
        //To make infinite streams finite, use limit
        // Print never ends, until manually interrupted
        // s4.forEach(System.out::println);

        // Below iterate overloaded method works in java versions > 9
        Stream<Integer> s6 = Stream.iterate(1, n -> n < 100, n -> n  + 1);

        Integer x = 10;
        class Test {
            Integer w;

        }
        Test i = new Test();
        System.out.println(Optional.ofNullable(x).map( y -> null).map(w -> i.w).orElse(123));
//        System.out.println(Optional.of(new Test()).map( y -> null).orElse(123));
        //Terminal Operations:


    }
}
