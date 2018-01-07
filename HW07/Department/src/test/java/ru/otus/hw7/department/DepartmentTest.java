package ru.otus.hw7.department;

import org.junit.Before;
import org.junit.Test;
import ru.otus.hw7.atm.ATM;
import ru.otus.hw7.atm.cash.CashType;
import ru.otus.hw7.atm.memento.pattern.Memento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.otus.hw7.atm.constants.Constants.*;
import static ru.otus.hw7.atm.memento.state.State.*;

public class DepartmentTest {
    private Department department;
    private AtmMementoPair pair1;
    private AtmMementoPair pair2;
    private AtmMementoPair pair3;

    @Before
    public void init() {
        pair1 = new AtmMementoPair(new Memento(STATE_1), new ATM());
        pair2 = new AtmMementoPair(new Memento(STATE_2), new ATM());
        pair3 = new AtmMementoPair(new Memento(STATE_3), new ATM());

        List<AtmMementoPair> atms = new ArrayList<AtmMementoPair>() {
            {
                add(pair1);
                add(pair2);
                add(pair3);
            }
        };

        department = new Department(atms);
    }

    @Test
    public void departmentTest() {
        int valuesSum = CashType.getCashValues().stream().mapToInt(Integer::intValue).sum();
        int firstAtmStartBalance = VALUE_5 * valuesSum;
        int secondAtmStartBalance = VALUE_30 * valuesSum;
        int thirdAtmStartBalance = VALUE_150 * valuesSum;

        checkBalances(firstAtmStartBalance, secondAtmStartBalance, thirdAtmStartBalance);

        putSomeToAtms();

        int firstAtmBalanceAfterPut = firstAtmStartBalance + valuesSum;
        int secondAtmBalanceAfterPut = secondAtmStartBalance + valuesSum;
        int thirdAtmBalanceAfterPut = thirdAtmStartBalance + valuesSum;
        checkBalances(firstAtmBalanceAfterPut, secondAtmBalanceAfterPut, thirdAtmBalanceAfterPut);

        department.restoreAll();
        checkBalances(firstAtmStartBalance, secondAtmStartBalance, thirdAtmStartBalance);
    }

    private void checkBalances(int firstBalance, int secondBalance, int thirdBalance) {
        assertEquals(Arrays.asList(firstBalance, secondBalance, thirdBalance), department.getAtmsBalance());
    }

    private void putSomeToAtms() {
        pair1.getAtm().put(createAnyCashMap(1, 1, 1));
        pair2.getAtm().put(createAnyCashMap(1, 1, 1));
        pair3.getAtm().put(createAnyCashMap(1, 1, 1));
    }

    private HashMap<CashType,Integer> createAnyCashMap(int thousands, int fiveHundreds, int oneHundreds) {
        return new HashMap<CashType, Integer>() {
            {
                put(CashType.THOUSAND, thousands);
                put(CashType.FIVE_HUNDRED, fiveHundreds);
                put(CashType.ONE_HUNDRED, oneHundreds);
            }
        };
    }
}