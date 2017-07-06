package com.rainy.repository;

import com.rainy.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    List<Blog> queryRecentBlogs();

    @Query("select b.blogType,count(*) from Blog b group by  b.blogType")
    List<Object> queryblogTypeAndCount();

    Page<Blog> findAll(Specification<Blog> specification,Pageable pageable);
}
