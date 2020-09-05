package com.spring.blogshop.web;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.blogshop.entity.Blog;
import com.spring.blogshop.entity.Comment;
import com.spring.blogshop.entity.Notification;
import com.spring.blogshop.entity.NotificationMessage;
import com.spring.blogshop.entity.Spittle;
import com.spring.blogshop.entity.User;
import com.spring.blogshop.model.SpittleForm;
import com.spring.blogshop.responsitory.BlogRepository;
import com.spring.blogshop.responsitory.SpittleRepository;
import com.spring.blogshop.responsitory.UserRepository;
import com.spring.blogshop.service.BlogFeedService;
import com.spring.blogshop.service.SpittleFeedService;

@Controller
public class BlogMessageController {
	private SpittleRepository spittleRepo;
	private SpittleFeedService feedService;
	private BlogFeedService blogFeedSerivce;
	private BlogRepository blogRepository;
	private UserRepository userRepository;
	private SimpMessagingTemplate messaging;
	
	@Autowired
	public BlogMessageController(SpittleRepository spittleRepo,SpittleFeedService feedService
			,BlogFeedService blogFeedSerivce,BlogRepository blogRepository,UserRepository userRepository,
			SimpMessagingTemplate messaging) {
		this.spittleRepo=spittleRepo;
		this.feedService=feedService;
		this.blogFeedSerivce=blogFeedSerivce;
		this.blogRepository=blogRepository;
		this.userRepository=userRepository;
		this.messaging=messaging;
	}
		
	@MessageMapping("/spittle")
	@SendToUser("/queue/notifications")
	public Notification handleSpittle(Principal principal,SpittleForm form) {
		Spittle spittle=new Spittle(principal.getName(), form.getText(), new Date());
		System.out.println(spittle.toString());
		System.out.println(principal.getName());
//		spittleRepo.save(comment);
		feedService.broadcastSpittle(spittle);
		return new Notification("Saved Spittle for user: "+principal.getName());
	}
	
	@MessageMapping("/blog/show/{id}")
	@SendToUser("/queue/notifications")
	public Notification handleBlog(@DestinationVariable("id")Long id,Principal principal,SpittleForm form) {
		Blog blog=blogRepository.findByBlogId(id);
		User user=userRepository.findByUsername(principal.getName());
		NotificationMessage notificationMessage=new NotificationMessage(blog.getUser().getUsername(),form.getText(), new Date(), user);
		if(blog.getAuthor().equals(principal.getName())) {
			
		}
		Comment comment=new Comment(principal.getName(), form.getText(), new Date());
		System.out.println(comment.toString());
		System.out.println(principal.getName());	
		blogFeedSerivce.broadcastBlog(comment);
		messaging.convertAndSend("/topic/blogfeed/"+blog.getUser().getUsername(), comment);
		comment.setBlog(blog);
		spittleRepo.save(comment);
		spittleRepo.saveNotification(notificationMessage);
		return new Notification("Saved Spittle for user: "+principal.getName());
	}
	
	@RequestMapping("/blog/notification")
	public String handleNotificationView(@RequestParam("username")String username,Model model) {
		List<NotificationMessage> listNotification=spittleRepo.findByIdUser(username);
		model.addAttribute("username", username);
		model.addAttribute("listNotification", listNotification);
		return "notification";
	}
	
//	@MessageMapping("/blog/notification/{username}")
//	@SendToUser("/queue/notifications")
//	public Notification handleNotificationMessage(@DestinationVariable("username")String username,Principal principal,
//			SpittleForm form) {
//		User user=userRepository.findByUsername(username);
//		NotificationMessage notificatioMessage=new NotificationMessage("Notification from :", new Date(), user);
//		System.out.println(notificatioMessage.toString());
//		System.out.println(principal.getName());	
////		messaging.convertAndSend("/topic/blogfeed/"+username, notificatioMessage);
//	
//		return new Notification("Saved Spittle for user: "+principal.getName());
//	}
	
	
	
	
	
	
}
