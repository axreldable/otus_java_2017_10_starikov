package ru.otus.hw4.logger.printer;

public class GcPrinter {
    private String generation;
    private String gcName;
    private String duration;

    public static GcPrinterBuilder builder() {
        return new GcPrinterBuilder();
    }

    public static class GcPrinterBuilder {
        private String generation;
        private String gcName;
        private String duration;

        public GcPrinterBuilder setGeneration(String generation) {
            this.generation = generation;
            return this;
        }

        public GcPrinterBuilder setGcName(String gcName) {
            this.gcName = gcName;
            return this;
        }

        public GcPrinterBuilder setDuration(String duration) {
            this.duration = duration;
            return this;
        }

        public GcPrinter build() {
            GcPrinter printer = new GcPrinter();
            printer.generation = generation;
            printer.gcName = gcName;
            printer.duration = duration;
            return printer;
        }
    }
}
