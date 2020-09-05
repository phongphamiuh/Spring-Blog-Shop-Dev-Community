package com.spring.blogshop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Blob;
import java.util.Arrays;
import java.util.Date;

@Data
@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
public class Book {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;

    @Column
    private String bookName;
    
    @Column  
    private String author;
    
    @Column 
    private double price;
   
    @Column
    private Date publishDate;
    
    @Column
    private String description;
    
    @Column
    private int page;
    
    @Basic(fetch= FetchType.LAZY)
    @Column
    @Lob
    private byte[] image;
     
}
