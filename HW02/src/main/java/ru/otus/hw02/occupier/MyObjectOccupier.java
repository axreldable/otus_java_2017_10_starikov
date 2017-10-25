package ru.otus.hw02.occupier;

import ru.otus.hw02.model.MyObject;

import static ru.otus.hw02.constants.CalcConstants.BIG_NUMBER;

public class MyObjectOccupier implements Occupier {
    @Override
    public void occupy() {
        MyObject[] array = new MyObject[BIG_NUMBER];
        for (int i = 0; i < BIG_NUMBER; i++) {
            array[i] = new MyObject();
        }
    }
}
