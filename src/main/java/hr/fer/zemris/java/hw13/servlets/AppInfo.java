package hr.fer.zemris.java.hw13.servlets;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * This is a class that represents an application information listener. Whenever
 * the web application starts, this listener will update the information about
 * the initialization of the same web app.
 * 
 * @author Dinz
 *
 */
@WebListener
public class AppInfo implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent req) {
		long time = new Date().getTime();
		req.getServletContext().setAttribute("startTime", time);
	}

}
