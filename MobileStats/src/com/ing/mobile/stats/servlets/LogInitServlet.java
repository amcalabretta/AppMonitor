package com.ing.mobile.stats.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


import net.sf.ehcache.CacheManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet used to start up log4j and shut down ehcache on destroy.
 */
@WebServlet(
		urlPatterns = { "/initLog" },
		loadOnStartup = 1,
		initParams = { 
				@WebInitParam(name = "log4j-init-file", value = "WEB-INF/classes/log4j.properties")
		})

public class LogInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
        String prefix =  getServletContext().getRealPath("/");
        String file = getInitParameter("log4j-init-file");
        // if the log4j-init-file is not set, then no point in trying
        if(file != null) {
          PropertyConfigurator.configure(prefix+file);
        }
        Logger.getLogger("initialisation").debug("Application started");
	}

	@Override
	public void destroy() {
		Logger.getLogger("initialisation").debug("Application stopped");
		CacheManager.getCacheManager("STATS_CACHE_MANAGER").shutdown();
		Logger.getLogger("initialisation").debug("Cache shut down");
		super.destroy();
	}
	
	

}
