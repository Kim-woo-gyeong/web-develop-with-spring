package org.example.mvc;


import org.example.mvc.annotation.RequestMapping;
import org.example.mvc.annotation.RequestMethod;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class AnnotationHandlerMapping implements HandlerMapping{
    private static final Logger log = LoggerFactory.getLogger(AnnotationHandlerMapping.class);
    private final Object[] basePackage;
    private final Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();
    public AnnotationHandlerMapping(Object... basePacakge){
        this.basePackage = basePacakge;
    }

    public void initialize(){
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> clazzWithControllerAnnotation = reflections.getTypesAnnotatedWith(org.example.mvc.annotation.Controller.class);

        clazzWithControllerAnnotation.forEach(clazz ->
                        Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod -> {
                            RequestMapping requestMappingAnnotation = declaredMethod.getDeclaredAnnotation(RequestMapping.class);
                            Arrays.stream(getRequestMethod(requestMappingAnnotation))
                                    .forEach(requestMethod -> handlers.put(
                                            new HandlerKey(requestMappingAnnotation.value(), requestMethod)
                                            ,new AnnotationHandler(clazz, declaredMethod)
                                    ));
                        })
                );

    }

    private RequestMethod[] getRequestMethod(RequestMapping requestMappingAnnotation){
        return requestMappingAnnotation.method();
    }
    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
