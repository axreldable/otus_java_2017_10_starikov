package ru.otus.hw15.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.hw15.constants.Constants;
import ru.otus.hw15.front.FrontendServiceImpl;
import ru.otus.hw15.html.page.create.HtmlCreator;
import ru.otus.hw15.model.CacheParams;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Configurable
@Component
public class CacheServlet extends Servlet {
    private final static Logger logger = Logger.getLogger(CacheServlet.class.getName());
    private CacheParams cacheParams = new CacheParams(0, 0, 0);

    public void setCacheParams(CacheParams cacheParams) {
        logger.info("in setCacheParams, cacheParams: " + cacheParams);
        this.cacheParams = cacheParams;
        logger.info("in setCacheParams, cacheParams after set: " + this.cacheParams);
    }

    @Autowired
    private FrontendServiceImpl frontendService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        logger.info("Create " + frontendService);
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {


        if (isLogin(request)) {
            frontendService.getCacheReport();
            logger.info("exec frontendService.getCacheReport()");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            logger.info("in isLogin. cacheParams: " + cacheParams);

            Map<String, Object> cacheParamsMap = new HashMap<>();
            cacheParamsMap.put("hit", cacheParams.getHint());
            cacheParamsMap.put("miss", cacheParams.getMiss());
            cacheParamsMap.put("size", cacheParams.getSize());

            logger.info("cacheParamsMap: " + cacheParamsMap);
            response.getWriter().println(HtmlCreator.instance().create(Constants.CACHE_PAGE, cacheParamsMap));
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
}
