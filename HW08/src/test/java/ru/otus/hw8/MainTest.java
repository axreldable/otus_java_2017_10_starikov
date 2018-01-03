package ru.otus.hw8;

import com.google.gson.Gson;
import org.junit.Test;
import ru.otus.hw8.json.writer.JsonObjectWriter;
import ru.otus.hw8.objects.JsonEditorOnlineObject;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void test() {
        JsonEditorOnlineObject object = new JsonEditorOnlineObject();
        String expectedString = "{\n" +
                "  \"array\": [\n" +
                "    1,\n" +
                "    2,\n" +
                "    3\n" +
                "  ],\n" +
                "  \"b\": true,\n" +
                "  \"number\": 123,\n" +
                "  \"inner\": {\n" +
                "    \"a\": \"b\",\n" +
                "    \"c\": \"d\",\n" +
                "    \"e\": \"f\"\n" +
                "  },\n" +
                "  \"string\": \"Hello World\"\n" +
                "}\n";
        String resultString = new JsonObjectWriter().toJsonString(object);
        assertEquals(expectedString, resultString);

        JsonEditorOnlineObject testObject = new Gson().fromJson(resultString, JsonEditorOnlineObject.class);
        assertEquals(testObject, object);
    }
}