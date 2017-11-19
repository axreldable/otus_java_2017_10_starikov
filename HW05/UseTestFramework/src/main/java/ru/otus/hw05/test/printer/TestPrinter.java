package ru.otus.hw05.test.printer;

public class TestPrinter {
    public static void printToConsole(String fullClassName, String methodName) {
        System.out.println(getShortName(fullClassName) + " - " + methodName);
    }

    private static String getShortName(String fullClassName) {
        return fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
    }
}
