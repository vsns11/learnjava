package ca.siva.ds.functional;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsInfo {
    public static <T> Set<T> intersect(Collection<T>... collections) {
        //Here is where the magic happens
        return (Set<T>) Arrays.stream(collections).reduce(
                (a,b) -> {
                    Set<T> c = new HashSet<>(a);
                    c.addAll(b);
                    return c;
                }).orElseGet(HashSet::new);
    }


    public static void main(String[] args) {
        List<Integer> arr1 = new ArrayList<>(){
            {
                add(10);
                add(12);
            }
        };

        List<Integer> arr2 = new ArrayList<>(){
            {
                add(12);
                add(13);
                add(10);
            }

        };
        List<Integer> collection = new ArrayList<>();
        collection.addAll(arr1);
        collection.addAll(arr1);
        collection.addAll(arr2);
//        System.out.println(collection);
//        System.out.println(collection.stream().distinct().collect(Collectors.toList()));
        streamTest();

    }

    public static void streamTest() {

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
        i.w = 100;
        List<Test> list1 = new ArrayList<>();
        list1.add(i);
        Stream<Test> es = Stream.empty();



        //Terminal Operations:
        /*
          Reduce 3 variants
          1) T reduce(T identity,  BinaryOperator<T> accumulator)
          2) Optional<U> reduce(BinaryOperator<T> accumulator)
          3) <T, R> R reduce(R identity, BiFunction<R, ? super T, R> accumulator, BinaryOperator<R> combiner)
         */
        List<Integer> sList = Arrays.asList(1, 2, 3, 3);
        System.out.println(sList);
        int totalSum = sList.parallelStream().reduce(0, (w1, q1) -> w1 + q1, (w2, q2) -> w2 + q2);
        int totalSum2 = sList.stream().reduce(0, (w1, q1) -> w1 + q1);
        System.out.println(totalSum);
        System.out.println(totalSum2);

        /*
         Collect has 2 variants
          1) R collect(Supplier<R> supplier, BiConsumer<R, T> accumulator, BiConsumer<R, R> combiner)
          2) <R, A> R collect(Collector<? super T, A, R> collector) ****
         */
        Map<Integer, Boolean> cmap = sList.stream().collect(
                () -> new HashMap<>(),
                (HashMap<Integer, Boolean> ir, Integer value) -> {
                    if (!ir.containsKey(value)) {
                        ir.put(value, Boolean.TRUE);
                    }
                },
                (HashMap<Integer, Boolean> map1, HashMap<Integer, Boolean> map2) -> {}
                );

        System.out.println(cmap);

        System.out.println(Stream.of("1", "2", "3").collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString());
        System.out.println(Stream.of("1", "2", "3").collect(TreeSet::new, TreeSet::add, TreeSet::addAll).toString());

        /*
         count()
         */
        System.out.println(Stream.iterate(1, n -> n < 3, n -> n + 1).count());

        /*
         min(Comparator<? super T > comparator)
         max(Comparator<? super T > comparator)
         */
        System.out.println(Stream.of(1, 2, 3).max(Integer::compareTo).orElse(null));

        /*
        Optional<T> findFirst()
        Optional<T> findAny()
        */
        System.out.println(Stream.of(1, 2, 3).findAny().orElse(null));
        System.out.println(Stream.of(1, 2, 3).findFirst().orElse(null));

        /*
         boolean allMatch(Predicate<? super T> predicate)
         boolean anyMatch(Predicate<? super T> predicate)
         boolean noneMatch(Predicate<? super T> predicate)
         */
        List<String> terList = List.of("1",  "2", "3");
        System.out.println(terList.stream().anyMatch(_x -> Character.isDigit(_x.charAt(0))));
        System.out.println(Stream.generate(() -> "1iva").allMatch(_x -> Character.isAlphabetic(_x.charAt(0))));
        System.out.println(Stream.generate(() -> "1iva").noneMatch(_x -> Character.isDigit(_x.charAt(0))));

        /*
        void forEach(Consumer < ? super T > action)
        */
        terList.stream().forEach(System.out::print);

        // Keeps the treeset sorted
        System.out.println(Stream.of(2, 1, 3).collect(Collectors.toCollection(TreeSet::new)).toString());

        // To keep treeset unsorted, doesn't guaranteed which impl of set you'll get.
        System.out.println(Stream.of(2, 1, 3).collect(Collectors.toSet()).toString());

        /*
            Intermediate Operations
            Stream<T> filter(Predicate<? super T> predicate)
            Stream<T> distinct()
            Stream<T> limit(long maxSize)
            Stream<T> skip(long n)
            Stream<R> map(Function<? super T, ? extends R> mapper)
            Stream<R> flatMap(Function<? super T, Stream<? extends R>> mapper)
            Stream<T> sorted(Comparator< ? super T> comparator)
            Stream<T> peek(Consumer<? super T> action)
         */
        // filter()
        terList.stream().filter(_x -> _x.startsWith("ab"));
        // distinct(), calls equals method on each of the method
        terList.stream().distinct().forEach(System.out::println);
        // skip(),It will skip 2 elements indexed at 0 and 1. And start processing from index 2
        terList.stream().skip(2).forEach(System.out::println);
        // map()
        Stream.of("monkey", "xx").map(String::length);
        // flatMap()
        Stream.of(Stream.of(2)).flatMap(w -> w).forEach(System.out::print);
        // sorted()
        Stream.of(2, 1).sorted().forEach(System.out::println);
        // sorted(Comparator< ? super T > comparator)
        Stream.of(2, 1).sorted(Comparator.naturalOrder()).forEach(System.out::print);
        System.out.println("\n");
        Stream.of(2, 0, 1).sorted(Comparator.reverseOrder()).forEach(System.out::print);
        Stream.of(2, 0, 1).sorted(Comparator.reverseOrder()).peek(System.out::print).forEach(System.out::print);

    }
}
