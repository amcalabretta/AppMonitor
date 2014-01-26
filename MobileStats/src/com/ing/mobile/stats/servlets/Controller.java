package com.ing.mobile.stats.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ing.mobile.model.dto.AndroidApplication;
import com.ing.mobile.model.dto.IOSApplication;
import com.ing.mobile.model.dto.WindowsPhoneApplication;
import com.ing.mobile.request.criteria.ApplicationsCriteria;
import com.ing.mobile.request.data.DataDomain;
import com.ing.mobile.request.data.DataType;
import com.ing.mobile.service.ServiceFactory;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/stats/*")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger("controller");
	public static String IOS_APP_ATTRIBUTE = "IOS_APP_ATTRIBUTE";
	public static String ANDROID_APP_ATTRIBUTE = "ANDROID_APP_ATTRIBUTE";
	public static String WINDOWS_PHONE_APP_ATTRIBUTE = "WINDOWS_PHONE_APP_ATTRIBUTE";
	
	/**
     * Default constructor. 
     */
    public Controller() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		List<IOSApplication> iosApplications = (List<IOSApplication>) ServiceFactory.getService(DataDomain.IOS, DataType.APPLICATIONS).getData(DataType.APPLICATIONS, DataDomain.IOS, new ApplicationsCriteria());
		List<AndroidApplication> androidApplications = (List<AndroidApplication>) ServiceFactory.getService(DataDomain.ANDROID, DataType.APPLICATIONS).getData(DataType.APPLICATIONS, DataDomain.ANDROID, new ApplicationsCriteria());
		List<WindowsPhoneApplication> wpApplications = (List<WindowsPhoneApplication>) ServiceFactory.getService(DataDomain.WINDOWS_PHONE, DataType.APPLICATIONS).getData(DataType.APPLICATIONS, DataDomain.WINDOWS_PHONE, new ApplicationsCriteria());
		log.debug("returning "+iosApplications.size()+" IOS Applications");
        log.debug("returning "+androidApplications.size()+" Android Applications");
        log.debug("returning "+wpApplications.size()+" Windows Phone Applications");
        request.setAttribute(IOS_APP_ATTRIBUTE, iosApplications );
        request.setAttribute(ANDROID_APP_ATTRIBUTE, androidApplications );
        request.setAttribute(WINDOWS_PHONE_APP_ATTRIBUTE, wpApplications );
		RequestDispatcher dispatcher = context.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request,response);
	}
}