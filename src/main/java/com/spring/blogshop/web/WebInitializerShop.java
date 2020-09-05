package com.spring.blogshop.web;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.spring.blogshop.security.SecurityConfig;
import com.spring.blogshop.web.RootConfig;
import com.spring.blogshop.web.WebMvcConfig;
import com.spring.blogshop.web.WebSocketStompConfig;

@Configuration
public class WebInitializerShop extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
        		RootConfig.class,SecurityConfig.class
        };
    }
    
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebMvcConfig.class,WebSocketStompConfig.class        
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }

    
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    	registration.setMultipartConfig(getMultipartConfigElement());
    	registration.setAsyncSupported(true);
    }
    
    @Bean
    protected MultipartConfigElement getMultipartConfigElement() {
    	return new MultipartConfigElement( null, 5000000, 5000000, 0);
    } 
}
