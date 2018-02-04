package ru.orus.hw12.servlet;

import ru.orus.hw12.html.page.create.HtmlCreator;
import ru.otus.hw13.CacheEngine;
import ru.otus.hw13.data.UserDataSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.orus.hw12.constants.Constants.CACHE_PAGE;

public class CacheServlet extends Servlet {
    private final CacheEngine<Long, UserDataSet> cache;

    public CacheServlet(CacheEngine<Long, UserDataSet> cache) {
        this.cache = cache;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Map<String, Object> cacheParams = new HashMap<>();
        cacheParams.put("hit", cache.getHitCount());
        cacheParams.put("miss", cache.getMissCount());
        cacheParams.put("size", cache.getSize());

        response.getWriter().println(HtmlCreator.instance().create(CACHE_PAGE, cacheParams));

        setOK(response);
    }
}
