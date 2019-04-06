package com.tech.bahera.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class EmailUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String emailId;
	private String subject;
	private String message;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Override
	public String toString() {
		return "EmailUser [userName=" + username + ", emailId=" + emailId + ", subject=" + subject + ", message="
				+ message + "]";
	}
	
	
	
	
}
