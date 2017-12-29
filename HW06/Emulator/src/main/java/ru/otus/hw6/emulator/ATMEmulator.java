package ru.otus.hw6.emulator;

import ru.otus.hw6.atm.ATM;
import ru.otus.hw6.atm.request.Request;
import ru.otus.hw6.atm.response.Response;
import ru.otus.hw6.emulator.exception.ExitException;
import ru.otus.hw6.emulator.exception.UnknownCommandTypeException;
import ru.otus.hw6.emulator.exception.WrongArgumentsException;
import ru.otus.hw6.emulator.parser.CommandParser;
import ru.otus.hw6.emulator.printer.Printer;
import ru.otus.hw6.emulator.printer.ResponsePrinter;

import java.util.Scanner;

/**
 * class for process commands to and response from ATM
 */
public class ATMEmulator {
    private ATM atm;
    private Printer printer;

    public ATMEmulator(ATM atm) {
        this.atm = atm;
        printer = new Printer();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                printer.printGreeting();
                printer.printCursor();
                String nextCommand = scanner.nextLine();

                Request request = CommandParser.parse(nextCommand);
                Response response = atm.execute(request);
                ResponsePrinter.print(response);
            } catch (ExitException e) {
                System.out.println(e.getMessage());
                break;
            } catch (UnknownCommandTypeException | WrongArgumentsException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
