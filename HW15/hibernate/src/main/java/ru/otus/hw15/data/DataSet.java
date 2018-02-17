package ru.otus.hw15.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode
// Designates a class whose mapping information is applied to the entities that inherit from it.
// A mapped superclass has no separate table defined for it.
@MappedSuperclass
abstract public class DataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indicates that the persistence provider must assign primary keys for the entity using a database identity column.
    @Getter @Setter private Long id;
}
