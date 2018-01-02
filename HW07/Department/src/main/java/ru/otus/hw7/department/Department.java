package ru.otus.hw7.department;

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
        atms.forEach(pair -> atmRemainders.add(pair.getAtm().getBalance()));
        return atmRemainders;
    }

    public void restoreAll() {
        atms.forEach(pair -> pair.getAtm().restoreFromMemento(pair.getMemento()));
    }
}
