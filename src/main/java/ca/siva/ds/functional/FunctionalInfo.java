package ca.siva.ds.functional;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FunctionalInfo {

    public static <T> List<T> filterMatches(Predicate<T> criteria, List<T> list) {
        return list.stream().filter(criteria).collect(Collectors.<T>toList());
    }

    public static <U, V, K> Map<U, K> convertListToMap(Predicate<V> criteria, List<V> list, Function<V, U> keyMapper,
                                                       Function<V, K> valueMapper) {
        return list.stream().filter(criteria).collect(Collectors.toMap(keyMapper, valueMapper));
    }


    static class A {
        int i;
        String j;

        public A(int i, String j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "A{" +
                    "i=" + i +
                    ", j='" + j + '\'' +
                    '}';
        }


    }

    static class Ahelper implements Predicate<A> {
        @Override
        public boolean test(A a) {
            return a.j.equals("siva");
        }
    }

    public static void main(String[] args) {
        List<A> dList = new ArrayList<>();

        dList.add(new A(1, "siva"));
        dList.add(new A(2, "sai"));
        dList.add(new A(3, "siva"));

        // old approach #1
        System.out.println(FunctionalInfo.filterMatches((x) -> {
            return x.j.equals("siva");
        }, dList));
        // old approach #1
        System.out.println(FunctionalInfo.filterMatches(new Ahelper(), dList));

        // new approaches
        System.out.println(FunctionalInfo.filterMatches(x -> x.j.equals("siva"), dList));

        final Map<Integer, String> cache = new HashMap<>();
        cache.put(1, "x");
        System.out.println(FunctionalInfo.filterMatches(x -> !cache.containsKey(x.i), dList));

        Supplier<Boolean> condition = () -> true;
        System.out.println(condition.get());

        System.out.println(FunctionalInfo.convertListToMap(
                        q -> q.i != 2, // criteria
                        dList,   // input list of objects
                        p -> p.i, // key extractor using the input item of dList
                        q -> q.j // value extractor using the input item of dList
                )
        );


        String a = dList.stream().filter((obj) -> obj.j != null && obj.j.equalsIgnoreCase("1")).collect(
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.size() < 1) {
                                return null;
                            }
                            return list.get(0).j;
                        }
                )
        );

//        System.out.println(a);

    }
}
