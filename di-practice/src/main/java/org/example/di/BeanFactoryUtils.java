package org.example.di;

import org.example.annotation.Inject;
import org.reflections.ReflectionUtils;
import org.reflections.util.ReflectionUtilsPredicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {
    private static final Logger log = LoggerFactory.getLogger(BeanFactoryUtils.class);
    public static Constructor<?> getInjectedConstructor (Class<?> clazz){
        Set<Constructor> injectConstructor = ReflectionUtils.getAllConstructors(clazz, ReflectionUtilsPredicates.withAnnotation(Inject.class));

        log.info("injectConstructor = {}", injectConstructor);
        if(injectConstructor.isEmpty()){
            return null;
        }

        return injectConstructor.iterator().next();
    }
}
