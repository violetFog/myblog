package com.rainy.service;

import com.rainy.model.Blog;

import java.util.List;

/**
 * Created by PC on 2017/6/19.
 */
public interface BlogService {
    public List<Blog> queryRecentBlogs();
}
