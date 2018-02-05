package ru.orus.hw12;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.orus.hw12.servlet.CacheServlet;
import ru.orus.hw12.servlet.LoginServlet;
import ru.otus.hw12.HibernateCacheService;
import ru.otus.hw12.data.AddressDataSet;
import ru.otus.hw12.data.UserDataSet;

import static ru.orus.hw12.constants.Constants.PORT;
import static ru.orus.hw12.constants.Constants.WEB_RESOURCES;

public class Main {
    public static void main(String[] args) throws Exception {
        ResourceHandler resourceHandler = configResourceHandler();
        HibernateCacheService dbService = startDbServiceWork();
        ServletContextHandler contextHandler = configContextHandler(dbService);

        Server server = configServer(resourceHandler, contextHandler);

        run(server);
    }

    private static ResourceHandler configResourceHandler() {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(WEB_RESOURCES);
        return resourceHandler;
    }

    private static ServletContextHandler configContextHandler(HibernateCacheService dbService) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(LoginServlet.class, "/login");
        context.addServlet(new ServletHolder(new CacheServlet(dbService.getCache())), "/cache");
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

    private static HibernateCacheService startDbServiceWork() {
        HibernateCacheService dbService = new HibernateCacheService();

        new Thread(() -> {
            try {
                while (true) {
                    dbService.save(new UserDataSet("name1", 26, new AddressDataSet("some street")));
                    dbService.load(1);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return dbService;
    }
}
