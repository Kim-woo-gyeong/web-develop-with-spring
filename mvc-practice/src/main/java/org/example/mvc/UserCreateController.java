package org.example.mvc;

import org.example.model.User;
import org.example.mvc.controller.Controller;
import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // user 추가
        UserRepository.save(new User(request.getParameter("userId"), request.getParameter("name")));
        // 클라이언트에게 users 로 다시 요청해달라고 요청함.
        // 그러면 클라이언트에게 GET 요청이 오고 -> /users 로 리다이렉트 해줌.
        return "redirect:/users";
    }
}
