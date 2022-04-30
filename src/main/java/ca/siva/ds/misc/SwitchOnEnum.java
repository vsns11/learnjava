package ca.siva.ds.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * From Java12, switch can return a value too.
 */
public class SwitchOnEnum {
    private static enum Size {
        SMALL, MEDIUM, LARGE, X_LARGE
    }

    private static void switchOnEnum(Size size) {
        switch (size) {

            case SMALL: {
                System.out.println("size is small");
                break;
            }
            case MEDIUM: {
                System.out.println("size is medium");
                break;
            }
            case LARGE: {
                System.out.println("size is large");
                break;
            }
            case X_LARGE: {
                System.out.println("size is X-large");
                break;
            }

            default: {
                System.out.println("size is not S,M,L or XL: " + size);
            }
        }
    }

    public static void main(String[] args) {
        // Here the -> denotes return value statement, so no need of return statement.
//        int digitInDecimal = 12;
//        char digitInHex =
//                switch (digitInDecimal) {
//                    case 0 -> '0';
//                    case 1 -> '1';
//                    case 2 -> '2';
//                    case 3 -> '3';
//                    case 4 -> '4';
//                    case 5 -> '5';
//                    case 6 -> '6';
//                    case 7 -> '7';
//                    case 8 -> '8';
//                    case 9 -> '9';
//                    case 10 -> 'A';
//                    case 11 -> 'B';
//                    case 12 -> 'C';
//                    case 13 -> 'D';
//                    case 14 -> 'E';
//                    case 15 -> 'F';
//
//                    default -> '?';
//                };
//
//        System.out.println(digitInHex);

        /**
         *
         * works with Java13. Here -> same as yield for returning the value.
         *String token = "123";
         *
         * int tokenType = switch(token) {
         *     case "123" : yield 0;
         *     case "abc" : yield 1;
         *     default : yield -1;
         * };
         *
         */

        Map<Object, Object> map = new HashMap();
        // referencing object comparison occurs and here it will be true.
        boolean mapIsObject = map instanceof HashMap;
        System.out.println();


        Object myObject = new String("sai"); // get object from somewhere

//        if(myObject instanceof String str &&
//                str.startsWith("Hello") ) {
//
//            System.out.println(str + " starts with Hello");
//        }
        for (int i =5, x=5; i < x; i++) {

        }
    }
}