package ru.otus.hw05;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static ru.otus.hw05.constants.Constants.*;
import static ru.otus.hw05.test.printer.TestPrinter.printToConsole;

public class FailedTest {
    private String className = this.getClass().getName();

    @Test
    public void test() {
        printToConsole(className, FAIL_MESSAGE);
        assert (1 == 2);
    }

    @Before
    public void before1() {
        printToConsole(className, BEFORE_1);
    }

    @Before
    public void before2() {
        printToConsole(className, BEFORE_2);
    }

    @After
    public void after1() {
        printToConsole(className, AFTER_1);
    }

    @After
    public void after2() {
        printToConsole(className, AFTER_2);
    }

}