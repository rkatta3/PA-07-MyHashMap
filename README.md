# PA-07-MyHashMap

## Overview
This assignment implements a generic hash table (HashMap) using chaining for collision resolution. Understanding hash tables is a fundamental skill for computer scientists and commonly tested in software engineering interviews.

## Learning Objectives
- Practice implementing generic classes in Java
- Understand hash table implementation and collision resolution
- Use chaining with linked lists to handle collisions
- Master hash function concepts and applications

## Assignment Description
Implement a generic hash map class called `MyHashMap` with exactly **8 buckets**. The implementation uses an ArrayList of linked lists, where each bucket contains a custom linked list of key-value pairs.

## Implementation Requirements

### Data Structure
- **ArrayList of linked lists**: Each bucket is a linked list of `HashNode` objects
- **Fixed size**: 8 buckets (expect many collisions)
- **Chaining**: Collisions are handled by adding nodes to the linked list at that bucket
- **Custom linked list**: Must implement your own linked list (no Java LinkedList)

### HashNode Structure
Each node in the linked lists contains:
```java
private K key;
private V value;
private HashNode<K, V> next;
```

Note: HashNode.java is provided for local testing but will be supplied by the autograder. Do not submit it.

### Hash Function
Use this exact hash function (provided):
```java
private int hash(K key) {
   int hashCode = key.hashCode();
   int index = hashCode % numBuckets;  
   return Math.abs(index);
}
```

## Required Methods

### Core Operations
- `void clear()` - Removes all key-value pairs
- `boolean containsKey(K key)` - Returns true if key exists
- `boolean containsValue(V value)` - Returns true if value exists
- `V get(K key)` - Returns value associated with key (null if not found)
- `boolean isEmpty()` - Returns true if map is empty
- `Set<K> keySet()` - Returns set of all keys
- `V put(K key, V value)` - Inserts/updates key-value pair, returns previous value or null
- `V remove(K key)` - Removes key-value pair, returns removed value or null
- `int size()` - Returns total number of key-value pairs

### Special Method
- `void printTable()` - Prints hash table structure with collision counts

#### printTable() Format
```
Index 0: (0 conflicts), []
Index 1: (0 conflicts), []
Index 2: (0 conflicts), []
Index 3: (0 conflicts), []
Index 4: (0 conflicts), []
Index 5: (0 conflicts), [ExampleKeyX, ]
Index 6: (0 conflicts), [ExampleKeyY, ]
Index 7: (0 conflicts), []
Total # of conflicts: 0
```

## Important Implementation Details

### PUT Method Behavior
- Add new key-value pairs at the **FRONT** of the linked list in each bucket
- If the same key exists with a different value, **update the value in place** (do not add a new node)
- Return the previous value if updating, or null if inserting new

### Collision Handling
- The hash table uses **chaining** to handle collisions
- Multiple keys can hash to the same bucket
- Each bucket maintains a linked list of all colliding key-value pairs

## Recommended Development Approach

1. **Write unit tests first** using Java's HashMap class to verify expected behavior
2. **Start with non-generic version** using String keys and Integer values
   - Implement the hash function
   - Use simple data structures
   - Initially assume perfect hashing
   - Add chaining for collision handling
3. **Convert to generic implementation** with type parameters K and V

## Method Documentation
For complete method specifications, refer to the Java HashMap documentation:
https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html

Note: All methods except `printTable()` follow standard Java HashMap behavior.

## Grading
- Correctness (autograder test cases)
- Code clarity and style
- Proper use of generics
- Academic integrity

## Submission
Submit `MyHashMap.java` to Gradescope.

**Note**: No package declaration for this assignment.

## Academic Integrity
- Write your own code
- Do not share code with other students
- Do not copy from online solutions
- Acceptable: Looking up Java syntax (e.g., "how to split a string in Java")
- Not acceptable: Looking up complete solutions to this assignment
