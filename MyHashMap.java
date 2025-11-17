/*
 * Author: Rithvik Katta
 * Course: CSC 210
 * File: MyHashMap.java
 * Purpose: This program implements a generic hash map using chaining with linked lists.
 * It supports basic map operations like put, get, remove, keySet, and printTable.
 * To run this, use another file to create an instance of MyHashMap
 * and call its methods.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyHashMap<K, V> {
    private ArrayList<HashNode<K, V>> buckets;  // ArrayList to hold the buckets
    private int numBuckets;                     // Number of total buckets
    private int size;                           // Number of key-value pairs stored

    /*
     * MyHashMap() -- Constructor initializes the hash table with 8 buckets.
     */
    public MyHashMap() {
        numBuckets = 8;
        size = 0;
        buckets = new ArrayList<>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(null); // Initialize each bucket to null
        }
    }

    /*
     * hash(key) -- Computes the hash index for the given key.
     */
    private int hash(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return Math.abs(index); // Ensure non-negative index
    }

    /*
     * put(key, value) -- Inserts the key-value pair into the map.
     * If the key already exists, updates the value and returns the old value.
     * If the key is new, adds it and returns null.
     */
    public V put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> head = buckets.get(index);

        // Search for the key in the chain
        while (head != null) {
            if (head.getKey().equals(key)) {
                V oldValue = head.getValue();
                head.setValue(value); // Update existing key
                return oldValue;
            }
            head = head.getNext();
        }

        // Add new node at the front of the chain
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.setNext(buckets.get(index));
        buckets.set(index, newNode);
        size++;
        return null;
    }

    /*
     * get(key) -- Returns the value associated with the key, or null if not found.
     */
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = buckets.get(index);

        // Traverse the chain at this bucket
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }

        return null;
    }

    /*
     * containsKey(key) -- Returns true if the key exists in the map.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /*
     * containsValue(value) -- Returns true if the value exists in the map.
     */
    public boolean containsValue(V value) {
        // Check all buckets for the value
        for (int i = 0; i < numBuckets; i++) {
            HashNode<K, V> current = buckets.get(i);
            while (current != null) {
                if (current.getValue().equals(value)) {
                    return true;
                }
                current = current.getNext();
            }
        }
        return false;
    }

    /*
     * remove(key) -- Removes the key and returns its value, or null if not found.
     */
    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> current = buckets.get(index);
        HashNode<K, V> prev = null;

        // Search for the key in the chain
        while (current != null) {
            if (current.getKey().equals(key)) {
                // Remove the node
                if (prev == null) {
                    buckets.set(index, current.getNext());
                } else {
                    prev.setNext(current.getNext());
                }
                size--;
                return current.getValue();
            }
            prev = current;
            current = current.getNext();
        }

        return null;
    }

    /*
     * clear() -- Empties the map by resetting all buckets and size.
     */
    public void clear() {
        for (int i = 0; i < numBuckets; i++) {
            buckets.set(i, null); // Remove all nodes
        }
        size = 0;
    }

    /*
     * isEmpty() -- Returns true if the map has no key-value pairs.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * size() -- Returns the number of key-value pairs in the map.
     */
    public int size() {
        return size;
    }

    /*
     * keySet() -- Returns a Set containing all keys in the map.
     */
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < numBuckets; i++) {
            HashNode<K, V> current = buckets.get(i);
            while (current != null) {
                keys.add(current.getKey());
                current = current.getNext();
            }
        }
        return keys;
    }

    /*
     * printTable() -- Prints the number of conflicts per bucket and the keys stored.
     */
    public void printTable() {
        int totalConflicts = 0;

        for (int i = 0; i < numBuckets; i++) {
            HashNode<K, V> current = buckets.get(i);
            int conflictCount = 0;
            StringBuilder keys = new StringBuilder("[");

            // Gather all keys in this bucket
            while (current != null) {
                keys.append(current.getKey()).append(", ");
                conflictCount++;
                current = current.getNext();
            }

            keys.append("]");

            int displayConflicts;
            if (conflictCount > 0) {
                displayConflicts = conflictCount - 1;
            } else {
                displayConflicts = 0;
            }

            System.out.println("Index " + i + ": (" + displayConflicts + " conflicts), " + keys);

            if (conflictCount > 1) {
                totalConflicts += (conflictCount - 1);
            }
        }

        System.out.println("Total # of conflicts: " + totalConflicts);
    }
}

