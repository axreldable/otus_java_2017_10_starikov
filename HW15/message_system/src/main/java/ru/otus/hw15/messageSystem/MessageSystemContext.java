package ru.otus.hw15.messageSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageSystemContext {
    @Autowired
    private final MessageSystem messageSystem;

    @Autowired
    private Address frontAddress;

    @Autowired
    private Address dbAddress;

    @PostConstruct
    public void start() {
        new Thread(()->{
            try {
                Thread.sleep(3000);
                messageSystem.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public MessageSystemContext(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public Address getFrontAddress() {
        return frontAddress;
    }

    public void setFrontAddress(Address frontAddress) {
        this.frontAddress = frontAddress;
    }

    public Address getDbAddress() {
        return dbAddress;
    }

    public void setDbAddress(Address dbAddress) {
        this.dbAddress = dbAddress;
    }
}
