package com.ing.mobile.service;

import org.apache.log4j.Logger;

import com.ing.mobile.request.data.DataDomain;
import com.ing.mobile.request.data.DataType;
import com.ing.mobile.service.impl.ApplicationsServiceImpl;


public class ServiceFactory {
	private static Logger log = Logger.getLogger("serviceFactory");
	private static Service applicationService = new ApplicationsServiceImpl();
	
	public static Service getService(DataDomain domain, DataType dataType) {
		log.debug("Service factory domain"+domain+",type:"+dataType);
//		if (domain.equals(DataDomain.ANDROID) && dataType.equals(DataType.RAW_DATA)) {
//			return rawDataAndroidService;
//		}
		if (dataType.equals(DataType.APPLICATIONS)) {
			log.debug("Service factory returning applications service");
			return applicationService;
		}
		log.debug("Service factory returning null");
		return null;
	}
}