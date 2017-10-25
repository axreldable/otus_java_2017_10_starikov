package ru.otus.hw02.occupier;

import static ru.otus.hw02.constants.CalcConstants.BIG_NUMBER;

public class ObjectOccupier implements Occupier {
    @Override
    public void occupy() {
        Object[] array = new Object[BIG_NUMBER];
        for (int i = 0; i < BIG_NUMBER; i++) {
            array[i] = new Object();
        }
    }
}
