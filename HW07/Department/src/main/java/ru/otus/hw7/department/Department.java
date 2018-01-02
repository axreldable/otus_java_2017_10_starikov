package ru.otus.hw7.department;

import ru.otus.hw7.atm.command.CommandType;
import ru.otus.hw7.atm.request.Request;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private List<AtmMementoPair> atms;

    public Department(List<AtmMementoPair> atms) {
        this.atms = atms;
        restoreAll();
    }

    public List<Integer> getAtmsBalance() {
        List<Integer> atmRemainders = new ArrayList<>();
        atms.forEach(pair -> atmRemainders.add(pair.getAtm().notify(createBalanceRequest()).getBalance()));
        return atmRemainders;
    }

    public void restoreAll() {
        atms.forEach(pair -> pair.getAtm().restoreFromMemento(pair.getMemento()));
    }

    private Request createBalanceRequest() {
        return Request.builder()
                .command(CommandType.Type.BALANCE)
                .build();
    }
}
