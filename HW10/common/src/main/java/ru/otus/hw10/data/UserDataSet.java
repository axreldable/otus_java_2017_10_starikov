package ru.otus.hw10.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public class UserDataSet extends DataSet {
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @Getter private AddressDataSet address;

    public UserDataSet() {
    }

    public UserDataSet(String name, int age, AddressDataSet address) {
        this.setId(-1L);
        this.name = name;
        this.age = age;
        this.address = address;
    }
}