package ru.otus.hw05.framework.run;

import ru.otus.hw05.framework.reflection.TestInstanceGetter;
import ru.otus.hw05.framework.reflection.TestInstanceRunner;

public class TestsRunner {
    public static void run (String name) {
        TestInstanceRunner testInstanceRunner = new TestInstanceRunner();

        new TestInstanceGetter(name).getTests().stream()
                .forEach(testInstanceRunner::run);
    }
}
