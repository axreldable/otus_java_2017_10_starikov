package ru.otus.hw7.atm.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ru.otus.hw7.atm.cash.CashType;
import ru.otus.hw7.atm.response.type.Type;

import java.util.Map;

@Builder
@Getter
@ToString
public class Response {
    private Type responseType;
    private String message;
    private Map<CashType, Integer> cash;
    private Integer balance;
}
