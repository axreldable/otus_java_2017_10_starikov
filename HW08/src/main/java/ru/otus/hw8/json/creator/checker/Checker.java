package ru.otus.hw8.json.creator.checker;

public class Checker {
    public static boolean isIterable(Object object) {
        return object instanceof Iterable;
    }

    public static boolean isPrimitive(Object object) {
        return object == null ||
                object instanceof Number ||
                object instanceof Boolean ||
                object instanceof Character ||
                object instanceof String;
    }

    public static boolean isArray(Object object) {
        return object.getClass().isArray();
    }
}
