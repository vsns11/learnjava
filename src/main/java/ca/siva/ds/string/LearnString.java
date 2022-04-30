package ca.siva.ds.string;

import java.nio.charset.Charset;
import java.util.Arrays;

public class LearnString {
    public static void main(String[] args) {
        // create String object
        String x = new String("string declaration #1");
        String y = "string declaration #2"; // String literal

        // String literals are obtained from a constant String pool which the jvm keeps internally.
        //In this e.g. both variables refer to the same instance in memory
        String p = "Hi";
        String q = "Hi"; // here both point to the same instance in StringPool

        // To point two String variables to separate String objects.
        String var1 = new String("Hi");
        String var2 = new String("Hi");

        // Java text blocks, introduced in Java13 using """ notation.
        String r = p + q;
        /**
         *  This concatenation is similar to. new StringBuilder(p).append(q).toString();
         * This becomes a huge concern with a looping over concatenation, as it creates 2 new objects
         * [ 1 builder + 1 string from toString]
         * in every iteration.
         */
        StringBuilder sb  = new StringBuilder(p);
        sb.append(q);

        // Substring
        System.out.println(p.substring(1));

        // indexOf takes string and returns the first index of the matching substring
        System.out.println(p.indexOf(q));
        System.out.println(p.indexOf(q, 0));

        // lastIndexOf returns the last matched starting index of the substring
        System.out.println(p.lastIndexOf(q));

        // matches: takes regex and returns true/false
        boolean matches = p.matches(".*hi.*");
        System.out.println(matches);

        //equals
        System.out.println(p.equals(r));

        //equalsIgnoreCase
        System.out.println(p.equalsIgnoreCase(r));

        //StartsWith
        System.out.println(p.startsWith("hi"));
        System.out.println(p.startsWith("hi", 0));

        //endsWith
        System.out.println(p.endsWith("hello"));

        //CompareTo
        System.out.println(p.compareTo(q));

        //Trimming Strings with trim, that removes whitespace, tab and new lines at the beginning and end.
        System.out.println(p.trim());

        //replace returns a new instance of the string
        System.out.println(p.replace("a", "x"));

        //replaceFirst replaces only the firstMatch of the regular expression
        System.out.println(p.replaceFirst("x", "new"));

        //replaceAll - replaces all the matches of regex
        System.out.println(p.replaceFirst("new", "old"));

        //split
        String   source = "A man drove with a car.";
        String[] occurrences = source.split("a");
        System.out.println(Arrays.toString(occurrences));

        //split with limit - the size of the split cuts down to the limit
        String   source1 = "A man drove with a car.";
        String[] occurrences1 = source.split("a", 1);
        System.out.println(Arrays.toString(occurrences1));

        //valueOf : - converts number to a string
        String intStr = String.valueOf(10);
        System.out.println("intStr = " + intStr);
        String flStr = String.valueOf(9.99);
        System.out.println("flStr = " + flStr);

        // toString() :-
        Integer integer = new Integer(123);
        String intStr2 = integer.toString();

        // characters and Bytes
        String theString = "This is a good day to code";
        System.out.println( theString.charAt(0) );
        System.out.println( theString.charAt(3) );

        //bytes
        String theString2 = "This is a good day to code";

        byte[] bytes1 = theString2.getBytes();
        byte[] bytes2 = theString2
                .getBytes(Charset.forName("UTF-8")); // follows utf-8 encoding scheme 1,2,3,4 bytes for each char

        /** from java13
         * String input = "Hello %s";
         *
         * String output1 = input.formatted("World");
         * System.out.println(output1);
         *
         * String output2 = input.formatted("Jakob");
         * System.out.println(output2);
         *
         *
         * String input  = "   Hey \n   This \n   is \n   indented.";
         * String output = input1.stripIndent();
         *
         * System.out.println(output);
         *
         * String input = "Hey, \\n This is not normally a line break.";
         * System.out.println(input);
         *
         * String output = input.translateEscapes();
         * System.out.println(output);
         */
        int amount = 12;
        // break to exit the switch, else continues the execution until finds a break
        switch(amount) {
            case     0 : System.out.println("amount is  0"); break;
            case     5 : System.out.println("amount is  5"); break;
            case    10 : System.out.println("amount is 10"); break;
            default    : System.out.println("amount is something else");
        }

    }
}
