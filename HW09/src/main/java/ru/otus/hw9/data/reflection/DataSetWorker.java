package ru.otus.hw9.data.reflection;

import ru.otus.hw9.data.DataSet;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import static ru.otus.hw9.data.reflection.ReflectionHelper.fillObject;
import static ru.otus.hw9.data.reflection.ReflectionHelper.getFieldValue;

public class DataSetWorker {
    public static String getTableName(DataSet dataSet) {
        return getTableName(dataSet.getClass());
    }

    public static String getTableName(Class clazz) {
        String simpleName = clazz.getSimpleName();
        return simpleName.substring(0, simpleName.length() - "DataSet".length()).toLowerCase();
    }

    public static Map<String, Object> getColumnValues(DataSet dataSet) throws IllegalAccessException {
        Map<String, Object> result = new HashMap<>();
        for (Field field: dataSet.getClass().getDeclaredFields()) {
            result.put(field.getName(), getFieldValue(field, dataSet));
        }
        return result;
    }

    public static <T extends DataSet> T createDataSet(Class<T> clazz, ResultSet rs) throws Exception {
        T dataSet = clazz.newInstance();
        if (!rs.next()) return null;
        fillObject(dataSet, rs);
        return dataSet;
    }
}
