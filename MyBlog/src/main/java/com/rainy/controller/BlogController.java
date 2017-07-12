package com.rainy.controller;

import com.alibaba.fastjson.JSON;
import com.rainy.model.Blog;
import com.rainy.repository.BlogRepository;
import com.rainy.service.BlogService;
import com.rainy.utils.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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

    @RequestMapping(value = "write")
    public String writeBlogs(){
        return "/page/write";
    }

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
        Map<String,Object> result= blogService.queryBlogs(blogType, title, page, limit);
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
    @RequestMapping(value = "/queryBlogById",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> queryBlogById(HttpServletRequest request){
        String blogId = request.getParameter("blogId");
        Map<String,Object> result=new HashMap<>();
        Blog blog = blogRepository.findOne(Integer.parseInt(blogId));
        result.put("blog", blog);
        return result;
    }
    @RequestMapping(value = "/view/{blogId}",method = {RequestMethod.GET})
    public String viewArticle(@PathVariable String blogId){
        Blog blog = blogRepository.findOne(Integer.parseInt(blogId));
        Integer number = blog.getNumber();
        blog.setNumber(number+1);
        System.out.println(blog);
        blogRepository.saveAndFlush(blog);
        return "/page/showBlog";
    }

    @RequestMapping(value = "/writeBlog",method = {RequestMethod.POST})
    public String writeBlog(HttpServletRequest request,ModelMap map){
        String blogText = request.getParameter("blogText");
        String blogTitle = request.getParameter("blogTitle");
        String blogType = request.getParameter("blogType");
        String isTop = request.getParameter("isTop");
        if(StringUtil.isNullStr(blogText)){
            return "redirect:/blogs/write";
        }
        Blog blog = new Blog();
        blog.setTitle(blogTitle);
        blog.setText(blogText);
        blog.setBlogType(blogType);
        blog.setIsTop(Integer.parseInt(isTop));
        Blog blog1 = blogRepository.saveAndFlush(blog);
        System.out.println(blog1);
        map.addAttribute("blog", blog1);
        return "redirect:/blogs/view/"+blog1.getBlogId();
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map<String,Object> upload(HttpServletRequest request,@RequestParam("thumbnail") MultipartFile tmpFile){
        Map<String,Object> map = new HashMap<>();
        String targetDirectory = request.getSession().getServletContext().getRealPath("/upload");
        String tmpFileName = tmpFile.getOriginalFilename();
        File target = new File(targetDirectory, tmpFileName);
        List<String> list = new ArrayList<>();
        list.add("/upload/"+tmpFileName);
        System.out.println(list.get(0));
        try {
            // 保存文件
            FileUtils.copyInputStreamToFile(tmpFile.getInputStream(), target);
        } catch (Exception e) {
           // e.printStackTrace();
            map.put("err",e);
            return map;
        }
        map.put("file_path",list);
        return map;
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
