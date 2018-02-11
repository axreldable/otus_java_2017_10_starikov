package multi.sort;

import util.ArrayUtils;

import static util.ArrayUtils.copyPart;
import static util.ArrayUtils.merge;

public class MultiThreadSorter {
    private static final int THREADS_AMOUNT = 4;
    public void sort(int[] ar) throws InterruptedException {
        if (ar.length < THREADS_AMOUNT) {
            ArrayUtils.sort(ar);
        } else {
            multiSort(ar);
        }
    }

    private void multiSort(int[] ar) throws InterruptedException {
        ThreadSorter[] sorters = createAndStart(ar);

        waitSorters(sorters);

        int[] mergeSorters = mergeSorters(sorters);

        System.arraycopy(mergeSorters, 0, ar, 0, ar.length);
    }

    private ThreadSorter[] createAndStart(int[] ar) {
        int elemInArray = ar.length / THREADS_AMOUNT;

        ThreadSorter[] result = new ThreadSorter[THREADS_AMOUNT];

        for (int i = 0; i < THREADS_AMOUNT; i++) {
            int start = i * elemInArray;
            int end = (i + 1) * elemInArray;
            if (isLastIter(i)) {
                end = ar.length;
            }
            ThreadSorter sorter = new ThreadSorter(copyPart(ar, start, end));
            sorter.start();
            result[i] = sorter;
        }

        return result;
    }

    private boolean isLastIter(int i) {
        return (i + 1) == THREADS_AMOUNT;
    }

    private void waitSorters(ThreadSorter[] sorters) throws InterruptedException {
        for (ThreadSorter sorter : sorters) {
            sorter.join();
        }
    }

    private int[] mergeSorters(ThreadSorter[] sorters) {
        int[] mergeAr = sorters[0].getAr();
        for (int i = 1; i < THREADS_AMOUNT; i++) {
            mergeAr = merge(mergeAr, sorters[i].getAr());
        }
        return mergeAr;
    }
}
