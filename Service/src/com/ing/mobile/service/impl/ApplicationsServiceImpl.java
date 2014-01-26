package com.ing.mobile.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ing.mobile.hibernate.HibernateUtil;
import com.ing.mobile.request.criteria.Criteria;
import com.ing.mobile.request.data.DataDomain;
import com.ing.mobile.request.data.DataType;
import com.ing.mobile.service.Service;


/**
 * Service returning all the applications, depending on the datadomain, it selects 
 * all the application referring to it.
 * */
public class ApplicationsServiceImpl implements Service {
	
	@Override
	public Object getData(DataType dataType, DataDomain dataDomain,Criteria criteria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List result = null;
		try {
			transaction = session.beginTransaction();
			Query query = null;
			switch (dataDomain) {
			case IOS:
					query = session.createQuery("FROM IOSApplication app");
					query.setCacheable(true);
					query.setCacheRegion("query.getApplications");
				break;
			case ANDROID:
					query = session.createQuery("FROM AndroidApplication app");
					query.setCacheable(true);
					query.setCacheRegion("query.getApplications");
				break;
			case WINDOWS:
					query = session.createQuery("FROM IOSApplication app");
					query.setCacheable(true);
					query.setCacheRegion("query.getApplications");
				break;
			case WINDOWS_PHONE:
					query = session.createQuery("FROM WindowsPhoneApplication app");
					query.setCacheable(true);
					query.setCacheRegion("query.getApplications");
				break;
			default:
				break;
			}
			result = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return result;
	}
}