package com.spring.blogshop.service.impl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.spring.blogshop.entity.Comment;
import com.spring.blogshop.entity.Spittle;
import com.spring.blogshop.entity.Notification;
import com.spring.blogshop.service.BlogFeedService;
@Service
public class BlogFeedServiceImpl implements BlogFeedService{

	private SimpMessagingTemplate messaging;
	private Pattern pattern = Pattern.compile("\\@(\\S+)");
	
	@Autowired
	public BlogFeedServiceImpl(SimpMessagingTemplate messaging) {
		this.messaging = messaging;
	}
	
	@Override
	public void broadcastBlog(Comment comment) {
		messaging.convertAndSend("/topic/blogfeed",comment);
			
		messaging.convertAndSendToUser(comment.getUser(), "/queue/notifications",
				new Notification("You sucess!"));
	}
}
