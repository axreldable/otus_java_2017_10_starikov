package ru.otus.hw6.emulator.printer;

public class Printer {
    public void printGreeting() {
        System.out.println("ATM commands:\n" +
                "- put cashType amount;\n" +
                "- get amount;\n" +
                "- balance;\n" +
                "- exit;");
    }

    public void printCursor() {
        System.out.print("> ");
    }


}
