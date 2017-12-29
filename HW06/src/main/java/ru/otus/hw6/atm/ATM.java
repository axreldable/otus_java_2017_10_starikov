package ru.otus.hw6.atm;

import ru.otus.hw6.atm.cash.CashType;
import ru.otus.hw6.atm.request.Request;
import ru.otus.hw6.atm.response.Response;
import ru.otus.hw6.atm.response.ResponseFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.otus.hw6.atm.cash.CashType.*;
import static ru.otus.hw6.atm.response.ResponseFactory.*;


public class ATM {
    private Map<CashType, Integer> cashMap = new HashMap<>();
    private int balance = 0;
    private boolean isEnoughCashType = false;

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
        Map<CashType, Integer> giveCash = getNeedCashMap(cashAmount);
        if (!isEnoughCashType) {
            return badGetNotEnoughCashTypeResponse(cashMap);
        }
        removeCash(giveCash);
        recalculateBalance();
        return okGetResponse(giveCash);
    }

    private void recalculateBalance() {
        balance = 0;
        cashMap.forEach((k, v) -> balance += k.getValue() * v);
    }

    private void removeCash(Map<CashType, Integer> giveCash) {
        giveCash.forEach((k, v) -> cashMap.merge(k, v, (oldValue, newValue) -> oldValue - newValue));
        removeZeroes();
    }

    private void removeZeroes() {
        cashMap.entrySet().removeIf(entry -> entry.getValue() == 0);
    }

    private Map<CashType,Integer> getNeedCashMap(int cashAmount) {
        int remainder = cashAmount;
        List<Integer> sortCashValues = getSortReverseCashValues();
        Map<CashType, Integer> result = new HashMap<>();
        for (Integer value : sortCashValues) {
            CashType cashType = parseCash(value);
            while (remainder >= value && result.get(cashType) != cashMap.get(cashType)) {
                if (!isThereIsNoSuchType(result, cashType) &&
                        (isThereIsNoCashType(cashType) || isCashTypeHasRunOut(result, cashType)))
                {
                    continue;
                }
                result.merge(cashType, 1, (oldValue, newValue) -> oldValue + newValue);
                remainder -= value;
            }
        }
        if (remainder > 0) {
            isEnoughCashType = false;
            return null;
        }
        isEnoughCashType = true;
        return result;
    }

    private boolean isThereIsNoSuchType(Map<CashType, Integer> map, CashType type) {
        return map.get(type) == null;
    }

    private boolean isThereIsNoCashType(CashType type) {
        return cashMap.get(type) == null;
    }

    private boolean isCashTypeHasRunOut(Map<CashType, Integer> map, CashType type) {
        return map.get(type) >= cashMap.get(type);
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
        return entries.stream().allMatch(x -> x > 0);
    }

    private void addToCash(Map<CashType, Integer> cash) {
        cash.forEach((cashType, amount) -> cashMap.merge(cashType, amount, (oldValue, newValue) -> oldValue + newValue));
    }

    private void addToBalance(Map<CashType, Integer> cash) {
        cash.forEach((cashType, amount) -> balance += cashType.getValue() * amount);
    }
}
