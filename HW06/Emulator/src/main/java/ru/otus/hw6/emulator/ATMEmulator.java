package ru.otus.hw6.emulator;

import ru.otus.hw6.atm.ATM;
import ru.otus.hw6.emulator.printer.GreetingPrinter;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class for process commands to ATM and process response from ATM
 */
public class ATMEmulator {
    private ATM atm;
    private GreetingPrinter printer;

    public ATMEmulator(ATM atm) {
        this.atm = atm;
        printer = new GreetingPrinter();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Logger logger = Logger.getLogger(ATMEmulator.class.getName());

        while (true) {
            try {
                printer.print();
                printer.printCursor();
                String nextCommand = scanner.nextLine();

                System.out.println(nextCommand);
            } catch (RuntimeException e) {
                logger.log(Level.INFO, e.getMessage());
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
    }
}
