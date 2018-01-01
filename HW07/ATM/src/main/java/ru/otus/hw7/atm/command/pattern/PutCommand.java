package ru.otus.hw7.atm.command.pattern;

import ru.otus.hw7.atm.ATM;
import ru.otus.hw7.atm.cash.CashType;
import ru.otus.hw7.atm.request.Request;
import ru.otus.hw7.atm.response.Response;

import java.util.Collection;
import java.util.Map;

import static ru.otus.hw7.atm.response.ResponseFactory.badPutResponse;
import static ru.otus.hw7.atm.response.ResponseFactory.okPutResponse;

public class PutCommand implements Command {
    private ATM atm;

    public PutCommand(ATM atm) {
        this.atm = atm;
    }


    @Override
    public Response execute(Request request) {
        Map<CashType, Integer> cash = request.getCash();
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
        cash.forEach((cashType, amount) -> atm.getCashMap().merge(cashType, amount, (oldValue, newValue) -> oldValue + newValue));
    }

    private void addToBalance(Map<CashType, Integer> cash) {
        int balance = atm.getBalance();
        balance += cash.keySet().stream().mapToInt(x -> x.getValue() * cash.get(x)).sum();
        atm.setBalance(balance);
    }
}
