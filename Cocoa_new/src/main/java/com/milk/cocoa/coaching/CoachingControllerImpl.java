package com.milk.cocoa.coaching;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

@RestController("coachingController")
public class CoachingControllerImpl {

	// cImg 다운로드 경로 (FTP시 "/opt/cocoa/image/coaching") = 기본이 로컬 C드라이브고 그 뒤 경로 입력
	private static final String COACH_IMAGE_REPO = "/cocoaRepo/coachImg";

	@Autowired
	private CoachingVO coachingVO;
	@Autowired
	private CoachingServiceImpl coachingServiceImpl;

	// 코칭 글 분야별 조회 (REST)
	@GetMapping("/coaching/{cField}")
	public ModelAndView viewCoachingPostByField(@PathVariable(value = "cField") String cField,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/coaching";
		mav.setViewName(url);

		// 분야의 첫 문자를 대문자로 변경 = 경로표시에 쓰기 위함
		String showField = cField.substring(0, 1).toUpperCase() + cField.substring(1, cField.length());

		// capitalized cField 값 전송
		mav.addObject("showField", showField);
		// cField 값 전송
		mav.addObject("cField", cField);

		// cField 수치화
		if (cField.equals("web")) {
			cField = "cField1";
		} else if (cField.equals("mobile")) {
			cField = "cField2";
		} else if (cField.equals("embedded")) {
			cField = "cField3";
		}

		// 조회된 코칭 글 정보 전송
		List<CoachingVO> coachingPost = coachingServiceImpl.selectCoachingPostByFieldService(cField);
		mav.addObject("coachingPost", coachingPost);

		return mav;
	}

	// 코칭 글 개발툴별 조회 (REST)
	@GetMapping("/coaching/{cField}/{tool}")
	public ModelAndView viewCoachingPostByTool(@PathVariable(value = "cField") String cField,
			@PathVariable(value = "tool") String tool, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/coaching";
		mav.setViewName(url);

		// 분야의 첫 문자를 대문자로 변경 = 경로표시에 쓰기 위함
		String showField = cField.substring(0, 1).toUpperCase() + cField.substring(1, cField.length());

		// capitalized cField 값 전송
		mav.addObject("showField", showField);
		// cField 값 전송
		mav.addObject("cField", cField);
		// tool 값 전송
		mav.addObject("tool", tool);

		// tool 수치화
		if (tool.equals("SpringFramework")) {
			tool = "tool1";
		} else if (tool.equals("Django")) {
			tool = "tool2";
		} else if (tool.equals("AndroidStudio")) {
			tool = "tool3";
		} else if (tool.equals("Xcode")) {
			tool = "tool4";
		} else if (tool.equals("Arduino")) {
			tool = "tool5";
		} else if (tool.equals("RaspberryPi")) {
			tool = "tool6";
		}

		// 조회된 코칭 글 정보 전송
		List<CoachingVO> coachingPost = coachingServiceImpl.selectCoachingPostByToolService(tool);
		mav.addObject("coachingPost", coachingPost);

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

		// id 직접 입력
		String id = memberVO.getId();
		coachingMap.put("coach", id);

		// cImg 직접 입력
		String cImg = this.cImgUpload(multipartRequest);
		coachingMap.put("cImg", cImg);

		// 수치화된 cField 값 기록 = 코칭 글 작성 후 이동 페이지를 위함
		String cFieldHistory = (String) coachingMap.get("cField");

		// cField 값 문자열화
		if (cFieldHistory.equals("cField1")) {
			cFieldHistory = "web";
		} else if (cFieldHistory.equals("cField2")) {
			cFieldHistory = "mobile";
		} else if (cFieldHistory.equals("cField3")) {
			cFieldHistory = "embedded";
		}

		// 성공, 실패 시 알림
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			// coachNO 따라 해당 회원의 경로로 업로드
			int coachNO = coachingServiceImpl.insertCoachingPostService(coachingMap);

			// 파일(이미지)가 유효하면 경로에도 저장
			if (cImg != null && cImg.length() != 0) {
				File srcFile = new File(COACH_IMAGE_REPO + "/" + "temp" + "/" + cImg);
				File destDir = new File(COACH_IMAGE_REPO + "/" + id + "/" + coachNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += " alert('등록이 완료되었습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/coaching/" + cFieldHistory + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {

			// 기존 파일 존재시 덮어씌우기
			File srcFile = new File(COACH_IMAGE_REPO + "/" + "temp" + "/" + cImg);
			srcFile.delete();

			message = " <script>";
			message += " alert('등록에 실패했습니다. 다시 시도해주세요.');');";
			message += " location.href='" + multipartRequest.getContextPath() + "/coaching/goCoachingWrite';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.BAD_REQUEST);
		}
		return resEnt;
	}

	// 파일 저장하기 = 경로에 이미지 다운로드
	private String cImgUpload(MultipartHttpServletRequest multipartRequest) throws IOException {

		String cImg = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {

			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			cImg = mFile.getOriginalFilename();

			File file = new File(COACH_IMAGE_REPO + "/" + "temp" + "/" + fileName);

			// 이미지가 유효하면 경로 생성 및 저장
			if (mFile.getSize() != 0) {
				if (!file.exists()) {
					if (file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(COACH_IMAGE_REPO + "/" + "temp" + "/" + cImg));
			}
		}
		return cImg;
	}

	// 파일 불러오기 = 경로에 저장된 이미지를 썸네일로 가져오기
	@RequestMapping("/cImgLoad")
	public void cImgLoad(@RequestParam("coach") String coach, @RequestParam("coachNO") int coachNO,
			@RequestParam("cImg") String cImg, HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		// 경로 = "코치-글번호-이미지명" 3단계로 찾아서 불러오기
		String downFile = COACH_IMAGE_REPO + "/" + coach + "/" + coachNO + "/" + cImg;
		File file = new File(downFile);

		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + cImg);
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

	// 코칭 글 Get in 조회 (REST)
	@GetMapping("/coaching/post/{coachNO}")
	public ModelAndView getInCoachingPostByNum(@PathVariable(value = "coachNO") int coachNO, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/coachingPost";
		mav.setViewName(url);

		// 조회된 코칭 글 정보 전송
		CoachingVO coachingInfo = coachingServiceImpl.selectCoachingPostByNumService(coachNO);
		mav.addObject("coachingInfo", coachingInfo);

		return mav;
	}

	// 코칭 글 Get in 수정
	@ResponseBody
	@RequestMapping(value = "/modCoachingPost", method = RequestMethod.POST)
	public ResponseEntity modCoachingPostByNum(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws IOException {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> coachingInfo = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			coachingInfo.put(name, value);
		}
		HttpSession session = multipartRequest.getSession();

		String cImg = cImgUpload(multipartRequest);
		coachingInfo.put("cImg", cImg);

		// 필요한 값들 따로 변수화
		String coachNO = (String) coachingInfo.get("coachNO");
		String coach = (String) coachingInfo.get("coach");
		String cField = (String) coachingInfo.get("cField");

		// 수치 to 글씨
		if (cField.equals("cField1")) {
			cField = "web";
		} else if (cField.equals("cField2")) {
			cField = "mobile";
		} else if (cField.equals("cField3")) {
			cField = "embedded";
		}

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			int result = coachingServiceImpl.updateCoachingPostByNumService(coachingInfo);

			if (cImg != null && cImg.length() != 0 && result != 0) {
				// defaultImg -> jsp 에서 꼭 받아오기
				String defaultImg = (String) coachingInfo.get("defaultImg");
				File oldFile = new File(COACH_IMAGE_REPO + "/" + coach + "/" + coachNO + "/" + defaultImg);
				oldFile.delete();

				File srcFile = new File(COACH_IMAGE_REPO + "/" + "temp" + "/" + cImg);
				File destDir = new File(COACH_IMAGE_REPO + "/" + coach + "/" + coachNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += " alert('작성 내용이 반영되었습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/coaching/" + cField + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		} catch (Exception e) {

			File srcFile = new File(COACH_IMAGE_REPO + "/" + "temp" + "/" + cImg);
			srcFile.delete();

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해주세요.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/coaching/post/" + coachNO + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.BAD_REQUEST);
		}
		return resEnt;
	}
}