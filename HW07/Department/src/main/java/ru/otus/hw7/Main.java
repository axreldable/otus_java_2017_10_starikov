package ru.otus.hw7;

import ru.otus.hw7.atm.ATM;
import ru.otus.hw7.atm.memento.pattern.Memento;
import ru.otus.hw7.department.AtmMementoPair;
import ru.otus.hw7.department.Department;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.otus.hw7.atm.memento.state.State.*;

public class Main {
    public static void main(String[] args) {
        List<AtmMementoPair> atms = new ArrayList<AtmMementoPair>() {
            {
                add(new AtmMementoPair(new Memento(STATE_1), new ATM()));
                add(new AtmMementoPair(new Memento(STATE_2), new ATM()));
                add(new AtmMementoPair(new Memento(STATE_3), new ATM()));
            }
        };
        Department department = new Department(atms);

        System.out.println(Arrays.toString(department.getAtmsBalance().toArray()));

        department.restoreAll();

        System.out.println("See DepartmentTest");
    }
}
