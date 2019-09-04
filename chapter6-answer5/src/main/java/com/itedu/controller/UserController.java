package com.itedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itedu.dto.User;
import com.itedu.service.UserService;

@Controller
public class UserController {
	@Autowired
	public UserService userService;

	@RequestMapping("/login")
	public ModelAndView login(User user) {
		ModelAndView mv = new ModelAndView();
		User u = userService.selectUserByUsernameAndPassword(user);
		// 根据用户名和密码查询user，如果存在，则跳转到 success.jsp 页面
		if (u != null) {
			mv.addObject("name", u.getUsername());
			mv.addObject("user", u);
			mv.setViewName("success");
		} else {
			// 如果不存在，则跳转到 login.jsp页面重新登录
			return new ModelAndView("redirect:/login.jsp");
		}
		return mv;
	}

}