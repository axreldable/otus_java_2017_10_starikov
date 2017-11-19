package ru.otus.hw05.test.analog.junit;

import ru.otus.hw05.framework.annotation.After;
import ru.otus.hw05.framework.annotation.Before;
import ru.otus.hw05.framework.annotation.Test;

import static ru.otus.hw05.constants.Constants.*;
import static ru.otus.hw05.test.printer.TestPrinter.printToConsole;

public class SuccessTest {
    private String className = this.getClass().getName();

    @Test
    public void test() {
        printToConsole(className, TEST + SEPARATOR + SUCCESS);
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
