package ru.otus.hw7.atm.command.pattern;

import ru.otus.hw7.atm.ATM;
import ru.otus.hw7.atm.request.Request;
import ru.otus.hw7.atm.response.Response;

import static ru.otus.hw7.atm.response.ResponseFactory.okBalanceResponse;

public class BalanceCommand implements Command {
    private ATM atm;

    public BalanceCommand(ATM atm) {
        this.atm = atm;
    }

    @Override
    public Response execute(Request request) {
        return okBalanceResponse(atm.getCashMap(), atm.getBalance());
    }
}
