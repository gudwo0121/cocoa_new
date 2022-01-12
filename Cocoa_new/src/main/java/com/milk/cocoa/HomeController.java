package com.milk.cocoa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	// 홈 화면 이동
	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView home() throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/home";
		mav.setViewName(url);
		return mav;
	}
}
