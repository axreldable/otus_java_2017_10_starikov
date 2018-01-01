package ru.otus.hw7.atm;

import lombok.Getter;
import lombok.Setter;
import ru.otus.hw7.atm.cash.CashType;
import ru.otus.hw7.atm.command.CommandType;
import ru.otus.hw7.atm.command.pattern.BalanceCommand;
import ru.otus.hw7.atm.command.pattern.Command;
import ru.otus.hw7.atm.command.pattern.GetCommand;
import ru.otus.hw7.atm.command.pattern.PutCommand;
import ru.otus.hw7.atm.request.Request;
import ru.otus.hw7.atm.response.Response;
import ru.otus.hw7.atm.state.State;

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
 */
public class ATM {
    @Getter private Map<CashType, Integer> cashMap = new HashMap<>();
    @Getter @Setter private int balance = 0;
    private Map<Type, Command> command = new HashMap<>();
    private State beginState;

    public ATM(State state) {
        command.put(PUT, new PutCommand(this));
        command.put(GET, new GetCommand(this));
        command.put(BALANCE, new BalanceCommand(this));
        this.beginState = state;
        setBeginState();
    }

    public Response executeCommand(Request request) {
        return command.get(request.getCommand()).execute(request);
    }

    private void setBeginState() {
       command.get(PUT).execute(Request.builder()
               .cash(CashType.getCashMapForValue(beginState.getValue()))
               .build());
    }

    public void rollBack() {
        cashMap = new HashMap<>();
        balance = 0;
        setBeginState();
    }
}
