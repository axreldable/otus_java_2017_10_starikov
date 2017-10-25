package ru.otus.hw02.calculator.container;

import ru.otus.hw02.occupier.container.ContainerMemoryOccupier;

import static ru.otus.hw02.constants.CalcConstants.NOT_SO_BIG_NUMBER;

public class ContainerMemoryCalculator implements ContainerCalculator {
    private Runtime runtime = Runtime.getRuntime();

    @Override
    public long calculate(int length) {
        System.gc();
        long startMem = getMemory();

        new ContainerMemoryOccupier().occupy(length);

        long endMem = getMemory();
        System.gc();

        return (endMem - startMem) / NOT_SO_BIG_NUMBER;
    }

    private long getMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
