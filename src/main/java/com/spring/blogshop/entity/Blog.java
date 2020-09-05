package com.spring.blogshop.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table
@RequiredArgsConstructor
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String header;
	
	@Column
	private String title;
	
	@Column
	private String author;
	
	@Column
	private String content;
	
	@Column
	private Date timeCreate;
	
	@Basic(fetch= FetchType.LAZY)
    @Column
    @Lob
    private byte[] imageBlog;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User user;
	
//	@ManyToMany(mappedBy = "blogs",fetch = FetchType.EAGER)
//    protected List<BookMark> bookMarks = new ArrayList<BookMark>();

	@OneToMany(mappedBy="blog",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<PostBookMark> postBookMark=new ArrayList<PostBookMark>();
	
	
	@OneToMany(mappedBy="blog",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Vote> Votes=new ArrayList<Vote>();
	
	public Blog(Long id, String header, String title, String author, String content, Date timeCreate, byte[] imageBlog,
			User user) {
		super();
		this.id = id;
		this.header = header;
		this.title = title;
		this.author = author;
		this.content = content;
		this.timeCreate = timeCreate;
		this.imageBlog = imageBlog;
		this.user = user;
	}
	
	public Blog(Long id, String header, String title, String author, String content, Date timeCreate, User user) {
		super();
		this.id = id;
		this.header = header;
		this.title = title;
		this.author = author;
		this.content = content;
		this.timeCreate = timeCreate;
		this.user = user;
	}	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blog other = (Blog) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", header=" + header + ", title=" + title + ", author=" + author + ", content="
				+ content + ", timeCreate=" + timeCreate + ", imageBlog=" + Arrays.toString(imageBlog) + ", user="
				+ user + "]";
	}



	
}
