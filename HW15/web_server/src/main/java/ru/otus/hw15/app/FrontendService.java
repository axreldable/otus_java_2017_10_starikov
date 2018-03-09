package ru.otus.hw15.app;

import ru.otus.hw15.messageSystem.Addressee;
import ru.otus.hw15.model.CacheParams;

public interface FrontendService extends Addressee {
    void init();
    void getCacheReport();
    void setCacheReport(CacheParams cacheParams);
}

