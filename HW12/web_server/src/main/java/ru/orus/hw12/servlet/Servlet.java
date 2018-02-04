package ru.orus.hw12.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
    void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
