package org.example;

import org.example.calculate.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CalculateServlet", urlPatterns = "/calculate")
//public class CalculateServlet implements Servlet {
public class CalculateServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CalculateServlet.class);
    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info("init");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("service");

        String operand1 = request.getParameter("operand1");
        String operator = request.getParameter("operator");
        String operand2 = request.getParameter("operand2");

        int result = Calculate.calculate(new PositiveNumber(Integer.parseInt(operand1)), operator, new PositiveNumber(Integer.parseInt(operand2)));

        PrintWriter writer = response.getWriter();
        writer.println(result);
    }

//    @Override
//    public ServletConfig getServletConfig() {
//        return null;
//    }
//
//    @Override
//    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
//        logger.info("service");
//
//        String operand1 = request.getParameter("operand1");
//        String operator = request.getParameter("operator");
//        String operand2 = request.getParameter("operand2");
//
//        int result = Calculate.calculate(new PositiveNumber(Integer.parseInt(operand1)), operator, new PositiveNumber(Integer.parseInt(operand2)));
//
//        PrintWriter writer = response.getWriter();
//        writer.println(result);
//    }

//    @Override
//    public String getServletInfo() {
//        return null;
//    }
//
//    @Override
//    public void destroy() {
//
//    }
}
