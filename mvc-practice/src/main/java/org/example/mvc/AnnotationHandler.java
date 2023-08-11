package org.example.mvc;

import org.example.mvc.view.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationHandler{
    private static final Logger log = LoggerFactory.getLogger(AnnotationHandler.class);
    private final Class<?> clazz;
    private final Method targetMethod;
    public AnnotationHandler(Class<?> clazz, Method targetMethod){
        this.clazz = clazz;
        this.targetMethod = targetMethod;
    }

    public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Constructor<?> defaultConstructor = clazz.getDeclaredConstructor();
        log.info("[AnnotationHandler] defaultConstructor = {}", defaultConstructor);
        Object targetObject = defaultConstructor.newInstance();
        log.info("[AnnotationHandler] targetMethod = {}", targetMethod);

        // request, response 오타 조심..
        // [argument type mismatch] 오타로 인해 해당 오류 계속해서 발생했음 ㅠ-ㅠ
        return (String)targetMethod.invoke(targetObject, request, response);
    }
}
