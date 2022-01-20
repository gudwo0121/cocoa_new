package com.milk.cocoa.member;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("memberController")
public class MemberControllerImpl {

	@Autowired
	private MemberServiceImpl memberServiceImpl;
	@Autowired
	private MemberVO memberVO;

	// 로그인 화면 이동
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/login";
		mav.setViewName(url);
		return mav;
	}

	// 회원가입 화면 이동
	@RequestMapping(value = "/signUp", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goSignUp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/signUp";
		mav.setViewName(url);
		return mav;
	}

	// 회원가입
	@ResponseBody
	@RequestMapping(value = "/signingUp", method = RequestMethod.POST)
	public int SignUp(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = memberServiceImpl.insertMemberListService(memberVO);
		return result;
	}

}
