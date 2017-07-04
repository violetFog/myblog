package com.rainy.repository;

import com.rainy.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by PC on 2017/6/19.
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    @Query("from Blog b where b.createTime >= '2017-01-01 00:00:00'")
    public List<Blog> queryRecentBlogs();
    @Query("from Blog b where b.isTop=1")
    public List<Blog> queryIsTopBlogs();
    @Query("from Blog b where b.isTop=0")
    public List<Blog> queryIsNotTopBlogs();
}
