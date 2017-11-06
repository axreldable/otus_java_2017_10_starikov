package ru.otus.hw4;

import ru.otus.hw4.leak.MemoryLeaker;

public class Main {
    public static void main(String[] args) {
        new MemoryLeaker().leak();
    }
}
