package com.milk.cocoa.review;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.milk.cocoa.request.RequestServiceImpl;
import com.milk.cocoa.request.RequestVO;

@RestController("reviewController")
public class ReviewControllerImpl {

	@Autowired
	private ReviewVO reviewVO;
	@Autowired
	private RequestVO requestVO;
	@Autowired
	private ReviewServiceImpl reviewServiceImpl;
	@Autowired
	private RequestServiceImpl requestServiceImpl;

	// 후기작성
	@ResponseBody
	@RequestMapping(value = "/reviewWrite", method = RequestMethod.POST)
	public ResponseEntity reviewWrite(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws IOException {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> reviewMap = new HashMap<String, Object>();

		// 입력된 정보를 map에 담아 쿼리문 처리
		// 쿼리문에 들어가야할 속성 하나라도 빠지면 에러 뜸 = 흰 화면만 뜸 + NullPointerException 발생
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			reviewMap.put(name, value);
		}
		// 세션 불러오기
		HttpSession session = multipartRequest.getSession();

		// 리다이렉트될 페이지 경로를 위한 요청 넘버
		String reqNO = (String) reviewMap.get("reqNO");

		// 후기작성 여부를 삽입시키기 위한 RequestVO
		requestVO.setReqNO(Integer.parseInt(reqNO));

		// 성공, 실패 시 알림
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			int isInserted = reviewServiceImpl.insertReviewService(reviewMap);
			int isUpdated = requestServiceImpl.updateRateInfoService(requestVO);

			if (isInserted == 1) {
				message = "<script>";
				message += " alert('후기작성이 완료되었습니다.');";
				message += " location.href='" + multipartRequest.getContextPath() + "/request/sent/" + reqNO + "';";
				message += " </script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			}

		} catch (Exception e) {

			message = " <script>";
			message += " alert('후기작성에 실패했습니다. 다시 시도해주세요.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/request/sent/" + reqNO + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.BAD_REQUEST);
		}
		return resEnt;
	}

	
	
}