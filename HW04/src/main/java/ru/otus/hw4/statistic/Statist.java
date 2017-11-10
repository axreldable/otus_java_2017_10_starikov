package ru.otus.hw4.statistic;

import ru.otus.hw4.statistic.mapper.LogMapper;
import ru.otus.hw4.statistic.result.model.ResultObject;

public class Statist {
    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            ResultObject resultObject = new LogMapper("gc_0" + i + ".log").map();
            System.out.println(resultObject);
        }
    }
}
