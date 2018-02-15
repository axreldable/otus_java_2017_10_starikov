package multi.sort;

import util.ArrayUtils;
import util.ThreadUtils;

import java.util.HashMap;
import java.util.Map;

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
        Map<Thread, Runnable> sortersMap = createThreadsMap(ar);

        ThreadUtils.start(sortersMap.keySet());

        ThreadUtils.wait(sortersMap.keySet());

        int[] mergeSorters = mergeSorters(sortersMap.values().toArray());

        System.arraycopy(mergeSorters, 0, ar, 0, ar.length);
    }

    private Map<Thread, Runnable> createThreadsMap(int[] ar) {
        int elemInArray = ar.length / THREADS_AMOUNT;

        Map<Thread, Runnable> result = new HashMap<>();

        for (int i = 0; i < THREADS_AMOUNT; i++) {
            int start = i * elemInArray;
            int end = (i + 1) * elemInArray;
            if (isLastIter(i)) {
                end = ar.length;
            }
            RunnableSorter sorter = new RunnableSorter(copyPart(ar, start, end));
            result.put(new Thread(sorter), sorter);
        }

        return result;
    }

    private boolean isLastIter(int i) {
        return (i + 1) == THREADS_AMOUNT;
    }

    private int[] mergeSorters(Object[] sorters) {
        int[] mergeAr = ((RunnableSorter) sorters[0]).getAr();
        for (int i = 1; i < THREADS_AMOUNT; i++) {
            mergeAr = merge(mergeAr,((RunnableSorter) sorters[i]).getAr());
        }
        return mergeAr;
    }
}
