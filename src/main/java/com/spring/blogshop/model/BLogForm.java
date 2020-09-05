package com.spring.blogshop.model;

import java.util.Date;

import com.spring.blogshop.entity.Blog;
import com.spring.blogshop.entity.BlogCommunity;
import com.spring.blogshop.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BLogForm {
	private Long id;
	
	private String header;
	
	private String title;
	
	private String author;
	
	private String content;
	
	private Date timeCreate;
	
    private byte[] imageBlogNew;
    
    public User user;
 
    
    public Blog toBlog() {
    	return new Blog(id, header, title, author, 
    			content, new Date(), imageBlogNew,user);
    }
    public Blog toBlogNotImage() {
    	return new Blog(id, header, title, author, content, new Date(), user);
    }
}
