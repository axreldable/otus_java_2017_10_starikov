package ru.otus.hw7.department;

import org.junit.Before;
import org.junit.Test;
import ru.otus.hw7.atm.ATM;
import ru.otus.hw7.atm.cash.CashType;
import ru.otus.hw7.atm.command.CommandType;
import ru.otus.hw7.atm.request.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.otus.hw7.atm.constants.Constants.*;
import static ru.otus.hw7.atm.state.State.*;

public class DepartmentTest {
    private Department department;
    private ATM atm1;
    private ATM atm2;
    private ATM atm3;

    @Before
    public void init() {
        atm1 = new ATM(STATE_1);
        atm2 = new ATM(STATE_2);
        atm3 = new ATM(STATE_3);

        List<ATM> atms = new ArrayList<ATM>() {
            {
                add(atm1);
                add(atm2);
                add(atm3);
            }
        };

        department = new Department(atms);
    }

    @Test
    public void departmentTest() {
        int valuesSum = CashType.getCashValues().stream().mapToInt(Integer::intValue).sum();
        int firstAtmStartBalance = STATE_1_VALUE * valuesSum;
        int secondAtmStartBalance = STATE_2_VALUE * valuesSum;
        int thirdAtmStartBalance = STATE_3_VALUE * valuesSum;

        checkBalances(firstAtmStartBalance, secondAtmStartBalance, thirdAtmStartBalance);

        putSomeToAtms();

        int firstAtmBalanceAfterPut = firstAtmStartBalance + valuesSum;
        int secondAtmBalanceAfterPut = secondAtmStartBalance + valuesSum;
        int thirdAtmBalanceAfterPut = thirdAtmStartBalance + valuesSum;
        checkBalances(firstAtmBalanceAfterPut, secondAtmBalanceAfterPut, thirdAtmBalanceAfterPut);

        department.rollBackAll();
        checkBalances(firstAtmStartBalance, secondAtmStartBalance, thirdAtmStartBalance);
    }

    private void checkBalances(int firstBalance, int secondBalance, int thirdBalance) {
        assertEquals(Arrays.asList(firstBalance, secondBalance, thirdBalance), department.getAtmsBalance());
    }

    private void putSomeToAtms() {
        atm1.executeCommand(createPutRequest(createAnyCashMap(1, 1, 1)));
        atm2.executeCommand(createPutRequest(createAnyCashMap(1, 1, 1)));
        atm3.executeCommand(createPutRequest(createAnyCashMap(1, 1, 1)));
    }

    private Request createPutRequest(HashMap<CashType, Integer> inputMap) {
        return Request.builder()
                .command(CommandType.Type.PUT)
                .cash(inputMap)
                .build();
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