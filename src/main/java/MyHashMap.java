import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple HashMap Implementation
 * Type parameters:
 * @param <K> - the type of keys maintained by this map
 * @param <V> - the type of mapped values
 */
public class MyHashMap<K,V> {
    //Fields
    //=======================================================
    /**
     * An array of buckets for keep by key-value
     */
    private LinkedList<Entry<K, V>>[] buckets;

    /**
     * The count buckets in Hash Table
     */
    private int capacity;

    /**
     * Default value for MyHashMap initialization
     */
    private int DEFAULT_CAPACITY = 16;

    /**
     * The count elements in Hash Table
     */
    private int size;
    //=======================================================

    //Constructors
    //=======================================================

    /**
     * Default constructor.
     * Constructs an empty HashMap with the default initial capacity (16).
     */
    public MyHashMap(){
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
        this.buckets = new LinkedList[capacity];
        // Инициализация каждого бакета пустым связным списком
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /**
     * Constructs an empty HashMap with the specified initial capacity.
     * @param capacity - specified initial capacity
     */
    public MyHashMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.buckets = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /**
     * Constructor of copy.
     * @param other - other MyHashMap
     */
    public MyHashMap(MyHashMap<K, V> other) {
        this.capacity = other.capacity;
        this.size = other.size;
        this.buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
            for (Entry<K, V> entry : other.buckets[i]) {
                buckets[i].add(new Entry<>(entry.getKey(), entry.getValue()));
            }
        }
    }
    //=======================================================

    //Methods
    //=======================================================

    /**
     * Add a new element in MyHashMap
     * @param key - the type of keys maintained by this map
     * @param value - the type of mapped values
     */
    public void put(K key, V value) {

        int hash = getHash(key);

        LinkedList<Entry<K, V>> bucket = buckets[hash % capacity];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {

                entry.setValue(value);
                return;
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;
    }

    /**
     * Return the value by key
     * @param key - the type of keys maintained by this map
     * @return value by key
     */
    public V get(K key) {
        int hash = getHash(key);

        LinkedList<Entry<K, V>> bucket = buckets[hash % capacity];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Remove all entries from the MyHashMap.
     */
    public void clear(){
        for (int i = 0; i < capacity; i++) {
            buckets[i].clear();
        }
        size = 0;
    }

    /**
     * Returns a list of all keys stored in the MyHashMap.
     * @return A list of all keys stored in the MyHashMap.
     */
    public List<K> keySet() {
        List<K> keys = new ArrayList<>();
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    /**
     * Returns a list of all values stored in the MyHashMap.
     * @return A list of all values stored in the MyHashMap.
     */
    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                values.add(entry.getValue());
            }
        }
        return values;
    }

    /**
     * Checks MyHashMap for emptiness
     * @return True if myHashMap is empty, else false
     */
    public boolean isEmpty(){
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return current size MyHashMap
     * @return Current size MyHashMap
     */
    public int size(){
        return size;
    }

    /**
     * removes the entry with the specified key from the HashMap.
     * @param key - specified key from the HashMap
     */
    public void remove(K key) {
        int hash = getHash(key);
        LinkedList<Entry<K, V>> bucket = buckets[hash % capacity];
        Iterator<Entry<K, V>> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (entry.getKey().equals(key)) {
                iterator.remove();
                size--;
                return;
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2); // Remove trailing comma and space
        }
        sb.append("}");
        return sb.toString();
    }

    private int getHash(K key) {
        return key == null ? 0 : key.hashCode();
    }
    public Iterator<Entry<K, V>> iterator() {
        return new HashMapIterator();
    }
    //=======================================================

    //Insert Class
    //=======================================================

    /**
     * Inner class representing a key-value pair
     * @param <K> - the type of keys maintained by this map
     * @param <V> - the type of mapped values
     */
    private static class Entry<K, V> {
        private K key;
        private V value;

        /**
         * Constructor to create an instance of an entry with a given key and value.
         * @param key - the type of keys maintained by this map.
         * @param value - the type of mapped values.
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Return current key
         * @return current key
         */
        public K getKey() {
            return key;
        }

        /**
         * Return current value
         * @return current value
         */
        public V getValue() {
            return value;
        }

        /**
         * changes the current value to a new.
         * @param value - the new value to be added.
         */
        public void setValue(V value) {
            this.value = value;
        }

    }
    /**
     * Inner class iterator implements interface Iterator.
     */

    private class HashMapIterator implements Iterator<Entry<K, V>> {
        private int bucketIndex = 0;

        private Iterator<Entry<K, V>> bucketIterator;

        public HashMapIterator() {
            if (size > 0) {
                bucketIterator = buckets[bucketIndex].iterator();
            }
        }

        @Override
        public boolean hasNext() {
            if (bucketIterator == null) {
                return false;
            }
            if (bucketIterator.hasNext()) {
                return true;
            }

            while (++bucketIndex < capacity) {
                if (!buckets[bucketIndex].isEmpty()) {
                    bucketIterator = buckets[bucketIndex].iterator();
                    return bucketIterator.hasNext();
                }
            }
            return false;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                return null;
            }
            return bucketIterator.next();
        }
    }
    //=======================================================

}
