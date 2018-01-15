package ru.otus.hw9.data.reflection;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReflectionHelper {
    public static Object getFieldValue(Field field, Object entity) throws IllegalAccessException {
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        Object result = field.get(entity);
        field.setAccessible(accessible);

        return result;
    }

    public static void fillObject(Object dataSet, ResultSet rs) throws SQLException, IllegalAccessException {
        for (Field field: dataSet.getClass().getDeclaredFields()) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(dataSet, rs.getObject(field.getName(), field.getType()));
            field.setAccessible(accessible);
        }
    }
}
