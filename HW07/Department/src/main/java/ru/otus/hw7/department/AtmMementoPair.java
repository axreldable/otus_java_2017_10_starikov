package ru.otus.hw7.department;

import lombok.Getter;
import lombok.Setter;
import ru.otus.hw7.atm.ATM;
import ru.otus.hw7.atm.memento.pattern.Memento;

public class AtmMementoPair {
    @Setter @Getter private Memento memento;
    @Setter @Getter private ATM atm;

    public AtmMementoPair(Memento memento, ATM atm) {
        this.memento = memento;
        this.atm = atm;
    }
}
