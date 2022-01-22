package com.milk.cocoa.member;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("memberController")
public class MemberControllerImpl {

	@Autowired
	private MemberServiceImpl memberServiceImpl;

	// 로그인 화면 이동
	@RequestMapping(value = "/goLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/login";
		mav.setViewName(url);
		return mav;
	}

	// 회원가입 화면 이동
	@RequestMapping(value = "/goSignUp", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goSignUp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/signUp";
		mav.setViewName(url);
		return mav;
	}

	// 회원가입
	@ResponseBody
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public int SignUp(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = memberServiceImpl.insertMemberListService(memberVO);
		return result;
	}

	// 아이디 중복확인 (java.lang.integer 에러 = ResponseBody 미스)
	@ResponseBody
	@RequestMapping(value = "/idChk", method = RequestMethod.POST)
	public int idCheck(MemberVO memberVO) {
		int result = 0;
		result = memberServiceImpl.selectCountByIdService(memberVO);
		return result;
	}

	// 로그인 검증
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO memberVO, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		MemberVO loginMember = memberServiceImpl.selectMemberByIdService(memberVO);
		String view = request.getParameter("view");
		if (memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
		} else {
			rAttr.addAttribute("result", "loginFailed");
		}
		return mav;
	}

}
