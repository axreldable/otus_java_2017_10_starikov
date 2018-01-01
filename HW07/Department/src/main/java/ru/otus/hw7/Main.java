package ru.otus.hw7;

import ru.otus.hw7.atm.ATM;
import ru.otus.hw7.department.Department;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.otus.hw7.atm.state.State.*;

public class Main {
    public static void main(String[] args) {
        List<ATM> atms = new ArrayList<ATM>() {
            {
                add(new ATM(STATE_1));
                add(new ATM(STATE_2));
                add(new ATM(STATE_3));
            }
        };
        Department department = new Department(atms);

        System.out.println(Arrays.toString(department.getAtmsBalance().toArray()));

        department.rollBackAll();

        System.out.println("See DepartmentTest");
    }
}
