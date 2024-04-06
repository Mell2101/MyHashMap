import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;


import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test
    void put() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("apple", 10);
        map.put("Car", 9);
        map.put("Cat", 8);
        map.put("Glass", 7);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        assertEquals(map.size(), arrayList.size());//MyHashMap.size == ArrayList.size

    }

    @Test
    void get() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("apple", 10);
        map.put("Car", 9);
        map.put("Cat", 8);
        map.put("Glass", 7);

        map.get("Car");

        assertEquals(map.get("Car"),9);// values of car == 9
    }

    @Test
    void clear() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("apple", 10);
        map.put("Car", 9);
        map.put("Cat", 8);
        map.put("Glass", 7);

        map.clear();

        assertTrue(map.isEmpty());// return true if MyHashMap is empty

    }

    @Test
    void keySet() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Car", 15);
        map.put("Cat", 8);
        map.put("apple", 10);
        map.put("Glass", 7);


        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Car");
        arrayList.add("Cat");
        arrayList.add("apple");
        arrayList.add("Glass");

        // list of all keys stored in the MyHashMap == ArrayList
        assertArrayEquals(map.keySet().toArray(),arrayList.toArray());
    }

    @Test
    void values() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Car", 15);
        map.put("Cat", 8);
        map.put("apple", 10);
        map.put("Glass", 7);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(15);
        arrayList.add(8);
        arrayList.add(10);
        arrayList.add(7);

        //list of all values stored in the MyHashMap == ArrayList
        assertArrayEquals(map.values().toArray(),arrayList.toArray());

    }

    @Test
    void remove() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Car", 15);
        map.put("Cat", 8);
        map.put("apple", 10);
        map.put("Glass", 7);

        //MyHashMap has size equals 4
        map.remove("Car");
        //MyHashMap has size equals 3

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(8);
        arrayList.add(10);
        arrayList.add(7);

        //MyHashMap == ArrayList
        assertArrayEquals(map.values().toArray(),arrayList.toArray());
    }

    @Test
    void size() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Car", 15);
        map.put("Cat", 8);
        map.put("apple", 10);
        map.put("Glass", 7);

        assertEquals(map.size(), 4);
    }

    @Test
    void testToString() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Car", 15);
        map.put("Cat", 8);
        map.put("apple", 10);
        map.put("Glass", 7);

        assertEquals(map.toString(),"{Car=15, Cat=8, apple=10, Glass=7}");

    }
}