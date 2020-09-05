package com.spring.blogshop.responsitory;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.spring.blogshop.entity.Blog;

@Repository
public interface PageAndSortBlogRepository extends PagingAndSortingRepository<Blog, Long> {

}
