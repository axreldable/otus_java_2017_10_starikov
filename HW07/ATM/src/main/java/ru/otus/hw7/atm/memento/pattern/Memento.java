package ru.otus.hw7.atm.memento.pattern;

import ru.otus.hw7.atm.memento.state.State;

public class Memento {
    private final State state;

    public Memento(State stateToSave) {
        state = stateToSave;
    }

    public State getSavedState() {
        return state;
    }
}
