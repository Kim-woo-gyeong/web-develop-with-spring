package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
    private Map<String, Controller> mappings = new HashMap<>();
    void init(){
        // 어떤한 경로도 설정되지 않는 경우.
        mappings.put("/", new HomeController());
    }

    public Controller findHandler(String uriPath){
        return mappings.get(uriPath);
    }
}

