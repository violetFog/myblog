package com.rainy.service.impl;


import com.rainy.model.Blog;
import com.rainy.repository.BlogRepository;
import com.rainy.service.BlogService;
import com.rainy.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/6/19.
 */
@Transactional
@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogRepository blogRepository;
    @Override
    public List<Blog> queryRecentBlogs() {
        return blogRepository.queryRecentBlogs();
    }

    @Override
    public Map<String,Object> queryBlogs(final String blogType,final String title,String pageStr,String limitStr) {
        Map<String,Object> resultMap = new HashMap<>();
        int page = 0;
        int limit = 0;
        Pageable pageable = null;
        if (!StringUtil.isNullStr(pageStr) && !StringUtil.isNullStr(limitStr)) {
            page = StringUtil.isNullStr(pageStr) ? 0 : Integer.parseInt(pageStr) - 1;
            limit = StringUtil.isNullStr(limitStr) ? 10 : Integer.parseInt(limitStr);
            limit = limit > 0 && limit < 1000 ? limit : 1000;
            page = page >= 0 ? page : 0;
            String sortBy = "isTop" ;
            Sort s = new Sort(Sort.Direction.DESC, sortBy);
            pageable = new PageRequest(page, limit, s);
        }
        Page<Blog> blogs=blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (!StringUtil.isNullStr(blogType)) {
                    predicates.add(criteriaBuilder.equal(root.get("blogType"), blogType));
                }
                if (!StringUtil.isNullStr(title)) {
                    predicates.add(criteriaBuilder.like(root.get("title").as(String.class), "%"+title+"%"));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
        Long count = blogs.getTotalElements();
        List<Blog> blogList = blogs.getContent();

        resultMap.put("count", count);
        resultMap.put("blogs", blogList);
        return resultMap;
    }

}
