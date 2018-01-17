package ru.otus.hw10.dao;

import ru.otus.hw10.data.UserDataSet;
import ru.otus.hw10.executor.Executor;

import java.util.List;

public class UserDao {
    private Executor executor;

    public UserDao(Executor executor) {
        this.executor = executor;
    }

    public void save(UserDataSet dataSet) {
        executor.save(dataSet);
    }

    public UserDataSet load(long id) {
        return executor.load(id, UserDataSet.class);
    }

    public List<UserDataSet> readAll() {
        return executor.readAll(UserDataSet.class);
    }
}
