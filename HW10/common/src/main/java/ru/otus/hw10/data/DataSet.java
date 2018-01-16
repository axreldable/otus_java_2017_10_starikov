package ru.otus.hw10.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
abstract public class DataSet {
    @Getter @Setter private Long id;
}
