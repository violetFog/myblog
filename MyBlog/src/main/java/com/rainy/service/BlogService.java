package com.rainy.service;

import com.rainy.model.Blog;

import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/6/19.
 */
public interface BlogService {
     List<Blog> queryRecentBlogs();
     Map<String,Object> queryBlogs(final String blogType,final String title,String pageStr,String limitStr);
}
