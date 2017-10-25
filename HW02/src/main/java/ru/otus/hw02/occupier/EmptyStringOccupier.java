package ru.otus.hw02.occupier;

import static ru.otus.hw02.constants.CalcConstants.BIG_NUMBER;

public class EmptyStringOccupier implements Occupier {
    @Override
    public void occupy() {
        String[] array = new String[BIG_NUMBER];
        for (int i = 0; i < BIG_NUMBER; i++) {
            array[i] = new String(new char[0]);
        }
    }
}
