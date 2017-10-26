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

        getOccupier(type).occupy();

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

    private Occupier getOccupier(MeasuredType type) {
        switch (type) {
            case EMPTY_STRING:
                return new EmptyStringOccupier();
            case STRING_POOL_EMPTY_STRING:
                return new EmptyStringFromStringPoolOccupier();
            case OBJECT:
                return new ObjectOccupier();
            case MY_OBJECT:
                return new MyObjectOccupier();
        }
        throw new UnsupportedOperationException("No such occupier");
    }

    private long getMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
