package ru.otus.hw8.objects.complex;

import lombok.EqualsAndHashCode;
import ru.otus.hw8.objects.simple.SimpleObject;

@EqualsAndHashCode
public class ComplexObject {
    SimpleObject[] objectsArray = {new SimpleObject(), new SimpleObject()};
    private SimpleObject simpleObject = new SimpleObject();
    private int[] intPrimitiveArray = {1, 2, 3};
    private boolean[] boolPrimitiveArray = {true, false, true};
    private double[] doublePrimitiveArray = {1.0, 2.0, 3.0};
}
