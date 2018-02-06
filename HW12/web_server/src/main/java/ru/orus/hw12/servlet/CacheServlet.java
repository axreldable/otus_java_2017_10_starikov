package ru.orus.hw12.servlet;

import ru.orus.hw12.html.page.create.HtmlCreator;
import ru.otus.hw12.CacheEngine;
import ru.otus.hw12.data.UserDataSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.orus.hw12.constants.Constants.*;

public class CacheServlet extends Servlet {
    private final CacheEngine<Long, UserDataSet> cache;

    public CacheServlet(CacheEngine<Long, UserDataSet> cache) {
        this.cache = cache;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        if (isLogin(request)) {
            Map<String, Object> cacheParams = new HashMap<>();
            cacheParams.put("hit", cache.getHitCount());
            cacheParams.put("miss", cache.getMissCount());
            cacheParams.put("size", cache.getSize());

            response.getWriter().println(HtmlCreator.instance().create(CACHE_PAGE, cacheParams));
            setOK(response);
        } else {
            response.sendRedirect("http://localhost:" + PORT + "/" + LOGIN);
        }
    }

    private boolean isLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if( cookies != null ) {
            for (Cookie cookie : cookies) {
                if (LOGIN_FOR_CACHE.equals(cookie.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
