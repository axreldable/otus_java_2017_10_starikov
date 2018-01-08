package ru.otus.hw8;

import com.google.gson.Gson;
import org.junit.Test;
import ru.otus.hw8.json.writer.JsonObjectWriter;
import ru.otus.hw8.objects.complex.ComplexObject;
import ru.otus.hw8.objects.simple.SimpleObject;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void testComplexObject() {
        testObject(new ComplexObject());
    }

    @Test
    public void testSimpleObject() {
        testObject(new SimpleObject());
    }

    private <T> void testObject(T object) {
        String resultString = new JsonObjectWriter().toJsonString(object);
//        System.out.println("my writer: + \n" + resultString);
//        System.out.println("Gson writer: + \n" + new Gson().toJson(object));
        T testObject = new Gson().fromJson(resultString, (Class<T>) object.getClass());
        assertEquals(testObject, object);
    }
}