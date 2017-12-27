package ru.otus.hw6;

import ru.otus.hw6.atm.ATM;
import ru.otus.hw6.emulator.ATMEmulator;

public class Main {
    public static void main(String[] args) {
        new ATMEmulator(new ATM()).run();
    }
}
