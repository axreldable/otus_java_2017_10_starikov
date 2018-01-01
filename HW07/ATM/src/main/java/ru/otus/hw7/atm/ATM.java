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

import java.util.HashMap;
import java.util.Map;

/**
 * ATM core class.
 * Has one public method execute, which get standard request
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
    private Map<CommandType.Type, Command> command = new HashMap<>();

    public ATM() {
        command.put(CommandType.Type.PUT, new PutCommand(this));
        command.put(CommandType.Type.GET, new GetCommand(this));
        command.put(CommandType.Type.BALANCE, new BalanceCommand(this));
    }

    public Response execute(Request request) {
        return command.get(request.getCommand()).execute(request);
    }
}
