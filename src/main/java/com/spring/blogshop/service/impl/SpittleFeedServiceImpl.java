package com.spring.blogshop.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.spring.blogshop.entity.Spittle;
import com.spring.blogshop.entity.Notification;
import com.spring.blogshop.service.SpittleFeedService;

@Service
public class SpittleFeedServiceImpl implements SpittleFeedService{

	private SimpMessagingTemplate messaging;
	private Pattern pattern = Pattern.compile("\\@(\\S+)");
	
	@Autowired
	public SpittleFeedServiceImpl(SimpMessagingTemplate messaging) {
		this.messaging = messaging;
	}
	
	@Override
	public void broadcastSpittle(Spittle spittle) {
		messaging.convertAndSend("/topic/spittlefeed",spittle);
		
		messaging.convertAndSendToUser(spittle.getUser(), "/queue/notifications",
				new Notification("You jush got mentioned!"));
		
		messaging.convertAndSendToUser(spittle.getUser(), "/queue/notifications",
				new Notification("You sucess!"));
	}
}
