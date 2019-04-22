package com.tech.bahera.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="userrecords")
public class UserRecord implements Serializable{
	
/**
	 * 
	 */

private static final long serialVersionUID = 1L;

@Id
@Field
private String id;
@Field
private String emailId;
@Field
private String jobTitle;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getJobTitle() {
	return jobTitle;
}
public void setJobTitle(String jobTitle) {
	this.jobTitle = jobTitle;
}


}

