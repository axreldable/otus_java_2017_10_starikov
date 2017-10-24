package ru.otus.hw02.printer;

import java.util.List;

public class ContainerMemRezPrinter implements Printer {
    private List<Integer> elementsNumber;
    private List<Long> occupyMemory;

    public ContainerMemRezPrinter(List<Integer> elementsNumber, List<Long> occupyMemory) {
        this.elementsNumber = elementsNumber;
        this.occupyMemory = occupyMemory;
    }

    @Override
    public void print() {
        System.out.println("Dependency 'elements in container - occupied memory'");
        System.out.println("Number of elements - occupied memory:");
        for (int i = 0; i < elementsNumber.size(); i++) {
            System.out.println(elementsNumber.get(i) + " - " + occupyMemory.get(i));
        }
    }
}
