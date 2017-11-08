package ru.otus.hw4.logger.info;

public enum Generation {
    YOUNG("end of minor GC"),
    OLD("end of major GC");

    private String logString;

    Generation(String logString) {
        this.logString = logString;
    }

    public static Generation parseGeneration(String logString) {
        if(YOUNG.logString.equals(logString)) { return YOUNG; }
        if(OLD.logString.equals(logString)) { return OLD; }
        throw new UnsupportedOperationException("No such GC generation: " + logString);
    }
}
