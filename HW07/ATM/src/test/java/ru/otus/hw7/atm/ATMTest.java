package ru.otus.hw7.atm;

import org.junit.Before;
import org.junit.Test;
import ru.otus.hw7.atm.cash.CashType;
import ru.otus.hw7.atm.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static ru.otus.hw7.atm.response.type.Type.ERROR;
import static ru.otus.hw7.atm.response.type.Type.OK;

public class ATMTest {
    private static final String ATM_BALANCE = "ATM Balance";

    private static final String ATM_PUT_BAD = "ATM can receive only positive cash";
    private static final String ATM_PUT_OK = "ATM successfully received cash";

    private static final String ATM_GET_BAD_NEGATIVE_VALUE = "ATM can give only positive values";
    private static final String ATM_GET_BAD_NOT_ENOUGH_MONEY = "ATM can't give cash - not enough money";
    private static final String ATM_GET_BAD_DENOMINATION = "ATM can give cash amount multiple of min value";
    private static final String ATM_GET_BAD_NOT_ENOUGH_CASH_TYPE = "ATM can't give cash - not enough cash type";
    private static final String ATM_GET_OK = "ATM successfully give money";

    private static Response response;
    private static ATM atm;

    @Before
    public void init() {
        atm = new ATM();
    }

    @Test
    public void testOkStory() {
        response = atm.balance();
        checkOkResponse(ATM_BALANCE, new HashMap<>(), 0);
        checkAtm(new HashMap<>(), 0);

        HashMap<CashType, Integer> inputMap = createCashMap(3, 2, 1);
        response = atm.put(inputMap);
        checkOkResponse(ATM_PUT_OK, inputMap, null);
        checkAtm(inputMap, getBalance(3, 2, 1));

        response = atm.withdraw(1100);
        HashMap<CashType, Integer> returnMap = createCashMap(1, 0, 1);
        checkOkResponse(ATM_GET_OK, returnMap, null);
        HashMap<CashType, Integer> remainderMap = createCashMap(2, 2, 0);
        checkAtm(remainderMap, getBalance(2, 2, 0));

        response = atm.balance();
        checkOkResponse(ATM_BALANCE, remainderMap, getBalance(2, 2, 0));
        checkAtm(remainderMap, getBalance(2, 2, 0));
    }

    @Test
    public void testPutNotPositiveCash() {
        HashMap<CashType, Integer> inputMap = createAnyCashMap(1, 0, 1);
        response = atm.put(inputMap);
        checkBadResponse(ATM_PUT_BAD, inputMap, null);
        checkAtm(new HashMap<>(), 0);

        inputMap = createAnyCashMap(1, -1, 1);
        response = atm.put(inputMap);
        checkBadResponse(ATM_PUT_BAD, inputMap, null);
        checkAtm(new HashMap<>(), 0);
    }

    @Test
    public void testBadGetNegativeValue() {
        response = atm.withdraw(-1000);
        checkBadResponse(ATM_GET_BAD_NEGATIVE_VALUE, null, null);
    }

    @Test
    public void testBadGetNotEnoughMoney() {
        HashMap<CashType, Integer> inputMap = createCashMap(3, 2, 1);
        response = atm.put(inputMap);
        checkOkResponse(ATM_PUT_OK, inputMap, null);
        checkAtm(inputMap, getBalance(3, 2, 1));

        response = atm.withdraw(getBalance(3, 2, 1) + 100);
        checkBadResponse(ATM_GET_BAD_NOT_ENOUGH_MONEY, null, getBalance(3, 2, 1));
        checkAtm(inputMap, getBalance(3, 2, 1));
    }

    @Test
    public void testBadGetDenomination() {
        HashMap<CashType, Integer> inputMap = createCashMap(3, 2, 1);
        response = atm.put(inputMap);
        checkOkResponse(ATM_PUT_OK, inputMap, null);
        checkAtm(inputMap, getBalance(3, 2, 1));

        response = atm.withdraw(getBalance(3, 2, 1) - 3);
        checkBadResponse(ATM_GET_BAD_DENOMINATION, null, null);
        checkAtm(inputMap, getBalance(3, 2, 1));
    }

    @Test
    public void testBadGetNotEnoughCashType() {
        HashMap<CashType, Integer> inputMap = createCashMap(3, 2, 1);
        response = atm.put(inputMap);
        checkOkResponse(ATM_PUT_OK, inputMap, null);
        checkAtm(inputMap, getBalance(3, 2, 1));

        response = atm.withdraw(3900); // not enough 100 cash
        checkBadResponse(ATM_GET_BAD_NOT_ENOUGH_CASH_TYPE, inputMap, null);
        checkAtm(inputMap, getBalance(3, 2, 1));
    }

    private static void checkBadResponse(String message, Map<CashType, Integer> cash, Integer balance) {
        assertEquals(ERROR, response.getResponseType());
        assertEquals(message, response.getMessage());
        assertEquals(cash, response.getCash());
        assertEquals(balance, response.getBalance());
    }

    private int getBalance(int thousands, int fiveHundreds, int oneHundreds) {
        return 1000*thousands + 500*fiveHundreds + 100*oneHundreds;
    }

    private HashMap<CashType,Integer> createCashMap(int thousands, int fiveHundreds, int oneHundreds) {
        return new HashMap<CashType, Integer>() {
            {
                if (thousands != 0) {
                    put(CashType.THOUSAND, thousands);
                }
                if (fiveHundreds != 0) {
                    put(CashType.FIVE_HUNDRED, fiveHundreds);
                }
                if (oneHundreds != 0) {
                    put(CashType.ONE_HUNDRED, oneHundreds);
                }
            }
        };
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

    private void checkAtm(HashMap<CashType, Integer> inputMap, int balance) {
        assertEquals(inputMap, atm.getCashMap());
        assertEquals(balance, atm.getBalance());
    }

    private static void checkOkResponse(String message, Map<CashType, Integer> cash, Integer balance) {
        assertEquals(OK, response.getResponseType());
        assertEquals(message, response.getMessage());
        assertEquals(cash, response.getCash());
        assertEquals(balance, response.getBalance());
    }
}