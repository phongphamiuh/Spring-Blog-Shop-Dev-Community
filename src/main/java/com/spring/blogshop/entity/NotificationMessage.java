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
public class NotificationMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String userNotification;
	
	@Column
	private String message;
		
	@Column
	private Date timeNotification;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User user;
	
	public NotificationMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public NotificationMessage() {
		// TODO Auto-generated constructor stub
	}

	public NotificationMessage(String userNotification,String message, Date timeNotification, User user) {
		super();
		this.userNotification=userNotification;
		this.message = message;
		this.timeNotification = timeNotification;
		this.user = user;
	}

	@Override
	public String toString() {
		return "NotificationMessage [id=" + id + ", userNotification=" + userNotification + ", message=" + message
				+ ", timeNotification=" + timeNotification + ", user=" + user + "]";
	}

	
	
	
	
}
