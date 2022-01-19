package com.milk.cocoa.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("memberController")
public class MemberControllerImpl {

	// 로그인 화면 이동
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/login";
		mav.setViewName(url);
		return mav;
	}

	// 회원가입 화면 이동
	@RequestMapping(value = "/signUp", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/signUp";
		mav.setViewName(url);
		return mav;
	}
}
