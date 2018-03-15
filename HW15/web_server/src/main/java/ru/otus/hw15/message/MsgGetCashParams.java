package ru.otus.hw15.message;

import ru.otus.hw15.CacheEngine;
import ru.otus.hw15.CacheService;
import ru.otus.hw15.app.MsgToDB;
import ru.otus.hw15.data.UserDataSet;
import ru.otus.hw15.messageSystem.Address;
import ru.otus.hw15.model.CacheParams;

import java.util.logging.Logger;

public class MsgGetCashParams extends MsgToDB {
    private final static Logger logger = Logger.getLogger(MsgGetCashParams.class.getName());

    public MsgGetCashParams(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(CacheService cacheService) {
        logger.info("in exec message in MsgGetCashParams");
        CacheEngine<Long, UserDataSet> cache = cacheService.getCache();
        CacheParams cacheParams = CacheParams.builder()
                .hint(cache.getHitCount())
                .miss(cache.getMissCount())
                .size(cache.getSize())
                .build();
        logger.info("cache params are: " + cacheParams);
        logger.info("getTo " + getTo());
        logger.info("getFrom " + getFrom());
        cacheService.getMS().sendMessage(new MsgCacheParamsAnswer(getTo(), getFrom(), cacheParams));
    }
}
