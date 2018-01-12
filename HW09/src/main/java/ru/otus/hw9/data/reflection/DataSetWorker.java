package ru.otus.hw9.data.reflection;

import ru.otus.hw9.data.DataSet;

import java.util.Map;

/**
 * Class that prepare data for sql query from DataSet object.
 */
public class DataSetWorker {
    private DataSet dataSet;

    public DataSetWorker(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public String getTableName() {
        return null;
    }

    public Map<String, Object> getColumnValues() {
        return null;
    }
}
