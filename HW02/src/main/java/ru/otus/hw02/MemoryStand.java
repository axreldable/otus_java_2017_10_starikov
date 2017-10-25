package ru.otus.hw02;

import ru.otus.hw02.calculator.container.ContainerMemoryCalculator;
import ru.otus.hw02.calculator.MemoryCalculator;
import ru.otus.hw02.printer.ContainerMemRezPrinter;

import java.util.ArrayList;
import java.util.List;

import static ru.otus.hw02.constants.MeasuredType.*;

// mvn clean package
// java -jar ./target/HW02.jar
public class MemoryStand {
    public static void main(String[] args) {
        MemoryCalculator calculator = new MemoryCalculator();
        calculator.calculate(EMPTY_STRING);
        calculator.calculate(STRING_POOL_EMPTY_STRING);
        calculator.calculate(OBJECT);
        calculator.calculate(MY_OBJECT);

        ContainerMemoryCalculator containerMemoryCalculator = new ContainerMemoryCalculator();
        List<Integer> elementsNumber = new ArrayList<>();
        List<Long> occupyMemory = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            elementsNumber.add(i);
            occupyMemory.add(containerMemoryCalculator.calculate(i));
        }
        ContainerMemRezPrinter printer = new ContainerMemRezPrinter(elementsNumber, occupyMemory);
        printer.print();
    }


}
