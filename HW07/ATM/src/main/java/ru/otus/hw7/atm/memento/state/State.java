package ru.otus.hw7.atm.memento.state;

import static ru.otus.hw7.atm.constants.Constants.*;

public enum State {
    STATE_1(VALUE_5),
    STATE_2(VALUE_30),
    STATE_3(VALUE_150),
    STATE_ZERO(0); // for existing ATMTest correct working

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
