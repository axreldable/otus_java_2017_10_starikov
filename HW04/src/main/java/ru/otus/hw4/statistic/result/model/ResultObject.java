package ru.otus.hw4.statistic.result.model;

import lombok.Setter;

public class ResultObject {
    private String logFileName;
    @Setter private String youngGcName;
    @Setter private String oldGcName;
    private int youngGcCounter;
    private int oldGcCounter;
    private int youngGcDuration;
    private int oldGcDuration;

    public ResultObject(String logFileName) {
        this.logFileName = logFileName;
    }

    public void incrementYoungGcCounter() {
        youngGcCounter++;
    }

    public void incrementOldGcCounter() {
        oldGcCounter++;
    }

    public void increaseYoungGcDuration(int duration) {
        youngGcDuration += duration;
    }

    public void increaseOldGcDuration(int duration) {
        oldGcDuration += duration;
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "logFileName='" + logFileName + '\'' +
                ", youngGcName='" + youngGcName + '\'' +
                ", oldGcName='" + oldGcName + '\'' +
                ", youngGcCounter=" + youngGcCounter +
                ", oldGcCounter=" + oldGcCounter +
                ", youngGcDuration=" + youngGcDuration +
                ", oldGcDuration=" + oldGcDuration +
                '}';
    }
}
