package ru.otus.hw13.servlet;

import ru.otus.hw13.html.page.create.HtmlCreator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.otus.hw13.constants.Constants.*;

public class LoginServlet extends Servlet {
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        response.getWriter().println(HtmlCreator.instance().create(LOGIN_PAGE, setMessageWrong("")));
        setOK(response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String login = request.getParameter(LOGIN_VARIABLE_NAME);
        String password = request.getParameter(PASSWORD_VARIABLE_NAME);


        if (isLoginSuccess(login, password)) {
            markTheUserAsLoginIn(response);
            response.sendRedirect("http://localhost:" + PORT + "/" + CACHE);
        }
        else {
            markTheUserAsLoginOut(request, response);
            response.getWriter().println(HtmlCreator.instance().create(LOGIN_PAGE, setMessageWrong("Wrong login or password!")));
            setOK(response);
        }
    }

    private boolean isLoginSuccess(String login, String password) {
        return LOGIN_PASSWORD.equals(login) && LOGIN_PASSWORD.equals(password);
    }

    private void markTheUserAsLoginOut(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if( cookies != null ) {
            for (Cookie cookie : cookies) {
                if (LOGIN_FOR_CACHE.equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    private void markTheUserAsLoginIn(HttpServletResponse response) {
        Cookie mark = new Cookie(LOGIN_FOR_CACHE, "WAS_LOGIN");
        response.addCookie(mark);
    }

    private Map<String,Object> setMessageWrong(String message) {
        Map<String, Object> data = new HashMap<>();
        data.put("messageWrong", message);
        return data;
    }
}
