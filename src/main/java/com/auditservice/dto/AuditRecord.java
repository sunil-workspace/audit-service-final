package com.auditservice.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection="AuditRecord")
public class AuditRecord {

	@Id
	String userId;
	String apiPath;
	String apiMethod;
	//Date apiCallTime;
	int apiResponseCode;
	
	public AuditRecord() {
		//no-arg constructor
	};
	
	public AuditRecord(String userId, String apiPath, String apiMethod, Date apiCallTime, int apiResponseCode) {
		this.userId = userId;
		this.apiPath = apiPath;
		this.apiMethod = apiMethod;
		//this.apiCallTime = apiCallTime;
		this.apiResponseCode = apiResponseCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public String getApiMethod() {
		return apiMethod;
	}

	public void setApiMethod(String apiMethod) {
		this.apiMethod = apiMethod;
	}

	/*public Date getApiCallTime() {
		return apiCallTime;
	}

	public void setApiCallTime(Date apiCallTime) {
		this.apiCallTime = apiCallTime;
	}*/

	public int getApiResponseCode() {
		return apiResponseCode;
	}

	public void setApiResponseCode(int apiResponseCode) {
		this.apiResponseCode = apiResponseCode;
	}

	
}
