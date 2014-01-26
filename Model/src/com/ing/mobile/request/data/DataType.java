package com.ing.mobile.request.data;

public enum DataType {
	RATINGS("ratings"),RELEASES("releases"),APPLICATIONS("applications");

	private String code;
	private DataType(String code) {
		this.code = code;
	}

	public static DataType fromString(String code) {
		for (DataType type: values()) {
			if (type.code.equals(code)) 
				return type;
		}
		return null;
	}

}
