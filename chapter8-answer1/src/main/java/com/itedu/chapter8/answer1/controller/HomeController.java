package com.itedu.chapter8.answer1.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@ModelAttribute
    public UserForm setUpForm() {
        return new UserForm();
    }

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(UserForm user, Model model) {

		return "home";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Valid  UserForm user, BindingResult result, Model model) {
		if (result.hasErrors()) {
            return "home";
        }
		System.out.println("User Page Requested");
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("phone", user.getPhone());
		return "user";
	}
}
