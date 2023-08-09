package org.example.mvc;

import org.example.annotation.RequestMethod;
import org.example.mvc.controller.*;

import java.util.HashMap;
import java.util.Map;

// Dispatcher -> HandlerMapping 을 거쳐서 Controller 를 선택.
//
public class RequestMappingHandlerMapping {
   // private Map<String, Controller> mappings = new HashMap<>();
   private Map<HandelerKey, Controller> mappings = new HashMap<>();
    void init(){
        /* TO-BE

        // 어떤한 경로도 설정되지 않는 경우.
        // dispatcherservlet 에서 적절한 controller 를 명시해주는 것.
        mappings.put("/", new HomeController());
        // GET 인 경우
        mappings.put("/users", new UserListController());
        // POST 인 경우
        // GET POST 둘다 같은 경로인 경우에는 구분을 할 수 없기 때문에 불가능하다.
        mappings.put("/users", new UserCreateController());
        */

        /* AS-IS */
        // 객체끼리의 비교이기 때문에 equals hashCode 가 필요하다.
        mappings.put(new HandelerKey("/", RequestMethod.GET), new HomeController());
        mappings.put(new HandelerKey("/users", RequestMethod.GET), new UserListController());
        mappings.put(new HandelerKey("/users", RequestMethod.POST), new UserCreateController());

        // forward 란 ? 단순하게 요청이 들어오면 users/form 으로 이동해달라.
        mappings.put(new HandelerKey("/users/form", RequestMethod.GET), new ForwardController("/user/form"));
    }

    public Controller findHandler(HandelerKey uriPath){

        return mappings.get(uriPath);
    }
}

