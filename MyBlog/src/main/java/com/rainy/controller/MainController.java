package com.rainy.controller;

import com.rainy.model.Blog;
import com.rainy.repository.BlogRepository;
import com.rainy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/5/3.
 */

@Controller
public class MainController {

    @RequestMapping("/")
    public String index(ModelMap map){
        return "index";
    }
    @RequestMapping("/blogs")
    public String blogs(ModelMap map){
        return "/page/blogs";
    }
    @RequestMapping("/albums")
    public String albums(ModelMap map){
        return "/page/albums";
    }
    @RequestMapping("/music")
    public String music(ModelMap map){
        return "/page/music";
    }
    @RequestMapping("/message")
    public String message(ModelMap map){
        return "/page/message";
    }
    @RequestMapping("/ace")
    public String ace(ModelMap map){
        return "/page/ace";
    }
    @RequestMapping("/me")
    public String me(ModelMap map){
        return "/page/me";
    }
    @RequestMapping("/show")
    public String show(ModelMap map){
        return "/page/showPhone";
    }
    @RequestMapping("/login")
    public String login(ModelMap map){
        Map<String,Object> mapp = new HashMap<>();
        mapp.put("name", "marry");
        map.addAttribute("loginName", mapp);
        return "page/login";
    }
    @RequestMapping("/login/check")
    @ResponseBody
    public Map<String,Object> check(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","jack");
        System.out.println(map.get("name"));
        return map;
    }



}
