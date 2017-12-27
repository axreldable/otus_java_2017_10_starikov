package ru.otus.hw6.atm;

import ru.otus.hw6.atm.cash.CashType;
import ru.otus.hw6.atm.request.Request;
import ru.otus.hw6.atm.response.Response;
import ru.otus.hw6.atm.response.ResponseFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static ru.otus.hw6.atm.cash.CashType.FIVE_HUNDRED;
import static ru.otus.hw6.atm.cash.CashType.ONE_HUNDRED;
import static ru.otus.hw6.atm.cash.CashType.THOUSAND;
import static ru.otus.hw6.atm.response.ResponseFactory.*;


public class ATM {
    private Map<CashType, Integer> cashMap = new HashMap<CashType, Integer>();
    private int balance = 0;

    public Response execute(Request request) {
        switch (request.getCommand()) {
            case PUT:
                return put(request.getCash());
            case GET:
                return get(request.getCashAmount());
            case BALANCE:
                return balance();
        }
        return badCommandResponse();
    }

    private Response balance() {
        return okBalanceResponse(cashMap, balance);
    }

    private Response get(int cashAmount) {
        if (cashAmount <= 0) {
            return badGetPositiveResponse();
        }
        if (balance < cashAmount) {
            return badGetNotEnoughMoneyResponse(balance);
        }
        if (cashAmount % CashType.getMinValue() != 0) {
            return ResponseFactory.badGetMultipleResponse();
        }
        Map<CashType, Integer> giveCash = getNeedCashMap(cashAmount, cashMap);

        return null;
    }

    public static Map<CashType,Integer> getNeedCashMap(int cashAmount, Map<CashType, Integer> cashMap) {
        // TODO: iterate through enum!
        int remainder = cashAmount;
        Map<CashType, Integer> result = new HashMap<>();
        while (remainder >= THOUSAND.getValue()) {
            if (result.get(THOUSAND) != null && (cashMap.get(THOUSAND) == null || result.get(THOUSAND) >= cashMap.get(THOUSAND))) {
                break;
            }
            result.merge(THOUSAND, 1, (oldValue, newValue) -> oldValue + newValue);
            remainder -= THOUSAND.getValue();
        }
        while (remainder >= FIVE_HUNDRED.getValue()) {
            if (result.get(FIVE_HUNDRED) != null && (cashMap.get(FIVE_HUNDRED) == null || result.get(FIVE_HUNDRED) >= cashMap.get(FIVE_HUNDRED))) {
                break;
            }
            result.merge(FIVE_HUNDRED, 1, (oldValue, newValue) -> oldValue + newValue);
            remainder -= FIVE_HUNDRED.getValue();
        }
        while (remainder >= ONE_HUNDRED.getValue()) {
            if (result.get(ONE_HUNDRED) != null && (cashMap.get(ONE_HUNDRED) == null || result.get(ONE_HUNDRED) >= cashMap.get(ONE_HUNDRED))) {
                break;
            }
            result.merge(ONE_HUNDRED, 1, (oldValue, newValue) -> oldValue + newValue);
            remainder -= ONE_HUNDRED.getValue();
        }
        if (remainder > 0) {
            throw new RuntimeException("ostatok: " + remainder);
        }
        return result;
    }

    private Response put(Map<CashType, Integer> cash) {
        if (!allValuesPositive(cash.values())) {
            return badPutResponse(cash);
        }
        addToCash(cash);
        addToBalance(cash);
        return okPutResponse(cash);
    }

    private boolean allValuesPositive(Collection<Integer> entries) {
        return entries.stream().anyMatch(x -> x <= 0);
    }

    private void addToCash(Map<CashType, Integer> cash) {
        cash.forEach((cashType, amount) -> cashMap.merge(cashType, amount, (oldValue, newValue) -> oldValue + newValue));
    }

    private void addToBalance(Map<CashType, Integer> cash) {
        cash.forEach((cashType, amount) -> balance += cashType.getValue() * amount);
    }
}
