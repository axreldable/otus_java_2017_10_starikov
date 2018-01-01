package ru.otus.hw7.atm.command;

public abstract class CommandType {
    public enum Type {
        PUT, GET, BALANCE
    }

    private Type type;

    public CommandType(Type type) {
        this.type = type;
    }
}
