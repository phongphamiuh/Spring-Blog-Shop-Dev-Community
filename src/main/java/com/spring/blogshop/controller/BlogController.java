package com.spring.blogshop.controller;

import java.io.InputStream;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.spring.blogshop.entity.Blog;
import com.spring.blogshop.entity.BlogCommunity;
import com.spring.blogshop.entity.Book;
import com.spring.blogshop.entity.BookMark;
import com.spring.blogshop.entity.Comment;
import com.spring.blogshop.entity.PostBookMark;
import com.spring.blogshop.entity.User;
import com.spring.blogshop.entity.Vote;
import com.spring.blogshop.model.BLogForm;
import com.spring.blogshop.model.Message;
import com.spring.blogshop.model.OutputMessage;
import com.spring.blogshop.model.SpittleForm;
import com.spring.blogshop.responsitory.BlogRepository;
import com.spring.blogshop.responsitory.CommunityRepository;
import com.spring.blogshop.responsitory.PageAndSortBlogRepository;
import com.spring.blogshop.responsitory.SpittleRepository;
import com.spring.blogshop.responsitory.UserRepository;
import com.spring.blogshop.service.SpittleFeedService;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	Logger logger=Logger.getLogger(BlogController.class);
	
	BlogRepository blogRepository;
	UserRepository userRepository;
	CommunityRepository communityRepository;
	PageAndSortBlogRepository pageAndSortBlog;
	SpittleRepository spittleRepository;
	

	
	@Autowired
	public BlogController(BlogRepository blogRepository,UserRepository userRepository,
			CommunityRepository communityRepositor,PageAndSortBlogRepository pageAndSortBlog,
			SpittleRepository spittleRepository) {
		this.blogRepository=blogRepository;
		this.userRepository=userRepository;
		this.communityRepository=communityRepositor;
		this.pageAndSortBlog=pageAndSortBlog;
		this.spittleRepository=spittleRepository;	
	}
	
	@InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        dateFormat.setLenient(true);
        //format input date
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        
        if (target.getClass() == Blog.class) {        
            // For upload Image.
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        }
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public String blogForm(@RequestParam(value = "id",defaultValue = "")Long id,Model model,
			@AuthenticationPrincipal User user,Principal principal){
		BLogForm blogForm=null;
		if(id!=null&&id>0) {
			Blog blog=blogRepository.findByBlogId(id);
			blogForm=new BLogForm(blog.getId(), blog.getHeader(), blog.getTitle(), blog.getAuthor(),
					blog.getContent(), blog.getTimeCreate(), blog.getImageBlog(),user);
		}
		if(blogForm==null) {
			blogForm=new BLogForm();
		}
//		String username=principal.getName();
//		User userNew=userRepository.findByUsername(username);
		
		model.addAttribute("user", user);
		model.addAttribute("blogForm", blogForm);
		
//		BookMark bookMark=new BookMark(userNew.getId());
//		blogRepository.bookMark(bookMark);
		return "blogForm";
	}
	
	@SuppressWarnings("null")
	@RequestMapping(method = RequestMethod.POST)
	public String addBlog(BLogForm blogForm,
			HttpServletRequest request,Model model,
			@AuthenticationPrincipal User user,BlogCommunity blogCommunity) {	
		Blog blogNew=null;		
		byte[] fileContent=null;
		Part muiltipart=null;
		try {		
			muiltipart= request.getPart("imageBlogNew");
//			String fileName=Paths.get(muiltipart.getSubmittedFileName()).getFileName().toString();		
			InputStream inputStream=muiltipart.getInputStream();
			
			fileContent=IOUtils.toByteArray(inputStream);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		blogForm.setImageBlogNew(fileContent);
		blogForm.setUser(user);
//		blogForm.setBlogCommunity(blogCommunity);		
		blogNew=blogForm.toBlog();	
		blogRepository.addBlog(blogNew);	
//		user.getBlogList().add(blog);
		return "redirect:/blog/bloglist";
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.GET)
	public String editBlogForm(@RequestParam(value = "id",defaultValue = "")Long id,Model model,
			@AuthenticationPrincipal User user,Principal principal){
		BLogForm blogForm=null;
		if(id!=null&&id>0) {
			Blog blog=blogRepository.findByBlogId(id);
			blogForm=new BLogForm(blog.getId(), blog.getHeader(), blog.getTitle(), blog.getAuthor(),
					blog.getContent(), blog.getTimeCreate(), blog.getImageBlog(),user);
		}
		if(blogForm==null) {
			blogForm=new BLogForm();
		}
//		String username=principal.getName();
//		User userNew=userRepository.findByUsername(username);
		
		model.addAttribute("user", user);
		model.addAttribute("blogForm", blogForm);
		
//		BookMark bookMark=new BookMark(userNew.getId());
//		blogRepository.bookMark(bookMark);
		return "editBlog";
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	public String editBlog(@RequestParam("id")Long id,BLogForm blogForm,HttpServletRequest request,Model model,
			@AuthenticationPrincipal User user,BlogCommunity blogCommunity,@RequestPart("imageBlogNew") MultipartFile multi) {	
		Blog blog=blogRepository.findByBlogId(id);
		System.out.println("Blog----------------------"+blog.toString());
		Blog blogNew=null;		
		byte[] fileContent=null;
		Part muiltipart=null;
		try {		
			muiltipart= request.getPart("imageBlogNew");
//			String fileName=Paths.get(muiltipart.getSubmittedFileName()).getFileName().toString();		
			InputStream inputStream=muiltipart.getInputStream();
			
			fileContent=IOUtils.toByteArray(inputStream);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(muiltipart.getSize()!=0) {
			logger.info("Upload Image-------------------------------------------------------------------------------------");
			blogForm.setImageBlogNew(fileContent);
			blogForm.setUser(user);	
			blogNew=blogForm.toBlog();
			blogRepository.addBlog(blogNew);	
		}else {
			logger.info("Not Upload Image-------------------------------------------------------------------------------------");
			blogForm.setImageBlogNew(blog.getImageBlog());
			blogForm.setUser(user);
			blogNew=blogForm.toBlogNotImage();		
			blogRepository.addBlog(blogNew);
		}		
		blogForm.setUser(user);		
		blogNew=blogForm.toBlog();	
		blogRepository.addBlog(blogNew);	
		return "redirect:/blog/bloglist";
	}
	
	
	
	@RequestMapping("/bloglist")
	public String blogList(Model model) {
		model.addAttribute("blogList", blogRepository.showBlogList());
		return "blogList";
	}
	
	@RequestMapping("/show/{id}")
	public String showBlogContent(@PathVariable("id")Long id,Model model,@AuthenticationPrincipal User user) {
		Blog blog=blogRepository.findByBlogId(id);
		List<Comment> commentList=spittleRepository.findByIdBlog(id);
		
		model.addAttribute("commentList", commentList);
		model.addAttribute("blogContent", blog);
		model.addAttribute("id", id);	
		List<Vote> blogVote=blogRepository.findByUsernameBlog(blog.getId());
    	int voteSum=blogVote.stream()
    		.mapToInt(Vote::getVotes)
    		.sum();
    	System.out.println("List Vote ----------:"+voteSum);
    	model.addAttribute("voteSum", voteSum);
    	model.addAttribute("spittleForm", new SpittleForm());
		return "blogContent";
	}
	
	
//	@RequestMapping(value="/comment/{id}",method = RequestMethod.POST)
//	public String comment(@PathVariable("id")Long id,SpittleForm spittleForm,@AuthenticationPrincipal User user) {
//		Blog blog=blogRepository.findByBlogId(id);
//		Comment comment=new Comment(user.getUsername(), spittleForm.getText(), new Date());
//		comment.setBlog(blog);
//		spittleRepository.save(comment);
//		return "redirect:/blog/show/{id}";
//	}
	
//	@RequestMapping("/show/bloguser/{id}")
//	public String showBlogUser(@PathVariable("id")Long id,Model model,@AuthenticationPrincipal User user) {
//		Blog blog=blogRepository.findByBlogId(id);
//		model.addAttribute("blogContent", blog);
//		model.addAttribute("id", id);		
//		return "postBlogContent";
//	}
	
	
//	@ResponseBody
//	@RequestMapping(value="/show/{id}",method = RequestMethod.GET,
//	produces = "application/json")
//	public Blog showBlog(@PathVariable("id")Long id,Model model,@AuthenticationPrincipal User user) {
////		User user=userRepository.findByUsername(user.getUsername());	
//		Blog blog=blogRepository.findById(id);
//		
//		return blog;
//	}
	
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id")Long id) {
		Blog blog=blogRepository.findByBlogId(id);
		if(blog==null) {
			logger.info("Delete fail!");

			return "blogList";
		}else {
			blogRepository.deleteById(blog);
		}
		return "redirect:/blog/bloglist";
	}
	// display post list
	@RequestMapping("/blogpost")
	public String blogPost(Model model,@AuthenticationPrincipal User user){	
		System.out.println(user.toString());
		model.addAttribute("blogPost", blogRepository.blogPost(user.getId()));
		return "blogPostList";
	}
		
	@RequestMapping("/bookmark/{id}")
	public String bookMark(@PathVariable("id")Long id,@AuthenticationPrincipal User user) {
		BookMark bookMark=null;
		try {
			bookMark=blogRepository.findByIdBookMark(user.getId());
		} catch (Exception e) {
			return "redirect:/login";
		}
		
		System.out.println(bookMark);
//		bookMark.getPostBookMark().add(postBookMark);
		Blog blog=blogRepository.findByBlogId(id);
		System.out.println(blog);
//		blog.getPostBookMark().add(postBookMark);
		//c2
//		bookMark.getBlogs().add(blog);
//		blog.getBookMarks().add(bookMark);		
//		blogRepository.addBlog(blog);
//		blogRepository.bookMark(bookMark);
		
		PostBookMark postBookMark=new PostBookMark(blog, bookMark);
		blogRepository.bookMarkPost(postBookMark);
		 
		return "redirect:/blog/bookmarklist";
	}
	
	

	@RequestMapping("/vote/decrease/{id}")
	public String decreaseVoteToBlog(@PathVariable("id")Long id,@AuthenticationPrincipal User user) {
		Blog blog=blogRepository.findByBlogId(id);
		Vote vote=new Vote(blog, user);
		blogRepository.vote(vote);
		return "redirect:/blog/bookmarklist";
	}
	
	@RequestMapping("/bookmarklist")
	public String bookMarkList(Model model,@AuthenticationPrincipal User user) {
		List<PostBookMark> postBookMark=blogRepository.showPostBookMark(user.getId());
		model.addAttribute("bookMarkList", postBookMark);
		return "bookMarkList";
	}
	
	
	@RequestMapping(value="/photo/{id}",method=RequestMethod.GET)
	@ResponseBody
	public byte[] downloadPhoto(@PathVariable("id")Long id) {
		Blog blog=blogRepository.findByBlogId(id);
		return blog.getImageBlog();
	}	
	
	@RequestMapping(value="/image/comment/{user}")
	@ResponseBody
	public byte[] downloadPhotoToUserComment(@PathVariable("user")String username) {
		User user=userRepository.findByUsername(username);
		return user.getImageUser();
	}
	
	
	@ResponseBody
    @RequestMapping(value="/bookshop",produces = "application/json")
    public Page<Blog> getListBooks(Model model,@RequestParam(value = "pages", required = false) Integer page,
											@RequestParam(value="rows",required=false) Integer rows										
    		) {    	
    	Pageable pagealbe=PageRequest.of(page, 4);
    	Page<Blog> pageBlog=pageAndSortBlog.findAll(pagealbe);
         // Constructs page request for current page
         // Note: page number for Spring Data JPA starts with 0, while jqGrid starts with 
    	return pageBlog;
    }
	
	
}
