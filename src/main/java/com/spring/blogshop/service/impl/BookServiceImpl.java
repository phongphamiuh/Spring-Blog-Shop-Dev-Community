package com.spring.blogshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.spring.blogshop.entity.Book;
import com.spring.blogshop.responsitory.BookResponsitory;
import com.spring.blogshop.service.BookService;

@Transactional
@Service
public class BookServiceImpl implements BookService{
	
	private BookResponsitory bookResponsitory;
	
	@Autowired
	public BookServiceImpl(BookResponsitory bookResponsitory) {
		this.bookResponsitory=bookResponsitory;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findAll() {	
		return Lists.newArrayList(bookResponsitory.findAll());
		
	}

	@Override
	@Transactional(readOnly = true)
	public Book findById(Long id) {
		return bookResponsitory.findById(id).get();
	}

	@Override
	public Book save(Book book) {
		return bookResponsitory.save(book);
	}

	@Autowired
	public void setBookRepository(BookResponsitory bookResponsitory) {
		this.bookResponsitory = bookResponsitory;
	}
	

	@Override
	@Transactional(readOnly = true)
	public Page<Book> findAllByPage(Pageable pageable) {
		return bookResponsitory.findAll(pageable);
	}
	
}
