package com.ing.mobile.model.dto;

import java.io.Serializable;

public class WindowsPhoneApplication implements Serializable {
	private static final long serialVersionUID = -4811534098547351261L;
	private int id;
	private String description;
	private String code;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desccription) {
		this.description = desccription;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
