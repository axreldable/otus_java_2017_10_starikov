package ru.otus.hw6.atm;

import org.junit.Test;
import ru.otus.hw6.atm.cash.CashType;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static ru.otus.hw6.atm.cash.CashType.FIVE_HUNDRED;
import static ru.otus.hw6.atm.cash.CashType.ONE_HUNDRED;
import static ru.otus.hw6.atm.cash.CashType.THOUSAND;

public class ATMTest {

    @Test
    public void test() {
        Map<CashType, Integer> cashMap = new HashMap<>();
        cashMap.put(ONE_HUNDRED, 2);

        Map<CashType, Integer> putMap = new HashMap<>();
        putMap.put(ONE_HUNDRED, 2);
        putMap.put(THOUSAND, 1);

        put(cashMap, putMap);

        assertTrue(cashMap.get(ONE_HUNDRED) == 4);
        assertTrue(cashMap.get(THOUSAND) == 1);
        assertTrue(cashMap.get(FIVE_HUNDRED) == null);


    }

    private void put(Map<CashType, Integer> cashMap, Map<CashType, Integer> putMap) {
        putMap.forEach((cashType, amount) -> cashMap.merge(cashType, amount, (oldValue, newValue) -> oldValue + newValue));
    }

    @Test
    public void getNeedCashMapTest() {
        Map<CashType, Integer> existMap = new HashMap<>();
        existMap.put(THOUSAND, 3);
        existMap.put(FIVE_HUNDRED, 5);
        existMap.put(ONE_HUNDRED, 6);

        Map<CashType, Integer> expectedAnswer = new HashMap<>();
        expectedAnswer.put(THOUSAND, 1);
        expectedAnswer.put(ONE_HUNDRED, 2);
        Map<CashType, Integer> returnedAnswer = ATM.getNeedCashMap(1200, existMap);
        assertTrue(expectedAnswer.equals(returnedAnswer));

        expectedAnswer.clear();
        expectedAnswer.put(THOUSAND, 3);
        expectedAnswer.put(FIVE_HUNDRED, 3);
        expectedAnswer.put(ONE_HUNDRED, 1);
        returnedAnswer = ATM.getNeedCashMap(4600, existMap);
        assertTrue(expectedAnswer.equals(returnedAnswer));

        expectedAnswer.clear();
        expectedAnswer.put(THOUSAND, 3);
        expectedAnswer.put(FIVE_HUNDRED, 5);
        expectedAnswer.put(ONE_HUNDRED, 6);
        returnedAnswer = ATM.getNeedCashMap(6100, existMap);
        assertTrue(expectedAnswer.equals(returnedAnswer));

        expectedAnswer.clear();
        expectedAnswer.put(THOUSAND, 3);
        expectedAnswer.put(FIVE_HUNDRED, 5);
        expectedAnswer.put(ONE_HUNDRED, 6);
        returnedAnswer = ATM.getNeedCashMap(6200, existMap);
        assertTrue(expectedAnswer.equals(returnedAnswer));

    }

}