package ru.otus.hw10.sql.executor;

import ru.otus.hw10.data.DataSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.otus.hw10.data.reflection.DataSetWorker.createDataSet;

/**
 * Class for execute sql statements
 */
public class SqlExecutor {
    private Connection connection;

    public SqlExecutor(Connection connection) {
        this.connection = connection;
    }

    public <T extends DataSet> T select(String query, long id, Class<T> clazz) throws Exception {
        try(Statement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            T dataSet = createDataSet(clazz, resultSet);
            if (dataSet != null) {
                dataSet.setId(id);
            }
            return dataSet;
        }
    }

    public long insert(String query) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getLong(1);
        }
    }

    public void close() throws Throwable {
        connection.close();
    }

    public <T extends DataSet> List<T> select(String query, Class<T> clazz) throws Exception {
        List<T> result = new ArrayList<>();
        try(Statement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            while (true) {
                T dataSet = createDataSet(clazz, resultSet);
                if (dataSet != null) {
                    dataSet.setId(resultSet.getLong("id"));
                    result.add(dataSet);
                } else {
                    break;
                }
            }
        }
        return result;
    }
}
