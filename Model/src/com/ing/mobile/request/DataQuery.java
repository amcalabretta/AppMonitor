package com.ing.mobile.request;

import com.ing.mobile.request.criteria.Criteria;
import com.ing.mobile.request.data.DataDomain;
import com.ing.mobile.request.data.DataType;


public class DataQuery {
	private DataDomain dataDomain;
	private DataType dataType;
	private Criteria criteria;
	
	public DataQuery(DataDomain dataDomain, DataType dataType, Criteria criteria) {
		super();
		this.dataDomain = dataDomain;
		this.dataType = dataType;
		this.criteria = criteria;
	}

	public DataDomain getDataDomain() {
		return dataDomain;
	}

	public void setDataDomain(DataDomain dataDomain) {
		this.dataDomain = dataDomain;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
}