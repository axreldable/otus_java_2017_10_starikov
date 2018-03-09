package ru.otus.hw15;

import ru.otus.hw15.data.UserDataSet;
import ru.otus.hw15.messageSystem.Addressee;

public interface CacheService extends Addressee {
    void init();

    CacheEngine<Long, UserDataSet> getCache();
}
