package org.example.mvc.controller;

import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.RequestMapping;
import org.example.mvc.annotation.RequestMethod;
import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//public class UserListController implements ControllerHandler {
public class UserListController{
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // user list 를 return 하는 controller.
        // jsp 로 이동을 시킬때 users 라는 정보를 전달해줘야 한다.

        request.setAttribute("users", UserRepository.findAll());
        return "/user/list";
    }
}
