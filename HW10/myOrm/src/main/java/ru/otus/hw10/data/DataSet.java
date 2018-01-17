package ru.otus.hw10.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Base class for all classes with which Executor work.
 *
 * @see ru.otus.hw10.executor.Executor
 */
@EqualsAndHashCode
abstract public class DataSet {
    @Getter @Setter private Long id;
}
