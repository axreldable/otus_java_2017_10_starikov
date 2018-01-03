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
        String expectedString = "{\"array\":[1,2,3],\"b\":true,\"number\":123,\"inner\":{\"a\":\"b\",\"c\":\"d\",\"e\":\"f\"},\"string\":\"Hello World\"}";
        String resultString = new JsonObjectWriter().toJsonString(object);
//        String resultString = new Gson().toJson(object);
        assertEquals(expectedString, resultString);

        JsonEditorOnlineObject testObject = new Gson().fromJson(resultString, JsonEditorOnlineObject.class);
        assertEquals(testObject, object);
    }
}