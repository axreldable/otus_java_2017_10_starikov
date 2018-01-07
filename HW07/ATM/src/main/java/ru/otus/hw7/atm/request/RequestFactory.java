package ru.otus.hw7.atm.request;

import ru.otus.hw7.atm.cash.CashType;

import java.util.HashMap;
import java.util.Map;

public class RequestFactory {
    public static Request createPutRequest(CashType type, int value) {
        return Request.builder()
                .cash(new HashMap<CashType, Integer>() {
                    {
                        put(type, value);
                    }
                })
                .build();
    }

    public static Request createGetRequest(int value) {
        return Request.builder()
                .cashAmount(value)
                .build();
    }

    public static Request createBalanceRequest() {
        return Request.builder().build();
    }

    public static Request createPutRequest(Map<CashType, Integer> cashMap) {
        return Request.builder()
                .cash(cashMap)
                .build();
    }
}
