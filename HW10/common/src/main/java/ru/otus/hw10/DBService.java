package ru.otus.hw10;

import ru.otus.hw10.data.DataSet;

public interface DBService {
    <T extends DataSet> void save(T dataSetObject);

    <T extends DataSet> T load(long id, Class<T> clazz);

    void shutdown();
}
