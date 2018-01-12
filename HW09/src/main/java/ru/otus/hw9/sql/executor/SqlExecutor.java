package ru.otus.hw9.sql.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for execute sql statements
 */
public class SqlExecutor {
    private Connection connection;

    public SqlExecutor(Connection connection) {
        this.connection = connection;
    }

    /**
     * Execute select query
     * @param query select query
     * @return query result set
     * @throws SQLException
     */
    public ResultSet select(String query) throws SQLException {
        try(Statement statement = connection.createStatement()) {
            return statement.executeQuery(query);
        }
    }

    /**
     * Execute insert statement
     * @param query insert statement
     * @throws SQLException
     */
    public void insert(String query) throws SQLException {
        try(Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
        connection.commit();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        connection.close();
    }
}
