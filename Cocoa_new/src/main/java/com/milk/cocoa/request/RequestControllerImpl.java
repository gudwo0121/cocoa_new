package com.milk.cocoa.request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	// 코칭 요청 이동 = coachNO & res 정보는 coachingPost에서 받아놓은 상태
	@RequestMapping(value = "/goSendRequest", method = RequestMethod.POST)
	public ModelAndView goSendRequest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/requestWrite";

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
			// reqNO 따라 해당 요청자의 경로로 업로드
			int reqNO = requestServiceImpl.insertRequestService(requestMap);

			// 파일(이미지)가 유효하면 경로에도 저장
			if (rImg != null && rImg.length() != 0) {
				File srcFile = new File(REQUEST_IMAGE_REPO + "/" + "temp" + "/" + rImg);
				File destDir = new File(REQUEST_IMAGE_REPO + "/" + req + "/" + reqNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += " alert('요청이 완료되었습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/coaching/post/" + coachNO + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {

			// 기존 파일 존재시 덮어씌우기
			File srcFile = new File(REQUEST_IMAGE_REPO + "/" + "temp" + "/" + rImg);
			srcFile.delete();

			message = " <script>";
			message += " alert('요청에 실패했습니다. 다시 시도해주세요.');');";
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
}
