package ru.otus.hw9.executor;

import ru.otus.hw9.data.DataSet;

import java.sql.Connection;

public class Executor {
    public Executor(Connection connection) {
    }

    public <T extends DataSet> void save(T user) {

    }
    public <T extends DataSet> T load(long id, Class<T> clazz) {
        return null;
    }
}
