package com.rainy.service.impl;

import com.rainy.model.Blog;
import com.rainy.repository.BlogRepository;
import com.rainy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PC on 2017/6/19.
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Override
    public List<Blog> queryRecentBlogs() {
        return blogRepository.queryRecentBlogs();
    }
}
