package com.tech.bahera.dto;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection="useraddr")
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;


	private String addline1;
		
	private String addline2;
		
	private String city;
		
	private String state;
		
	private String country;
		
	private String pcode;

	private String emailId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddline1() {
		return addline1;
	}

	public void setAddline1(String addline1) {
		this.addline1 = addline1;
	}

	public String getAddline2() {
		return addline2;
	}

	public void setAddline2(String addline2) {
		this.addline2 = addline2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", addline1=" + addline1 + ", addline2=" + addline2 + ", city=" + city + ", state="
				+ state + ", country=" + country + ", pcode=" + pcode + ", emailId=" + emailId + "]";
	}

	
	
	
}
