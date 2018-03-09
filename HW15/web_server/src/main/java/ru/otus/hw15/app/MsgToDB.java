package ru.otus.hw15.app;

import ru.otus.hw15.CacheService;
import ru.otus.hw15.messageSystem.Address;
import ru.otus.hw15.messageSystem.Addressee;
import ru.otus.hw15.messageSystem.Message;

public abstract class MsgToDB extends Message {
    public MsgToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof CacheService) {
            exec((CacheService) addressee);
        }
    }

    public abstract void exec(CacheService dbService);
}
