package ru.otus.hw15.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.server.standard.SpringConfigurator;
import ru.otus.hw15.app.FrontendService;
import ru.otus.hw15.message.MsgGetCashParams;
import ru.otus.hw15.messageSystem.Address;
import ru.otus.hw15.messageSystem.Message;
import ru.otus.hw15.messageSystem.MessageSystem;
import ru.otus.hw15.messageSystem.MessageSystemContext;
import ru.otus.hw15.model.CacheParams;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

@Component
@ServerEndpoint(value = "/cache_web_socket", configurator = SpringConfigurator.class)
@Controller
public class FrontendServiceImpl implements FrontendService {
    private final static Logger logger = Logger.getLogger(FrontendServiceImpl.class.getName());

    @Autowired
    private Address frontAddress;

    private Session session;

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
        sendMessage(cacheParams.toString());
    }

    @OnOpen
    public void open(Session session) {
        logger.info("open");
    }

    @OnClose
    public void closedConnection(Session session) {
        logger.info("close");
    }

    @OnError
    public void error(Session session, Throwable t) {
        logger.info("open");
    }


    @OnMessage
    public void onMessage(Session session, String msg) {
        logger.info("in webSocket, sessionId = " + session.getId());
        this.session = session;
        getCacheReport();
    }

    public void sendMessage(String cacheParams) {
        try {
            for (Session sess : session.getOpenSessions()) {
                if (sess.isOpen())
                    sess.getBasicRemote().sendText(cacheParams);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }
}
