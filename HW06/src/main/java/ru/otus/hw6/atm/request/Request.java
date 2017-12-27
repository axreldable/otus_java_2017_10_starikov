package ru.otus.hw6.atm.request;

import lombok.Builder;
import lombok.Getter;
import ru.otus.hw6.atm.cash.CashType;
import ru.otus.hw6.atm.command.CommandType;

import java.util.Map;

@Builder
@Getter
public class Request {
    private CommandType command;
    private Map<CashType, Integer> cash;
    private int cashAmount;
}
