package ru.otus.hw03;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.otus.hw03.collection.MyArrayList;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MyArrayListTest {
    List<Integer> myArrayList;

    @Before
    public void beforeTest() {
        myArrayList = new MyArrayList<>();
    }

    @After
    public void clearList() {
        myArrayList.clear();
    }

    @Test
    public void testAddAll() {
        Collections.addAll(myArrayList, 1, 2, 3);

        checkList(myArrayList);
    }

    @Test
    public void testCopyTo() {
        List<Integer> src = new MyArrayList<>();
        Collections.addAll(src, 1, 2, 3);
        Collections.addAll(myArrayList, 10, 11, 12);
        Collections.copy(myArrayList, src);

        checkList(myArrayList);
    }

    @Test
    public void testCopyFrom() {
        Collections.addAll(myArrayList, 1, 2, 3);
        List<Integer> dest = new MyArrayList<>();
        Collections.addAll(dest, 10, 11, 12);
        Collections.copy(dest, myArrayList);

        checkList(dest);
    }

    private void checkList(List<Integer> list) {
        assertEquals(3, list.size());
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
        assertTrue(!list.contains(10));
        assertTrue(!list.contains(11));
        assertTrue(!list.contains(12));
    }

    @Test
    public void testCopyMoreThenThreshold() {
        List<Integer> src = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            src.add(i);
        }
        for (int i = 10; i > 0; i--) {
            myArrayList.add(i);
        }
        Collections.copy(myArrayList, src);

        checkLongList(myArrayList);
    }

    private void checkLongList(List<Integer> list) {
        assertEquals(10, list.size());
        for (int i = 0; i < 10; i++) {
            assertEquals(i, (int) list.get(i));
        }
    }

    @Test
    public void testSort() {
        Collections.addAll(myArrayList, 3, 2, 1, 25, 44, 9);
        Collections.sort(myArrayList);

        checkSortList(myArrayList);
    }

    private void checkSortList(List<Integer> list) {
        assertEquals(6, list.size());
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
        assertTrue(list.contains(9));
        assertTrue(list.contains(25));
        assertTrue(list.contains(44));
        assertTrue(!list.contains(105));
        assertArrayEquals(new Integer[] {1, 2, 3, 9, 25, 44}, list.toArray());
    }

}