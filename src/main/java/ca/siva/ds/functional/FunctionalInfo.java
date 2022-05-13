package ca.siva.ds.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionalInfo {

    public <T> List<T> filter(Predicate<T> criteria, List<T> list) {
        return list.stream().filter(criteria).collect(Collectors.<T>toList());
    }

    static class A {
        int i;
        String j;

        public A(int i, String j) {
            this.i = i;
            this.j = j;
        }
    }


    public static void main(String[] args) {
        List<A> dList = new ArrayList<>();



        String a = dList.stream().filter((obj) -> obj.j!= null && obj.j.equalsIgnoreCase("1")).collect(
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
        System.out.println(a);

    }
}
