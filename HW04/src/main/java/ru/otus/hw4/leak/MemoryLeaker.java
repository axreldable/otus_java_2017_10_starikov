package ru.otus.hw4.leak;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeaker implements Leaker {
    @Override
    public void leak() {
        List<String> stringList = new ArrayList<>();

        while (true) {
            stringList.add(new String(new char[0]));
            stringList.add(new String(new char[0]));

            stringList.remove(0);
        }

    }
}
