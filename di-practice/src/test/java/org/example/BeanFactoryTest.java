package org.example;

import org.assertj.core.api.Assertions;
import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.controller.UserController;
import org.example.di.BeanFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class BeanFactoryTest {
    private static final Logger log = LoggerFactory.getLogger(BeanFactoryTest.class);
    private Reflections reflections;
    private BeanFactory beanFactory;
    @BeforeEach
    void setUp(){
        reflections = new Reflections("org.example");
        Set<Class<?>> preInstantiated = getTypesAnnotiatedWith(Controller.class, Service.class);
        log.info("preInstantiated = {}", preInstantiated);
        beanFactory = new BeanFactory(preInstantiated);
    }

    private Set<Class<?>> getTypesAnnotiatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> beans = new HashSet<>();
        for(Class<? extends Annotation> annotation : annotations){
            // "org.example" 하위 폴더에 annotation 이 붙은 class 를 뽑아냄.
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }
        return beans;
    }

    @Test
    void diTest(){
        log.info("beanFactory = {}",beanFactory);
        UserController userController = beanFactory.getBean(UserController.class);
        Assertions.assertThat(userController).isNotNull();
    }
}
