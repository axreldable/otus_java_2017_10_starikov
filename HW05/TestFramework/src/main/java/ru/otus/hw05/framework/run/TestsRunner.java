package ru.otus.hw05.framework.run;

import ru.otus.hw05.framework.model.TestInstance;
import ru.otus.hw05.framework.reflection.TestInstanceGetter;
import ru.otus.hw05.framework.reflection.TestInstanceRunner;

import java.util.Set;

public class TestsRunner {
    public static void run (String name) {
        Set<TestInstance> tests = new TestInstanceGetter(name).getTests();

        TestInstanceRunner testInstanceRunner = new TestInstanceRunner();
        for (TestInstance test : tests) {
            testInstanceRunner.run(test);
        }
    }
}
