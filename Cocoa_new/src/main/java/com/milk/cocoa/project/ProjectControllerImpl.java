package com.milk.cocoa.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("projectController")
public class ProjectControllerImpl {

	@Autowired
	private ProjectVO projectVO;
	@Autowired
	private ProjectServiceImpl projectServiceImpl;

	// 프로젝트 리스트 화면 이동
	@RequestMapping(value = "/goProjectList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goProjectList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/project";
		mav.setViewName(url);

		// 프로젝트 리스트 전체 조회
		List<ProjectVO> projectList = projectServiceImpl.selectProjectListService();
		mav.addObject("projectList", projectList);

		return mav;
	}

	// 프로젝트 모집 이동
	@RequestMapping(value = "/goProjectWrite", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goProjectWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/projectWrite";
		mav.setViewName(url);
		return mav;
	}
}
