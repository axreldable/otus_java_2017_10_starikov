import org.junit.Test;
import multi.sort.MultiThreadSorter;

import static org.junit.Assert.*;
import static util.ArrayUtils.*;

public class SortTest {
    @Test
    public void test() throws InterruptedException {
        int[] array1 = getRandomArray(37);
        int[] array2 = copy(array1);

        System.out.println("Not sorted array:");
        print(array1);

        sort(array1);
        System.out.println("Sorted array:");
        print(array1);

        new MultiThreadSorter().sort(array2);
        System.out.println("Multi Sorted array:");
        print(array2);

        assertArrayEquals(array1, array2);
    }

}
