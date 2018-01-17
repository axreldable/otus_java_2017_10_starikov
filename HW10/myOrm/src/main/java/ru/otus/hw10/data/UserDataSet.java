package ru.otus.hw10.data;

import lombok.EqualsAndHashCode;

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