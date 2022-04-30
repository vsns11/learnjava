package ca.siva.ds.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class LearnArrays {

    static class Employee {
        public String name;
        public int employeeId;

        public Employee(String name, int employeeId) {
            this.name = name;
            this.employeeId = employeeId;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", employeeId=" + employeeId +
                    '}';
        }
    }

    public static void main(String[] args) {
        //primitive array and sort
        int[] x = {1, 2, 3, 0};
        Arrays.sort(x);
        System.out.println(Arrays.toString(x));

        // copy
        int[] y, z;
        y = Arrays.copyOfRange(x, 0, x.length);
        z = Arrays.copyOf(x, x.length);
        System.out.println(Arrays.toString(y));
        System.out.println(Arrays.toString(z));

        //Sort objects
        Employee[] employees = new Employee[3];
        employees[0] = new Employee("Siva", 3);
        employees[1] = new Employee("Naga", 2);
        employees[2] = new Employee("Sai", 1);
        //Comparator
        Arrays.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Integer.compare(o1.employeeId, o2.employeeId);
            }
        });
        //Lambda Expressions
        Arrays.sort(employees, (o1, o2) -> Integer.compare(o1.employeeId, o2.employeeId));
        //ComparingInt Expressions
        Arrays.sort(employees, Comparator.comparingInt(o -> o.employeeId));
        System.out.println(Arrays.toString(employees));

        //fill
        int[] p = new int[5];
        Arrays.fill(p, 12);
        System.out.println(Arrays.toString(p));
        Arrays.fill(p, 0, 2, 13);
        System.out.println(Arrays.toString(p));

        //BinarySearch: if key found, return index
        int[] w = {0, 1, 2 ,3 ,4 ,5 ,6, 7, 9};
        System.out.println(Arrays.binarySearch(w, 8));
        System.out.println(Arrays.binarySearch(w, 2));
        // the right position is at, 0(returns -1) + 1
        System.out.println(Arrays.binarySearch(w, 0, 2, -1));

        //Equals: check if 2 arrays are equal
        int[] arr1 = {0, 1, 2};
        int[] arr2 = {0, 1, 2};
        System.out.println(Arrays.equals(arr1, arr2));
    }
}
