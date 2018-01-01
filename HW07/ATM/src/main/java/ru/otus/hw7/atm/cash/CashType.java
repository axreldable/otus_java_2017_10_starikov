package ru.otus.hw7.atm.cash;

import ru.otus.hw7.atm.exception.UnknownCashValueException;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

/**
 * Cash type in the ATM.
 * You can add new bills here, but the logic will remain the same
 */
public enum CashType implements Comparable<CashType> {
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500),
    THOUSAND(1000);

    private int value;

    public int getValue() {
        return value;
    }

    CashType(int value) {
        this.value = value;
    }

    public static int getMinValue() {
        return getCashValues().stream().min(Integer::compareTo).get();
    }

    public static List<Integer> getCashValues() {
        return stream(CashType.values()).map(CashType::getValue).collect(Collectors.toList());
    }

    public static List<Integer> getSortReverseCashValues() {
        List<Integer> cashValues = getCashValues();
        Collections.sort(cashValues);
        Collections.reverse(cashValues);
        return cashValues;
    }

    public static CashType parseCash(int value) {
        for(CashType cacheType : CashType.values()){
            if (cacheType.value == value){
                return cacheType;
            }
        }
        throw new UnknownCashValueException("unknown cash value: " + value);
    }

    public static Map<CashType,Integer> getCashMapForValue(int value) {
        return new HashMap<CashType,Integer>() {
            {
                put(ONE_HUNDRED, value);
                put(FIVE_HUNDRED, value);
                put(THOUSAND, value);
            }
        };
    }
}
