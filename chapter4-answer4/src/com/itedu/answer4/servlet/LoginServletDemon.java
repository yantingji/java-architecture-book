package com.itedu.answer4.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itedu.answer4.dao.LoginDao;
import com.itedu.answer4.dto.UserDto;

public class LoginServletDemon extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String username = request.getParameter("username");
         String password = request.getParameter("password");
         System.out.println("username=" + username);
         System.out.println("password=" + password);
         LoginDao loginDao=new LoginDao();
         try {
			UserDto user = loginDao.getUser(username);
			if(user!=null) {
				if(user.getPassword().trim().equals(password)) {
					  request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
				}else {
					  request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
