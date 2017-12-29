package ru.otus.hw6.emulator.exception;

public class UnknownCommandTypeException extends Exception {
    public UnknownCommandTypeException(String message) {
        super(message);
    }
}
