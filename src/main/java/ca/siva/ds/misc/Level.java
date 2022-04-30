package ca.siva.ds.misc;

import java.util.EnumMap;
import java.util.EnumSet;

public enum Level {
    HIGH {
        @Override
        public String asLowerCase() {
            return HIGH.toString().toLowerCase();
        }
    },
    MEDIUM {
        @Override
        public String asLowerCase() {
            return MEDIUM.toString().toLowerCase();
        }
    },
    LOW {
        @Override
        public String asLowerCase() {
            return LOW.toString().toLowerCase();
        }
    };

    public static void testStatic() {

    }

    public abstract String asLowerCase();
}

class Test {
    public static void main(String[] args) {
        EnumSet<Level> enumSet = EnumSet.of(Level.HIGH, Level.MEDIUM);

        EnumMap<Level, String> enumMap = new EnumMap<Level, String>(Level.class);
        enumMap.put(Level.HIGH, "High level");
        enumMap.put(Level.MEDIUM, "Medium level");
        enumMap.put(Level.LOW, "Low level");

        String levelValue = enumMap.get(Level.HIGH);
    }
}