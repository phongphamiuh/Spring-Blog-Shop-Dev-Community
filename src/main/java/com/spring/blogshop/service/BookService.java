package com.spring.blogshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.blogshop.entity.Book;

public interface BookService {
		List<Book> findAll();
		Book findById(Long id);
		Book save(Book book);
	    Page<Book> findAllByPage(Pageable pageable);
}
