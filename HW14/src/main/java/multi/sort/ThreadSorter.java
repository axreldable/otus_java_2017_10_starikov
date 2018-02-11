package multi.sort;

import lombok.Getter;

import static util.ArrayUtils.sort;

public class ThreadSorter extends Thread {
    @Getter private int[] ar;

    public ThreadSorter(int[] ar) {
        this.ar = ar;
    }

    @Override
    public void run() {
        sort(ar);
    }
}
