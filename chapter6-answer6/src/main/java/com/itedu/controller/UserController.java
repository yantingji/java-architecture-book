package com.itedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itedu.dto.UserDto;
import com.itedu.form.UserForm;
import com.itedu.service.IUserService;

@Controller
public class UserController {
	@Autowired
	public IUserService userService;

	@ModelAttribute // (1)
    public UserForm setUserForm() {
		UserForm userForm = new UserForm();
        return userForm;
    }

	@RequestMapping("/")
	public String welcome(UserForm userForm){
		return "login";
	}


	@RequestMapping("/login")
	public ModelAndView login(UserForm user){
		ModelAndView mv = new ModelAndView();

		UserDto userDto = new UserDto();
		UserDto u = userService.selectUserByUsernameAndPassword(userDto);
		//根据用户名和密码查询user，如果存在，则跳转到 success.jsp 页面
		if(u != null){
			mv.addObject("name", u.getName());
			mv.addObject("user", u);
			mv.setViewName("success");
		}else{
			//如果不存在，则跳转到 login.jsp页面重新登录
			return new ModelAndView("redirect:/login.jsp");
		}
		return mv;
	}

}