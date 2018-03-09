package ru.otus.hw15.message;

import ru.otus.hw15.app.FrontendService;
import ru.otus.hw15.app.MsgToFrontend;
import ru.otus.hw15.messageSystem.Address;
import ru.otus.hw15.model.CacheParams;

public class MsgCacheParamsAnswer extends MsgToFrontend {
    private final CacheParams cacheParams;

    public MsgCacheParamsAnswer(Address from, Address to, CacheParams cacheParams) {
        super(from, to);
        this.cacheParams = cacheParams;
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.setCacheReport(cacheParams);
    }
}
