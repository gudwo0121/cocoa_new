package com.milk.cocoa.coaching;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("coachingController")
public class CoachingControllerImpl {

	// 코칭 리스트 이동
	@RequestMapping(value = "/coachingList", method = RequestMethod.POST)
	public ModelAndView viewCoachingList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/coaching";
		mav.setViewName(url);
		return mav;
	}
	
	// 코칭 등록 이동
	@RequestMapping(value = "/coachingWrite", method = RequestMethod.POST)
	public ModelAndView viewCoachingWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/coachingWrite";
		mav.setViewName(url);
		return mav;
	}
	
}
