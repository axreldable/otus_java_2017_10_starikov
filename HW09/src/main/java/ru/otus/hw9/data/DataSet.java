package ru.otus.hw9.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Base class for all classes with which Executor work.
 *
 * @see ru.otus.hw9.executor.Executor
 */
@EqualsAndHashCode
abstract public class DataSet {
    @Getter @Setter private Long id;
}
