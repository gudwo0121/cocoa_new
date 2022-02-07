package com.milk.cocoa.coaching;

import java.io.Console;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.milk.cocoa.member.MemberVO;

@Controller("coachingController")
public class CoachingControllerImpl {

	@Autowired
	private CoachingVO coachingVO;
	@Autowired
	private CoachingServiceImpl coachingServiceImpl;

	// 코칭 리스트 이동
	@RequestMapping(value = "/goCoachingList", method = { RequestMethod.GET, RequestMethod.POST })
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
	@RequestMapping(value = "/goCoachingWrite", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goCoachingWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/coachingWrite";
		mav.setViewName(url);
		return mav;
	}

	// 코칭 등록
	@ResponseBody
	@RequestMapping(value = "/coachingWrite", method = RequestMethod.POST)
	public ResponseEntity coachingWrite(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> coachingMap = new HashMap<String, Object>();

		// 입력된 정보를 map에 담아 쿼리문 처리
		// 쿼리문에 들어가야할 속성 하나라도 빠지면 에러 뜸 = 흰 화면만 뜸 + NullPointerException 발생
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			coachingMap.put(name, value);
		}

		// 세션 불러오기
		HttpSession session = multipartRequest.getSession();

		// memberVO로 세션에 저장된 로그인한 회원의 정보를 저장
		MemberVO memberVO = (MemberVO) session.getAttribute("member");

		// coachWriteForm에 존재하지 않는 id 직접 입력
		String id = memberVO.getId();
		coachingMap.put("coach", id);

//		// coachWriteForm에 불러온 파일(이미지) 직접 입력
//		String cImg = this.upload(multipartRequest);
		// 하드코딩
		String cImg = "test";
		coachingMap.put("cImg", cImg);

		// 성공, 실패 시 알림
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			// 다운로드 파일을 작성하는 회원의 id(coach)로 폴더 생성
			// 삭제가 되어도 그 후에 해당 회원이 업로드를 하면 같은 폴더에 생성이 됌
			int coachNO = coachingServiceImpl.insertCoachingListService(coachingMap);
//			if (cImg != null && cImg.length() != 0) {
//				File srcFile = new File(COACH_IMAGE_REPO + "/" + "temp" + "/" + cImg);
//				File destDir = new File(COACH_IMAGE_REPO + "/" + id + "/" + coachNO);
//				FileUtils.moveFileToDirectory(srcFile, destDir, true);
//			}

			message = "<script>";
			message += " alert('등록이 완료되었습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/goCoachingList'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {

//			File srcFile = new File(COACH_IMAGE_REPO + "/" + "temp" + "/" + cImg);
//			srcFile.delete();

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해주세요.');');";
			message += " location.href='" + multipartRequest.getContextPath() + "/goCoachingList'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		return resEnt;
	}
}
