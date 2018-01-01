package ru.otus.hw7.atm.command.pattern;

import ru.otus.hw7.atm.request.Request;
import ru.otus.hw7.atm.response.Response;

public interface Command {
    Response execute(Request request);
}
