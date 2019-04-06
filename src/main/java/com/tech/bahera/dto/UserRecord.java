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
private String reportName;

@Field
private String usrReportRefId;
@Field
private String doctorName;
@Field
private String doctorFee;
@Field
private String hospName;
@Field
private String reportsFee;
@Field
private List<String> reportFiles;

@Field
private List<String> reportFileIds;

@Field
private String datetime;

@Field
private String labCenterId;

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

public String getReportName() {
	return reportName;
}

public void setReportName(String reportName) {
	this.reportName = reportName;
}

public String getUsrReportRefId() {
	return usrReportRefId;
}

public void setUsrReportRefId(String usrReportRefId) {
	this.usrReportRefId = usrReportRefId;
}

public String getDoctorName() {
	return doctorName;
}

public void setDoctorName(String doctorName) {
	this.doctorName = doctorName;
}

public String getDoctorFee() {
	return doctorFee;
}

public void setDoctorFee(String doctorFee) {
	this.doctorFee = doctorFee;
}

public String getHospName() {
	return hospName;
}

public void setHospName(String hospName) {
	this.hospName = hospName;
}

public String getReportsFee() {
	return reportsFee;
}

public void setReportsFee(String reportsFee) {
	this.reportsFee = reportsFee;
}

public List<String> getReportFiles() {
	return reportFiles;
}

public void setReportFiles(List<String> reportFiles) {
	this.reportFiles = reportFiles;
}

public List<String> getReportFileIds() {
	return reportFileIds;
}

public void setReportFileIds(List<String> reportFileIds) {
	this.reportFileIds = reportFileIds;
}

public String getDatetime() {
	return datetime;
}

public void setDatetime(String datetime) {
	this.datetime = datetime;
}

public String getLabCenterId() {
	return labCenterId;
}

public void setLabCenterId(String labCenterId) {
	this.labCenterId = labCenterId;
}

@Override
public String toString() {
	return "UserRecord [id=" + id + ", emailId=" + emailId + ", reportName=" + reportName + ", usrReportRefId="
			+ usrReportRefId + ", doctorName=" + doctorName + ", doctorFee=" + doctorFee + ", hospName=" + hospName
			+ ", reportsFee=" + reportsFee + ", reportFiles=" + reportFiles + ", reportFileIds=" + reportFileIds
			+ ", datetime=" + datetime + ", labCenterId=" + labCenterId + "]";
}




}

