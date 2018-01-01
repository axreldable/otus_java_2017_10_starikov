package ru.otus.hw7.atm;

import java.lang.reflect.Field;

public class ReflectionHelper {
    public static <T> T getPrivateField(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return  (T) field.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
