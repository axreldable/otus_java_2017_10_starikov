package ru.otus.hw05;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static ru.otus.hw05.constants.Constants.*;
import static ru.otus.hw05.test.printer.TestPrinter.printToConsole;

public class FailedBeforeTest {
    private String className = this.getClass().getName();

    @Test
    public void test() {
        printToConsole(className, TEST);
    }


    @Before
    public void before1() throws Exception {
        printToConsole(className, BEFORE_1 + SEPARATOR + THROW_EXCEPTION);
        throw new Exception(EXCEPTION_MESSAGE);
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
