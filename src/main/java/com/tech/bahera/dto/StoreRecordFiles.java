package com.tech.bahera.dto;

import java.io.File;
import java.io.Serializable;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="userfiles")
public class StoreRecordFiles implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Field
    private String name;
	
	@Field
	private String dateTime; 

	@Id
    @Field
    private String emailId;

    @Field
    private String contentType;

    @Field
    private Binary file;

    public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Binary getFile() {
		return file;
	}

	public void setFile(Binary file) {
		this.file = file;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "createRecords [name=" + name + ", dateTime=" + dateTime + ", emailId=" + emailId
				+ ", docType=" + contentType + ", file=" + file + "]";
	}

	

    
}
