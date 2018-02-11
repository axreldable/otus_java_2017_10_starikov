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

    public static int[] copyPart(int[] ar, int from, int to) {
        return Arrays.copyOfRange(ar, from, to);
    }

    public static int[] merge(int[] a, int[] b) {

        int[] answer = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length)
            answer[k++] = a[i] < b[j] ? a[i++] :  b[j++];

        while (i < a.length)
            answer[k++] = a[i++];


        while (j < b.length)
            answer[k++] = b[j++];

        return answer;
    }

}
