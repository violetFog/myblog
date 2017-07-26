package com.rainy.controller;


import com.rainy.model.User;
import com.rainy.service.UserService;
import com.rainy.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PC on 2017/7/26.
 */
@Controller
@RequestMapping(value = "/login")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/check",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> check(HttpServletRequest request){
        String loginName = StringUtil.null2Str(request.getParameter("loginName"));
        String loginPsd = StringUtil.null2Str(request.getParameter("loginPsd"));
        Map<String,Object> result = new HashMap<>();
        User user = userService.queryByUserNameAndPassword(loginName, loginPsd);
        if(user!=null){
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            result.put("success","login success");
        }else{
            result.put("failed","userName or password is error");
        }
        return result;
    }

    @RequestMapping(value = "/checkLogin",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> checkLogin(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null){
            result.put("success", "user has logged in");
        }
        return result;
    }
}
