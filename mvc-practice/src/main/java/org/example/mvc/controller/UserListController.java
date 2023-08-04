package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListController implements Controller{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // user list 를 return 하는 controller.
        // jsp 로 이동을 시킬때 users 라는 정보를 전달해줘야 한다.

        request.setAttribute("users", List.of());
        return "/user/list.jsp";
    }
}
