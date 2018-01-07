package ru.otus.hw7.department;

import lombok.Getter;
import ru.otus.hw7.atm.ATM;
import ru.otus.hw7.atm.memento.pattern.Memento;

public class AtmMementoPair {
    @Getter private final Memento memento;
    @Getter private final ATM atm;

    public AtmMementoPair(Memento memento, ATM atm) {
        this.memento = memento;
        this.atm = atm;
    }
}
