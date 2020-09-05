package com.spring.blogshop.responsitory;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.spring.blogshop.entity.Book;
@Repository
public interface BookResponsitory extends PagingAndSortingRepository<Book, Long>{
}
