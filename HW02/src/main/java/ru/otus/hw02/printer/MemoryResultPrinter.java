package ru.otus.hw02.printer;

import ru.otus.hw02.constants.MeasuredType;

public class MemoryResultPrinter implements Printer {
    private Long startMemory;
    private Long endMemory;
    private Long occupyMemory;
    private MeasuredType type;

    public static MemoryResultPrinterBuilder builder() {
        return new MemoryResultPrinterBuilder();
    }

    public static class MemoryResultPrinterBuilder {
        private Long startMemory;
        private Long endMemory;
        private Long occupyMemory;
        private MeasuredType type;

        public MemoryResultPrinterBuilder setStartMemory(long startMemory) {
            this.startMemory = startMemory;
            return this;
        }

        public MemoryResultPrinterBuilder setEndMemory(long endMemory) {
            this.endMemory = endMemory;
            return this;
        }

        public MemoryResultPrinterBuilder setOccupyMemory(Long occupyMemory) {
            this.occupyMemory = occupyMemory;
            return this;
        }

        public MemoryResultPrinterBuilder setType(MeasuredType type) {
            this.type = type;
            return this;
        }

        public MemoryResultPrinter build() {
            MemoryResultPrinter printer = new MemoryResultPrinter();
            printer.type = type;
            printer.endMemory = endMemory;
            printer.startMemory = startMemory;
            printer.occupyMemory = occupyMemory;
            return printer;
        }
    }

    @Override
    public void print() {
        StringBuilder answer = new StringBuilder("Result of memory calculation:\n");
        answer.append(type == null ? "" : "measured type = " + type + "\n");
        answer.append(startMemory == null ? "" : "start memory = " + startMemory + "\n");
        answer.append(endMemory == null ? "" : "end memory = " + endMemory + "\n");
        answer.append(occupyMemory == null ? "" : "occupied memory = " + occupyMemory + "\n");

        System.out.println(answer);
    }
}
