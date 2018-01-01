package ru.otus.hw7.department;

import ru.otus.hw7.atm.ATM;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private List<ATM> atms;

    public Department(List<ATM> atms) {
        this.atms = atms;
    }

    public List<Integer> getAtmsBalance() {
        List<Integer> atmRemainders = new ArrayList<>();
        for (ATM atm : atms) {
            atmRemainders.add(atm.getBalance());
        }
        return atmRemainders;
    }


    public void rollBackAll() {
        for (ATM atm : atms) {
            atm.rollBack();
        }
    }
}
