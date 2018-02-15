package util;

import java.util.Set;

public class ThreadUtils {
    public static void start(Set<Thread> threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public static void wait(Set<Thread> threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
