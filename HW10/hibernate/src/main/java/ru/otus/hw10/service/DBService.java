package ru.otus.hw10.service;

import ru.otus.hw10.data.UserDataSet;

import java.util.List;

public interface DBService {
    void save(UserDataSet dataSet);

    UserDataSet load(long id);

    void shutdown();

    List<UserDataSet> readAll();

    int getAmount();
}
