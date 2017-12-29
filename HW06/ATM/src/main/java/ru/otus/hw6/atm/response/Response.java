package ru.otus.hw6.atm.response;

import lombok.Builder;
import lombok.Getter;
import ru.otus.hw6.atm.cash.CashType;
import ru.otus.hw6.atm.response.type.Type;

import java.util.Map;

@Builder
@Getter
public class Response {
    private Type responseType;
    private String message;
    private Map<CashType, Integer> cash;
    private Integer balance;
}
