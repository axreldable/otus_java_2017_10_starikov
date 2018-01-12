package ru.otus.hw9.executor.data.set;

import lombok.EqualsAndHashCode;
import ru.otus.hw9.data.DataSet;

@EqualsAndHashCode(callSuper = true)
public class UserDataSet extends DataSet {
    private String name;
    private int age;

    public UserDataSet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public UserDataSet() {
    }
}