package com.spring.blogshop.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.spring.blogshop.web.DataServiceConfiguration;
import com.spring.blogshop.web.RootConfig;
import com.spring.blogshop.web.WebMvcConfig;

public class BlogControllerTest {
	
	private GenericApplicationContext ctx;	 	
	
	
	@Test
	public void addBookMarkToFavoriteList(){
		 ctx = new AnnotationConfigApplicationContext(DataServiceConfiguration.class);
		System.out.println("Hello----------------------------------");
	}
}
