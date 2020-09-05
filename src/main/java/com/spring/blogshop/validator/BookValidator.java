package com.spring.blogshop.validator;

import javax.validation.Validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.spring.blogshop.entity.Book;
@Component
public class BookValidator implements org.springframework.validation.Validator{

	@Override
	public boolean supports(Class<?> clazz) {	
		return clazz==Book.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Book book=(Book)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bookName","NotEmpty.bookForm.name");
		
	}
	
}
