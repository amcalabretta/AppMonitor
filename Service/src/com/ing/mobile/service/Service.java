package com.ing.mobile.service;

import com.ing.mobile.request.criteria.Criteria;
import com.ing.mobile.request.data.DataDomain;
import com.ing.mobile.request.data.DataType;

public interface Service {
	Object getData(DataType dataType,DataDomain dataDomain,Criteria criteria);
}
