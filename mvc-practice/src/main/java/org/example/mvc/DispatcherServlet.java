package org.example.mvc;

import org.example.annotation.RequestMethod;
import org.example.mvc.controller.Controller;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMappingHandlerMapping rmhm;
    private List<ViewResolver> viewResolvers;
    private List<HandlerAdapter> handlerAdapters;

    @Override
    public void init() throws ServletException {
        rmhm = new RequestMappingHandlerMapping();
        rmhm.init();

        viewResolvers = Collections.singletonList(new JspViewResolver());
    }
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        log.info("[DispatcherServlet] service started");

        try{
            Controller handler = rmhm.findHandler(new HandelerKey(request.getRequestURI(), RequestMethod.valueOf(request.getMethod())));

            String viewName = handler.handleRequest(request, response);
            //viewResolver에게 위임.
            //RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);

            // To-Be
            //requestDispatcher.forward(request,response);

            //As-Is
            //viewName을 가지고 redirect 할지 forward 할지 분리처리.
            for(ViewResolver viewResolver : viewResolvers){
                View view = viewResolver.resolverView(viewName);
                view.render(new HashMap<>(), request, response);
            }

        }catch (Throwable e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }
}
