package ru.otus.hw6.atm.response;

import ru.otus.hw6.atm.cash.CashType;

import java.util.Map;

import static ru.otus.hw6.atm.response.type.Type.ERROR;
import static ru.otus.hw6.atm.response.type.Type.OK;

public class ResponseFactory {
    private static final String BED_COMMAND = "unknown command";

    private static final String ATM_BALANCE = "ATM Balance";

    private static final String ATM_PUT_BAD = "ATM can receive only positive cash";
    private static final String ATM_PUT_OK = "ATM successfully received cash";

    private static final String ATM_GET_BAD_NEGATIVE_VALUE = "ATM can give only positive values";
    private static final String ATM_GET_BAD_NOT_ENOUGH_MONEY = "ATM can't give cash - not enough money";
    private static final String ATM_GET_BAD_DENOMINATION = "ATM can give cash amount multiple of min value";
    private static final String ATM_GET_OK = "ATM successfully give money";

    public static Response badCommandResponse() {
        return Response.builder()
                .responseType(ERROR)
                .message(BED_COMMAND)
                .build();
    }

    public static Response okBalanceResponse(Map<CashType, Integer> cash, int balance) {
        return Response.builder()
                .responseType(OK)
                .message(ATM_BALANCE)
                .cash(cash)
                .balance(balance)
                .build();
    }

    public static Response okPutResponse(Map<CashType,Integer> cash) {
        return Response.builder()
                .responseType(OK)
                .message(ATM_PUT_OK)
                .cash(cash)
                .build();
    }

    public static Response badPutResponse(Map<CashType, Integer> cash) {
        return Response.builder()
                .responseType(ERROR)
                .message(ATM_PUT_BAD)
                .cash(cash)
                .build();
    }

    public static Response badGetPositiveResponse() {
        return Response.builder()
                .responseType(ERROR)
                .message(ATM_GET_BAD_NEGATIVE_VALUE)
                .build();
    }

    public static Response badGetNotEnoughMoneyResponse(int balance) {
        return Response.builder()
                .responseType(ERROR)
                .message(ATM_GET_BAD_NOT_ENOUGH_MONEY)
                .balance(balance)
                .build();
    }

    public static Response badGetMultipleResponse() {
        return Response.builder()
                .responseType(ERROR)
                .message(ATM_GET_BAD_DENOMINATION)
                .build();
    }
}
