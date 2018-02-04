package ru.otus.hw12.service;

import ru.otus.hw12.data.UserDataSet;

import java.util.List;

public interface DBService {
    void save(UserDataSet dataSet);

    UserDataSet load(long id);

    void shutdown();

    List<UserDataSet> readAll();

    int getAmount();
}
