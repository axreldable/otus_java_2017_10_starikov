package ru.otus.hw7.atm.command.pattern;

import ru.otus.hw7.atm.ATM;
import ru.otus.hw7.atm.cash.CashType;
import ru.otus.hw7.atm.request.Request;
import ru.otus.hw7.atm.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.otus.hw7.atm.response.ResponseFactory.*;
import static ru.otus.hw7.atm.response.ResponseFactory.okGetResponse;

public class GetCommand implements Command {
    private ATM atm;
    private boolean isEnoughCashType = false;

    public GetCommand(ATM atm) {
        this.atm = atm;
    }

    @Override
    public Response execute(Request request) {
        int cashAmount = request.getCashAmount();
        if (cashAmount <= 0) {
            return badGetPositiveResponse();
        }
        if (atm.getBalance() < cashAmount) {
            return badGetNotEnoughMoneyResponse(atm.getBalance());
        }
        if (cashAmount % CashType.getMinValue() != 0) {
            return badGetMultipleResponse();
        }
        Map<CashType, Integer> giveCash = getNeedCashMap(cashAmount);
        if (!isEnoughCashType) {
            return badGetNotEnoughCashTypeResponse(atm.getCashMap());
        }
        removeCash(giveCash);
        recalculateBalance();
        return okGetResponse(giveCash);
    }

    private void recalculateBalance() {
        int balance = 0;
        Map<CashType, Integer> cashMap = atm.getCashMap();
        balance += cashMap.keySet().stream().mapToInt(x -> x.getValue() * cashMap.get(x)).sum();
        atm.setBalance(balance);
    }

    private void removeCash(Map<CashType, Integer> giveCash) {
        giveCash.forEach((k, v) -> atm.getCashMap().merge(k, v, (oldValue, newValue) -> oldValue - newValue));
        removeZeroes();
    }

    private void removeZeroes() {
        atm.getCashMap().entrySet().removeIf(entry -> entry.getValue() == 0);
    }

    private Map<CashType,Integer> getNeedCashMap(int cashAmount) {
        int remainder = cashAmount;
        List<Integer> sortCashValues = CashType.getSortReverseCashValues();
        Map<CashType, Integer> result = new HashMap<>();
        for (Integer value : sortCashValues) {
            CashType cashType = CashType.parseCash(value);
            while (remainder >= value && result.get(cashType) != atm.getCashMap().get(cashType)) {
                if (isThereIsSuchType(result, cashType) &&
                        (!isThereIsCashType(cashType) || isCashTypeHasRunOut(result, cashType)))
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

    private boolean isThereIsSuchType(Map<CashType, Integer> map, CashType type) {
        return map.get(type) != null;
    }

    private boolean isThereIsCashType(CashType type) {
        return atm.getCashMap().get(type) != null;
    }

    private boolean isCashTypeHasRunOut(Map<CashType, Integer> map, CashType type) {
        return map.get(type) >= atm.getCashMap().get(type);
    }
}
