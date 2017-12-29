package ru.otus.hw6.emulator.command;

import ru.otus.hw6.emulator.exception.UnknownCommandTypeException;

public enum EmulatorCommand {
    PUT("PUT"),
    GET("GET"),
    BALANCE("BALANCE"),
    EXIT("EXIT");

    private String command;

    EmulatorCommand(String command) {
        this.command = command;
    }

    public static EmulatorCommand parseCommand(String command) throws Exception {
        switch (command.toUpperCase()) {
            case "PUT": {
                return PUT;
            }
            case "GET": {
                return GET;
            }
            case "BALANCE": {
                return BALANCE;
            }
            case "EXIT": {
                return EXIT;
            }
            default: {
                throw new UnknownCommandTypeException("unknown command: " + command);
            }
        }
    }
}
