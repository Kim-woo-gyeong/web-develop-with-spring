package org.example.di;

import org.example.annotation.Inject;
import org.example.controller.UserController;
import org.reflections.ReflectionUtils;
import org.reflections.util.ReflectionUtilsPredicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {
    private static final Logger log = LoggerFactory.getLogger(BeanFactoryUtils.class);
    // Todo: 왜 인스턴스 변수로 담는지.
    private final Set<Class<?>> preInstantiatedClazz;
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClazz){
        this.preInstantiatedClazz = preInstantiatedClazz;
        initialize();
    }

    public void initialize(){
        for(Class<?> clazz : preInstantiatedClazz){
            Object instance = createdInstance(clazz);
            beans.put(clazz, instance);
        }
    }

    public Object createdInstance(Class<?> clazz){
        // 생성자
        Constructor<?> constructor = findConstructor(clazz);
        List<Object> parameters = new ArrayList<>();
        // 파라미터
        for(Class<?> typeClass : constructor.getParameterTypes()) {
            parameters.add(getBean(typeClass));
        }

        //인스턴스생성
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> findConstructor(Class<?> clazz) {
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(clazz);
        if(Objects.nonNull(constructor)){
            return constructor;
        }

        return clazz.getConstructors()[0];
    }


    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }
}
