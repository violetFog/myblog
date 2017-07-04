package com.rainy.controller;

import com.alibaba.fastjson.JSON;
import com.rainy.model.Blog;
import com.rainy.repository.BlogRepository;
import com.rainy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping(value = "/queryAllBlogs",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> queryAllBlogs(){
        Map<String,Object> result=new HashMap<>();
        List<Blog> isTopBlogs = blogService.queryIsTopBlogs();
        List<Blog> isNotTopBlogs = blogService.queryIsNotTopBlogs();
        result.put("isTop",isTopBlogs);
        result.put("isNotTop",isNotTopBlogs);
        return result;
    }

    @RequestMapping(value = "/{blogId}",method = {RequestMethod.GET})
    public String viewArticle(@PathVariable String blogId,ModelMap map){
        Blog blog = blogRepository.findOne(Integer.parseInt(blogId));
        map.addAttribute("blog", blog);
        return "/page/showBlog";
    }

}
