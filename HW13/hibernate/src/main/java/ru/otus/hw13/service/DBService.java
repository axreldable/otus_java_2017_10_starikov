package ru.otus.hw13.service;

import ru.otus.hw13.data.UserDataSet;

import java.util.List;

public interface DBService {
    void save(UserDataSet dataSet);

    UserDataSet load(long id);

    void shutdown();

    List<UserDataSet> readAll();

    int getAmount();
}
