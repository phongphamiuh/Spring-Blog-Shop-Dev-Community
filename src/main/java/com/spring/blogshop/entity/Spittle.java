package com.spring.blogshop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table
public class Spittle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String user;
	
	@Column
	private String message;
	
	@Column
	private Date timestamp;
	
	public Spittle(String user, String message, Date timestamp) {
		super();
		this.user = user;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "Spittle [id=" + id + ", user=" + user + ", message=" + message + ", timestamp=" + timestamp + "]";
	}
	
	
}
