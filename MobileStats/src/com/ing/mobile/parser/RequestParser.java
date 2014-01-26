package com.ing.mobile.parser;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.ing.mobile.request.DataQuery;
import com.ing.mobile.request.criteria.ApplicationsCriteria;
import com.ing.mobile.request.criteria.Criteria;
import com.ing.mobile.request.data.DataDomain;
import com.ing.mobile.request.data.DataType;

public class RequestParser {
	private static String DOMAIN_PARAMETER = "domain";
	private static String START_DATE = "startDate";
	private static String END_DATE = "endDate";
	private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");
	private static Logger log = Logger.getLogger("reqParser");
	
	public static DataQuery parseRequest(HttpServletRequest request) {
		try {
			DataDomain domain = DataDomain.fromString(request.getParameter(DOMAIN_PARAMETER));
			String requestUrl = request.getRequestURL().toString();
			String urlType = requestUrl.substring(requestUrl.lastIndexOf('/')+1);
			DataType type = DataType.fromString(urlType);
			log.debug("domain:"+domain+",type:"+type);
			Criteria criteria = null;
			switch (type) {
				case RELEASES://releases
//					DateTime dateTimeStart = DateTime.parse("", formatter);
//					DateTime dateTimeEnd = DateTime.parse("", formatter);
//					if (request.getParameter(START_DATE)!=null) {
//						log.debug("setting the start date");
//						start = Calendar.getInstance();
//						start.setTime(formatter.getParser(). parse(request.getParameter("startDate")));
//					}
//					if (request.getParameter(END_DATE)!=null) {
//						log.debug("setting the end date");
//						end = Calendar.getInstance();
//						end.setTime(formatter.parse(request.getParameter("endDate")));
//					}
//					criteria = new ReleasesCriteria();
				break;
				case APPLICATIONS://applications
					criteria = new ApplicationsCriteria();
				break;
				default:
					break;
			}
			return new DataQuery(domain, type, criteria);
		} catch(Exception ex) {
			log.error("Exception", ex);
			return null;
		}	
	}
}