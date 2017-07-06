package com.rainy.controller;

import com.alibaba.fastjson.JSON;
import com.rainy.model.Blog;
import com.rainy.repository.BlogRepository;
import com.rainy.service.BlogService;
import com.rainy.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/7/3.
 */
@Controller
@RequestMapping(value = "/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogRepository blogRepository;

    @RequestMapping(value = "/queryRecentBlogs",method = {RequestMethod.POST})
    @ResponseBody
    public List<Blog> queryRecentBlogs(){
        List<Blog> blogList = blogService.queryRecentBlogs();
        return blogList;
    }
    @RequestMapping(value = "/queryBlogs",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> queryBlogs(HttpServletRequest request){
        String blogType = request.getParameter("blogType");
        String title = request.getParameter("title");
        String limit = request.getParameter("limit");
        String page = request.getParameter("page");
        Map<String,Object> result= blogService.queryBlogs(blogType,title,page,limit);
        return result;
    }
    @RequestMapping(value = "/queryBlogsType",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> queryBlogsType(){
        Map<String,Object> result=new HashMap<>();
        long count = blogRepository.count();
        List<Object> list = blogRepository.queryblogTypeAndCount();
        result.put("count", count);
        result.put("typeList", list);
        return result;
    }

    @RequestMapping(value = "/{blogId}",method = {RequestMethod.GET})
    public String viewArticle(@PathVariable String blogId,ModelMap map){
        Blog blog = blogRepository.findOne(Integer.parseInt(blogId));
        map.addAttribute("blog", blog);
        return "/page/showBlog";
    }
    @RequestMapping(value = "/test",method = {RequestMethod.GET})
    @ResponseBody
    public List<Object> test(){
        Map<String,Object> result=new HashMap<>();
        List<Object> objects = blogRepository.queryblogTypeAndCount();
        for (Object o : objects) {
            System.out.println(o.toString());
        }
        return  objects;
    }
}
