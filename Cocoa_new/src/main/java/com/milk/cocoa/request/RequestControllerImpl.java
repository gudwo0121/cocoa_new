package com.milk.cocoa.request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.milk.cocoa.member.MemberVO;

@RestController("requestController")
public class RequestControllerImpl {

	// rImg 다운로드 경로 (FTP시 "/opt/cocoa/image/request") = 기본이 로컬 C드라이브고 그 뒤 경로 입력
	private static final String REQUEST_IMAGE_REPO = "/cocoaRepo/requestImg";

	@Autowired
	private RequestVO requestVO;
	@Autowired
	private RequestServiceImpl requestServiceImpl;

	// 코칭 요청 이동 = coachNO & res 정보는 requestPost에서 받아놓은 상태
	@RequestMapping(value = "/goSendRequest", method = RequestMethod.POST)
	public ModelAndView goSendRequest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "";

		// 세션 값으로 로그인 여부 판단
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");

		// 비 로그인시 로그인 화면 강제 이동
		if (memberVO == null) {
			url = "/login";
		} else {
			url = "/requestWrite";
		}

		// 코칭 요청 시 res 값으로 필요
		mav.addObject("res", request.getParameter("res"));
		// 코칭 요청 후 돌아가는 페이지 이동에 필요
		mav.addObject("coachNO", request.getParameter("coachNO"));

		mav.setViewName(url);
		return mav;
	}

	// 코칭 요청
	@ResponseBody
	@RequestMapping(value = "/requestWrite", method = RequestMethod.POST)
	public ResponseEntity requestWrite(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws IOException {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> requestMap = new HashMap<String, Object>();

		// 입력된 정보를 map에 담아 쿼리문 처리
		// 쿼리문에 들어가야할 속성 하나라도 빠지면 에러 뜸 = 흰 화면만 뜸 + NullPointerException 발생
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			requestMap.put(name, value);
		}

		// 세션 불러오기
		HttpSession session = multipartRequest.getSession();

		// memberVO로 세션에 저장된 로그인한 회원의 정보를 저장
		MemberVO memberVO = (MemberVO) session.getAttribute("member");

		// id 직접 입력 = 요청자(req)
		String req = memberVO.getId();
		requestMap.put("req", req);

		// coachNO 직접 입력
		String coachNO = (String) requestMap.get("coachNO");

		// rImg 직접 입력
		String rImg = this.rImgUpload(multipartRequest);
		requestMap.put("rImg", rImg);

		// 성공, 실패 시 알림
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			int isInserted = requestServiceImpl.insertRequestService(requestMap);
			// reqNO 따로 빼주기 = 안그럼 '/1'에만 저장됨
			int reqNO = (Integer) requestMap.get("reqNO");

			// 파일(이미지)가 유효하면 경로에도 저장
			if (rImg != null && rImg.length() != 0) {
				File srcFile = new File(REQUEST_IMAGE_REPO + "/" + "temp" + "/" + rImg);
				File destDir = new File(REQUEST_IMAGE_REPO + "/" + req + "/" + reqNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += " alert('요청이 완료되었습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/request/sent/" + reqNO + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {

			// 기존 파일 존재시 덮어씌우기
			File srcFile = new File(REQUEST_IMAGE_REPO + "/" + "temp" + "/" + rImg);
			srcFile.delete();

			message = " <script>";
			message += " alert('요청에 실패했습니다. 다시 시도해주세요.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/goSendRequest';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.BAD_REQUEST);
		}
		return resEnt;
	}

	// 파일 저장하기 = 경로에 이미지 다운로드
	private String rImgUpload(MultipartHttpServletRequest multipartRequest) throws IOException {

		String rImg = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {

			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			rImg = mFile.getOriginalFilename();

			File file = new File(REQUEST_IMAGE_REPO + "/" + "temp" + "/" + fileName);

			// 이미지가 유효하면 경로 생성 및 저장
			if (mFile.getSize() != 0) {
				if (!file.exists()) {
					if (file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(REQUEST_IMAGE_REPO + "/" + "temp" + "/" + rImg));
			}
		}
		return rImg;
	}

	// 파일 불러오기 = 경로에 저장된 이미지를 썸네일로 가져오기
	@RequestMapping("/rImgLoad")
	public void rImgLoad(@RequestParam("req") String req, @RequestParam("reqNO") int reqNO,
			@RequestParam("rImg") String rImg, HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		// 경로 = "요청자-글번호-이미지명" 3단계로 찾아서 불러오기
		String downFile = REQUEST_IMAGE_REPO + "/" + req + "/" + reqNO + "/" + rImg;
		File file = new File(downFile);

		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + rImg);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer);
			if (count == -1)
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}

	// 보낸 요청 목록 이동 = 조회 포함 (REST)
	@GetMapping("/request/sent")
	public ModelAndView viewRequestSent(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/requestSent";
		mav.setViewName(url);

		// 세션 불러오기
		HttpSession session = request.getSession();
		// 세션값 VO에 담기
		MemberVO memberVO = (MemberVO) session.getAttribute("member");

		if (memberVO == null) {
			return mav;
		}
		// 세션값에서 현재 로그인한 id값 get = req로 활용
		String req = memberVO.getId();

		// 보낸 요청 리스트 정보 전송
		List<RequestVO> requestList = requestServiceImpl.selectRequestByReqService(req);
		mav.addObject("requestList", requestList);

		return mav;
	}

	// 받은 요청 목록 이동 = 조회 포함 (REST)
	@GetMapping("/request/got")
	public ModelAndView viewRequestGot(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/requestGot";
		mav.setViewName(url);

		// 세션 불러오기
		HttpSession session = request.getSession();
		// 세션값 VO에 담기
		MemberVO memberVO = (MemberVO) session.getAttribute("member");

		if (memberVO == null) {
			return mav;
		}
		// 세션값에서 현재 로그인한 id값 get = res로 활용
		String res = memberVO.getId();

		// 받은 요청 리스트 정보 전송
		List<RequestVO> requestList = requestServiceImpl.selectRequestByResService(res);
		mav.addObject("requestList", requestList);

		return mav;
	}

	// 보낸 요청 상세 조회 = REST
	@GetMapping("/request/sent/{reqNO}")
	public ModelAndView viewSentReqDetails(@PathVariable(value = "reqNO") int reqNO, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/requestSentDetails";
		mav.setViewName(url);

		// 보낸 요청 상세 정보 전송
		RequestVO requestInfo = requestServiceImpl.selectRequestByNumService(reqNO);
		mav.addObject("requestInfo", requestInfo);

		return mav;
	}

	// 보낸 요청 수정
	@ResponseBody
	@RequestMapping(value = "/modRequest", method = RequestMethod.POST)
	public ResponseEntity modRequestByNum(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws IOException {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> requestInfo = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			requestInfo.put(name, value);
		}
		HttpSession session = multipartRequest.getSession();

		String rImg = rImgUpload(multipartRequest);
		requestInfo.put("rImg", rImg);

		String req = (String) requestInfo.get("req");
		String reqNO = (String) requestInfo.get("reqNO");

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			int result = requestServiceImpl.updateRequestByNumService(requestInfo);

			if (rImg != null && rImg.length() != 0 && result != 0) {
				// defaultImg -> jsp 에서 꼭 받아오기
				String defaultImg = (String) requestInfo.get("defaultImg");
				File oldFile = new File(REQUEST_IMAGE_REPO + "/" + req + "/" + reqNO + "/" + defaultImg);
				oldFile.delete();

				File srcFile = new File(REQUEST_IMAGE_REPO + "/" + "temp" + "/" + rImg);
				File destDir = new File(REQUEST_IMAGE_REPO + "/" + req + "/" + reqNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += " alert('작성 내용이 반영되었습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/request/sent/" + reqNO + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		} catch (Exception e) {

			File srcFile = new File(REQUEST_IMAGE_REPO + "/" + "temp" + "/" + rImg);
			srcFile.delete();

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해주세요.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/request/sent/" + reqNO + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.BAD_REQUEST);
		}
		return resEnt;
	}

	// 보낸 요청 (대기) 철회 = Ajax
	@ResponseBody
	@RequestMapping(value = "/delRequest", method = RequestMethod.POST)
	public int delRequestByNum(int reqNO, String req) throws IOException {
		int isDeleted = 0;
		isDeleted = requestServiceImpl.deleteRequestByNumService(reqNO);

		File destDir = new File(REQUEST_IMAGE_REPO + "/" + req + "/" + reqNO);
		FileUtils.deleteDirectory(destDir);

		return isDeleted;
	}

	// 받은 요청 상세 조회 = REST
	@GetMapping("/request/got/{reqNO}")
	public ModelAndView viewGotReqDetails(@PathVariable(value = "reqNO") int reqNO, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/requestGotDetails";
		mav.setViewName(url);

		// 받은 요청 상세 정보 전송
		RequestVO requestInfo = requestServiceImpl.selectRequestByNumService(reqNO);
		mav.addObject("requestInfo", requestInfo);

		return mav;
	}

	// 받은 요청 수락 이동 = REST
	@GetMapping("/request/got/{reqNO}/accept")
	public ModelAndView viewReqAcceptForm(@PathVariable(value = "reqNO") int reqNO, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/requestAcceptForm";

		// 받은 요청 상세 정보 전송
		RequestVO requestInfo = requestServiceImpl.selectRequestByNumService(reqNO);
		mav.addObject("requestInfo", requestInfo);

		mav.setViewName(url);
		return mav;
	}

	// 받은 요청 수락
	@ResponseBody
	@RequestMapping(value = "/acceptRequest", method = RequestMethod.POST)
	public ResponseEntity acceptRequest(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws UnsupportedEncodingException {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> acceptInfo = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			acceptInfo.put(name, value);
		}
		HttpSession session = multipartRequest.getSession();

		// 요청 넘버 꺼내오기
		String reqNO = (String) acceptInfo.get("reqNO");

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			int result = requestServiceImpl.updateAcceptInfoService(acceptInfo);

			if (result > 0) {
				message = "<script>";
				message += " alert('요청이 수락되었습니다.');";
				message += " location.href='" + multipartRequest.getContextPath() + "/request/sent/';";
				message += " </script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
			}
		} catch (Exception e) {

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해주세요.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/request/got/" + reqNO + "/accept';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.BAD_REQUEST);
		}
		return resEnt;
	}

}