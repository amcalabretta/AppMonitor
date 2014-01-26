package com.ing.mobile.request.data;

public enum DataDomain {
	ANDROID("android"),IOS("ios"),WINDOWS("windows"),WINDOWS_PHONE("wp");
	private String code;
	
	private DataDomain(String code) {
		this.code = code;
	}

	public static DataDomain fromString(String code) {
		for (DataDomain domain: values()) {
			if (domain.code.equals(code)) 
				return domain;
		}
		return null;
	}
}
