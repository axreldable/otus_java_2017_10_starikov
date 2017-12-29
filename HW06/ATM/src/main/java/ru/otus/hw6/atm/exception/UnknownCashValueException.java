package ru.otus.hw6.atm.exception;

public class UnknownCashValueException extends RuntimeException {
    public UnknownCashValueException(String message) {
        super(message);
    }
}
