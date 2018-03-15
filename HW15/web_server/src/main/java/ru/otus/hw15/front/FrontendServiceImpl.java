package ru.otus.hw15.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.hw15.app.FrontendService;
import ru.otus.hw15.message.MsgGetCashParams;
import ru.otus.hw15.messageSystem.Address;
import ru.otus.hw15.messageSystem.Message;
import ru.otus.hw15.messageSystem.MessageSystem;
import ru.otus.hw15.messageSystem.MessageSystemContext;
import ru.otus.hw15.model.CacheParams;
import ru.otus.hw15.ws.WebSocket;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Component
public class FrontendServiceImpl implements FrontendService {
    private final static Logger logger = Logger.getLogger(FrontendServiceImpl.class.getName());

    @Autowired
    private Address frontAddress;
    @Autowired
    private WebSocket socket;

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
    public void sendCache(CacheParams cacheParams) {
        logger.info("in send cache report, cacheParams: " + cacheParams);
        socket.sendMessage(cacheParams.toString());
    }
}
