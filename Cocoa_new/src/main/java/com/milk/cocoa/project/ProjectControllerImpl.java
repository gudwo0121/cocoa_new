package com.milk.cocoa.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("projectController")
public class ProjectControllerImpl {

	// 프로젝트 리스트 화면 이동
	@RequestMapping(value = "/projectList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView view_project(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/project";
		mav.setViewName(url);
		return mav;
	}

	// 프로젝트 모집 이동
	@RequestMapping(value = "/projectWrite", method = RequestMethod.POST)
	public ModelAndView viewProjectWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/projectWrite";
		mav.setViewName(url);
		return mav;
	}
}
