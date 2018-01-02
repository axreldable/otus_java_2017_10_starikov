package ru.otus.hw7.atm.observer.pattern;

import ru.otus.hw7.atm.request.Request;
import ru.otus.hw7.atm.response.Response;

public interface Observer {
    Response notify(Request request);
}
