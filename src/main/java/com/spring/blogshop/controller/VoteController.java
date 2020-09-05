package com.spring.blogshop.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.blogshop.entity.Blog;
import com.spring.blogshop.entity.User;
import com.spring.blogshop.entity.Vote;
import com.spring.blogshop.responsitory.BlogRepository;
import com.spring.blogshop.responsitory.UserRepository;

@Controller
@RequestMapping("blog")
public class VoteController {
	
	
	BlogRepository blogRepo;
	UserRepository userRepo;
	
	@Autowired
	public VoteController(BlogRepository blogRepo,UserRepository userRepo) {
		this.blogRepo=blogRepo;
		this.userRepo=userRepo;
	}
	@RequestMapping("/ajax")
    public ModelAndView helloAjaxTest() {
        return new ModelAndView("ajax", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
    }
 
    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET,produces = "application/json")
    public @ResponseBody
    Blog getTime() {
        Random rand = new Random();
        float r = rand.nextFloat() * 100;
        Blog blog=blogRepo.findByBlogId(2L);
        String result = "<br>Next Random # is <b>" + r + 
        		"</b>. Generated on <b>" + new Date().toString() + "</b>"+blog;     
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
        return blog;
    }
    
    @RequestMapping(value="/increase/vote/{id}")
    public String increaseBlog(@PathVariable("id")Long id,@AuthenticationPrincipal User user,Model model) {
    	User userNew=null;
    	Blog blog=blogRepo.findByBlogId(id);
    	try {
    		userNew=userRepo.findByUsername(user.getUsername());
		} catch (Exception e) {
			return "redirect:/login";
		}
    	Vote vote=new Vote(blog, userNew);
    	
    	vote.increaseVote();
    	blogRepo.vote(vote);
    	return "redirect:/blog/show/"+id;
    }
    
    @RequestMapping(value="/decrease/vote/{id}")
    public String decreaseBlog(@PathVariable("id")Long id,@AuthenticationPrincipal User user,Model model) {
    	User userNew=null;
    	Blog blog=blogRepo.findByBlogId(id);
    	try {
    		userNew=userRepo.findByUsername(user.getUsername());
		} catch (Exception e) {
			return "redirect:/login";
		}
    	Vote vote=new Vote(blog, userNew);	
    	vote.decreaseVote();
    	blogRepo.vote(vote);
    	return "redirect:/blog/show/"+id;
    }
    
   
    
//    @ResponseBody
//    @RequestMapping(value="/vote/increase/{id}",method = RequestMethod.GET,produces = "application/json")
//	public Vote increaseVoteToBlog(@PathVariable("id")Long id,Model model,
//			@AuthenticationPrincipal User user) throws JsonMappingException, JsonProcessingException {
//    	ObjectMapper objMapper=new ObjectMapper();
//		String content=blogRepo.findById(id).toString();
//	//	System.out.println(content);
//		User userNew=userRepo.findByUsername(user.getUsername());	
//		Blog blog=objMapper.readValue(content, Blog.class);
//		System.out.println("Java object converted to JSON String, written to file"); 
//		System.out.println(objMapper.writeValueAsString(content));
//	
//		System.out.println(blog.toString());
//		Vote vote=new Vote(blog, userNew);
////		userNew.getVotes().add(vote);
////		blogRepo.vote(vote);
//		
//		return vote;
//	}
}
