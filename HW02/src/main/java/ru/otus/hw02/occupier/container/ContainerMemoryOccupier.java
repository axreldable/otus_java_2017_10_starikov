package ru.otus.hw02.occupier.container;

import java.util.ArrayList;
import java.util.List;

import static ru.otus.hw02.constants.CalcConstants.NOT_SO_BIG_NUMBER;

public class ContainerMemoryOccupier implements ContainerOccupier {
    @Override
    public void occupy(int length) {
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
