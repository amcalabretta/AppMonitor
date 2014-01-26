package com.ing.mobile.request.criteria;

import java.util.Calendar;

public class RawDataCriteria implements Criteria{
	private Calendar startDate;
	private Calendar endDate;
	
	public RawDataCriteria(Calendar startDate, Calendar endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
}
