package ru.otus.hw13.servlet;

import ru.otus.hw13.html.page.create.HtmlCreator;
import ru.otus.hw13.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends Servlet {
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Map<String, Object> data = new HashMap<>();
        data.put("messageWrong", "Wrong login or password!");
        response.getWriter().println(HtmlCreator.instance().create(Constants.LOGIN_PAGE, setMessageWrong("")));

        setOK(response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String login = request.getParameter(Constants.LOGIN_VARIABLE_NAME);
        String password = request.getParameter(Constants.PASSWORD_VARIABLE_NAME);


        if (Constants.LOGIN_PASSWORD.equals(login) && Constants.LOGIN_PASSWORD.equals(password) ) {
            // TODO: Remove this:
            response.sendRedirect("http://localhost:" + Constants.PORT + "/" + Constants.CACHE);
        }
        else {

            response.getWriter().println(HtmlCreator.instance().create(Constants.LOGIN_PAGE, setMessageWrong("Wrong login or password!")));
            setOK(response);
        }
    }

    private Map<String,Object> setMessageWrong(String message) {
        Map<String, Object> data = new HashMap<>();
        data.put("messageWrong", message);
        return data;
    }
}
