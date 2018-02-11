package util;

import java.util.Arrays;
import java.util.Random;

public class ArrayUtils {
    public static int[] getRandomArray(int length) {
        int[] ar = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            ar[i] = random.nextInt(100);
        }
        return ar;
    }

    public static void print(int[] ar) {
        System.out.println(Arrays.toString(ar));
    }

    public static void sort(int[] ar) {
        Arrays.sort(ar);
    }

    public static int[] copy(int[] ar) {
        return Arrays.copyOf(ar, ar.length);
    }

}
