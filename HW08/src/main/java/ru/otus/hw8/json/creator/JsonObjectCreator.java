package ru.otus.hw8.json.creator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.logging.Logger;

public class JsonObjectCreator {
    private final static Logger log = Logger.getLogger(JsonObjectCreator.class.getName());

    public Object create(Object object) {
        if (isPrimitive(object)) {
            return object;
        }
        if (isArray(object)) {
            return createArray(object);
        }
        if (isIterable(object)) {
            return createArrayFromIterable((Iterable) object);
        }
        return createJsonObject(object);
    }

    private Object createArrayFromIterable(Iterable iterable) {
        JSONArray array = new JSONArray();
        for (Object object : iterable) {
            array.add(create(object));
        }
        return array;
    }

    private boolean isIterable(Object object) {
        return object instanceof Iterable;
    }

    private Object createJsonObject(Object object) {
        JSONObject jsonObject = new JSONObject();
        for (Field field: object.getClass().getDeclaredFields()) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            try {
                jsonObject.put(field.getName(), create(field.get(object)));
            } catch (Exception ex) {
                log.warning(ex.getMessage());
            } finally {
                field.setAccessible(accessible);
            }
        }
        return jsonObject;
    }

    private JSONArray createArray(Object object) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < Array.getLength(object); i++) {
            jsonArray.add(create(Array.get(object, i)));
        }
        return jsonArray;
    }

    private boolean isPrimitive(Object object) {
        return object == null ||
                object instanceof Number ||
                object instanceof Boolean ||
                object instanceof Character ||
                object instanceof String;
    }

    private boolean isArray(Object object) {
        return object.getClass().isArray();
    }
}
