package com.spring.blogshop.responsitory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.spring.blogshop.entity.Blog;
import com.spring.blogshop.entity.Book;
import com.spring.blogshop.entity.BookMark;
import com.spring.blogshop.entity.PostBookMark;
import com.spring.blogshop.entity.Vote;

@Repository
public interface BlogRepository {	
	public Blog addBlog(Blog blog);
	public List<Blog> showBlogList();
	public Blog findByBlogId(Long id);
	public void deleteById(Blog blog);
	
	public List<Blog> blogPost(Long id);
	
	public BookMark bookMark(BookMark bookMark);
	public PostBookMark bookMarkPost(PostBookMark postBookMark);	
	public BookMark findByIdBookMark(Long id);
	
	public List<PostBookMark> showPostBookMark(Long id);
	
	public Blog findById1(Long id);
	public Vote vote(Vote vote);
	public List<Vote> findByUsernameBlog(Long id);
	
	//pageable
	
	
}
