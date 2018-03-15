package ru.otus.hw15.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.server.standard.SpringConfigurator;
import ru.otus.hw15.app.FrontendService;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

@ServerEndpoint(value = "/cache_web_socket", configurator = SpringConfigurator.class)
@Controller
public class WebSocket {

    private static final Logger logger = Logger.getLogger(WebSocket.class.getName());

    private Session session;

    @Autowired
    FrontendService frontendService;

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
        this.session = session;
        this.frontendService.getCacheReport();
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
