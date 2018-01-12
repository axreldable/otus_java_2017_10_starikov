package ru.otus.hw9.executor;

import ru.otus.hw9.data.DataSet;
import ru.otus.hw9.sql.executor.SqlExecutor;

import java.sql.Connection;

/**
 * Executor work with sql connection and save or load DataSet heirs to or from database
 *
 * The object corresponds to table with name as class name without DataSet postfix in low case.
 * And tables's field names exactly the same as object's field names.
 */
public class Executor {
    private SqlExecutor sqlExecutor;

    public Executor(Connection connection) {
        this.sqlExecutor = new SqlExecutor(connection);
    }

    /**
     * Get the heir from DataSet and save it to database
     * @param dataSetObject the heir from DataSet
     */
    public <T extends DataSet> void save(T dataSetObject) {

    }

    /**
     * Get object id and class and return the heir from DataSet
     * @param id object id
     * @param clazz object class
     * @return the heir from DataSet
     */
    public <T extends DataSet> T load(long id, Class<T> clazz) {
        return null;
    }
}
