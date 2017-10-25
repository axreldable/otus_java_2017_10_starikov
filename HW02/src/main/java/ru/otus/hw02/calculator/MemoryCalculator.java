package ru.otus.hw02.calculator;

import ru.otus.hw02.constants.MeasuredType;
import ru.otus.hw02.occupier.*;
import ru.otus.hw02.printer.MemoryResultPrinter;

import static ru.otus.hw02.constants.CalcConstants.BIG_NUMBER;

public class MemoryCalculator implements Calculator {
    private Runtime runtime = Runtime.getRuntime();

    @Override
    public void calculate(MeasuredType type) {
        System.gc();
        long startMem = getMemory();

        occupyMemory(type);

        long endMem = getMemory();
        System.gc();

        long occupyMem = (endMem - startMem) / BIG_NUMBER;

        MemoryResultPrinter.builder()
                .setType(type)
                .setStartMemory(startMem)
                .setEndMemory(endMem)
                .setOccupyMemory(occupyMem)
                .build()
                .print();
    }

    private void occupyMemory(MeasuredType type) {
        switch (type) {
            case EMPTY_STRING:
                new EmptyStringOccupier().occupy();
                break;
            case STRING_POOL_EMPTY_STRING:
                new EmptyStringFromStringPoolOccupier().occupy();
                break;
            case OBJECT:
                new ObjectOccupier().occupy();
                break;
            case MY_OBJECT:
                new MyObjectOccupier().occupy();
                break;
        }
    }

    private long getMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
