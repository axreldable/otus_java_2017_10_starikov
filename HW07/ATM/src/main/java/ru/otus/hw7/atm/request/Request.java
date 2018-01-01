package ru.otus.hw7.atm.request;

import lombok.Builder;
import lombok.Getter;
import ru.otus.hw7.atm.cash.CashType;
import ru.otus.hw7.atm.command.CommandType;

import java.util.Map;

@Builder
@Getter
public class Request {
    private CommandType.Type command;
    private Map<CashType, Integer> cash;
    private int cashAmount;
}
