package com.spring.blogshop.responsitory;

import java.util.List;

import com.spring.blogshop.entity.Comment;
import com.spring.blogshop.entity.NotificationMessage;

public interface SpittleRepository {
	Comment save(Comment comment);
	
	Comment findOne(Long id);
	
	List<Comment> findByIdBlog(Long id);
	
	NotificationMessage saveNotification(NotificationMessage notification);
	
	List<NotificationMessage> findByIdUser(String username);
}
