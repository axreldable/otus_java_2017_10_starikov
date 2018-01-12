package ru.otus.hw9.data.reflection;

import org.junit.Test;
import ru.otus.hw9.data.DataSet;
import ru.otus.hw9.executor.data.set.UserDataSet;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static ru.otus.hw9.data.reflection.DataSetWorker.getColumnValues;
import static ru.otus.hw9.data.reflection.DataSetWorker.getTableName;

public class DataSetWorkerTest {
    @Test
    public void getTableNameTest() {
        assertEquals("user", getTableName(new UserDataSet("", 1)));
    }

    @Test
    public void getColumnValuesTest() throws IllegalAccessException {
        DataSet dataSet = new UserDataSet("", 1);
        Map<String, Object> columnValues = getColumnValues(dataSet);
        assertEquals(new HashMap<String, Object>() {
            {
                put("name", "");
                put("age", 1);
            }
        }, columnValues);

        dataSet = new UserDataSet(null, 1);
        columnValues = getColumnValues(dataSet);
        assertEquals(new HashMap<String, Object>() {
            {
                put("name", null);
                put("age", 1);
            }
        }, columnValues);
    }
}