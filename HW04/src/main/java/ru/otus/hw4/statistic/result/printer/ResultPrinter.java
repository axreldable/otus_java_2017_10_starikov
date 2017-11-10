package ru.otus.hw4.statistic.result.printer;

import ru.otus.hw4.statistic.result.model.ResultObject;

public class ResultPrinter implements Printer {
    private ResultObject resultObject;

    public ResultPrinter(ResultObject resultObject) {
        this.resultObject = resultObject;
    }

    @Override
    public void print() {
        System.out.println(

        );
    }
}
