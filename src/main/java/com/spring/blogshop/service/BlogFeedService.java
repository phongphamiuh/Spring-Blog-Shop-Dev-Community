package com.spring.blogshop.service;

import com.spring.blogshop.entity.Comment;
import com.spring.blogshop.entity.Spittle;

public interface BlogFeedService {
	void broadcastBlog(Comment comment);
}
