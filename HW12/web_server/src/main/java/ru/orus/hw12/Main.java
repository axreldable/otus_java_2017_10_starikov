package ru.orus.hw12;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.orus.hw12.servlet.CacheServlet;
import ru.orus.hw12.servlet.LoginServlet;

import static ru.orus.hw12.constants.Constants.PORT;
import static ru.orus.hw12.constants.Constants.WEB_RESOURCES;

public class Main {
    public static void main(String[] args) throws Exception {
        ResourceHandler resourceHandler = configResourceHandler();
        ServletContextHandler contextHandler = configContextHandler();

        Server server = configServer(resourceHandler, contextHandler);

        run(server);
    }

    private static ResourceHandler configResourceHandler() {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(WEB_RESOURCES);
        return resourceHandler;
    }

    private static ServletContextHandler configContextHandler() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(LoginServlet.class, "/login");
        context.addServlet(CacheServlet.class, "/cache");
        return context;
    }

    private static Server configServer(ResourceHandler resourceHandler, ServletContextHandler contextHandler) {
        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, contextHandler));
        return server;
    }

    private static void run(Server server) throws Exception {
        server.start();
        server.join();
    }
}
