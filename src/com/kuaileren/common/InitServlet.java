package com.kuaileren.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InitServlet extends HttpServlet {

	private static final Log log = LogFactory.getLog(InitServlet.class);

	private static final long serialVersionUID = 6066089839055144264L;
	
	
	public void init(ServletConfig servletConfig) throws ServletException {

		System.out.println("系统正在启动");

	}
	
}
