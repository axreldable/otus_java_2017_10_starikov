package ru.otus.hw10.executor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.otus.hw10.data.UserDataSet;
import ru.otus.hw10.executor.connection.MySqlConnectionHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.assertNull;

public class ExecutorTest {
    private static final String DB_NAME = "otus";
    private static final String ROOT = "root";
    private Executor executor;
    private Connection connection;

    @Before
    public void connect() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("db", DB_NAME);
        properties.setProperty("user", ROOT);
        properties.setProperty("password", ROOT);
        Connection connection = new MySqlConnectionHelper().createConnection(properties);
        this.connection = connection;
        this.executor = new Executor(connection);
    }

    @After
    public void disconnect() throws SQLException {
        new MySqlConnectionHelper().disconnect(connection);
    }

    @Test
    public void nullTest() throws Exception {
        UserDataSet userFrom = executor.load(-1L, UserDataSet.class);
        assertNull(userFrom);
    }
}