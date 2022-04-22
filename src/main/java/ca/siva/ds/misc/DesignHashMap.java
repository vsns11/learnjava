package ca.siva.ds.misc;

import java.util.LinkedList;
import java.util.List;

class MyHashMap {
    private static int size=2069;

    private Bucket[] buckets;

    public MyHashMap() {
        buckets = new Bucket[MyHashMap.size];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket();
        }
    }

    public int getHash(int key) {
        return key % size;
    }

    public void put(int key, int value) {
        int hash = getHash(key);
        buckets[hash].update(key, value);
    }

    public int get(int key) {
        int hash = getHash(key);
        return buckets[hash].get(key);
    }

    public void remove(int key) {
        int hash = getHash(key);
        buckets[hash].remove(key);
    }

    class Pair<U, V> {
        U key;
        V value;

        public Pair(U key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    class Bucket {
        List<Pair<Integer, Integer>>records;

        public Bucket() {
            records = new LinkedList<>();
        }

        public void update(int key, int value) {
            boolean found = false;
            for (Pair<Integer, Integer> item: records) {
                if (item.key == key) {
                    found = true;
                    item.value = value;
                }
            }
            if (!found) records.add(new Pair<>(key, value));
        }

        public void remove(int key) {
            Pair<Integer, Integer> foundRecord = null;
            for (Pair<Integer, Integer> item: records) {
                if (item.key == key) {
                    foundRecord = item;
                }
            }
            records.remove(foundRecord);
        }

        public int get(int key) {
            for (Pair<Integer, Integer> item: records) {
                if (item.key == key) {
                    return item.value;
                }
            }

            return -1;
        }

    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */