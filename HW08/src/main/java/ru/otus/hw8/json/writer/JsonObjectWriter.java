package ru.otus.hw8.json.writer;

import org.json.simple.JSONObject;
import ru.otus.hw8.json.creator.JsonObjectCreator;

/**
 * JSON serializer.
 * Can serialize primitives, different objects, arrays of primitives and different objects.
 * And also standard collections.
 */
public class JsonObjectWriter {
    public String toJsonString(Object object) {
        Object jsonObject = new JsonObjectCreator().create(object);
        return ((JSONObject) jsonObject).toJSONString();
    }
}
