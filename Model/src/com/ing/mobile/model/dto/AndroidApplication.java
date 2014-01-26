package com.ing.mobile.model.dto;

import java.io.Serializable;

public class AndroidApplication implements Serializable {
	private static final long serialVersionUID = -1300066045108303816L;
	private int id;
	private String appDescription;
	private String packageName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAppDescription() {
		return appDescription;
	}
	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}