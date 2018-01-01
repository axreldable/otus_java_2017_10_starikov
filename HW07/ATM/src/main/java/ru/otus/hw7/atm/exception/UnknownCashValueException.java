package ru.otus.hw7.atm.exception;

public class UnknownCashValueException extends RuntimeException {
    public UnknownCashValueException(String message) {
        super(message);
    }
}
