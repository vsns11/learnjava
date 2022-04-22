package ca.siva.ds.misc;

import java.util.LinkedList;

// Leetcode: 705
// Time Complexity: O(N/k), space: O(K+M)   here M defines the number of unique values
// that are inserted into all the buckets, K  => total size of all buckets

/**
 * N => Total number of values
 * K => Number of Buckets
 *  At most N/K would be the max size of each bucket, therefore O(N/K)
 *
 */

class MyHashSet {

    public Bucket[] bucketList;
    public static int TOTAL_NUM_OF_BUCKETS = 769;

    public MyHashSet() {
        bucketList = new Bucket[MyHashSet.TOTAL_NUM_OF_BUCKETS];
        for (int i = 0; i < TOTAL_NUM_OF_BUCKETS; i++) {
            bucketList[i] = new Bucket();
        }
    }

    public int genHash(int key) {
        return key % TOTAL_NUM_OF_BUCKETS;
    }

    public void add(int key) {
        int hashVal = genHash(key);
        bucketList[hashVal].add(key);
    }

    public void remove(int key) {
        int hashVal = genHash(key);
        bucketList[hashVal].delete(key);
    }

    public boolean contains(int key) {
        int hashVal = genHash(key);
        return bucketList[hashVal].contains(key);
    }

    class Bucket {
        private LinkedList<Integer> container;

        public Bucket() {
            container = new LinkedList<Integer>();
        }

        public void add(int key) {
            int idx = container.indexOf(key);
            if (idx == -1) container.addFirst(key);

        }

        public void delete(int key) {
            int idx = container.indexOf(key);
            if (idx != -1) this.container.remove(idx);

        }

        public boolean contains(int key) {
            int idx = container.indexOf(key);
            return idx != -1;
        }

    }
}


/**
 * 3 approaches to avoid collision in hashset
 *  >> separate chaining > provide separate buckets for each generated hash value(same or different).
 *  >> open address allocation > allocate the free address when there's a fewer collisions.
 *  >> 2 pick hashing > pick the hash with lowest collisions.
 *
 *
 */

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */