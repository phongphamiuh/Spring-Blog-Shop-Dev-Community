package com.spring.blogshop.controller;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.spring.blogshop.entity.Book;
import com.spring.blogshop.service.BookService;
import com.spring.blogshop.util.BookGrid;
import com.spring.blogshop.validator.BookValidator;

@Controller
@RequestMapping("/shop")
@EnableWebMvc
public class ShopController {

	@Autowired
	BookValidator bookValidator;
	
	@Autowired
	BookService bookService;
	
	@InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
 
        if (target.getClass() == Book.class) {
            dataBinder.setValidator(bookValidator);
            // For upload Image.
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        }
    }
	
    @RequestMapping(method=RequestMethod.GET)
    public String addProductForm(Model model){
    	model.addAttribute("bookForm", new Book());
        return"productform";
    }
    
    // Annotaion @Validated và BindingResult phải đứng liên tiếp.
    @RequestMapping(method = RequestMethod.POST)
    public String addProduct(Model model,@Validated @ModelAttribute("bookForm") Book book,BindingResult result,
    		@RequestParam(value = "image",required = false) MultipartFile image) {
    	if(result.hasErrors()) {
    		return "productform";
    	}
    	byte[] fileContent=null;
    	try {	
			InputStream inputStream = image.getInputStream();
			fileContent=IOUtils.toByteArray(inputStream);
 		} catch (Exception e) {
			e.printStackTrace();
		}
    	book.setImage(fileContent); 	
    	bookService.save(book);   	
    	return "redirect:/shop/productlist"; 
    }
    
    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] downloadPhoto(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);    
        return book.getImage();
    }
      
    @RequestMapping("/productlist")
    public String productList(Model model) {
    	List<Book> bookList=bookService.findAll();
    	for (Book book : bookList) {
			System.out.println("danh sach book"+book.toString());
		}
    	model.addAttribute("productList", bookList);
    	return "productlist";
    }
      
    // Sử dụng pagination and sort trả về Json.
    // return localhost:8080//SpringBlogShopDevCommunity/shop/bookshop?page=1
    @ResponseBody
    @RequestMapping(value="/bookshop",produces = "application/json")
    public Page<Book> getListBooks(Model model,@RequestParam(value = "pages", required = false) Integer page,
											@RequestParam(value="rows",required=false) Integer rows										
    		) {    	
    	Pageable pageAble=PageRequest.of(page, 4);
    	Page<Book> pageBook=bookService.findAllByPage(pageAble);
         // Constructs page request for current page
         // Note: page number for Spring Data JPA starts with 0, while jqGrid starts with 
    	return pageBook;
    }
       
}
