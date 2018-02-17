package ru.otus.hw15.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.hw15.CacheEngine;
import ru.otus.hw15.HibernateCacheService;
import ru.otus.hw15.data.AddressDataSet;
import ru.otus.hw15.data.UserDataSet;
import ru.otus.hw15.html.page.create.HtmlCreator;
import ru.otus.hw15.constants.Constants;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configurable
public class CacheServlet extends Servlet {
    private CacheEngine<Long, UserDataSet> cache;

    @Autowired
    private HibernateCacheService cacheService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        startDbServiceWork();
        cache = cacheService.getCache();
        System.out.println();
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        if (isLogin(request)) {
            Map<String, Object> cacheParams = new HashMap<>();
            cacheParams.put("hit", cache.getHitCount());
            cacheParams.put("miss", cache.getMissCount());
            cacheParams.put("size", cache.getSize());

            response.getWriter().println(HtmlCreator.instance().create(Constants.CACHE_PAGE, cacheParams));
            setOK(response);
        } else {
            response.sendRedirect("/" + Constants.LOGIN);
        }
    }

    private boolean isLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if( cookies != null ) {
            for (Cookie cookie : cookies) {
                if (Constants.LOGIN_FOR_CACHE.equals(cookie.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void startDbServiceWork() {
        new Thread(() -> {
            try {
                while (true) {
                    cacheService.save(new UserDataSet("name1", 26, new AddressDataSet("some street")));
                    cacheService.load(1);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
