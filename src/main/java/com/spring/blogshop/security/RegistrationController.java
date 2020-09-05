package com.spring.blogshop.security;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.multipart.MultipartFile;

import com.spring.blogshop.entity.BookMark;
import com.spring.blogshop.entity.User;
import com.spring.blogshop.responsitory.BlogRepository;
import com.spring.blogshop.responsitory.UserRepository;


@Controller
@RequestMapping("/register")
public class RegistrationController {
	private UserRepository userRepo;
	private BlogRepository blogRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public RegistrationController(UserRepository userRepo,PasswordEncoder passwordEncoder, BlogRepository blogRepository) {
		this.userRepo=userRepo;
		this.passwordEncoder=passwordEncoder;
		this.blogRepository=blogRepository;
	}
	
	@GetMapping
	public String registerForm(Model model) {
		model.addAttribute("registrationForm", new RegistrationForm());
		return "registration";
	}
	
	@PostMapping
	public String processRegistration(RegistrationForm form,@RequestPart("imageUserNew")MultipartFile multipart) {	
		byte[] fileContent=null;
		try {
			InputStream inputStream=multipart.getInputStream();
			fileContent=IOUtils.toByteArray(inputStream);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		form.setImageUserNew(fileContent);
		userRepo.save(form.toUser(passwordEncoder));
		
		User user=userRepo.findByUsername(form.getUsername());
		BookMark bookMark=new BookMark(user.getId());
		blogRepository.bookMark(bookMark);
		return "redirect:/login";
	}
	
	@GetMapping("/{username}")
	@ResponseBody
	public User user(@PathVariable("username")String username){
		return userRepo.findByUsername(username);
	}
}
