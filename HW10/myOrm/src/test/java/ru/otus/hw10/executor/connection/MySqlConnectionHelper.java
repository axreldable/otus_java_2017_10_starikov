package ru.otus.hw10.executor.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnectionHelper {
    private final static String DEFAULT_PORT = "3306";
    private final static String DEFAULT_HOST = "localhost";
    private final static String EMPTY_STRING = "";

    private Properties properties;

    public Connection createConnection(Properties properties) throws Exception {
        this.properties = properties;
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(getConnectionUrl(), getUser(), getPassword());
    }

    public void disconnect(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    private String getConnectionUrl() {
        return "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDbName() + "?serverTimezone=UTC";
    }

    private String getHost() {
        return properties.getProperty("host", DEFAULT_HOST);
    }

    private String getPort() {
        return properties.getProperty("port", DEFAULT_PORT);
    }

    private String getDbName() {
        return properties.getProperty("db", EMPTY_STRING);
    }

    private String getPassword() {
        return properties.getProperty("password", EMPTY_STRING);
    }

    private String getUser() {
        return properties.getProperty("user", EMPTY_STRING);
    }
}
