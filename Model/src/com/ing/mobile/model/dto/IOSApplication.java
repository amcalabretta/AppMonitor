package com.ing.mobile.model.dto;

import java.io.Serializable;
/**
 * 
 * */
public class IOSApplication implements Serializable {
	private static final long serialVersionUID = -1927842717468685655L;
	private int id;
	private int appId;
	private String code;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
