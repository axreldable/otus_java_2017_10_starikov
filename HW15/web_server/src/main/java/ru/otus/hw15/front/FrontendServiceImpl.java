package ru.otus.hw15.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.hw15.app.FrontendService;
import ru.otus.hw15.message.MsgGetCashParams;
import ru.otus.hw15.messageSystem.*;
import ru.otus.hw15.model.CacheParams;
import ru.otus.hw15.servlet.CacheServlet;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Component
public class FrontendServiceImpl implements FrontendService {
    private final static Logger logger = Logger.getLogger(FrontendServiceImpl.class.getName());

    @Autowired
    private Address frontAddress;
    @Autowired
    private CacheServlet cacheServlet;


    private final MessageSystemContext context;

    public FrontendServiceImpl(MessageSystemContext context) {
        this.context = context;
    }

    @PostConstruct
    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public Address getAddress() {
        return frontAddress;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }

    @Override
    public void getCacheReport() {
        Message message = new MsgGetCashParams(getAddress(), context.getDbAddress());
        context.getMessageSystem().sendMessage(message);
        logger.info("send message from " + message.getFrom() + " to " + message.getTo());
    }

    @Override
    public void setCacheReport(CacheParams cacheParams) {
        logger.info("in send cache report, cacheParams: " + cacheParams);
        cacheServlet.setCacheParams(cacheParams);
    }
}
