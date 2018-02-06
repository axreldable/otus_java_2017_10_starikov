package ru.orus.hw12.servlet;

import ru.orus.hw12.html.page.create.HtmlCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.orus.hw12.constants.Constants.*;

public class LoginServlet extends Servlet {
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Map<String, Object> data = new HashMap<>();
        data.put("messageWrong", "Wrong login or password!");
        response.getWriter().println(HtmlCreator.instance().create(LOGIN_PAGE, setMessageWrong("")));

        setOK(response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String login = request.getParameter(LOGIN_VARIABLE_NAME);
        String password = request.getParameter(PASSWORD_VARIABLE_NAME);


        if (LOGIN_PASSWORD.equals(login) && LOGIN_PASSWORD.equals(password) ) {
            response.sendRedirect("http://localhost:" + PORT + "/" + CACHE);
        }
        else {

            response.getWriter().println(HtmlCreator.instance().create(LOGIN_PAGE, setMessageWrong("Wrong login or password!")));
            setOK(response);
        }
    }

    private Map<String,Object> setMessageWrong(String message) {
        Map<String, Object> data = new HashMap<>();
        data.put("messageWrong", message);
        return data;
    }
}
