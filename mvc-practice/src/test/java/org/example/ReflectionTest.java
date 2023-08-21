package org.example;

import org.example.model.User;
import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.Service;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
* @Controller 어노테이션이 설정되어있는 모든 클래스를 찾아서 출력한다.
* */
public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
    @Test
    void controllerScan(){
        // org.example 하위로 모든 리플렉션을 탐색한다.
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));
        logger.debug("beans : [{}]", beans);
    }

    @Test
    void showClass(){
        Class<User> clazz = User.class;

        logger.debug(clazz.getName());
        logger.debug("User all declared fields : [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all declared constructors : [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all declared methods : [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    // 힙 영역에 ㅇ로드 되어 있는 클래스 타입의 객체 가져오는 방법.
    @Test
    void load() throws ClassNotFoundException {
        //1.
        Class<User> clazz1 = User.class;

        //2.
        User user = new User("wizard", "홍길동");
        Class<? extends User> clazz2 = user.getClass();

        //3.
        Class<?> clazz3 = Class.forName("org.example.model.User");

        logger.debug("clazz1 : [{}]", clazz1);
        logger.debug("clazz2 : [{}]", clazz2);
        logger.debug("clazz3 : [{}]", clazz3);

        // 3개 다 동일한 객체이다.
    }
    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }
}
