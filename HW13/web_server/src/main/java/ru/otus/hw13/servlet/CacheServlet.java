package ru.otus.hw13.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw13.data.AddressDataSet;
import ru.otus.hw13.html.page.create.HtmlCreator;
import ru.otus.hw13.CacheEngine;
import ru.otus.hw13.HibernateCacheService;
import ru.otus.hw13.data.UserDataSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.otus.hw13.constants.Constants.CACHE_PAGE;

public class CacheServlet extends Servlet {
    private CacheEngine<Long, UserDataSet> cache;
    private HibernateCacheService service;

    @Override
    public void init() {
        //TODO: Create one context for the application. Inject beans.
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
        service = (HibernateCacheService) context.getBean("cacheService");
        cache = service.getCache();
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        workWithService();

        Map<String, Object> cacheParams = new HashMap<>();
        cacheParams.put("hit", cache.getHitCount());
        cacheParams.put("miss", cache.getMissCount());
        cacheParams.put("size", cache.getSize());

        response.getWriter().println(HtmlCreator.instance().create(CACHE_PAGE, cacheParams));

        setOK(response);
    }

    private void workWithService() {
        for (int i = 0; i < 100; i++) {
            service.save(new UserDataSet("name1", 26, new AddressDataSet("some street")));
            service.load(1);
        }
    }
}
