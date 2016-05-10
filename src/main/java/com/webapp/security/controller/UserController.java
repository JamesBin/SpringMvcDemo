package com.webapp.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.webapp.security.model.User;
import com.webapp.security.service.UserService;
import com.webapp.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request) {
        User resultUser = userService.login(user);
        if (resultUser.getId() == null) {
            request.setAttribute("user", user);
            request.setAttribute("errorMsg", "is null");
            return "login";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", resultUser);
            return "main";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.jsp";
    }


    @RequestMapping("/delete")
    public void delete(@RequestParam(value = "id") String id, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        userService.delete(Integer.parseInt(id));
        result.put("success", true);
        ResponseUtil.write(result, response);
    }

    @RequestMapping("/save")
    public String save(User user) {
        if (user.getId() == null) {
            userService.add(user);
        } else {
            userService.update(user);
        }
        return "redirect:/user/list.do";
    }
}
