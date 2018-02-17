package ru.otus.hw15.servlet;

import ru.otus.hw15.html.page.create.HtmlCreator;
import ru.otus.hw15.constants.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends Servlet {
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        response.getWriter().println(HtmlCreator.instance().create(Constants.LOGIN_PAGE, setMessageWrong("")));
        setOK(response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String login = request.getParameter(Constants.LOGIN_VARIABLE_NAME);
        String password = request.getParameter(Constants.PASSWORD_VARIABLE_NAME);


        if (isLoginSuccess(login, password)) {
            markTheUserAsLoginIn(response);
            response.sendRedirect("/" + Constants.CACHE);
        }
        else {
            markTheUserAsLoginOut(request, response);
            response.getWriter().println(HtmlCreator.instance().create(Constants.LOGIN_PAGE, setMessageWrong("Wrong login or password!")));
            setOK(response);
        }
    }

    private boolean isLoginSuccess(String login, String password) {
        return Constants.LOGIN_PASSWORD.equals(login) && Constants.LOGIN_PASSWORD.equals(password);
    }

    private void markTheUserAsLoginOut(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if( cookies != null ) {
            for (Cookie cookie : cookies) {
                if (Constants.LOGIN_FOR_CACHE.equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    private void markTheUserAsLoginIn(HttpServletResponse response) {
        Cookie mark = new Cookie(Constants.LOGIN_FOR_CACHE, "WAS_LOGIN");
        response.addCookie(mark);
    }

    private Map<String,Object> setMessageWrong(String message) {
        Map<String, Object> data = new HashMap<>();
        data.put("messageWrong", message);
        return data;
    }
}
