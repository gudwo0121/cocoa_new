package com.milk.cocoa.coaching;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("coachingController")
public class CoachingControllerImpl {

	@Autowired
	private CoachingVO coachingVO;
	@Autowired
	private CoachingServiceImpl coachingServiceImpl;

	// 코칭 리스트 이동
	@RequestMapping(value = "/coachingList", method = RequestMethod.POST)
	public ModelAndView goCoachingList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/coaching";
		mav.setViewName(url);
		
		// 코칭 리스트 전체 조회
		List<CoachingVO> coachingList = coachingServiceImpl.selectCoachingListService();
		mav.addObject("coachingList", coachingList);
		
		return mav;
	}

	// 코칭 등록 이동
	@RequestMapping(value = "/coachingWrite", method = RequestMethod.POST)
	public ModelAndView goCoachingWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/coachingWrite";
		mav.setViewName(url);
		return mav;
	}

}
