package ru.otus.hw02.occupier;

import ru.otus.hw02.model.MyObject;

import java.util.ArrayList;
import java.util.List;

import static ru.otus.hw02.constants.CalcConstants.BIG_NUMBER;
import static ru.otus.hw02.constants.CalcConstants.NOT_SO_BIG_NUMBER;

public class MemoryOccupier {
    public static void occupyEmptyString() {
        String[] array = new String[BIG_NUMBER];
        for (int i = 0; i < BIG_NUMBER; i++) {
            array[i] = new String(new char[0]);
        }
    }

    public static void occupyEmptyStringFromStringPool() {
        String[] array = new String[BIG_NUMBER];
        for (int i = 0; i < BIG_NUMBER; i++) {
            array[i] = "";
        }
    }

    public static void occupyObject() {
        Object[] array = new Object[BIG_NUMBER];
        for (int i = 0; i < BIG_NUMBER; i++) {
            array[i] = new Object();
        }
    }

    public static void occupyMyObject() {
        MyObject[] array = new MyObject[BIG_NUMBER];
        for (int i = 0; i < BIG_NUMBER; i++) {
            array[i] = new MyObject();
        }
    }

    public static void occupyMemoryByCollection(int length) {
        int randomNumber = 255;
        List<List<Integer>> arrayList = new ArrayList<>(NOT_SO_BIG_NUMBER);
        for (int i = 0; i < NOT_SO_BIG_NUMBER; i++) {
            List<Integer> collection = new ArrayList<>();
            for (int j = 0; j < length; j++) {
                collection.add(randomNumber);
            }
            arrayList.add(collection);
        }
    }
}
