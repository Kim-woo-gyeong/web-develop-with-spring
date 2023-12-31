package org.example.mvc;

import org.example.mvc.controller.ControllerHandler;
import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleControllerHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerHandler);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewName = ((ControllerHandler) handler).handleRequest(request, response);
        return new ModelAndView(viewName);
    }
}
