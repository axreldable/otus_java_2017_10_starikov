package ru.otus.hw10.executor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.otus.hw10.service.connection.MySqlConnectionHelper;
import ru.otus.hw10.data.UserDataSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.junit.Assert.*;

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
    public void test() throws Exception {
        UserDataSet userTo = new UserDataSet("name1", 1);
        assertNull(userTo.getId());

        int startUserAmount = getUserAmount();
        executor.save(userTo);
        assertNotNull(userTo.getId());
        assertEquals(startUserAmount + 1, getUserAmount());

        UserDataSet userFrom = executor.load(userTo.getId(), UserDataSet.class);
        assertEquals(userTo, userFrom);
    }

    @Test
    public void nullTest() {
        UserDataSet userFrom = executor.load(-1L, UserDataSet.class);
        assertNull(userFrom);
    }

    private int getUserAmount() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT COUNT(*) as total FROM user");
        rs.next();
        return rs.getInt("total");
    }
}