package ru.otus.hw10.service;

import ru.otus.hw10.dao.UserDao;
import ru.otus.hw10.data.UserDataSet;
import ru.otus.hw10.executor.Executor;
import ru.otus.hw10.service.connection.MySqlConnectionHelper;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

public class DBServiceImpl implements DBService {
    private static final String DB_NAME = "otus";
    private static final String ROOT = "root";
    private Executor executor;

    public DBServiceImpl() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("db", DB_NAME);
        properties.setProperty("user", ROOT);
        properties.setProperty("password", ROOT);
        Connection connection = new MySqlConnectionHelper().createConnection(properties);
        this.executor = new Executor(connection);
    }

    @Override
    public void save(UserDataSet dataSet) {
        new UserDao(executor).save(dataSet);
    }

    @Override
    public UserDataSet load(long id) {
        return new UserDao(executor).load(id);
    }

    @Override
    public void shutdown() {
        executor.close();
    }

    @Override
    public List<UserDataSet> readAll() {
        return new UserDao(executor).readAll();
    }

    @Override
    public int getAmount() {
        return readAll().size();
    }
}
