package ru.otus.hw6.atm.cash;

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
        return ONE_HUNDRED.getValue();
    }

    public static CashType parseType(int value) {
        switch (value) {
            case 100: {
                return ONE_HUNDRED;
            }
            case 500: {
                return FIVE_HUNDRED;
            }
            case 1000: {
                return THOUSAND;
            }
        }
        return null;
    }


}
