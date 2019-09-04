package com.itedu.test.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itedu.spring.framework.annotation.Autowired;
import com.itedu.spring.framework.annotation.Controller;
import com.itedu.spring.framework.annotation.RequestMapping;
import com.itedu.spring.framework.annotation.RequestParam;
import com.itedu.spring.framework.webmvc.ModelAndView;
import com.itedu.test.service.ModifyService;
import com.itedu.test.service.QueryService;

@Controller
@RequestMapping("/login")
public class HomeController {

	@Autowired
	QueryService queryService;

	@Autowired
	ModifyService modifyService;

	@RequestMapping("/test")
	public ModelAndView query(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("name") String name) {
		String result = queryService.query(name);
//		System.out.println(result);
		Map mapBody = new HashMap();
		mapBody.put("username", "365itedu");
		ModelAndView mv = new ModelAndView("login.html", mapBody);
		return mv;
//        return out(response,result);
	}

	private String out(HttpServletResponse resp, String str) {
		try {
			resp.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
