package ru.otus.hw7.atm;

import lombok.Getter;
import lombok.Setter;
import ru.otus.hw7.atm.cash.CashType;
import ru.otus.hw7.atm.command.pattern.BalanceCommand;
import ru.otus.hw7.atm.command.pattern.Command;
import ru.otus.hw7.atm.command.pattern.GetCommand;
import ru.otus.hw7.atm.command.pattern.PutCommand;
import ru.otus.hw7.atm.memento.pattern.Memento;
import ru.otus.hw7.atm.observer.pattern.Observer;
import ru.otus.hw7.atm.request.Request;
import ru.otus.hw7.atm.response.Response;
import ru.otus.hw7.atm.memento.state.State;

import java.util.HashMap;
import java.util.Map;

import static ru.otus.hw7.atm.command.CommandType.*;
import static ru.otus.hw7.atm.command.CommandType.Type.BALANCE;
import static ru.otus.hw7.atm.command.CommandType.Type.GET;
import static ru.otus.hw7.atm.command.CommandType.Type.PUT;

/**
 * ATM core class.
 * Has one public method executeCommand, which get standard request
 * and return OK response if the command successfully complete
 * or ERROR if the command failed.
 *
 * Thanks for PATTERN COMMAND logic from different commands moves to different classes.
 * It's much more readable.
 * And it's much more convenient to develop the functionality of different commands.
 *
 * Strange logic of working with the atm state just to try MEMENTO PATTERN
 * Better to pass the initial state in the constructor.
 *
 * OBSERVER PATTERN also add for working with balance
 */
public class ATM implements Observer {
    @Getter private Map<CashType, Integer> cashMap = new HashMap<>();
    @Getter @Setter private int balance = 0;
    private Map<Type, Command> command = new HashMap<>();
    private State state;

    public ATM() {
        command.put(PUT, new PutCommand(this));
        command.put(GET, new GetCommand(this));
        command.put(BALANCE, new BalanceCommand(this));
    }

    public Response executeCommand(Request request) {
        return command.get(request.getCommand()).execute(request);
    }

    // just for Memento pattern //
    public void set(State state) {
        this.state = state;
    }

    public Memento saveToMemento() {
        return new Memento(this.state);
    }

    public void restoreFromMemento(Memento memento) {
        this.state = memento.getSavedState();
        setCashForState();
    }

    private void setCashForState() {
        cashMap = new HashMap<>();
        balance = 0;

        command.get(PUT).execute(Request.builder()
                .cash(CashType.getCashMapForValue(state.getValue()))
                .build());
    }
    /////////////////////////////////////


    // for observer pattern
    @Override
    public Response notify(Request request) {
        return executeCommand(request);
    }
}
