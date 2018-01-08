package ru.otus.hw8.json.writer;

import ru.otus.hw8.json.creator.JsonObjectCreator;
import ru.otus.hw8.json.my.simple.json.MyJsonAware;

/**
 * JSON serializer.
 * Can serialize primitives, different objects, arrays of primitives and different objects.
 * And also standard collections.
 */
public class JsonObjectWriter {
    public String toJsonString(Object object) {
        Object jsonObject = new JsonObjectCreator().create(object);
        return ((MyJsonAware) jsonObject).toJsonString();
    }
}
