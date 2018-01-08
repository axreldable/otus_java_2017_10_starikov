package ru.otus.hw8.json.creator;

import ru.otus.hw8.json.my.simple.json.MyJsonArray;
import ru.otus.hw8.json.my.simple.json.MyJsonObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.logging.Logger;

import static ru.otus.hw8.json.creator.checker.Checker.*;

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

    @SuppressWarnings("unchecked")
    private MyJsonArray createArrayFromIterable(Iterable iterable) {
        MyJsonArray array = new MyJsonArray();
        for (Object object : iterable) {
            array.add(create(object));
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    private MyJsonObject createJsonObject(Object object) {
        MyJsonObject jsonObject = new MyJsonObject();
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

    @SuppressWarnings("unchecked")
    private MyJsonArray createArray(Object object) {
        MyJsonArray jsonArray = new MyJsonArray();
        for (int i = 0; i < Array.getLength(object); i++) {
            jsonArray.add(create(Array.get(object, i)));
        }
        return jsonArray;
    }


}
