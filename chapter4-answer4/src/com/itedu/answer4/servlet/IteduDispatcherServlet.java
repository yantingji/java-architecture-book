package com.itedu.answer4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IteduDispatcherServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String urlStr = request.getRequestURI();
        if("/chapter4-answer4/home".equals(urlStr)) {
        	request.getSession().setAttribute("userId", "00001");
        	request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
    	}else if("".equals(urlStr)) {
    		request.getRequestDispatcher("/WEB-INF/jsp/XXX.jsp").forward(request, response);
    	}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String urlStr = request.getRequestURI();
    	if("/chapter4-answer4/user".equals(urlStr)) {
    		request.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(request, response);
    		
    	}else if("".equals(urlStr)) {
    		request.getRequestDispatcher("/WEB-INF/jsp/XXX.jsp").forward(request, response);
    	}
       
    }
}
