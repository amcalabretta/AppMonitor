package com.ing.mobile.stats.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.ing.mobile.parser.RequestParser;
import com.ing.mobile.request.DataQuery;
import com.ing.mobile.service.Service;
import com.ing.mobile.service.ServiceFactory;

/**
 * Servlet implementation class DataServlet
 */
@WebServlet("/data/*")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger("dataLogger");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream os = response.getOutputStream();
		response.setContentType("application/json");
		response.setHeader("Content-type","text/html");
		Gson gson = new Gson();
		try {
			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String paramName = parameterNames.nextElement();
				String[] paramValues = request.getParameterValues(paramName);
				log.debug("Parameter name:"+paramName+",value:"+paramValues[0]);
			}
			DataQuery dataQuery = RequestParser.parseRequest(request);
			log.debug("DataQuery type:"+dataQuery.getDataType()+", domain:"+dataQuery.getDataDomain());
			Service service = ServiceFactory.getService(dataQuery.getDataDomain(),dataQuery.getDataType());
			Object rawResponse = service.getData(dataQuery.getDataType(),dataQuery.getDataDomain(), dataQuery.getCriteria());
			log.debug("Response type:"+rawResponse.getClass().getName());
			os.print(gson.toJson(rawResponse));
			log.debug("======================");
		} catch (Exception ex) {
			log.error("Error", ex);
		} finally {
			os.flush();
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
