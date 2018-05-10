package com.selton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author seltonzyf@gmail.com
 * @date 2018/5/10 13:59
 */
@WebServlet(name = "DataSendServlet")
public class DataSendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().print("{\"total\": 11, \"rows\":[{\"testId\":9, \"testName\":\"selton\", \"testPassword\": 1}]}");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
