package com.spring.blogshop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Comment {
	@Id
	@GeneratedValue(generator = Constants.ID_GENERATOR)
	private Long id;
	
	@Column
	private String user;
	
	@Column
	private String message;
	
	@Column
	private Date timestamp;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BLOG_ID", nullable = false)
	public Blog blog;

	@Override
	public String toString() {
		return "Comment [id=" + id + ", user=" + user + ", message=" + message + ", timestamp=" + timestamp + ", blog="
				+ blog + "]";
	}

	public Comment(String user, String message, Date timestamp) {
		super();
		this.user = user;
		this.message = message;
		this.timestamp = timestamp;
	}

	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	

	
	
	
}
