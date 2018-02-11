package util;

import org.junit.Test;

import static org.junit.Assert.*;
import static util.ArrayUtils.merge;

public class ArrayUtilsTest {
    @Test
    public void mergeTest() {
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, merge(new int[] {2, 4, 5}, new int[] {1, 3}));
    }
}