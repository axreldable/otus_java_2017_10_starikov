package ru.otus.hw10.executor;

import ru.otus.hw10.data.DataSet;
import ru.otus.hw10.data.UserDataSet;
import ru.otus.hw10.sql.executor.SqlExecutor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import static ru.otus.hw10.data.reflection.DataSetWorker.getColumnValues;
import static ru.otus.hw10.data.reflection.DataSetWorker.getTableName;
import static ru.otus.hw10.sql.query.SqlQueryCreator.createInsert;
import static ru.otus.hw10.sql.query.SqlQueryCreator.createSelect;

/**
 * Executor work with sql connection and save or load DataSet heirs to or from database
 *
 * The object corresponds to table with name as class name without DataSet postfix in low case.
 * And tables's field names exactly the same as object's field names.
 */
public class Executor {
    private final static Logger log = Logger.getLogger(Executor.class.getName());
    private SqlExecutor sqlExecutor;

    public Executor(Connection connection) {
        this.sqlExecutor = new SqlExecutor(connection);
    }

    /**
     * Get the heir from DataSet and save it to database
     * @param dataSetObject the heir from DataSet
     */
    public <T extends DataSet> void save(T dataSetObject) {
        long id = 0;
        try {
            id = sqlExecutor.insert(createInsert(getTableName(dataSetObject), getColumnValues(dataSetObject)));
        } catch (SQLException | IllegalAccessException e) {
            log.info(e.getMessage());
        }
        dataSetObject.setId(id);
    }

    /**
     * Get object id and class and return the heir from DataSet
     * @param id object id
     * @param clazz object class
     * @return the heir from DataSet
     */
    public <T extends DataSet> T load(long id, Class<T> clazz) {
        try {
            return sqlExecutor.select(createSelect(getTableName(clazz), null, id), id, clazz);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    public void close() {
        try {
            sqlExecutor.close();
        } catch (Throwable throwable) {
            log.info(throwable.getMessage());
        }
    }

    public <T extends DataSet> List<T> readAll(Class<T> clazz) {
        try {
            return sqlExecutor.select(createSelect(getTableName(clazz)), clazz);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }
}
