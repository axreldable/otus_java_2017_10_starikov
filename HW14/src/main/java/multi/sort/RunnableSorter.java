package multi.sort;

import lombok.Getter;

import static util.ArrayUtils.sort;

public class RunnableSorter implements Runnable {
    @Getter private int[] ar;

    public RunnableSorter(int[] ar) {
        this.ar = ar;
    }

    @Override
    public void run() {
        sort(ar);
    }
}
