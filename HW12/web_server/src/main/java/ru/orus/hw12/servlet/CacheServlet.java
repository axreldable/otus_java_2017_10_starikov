package ru.orus.hw12.servlet;

import ru.orus.hw12.html.page.create.HtmlCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.orus.hw12.constants.Constants.CACHE_PAGE;

public class CacheServlet extends Servlet {
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Map<String, Object> cacheParams = new HashMap<>();
        cacheParams.put("hit", "0");
        cacheParams.put("miss", "0");
        cacheParams.put("size", 0);

        response.getWriter().println(HtmlCreator.instance().create(CACHE_PAGE, cacheParams));

        setOK(response);
    }
}
