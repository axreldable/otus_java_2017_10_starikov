package ru.otus.hw05;

import ru.otus.hw05.framework.run.TestsRunner;
import ru.otus.hw05.test.analog.junit.SuccessTest;

public class Main {
    public static void main(String[] args) {
        runTestClass();
        System.out.println("------------------------------");
        runAllTests();
    }

    private static void runTestClass() {
        TestsRunner.run(SuccessTest.class.getCanonicalName());
    }

    private static void runAllTests() {
        TestsRunner.run("ru.otus.hw05.test.analog.junit");
    }
}
