package ru.otus.hw4;

import ru.otus.hw4.leak.MemoryLeaker;
import ru.otus.hw4.logger.GcLogger;

public class Main {
    public static void main(String[] args) {
        System.out.println("!!!!!!");
        new GcLogger().start();
        new MemoryLeaker().leak();
    }
}
