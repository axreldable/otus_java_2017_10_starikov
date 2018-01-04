package ru.otus.hw8.objects.complex;

import lombok.EqualsAndHashCode;
import ru.otus.hw8.objects.simple.SimpleObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode
public class ComplexObject {
    SimpleObject[] objectsArray = {new SimpleObject(), new SimpleObject()};
    private SimpleObject simpleObject = new SimpleObject();
    private int[] intPrimitiveArray = {1, 2, 3};
    private boolean[] boolPrimitiveArray = {true, false, true};
    private double[] doublePrimitiveArray = {1.0, 2.0, 3.0};
    List<SimpleObject> list = new ArrayList<SimpleObject>() {
        {
            add(new SimpleObject());
            add(new SimpleObject());
        }
    };
    Set<SimpleObject> set = new HashSet<SimpleObject>() {
        {
            add(new SimpleObject());
            add(new SimpleObject());
        }
    };
}
