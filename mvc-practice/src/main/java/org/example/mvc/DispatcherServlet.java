package org.example.mvc;

import org.example.annotation.RequestMethod;
import org.example.mvc.controller.Controller;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.ModelAndView;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private List<ViewResolver> viewResolvers;
    private List<HandlerAdapter> handlerAdapters;
    private List<HandlerMapping> handlerMappings;

    @Override
    public void init() throws ServletException {
        RequestMappingHandlerMapping rmhm = new RequestMappingHandlerMapping();
        rmhm.init();

        handlerMappings = List.of(rmhm);
        viewResolvers = Collections.singletonList(new JspViewResolver());
        handlerAdapters = List.of(new SimpleControllerHandlerAdapter());
    }
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        log.info("[DispatcherServlet] service started");
        String requestURI = request.getRequestURI();
        RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());
        try{
            // To-Be
            //Controller handler = rmhm.findHandler(new HandlerKey(request.getRequestURI(), RequestMethod.valueOf(request.getMethod())));
            //As-Is : Controller 뿐 아니라 동적으로 관리하기 위함.
            Object handler = handlerMappings.stream()
                    .filter(hm -> hm.findHandler(new HandlerKey(requestURI, requestMethod)) != null)
                    .map(hm -> hm.findHandler(new HandlerKey(requestURI, requestMethod)))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No handler for [" +  requestMethod + ", " + requestURI + "]"));

            //HandlerAdapter에게 위임하여 어떤 view 를 보여줄것인지 요청함.
            //String viewName = handler.handleRequest(request, response);
            //viewResolver에게 위임.
            //RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);

            HandlerAdapter handlerAdapter = handlerAdapters.stream()
                        .filter(ha -> ha.supports(handler))
                        .findFirst()
                        .orElseThrow(() -> new ServletException("No adapter for handler [" + handler + "]"));

            ModelAndView modelAndView = handlerAdapter.handle(request, response, handler);

            // To-Be
            //requestDispatcher.forward(request,response);

            //As-Is
            //viewName을 가지고 redirect 할지 forward 할지 분리처리.
            for(ViewResolver viewResolver : viewResolvers){
                View view = viewResolver.resolverView(modelAndView.getViewName());
                view.render(new HashMap<>(), request, response);
            }

        }catch (Throwable e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }
}
