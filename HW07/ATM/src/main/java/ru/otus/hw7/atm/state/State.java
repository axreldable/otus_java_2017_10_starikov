package ru.otus.hw7.atm.state;

import static ru.otus.hw7.atm.constants.Constants.*;

public enum State {
    STATE_1(STATE_1_VALUE),
    STATE_2(STATE_2_VALUE),
    STATE_3(STATE_3_VALUE),
    STATE_ZERO(0); // for existing ATMTest

    private int value;

    public int getValue() {
        return value;
    }

    State(int value) {
        this.value = value;
    }

    public static State parseState(int value) {
        for(State cacheType : State.values()){
            if (cacheType.value == value){
                return cacheType;
            }
        }
        return null;
    }
}
