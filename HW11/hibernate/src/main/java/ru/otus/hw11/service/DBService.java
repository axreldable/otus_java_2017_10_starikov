package ru.otus.hw11.service;

import ru.otus.hw11.data.UserDataSet;

import java.util.List;

public interface DBService {
    void save(UserDataSet dataSet);

    UserDataSet load(long id);

    void shutdown();

    List<UserDataSet> readAll();

    int getAmount();
}
