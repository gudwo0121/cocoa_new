package com.milk.cocoa.project;

import java.io.File;
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

import net.coobird.thumbnailator.Thumbnails;

@RestController("projectController")
public class ProjectControllerImpl {

	// pImg 다운로드 경로 (FTP시 "/opt/cocoa/image/project") = 기본이 로컬 C드라이브고 그 뒤 경로 입력
	private static final String PROJECT_IMAGE_REPO = "/cocoaRepo/projectImg";

	@Autowired
	private ProjectVO projectVO;
	@Autowired
	private ProjectServiceImpl projectServiceImpl;

	// 프로젝트 글 분야별 조회 (REST)
	@GetMapping("/project/{pField}")
	public ModelAndView viewProjectPostByField(@PathVariable(value = "pField") String pField,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/project";
		mav.setViewName(url);

		// 분야의 첫 문자를 대문자로 변경 = 경로표시에 쓰기 위함
		String showField = pField.substring(0, 1).toUpperCase() + pField.substring(1, pField.length());

		// capitalized pField 값 전송
		mav.addObject("showField", showField);
		// pField 값 전송
		mav.addObject("pField", pField);

		// pField 수치화
		if (pField.equals("web")) {
			pField = "pField1";
		} else if (pField.equals("mobile")) {
			pField = "pField2";
		} else if (pField.equals("embedded")) {
			pField = "pField3";
		}

		// 조회된 프로젝트 글 정보 전송
		List<ProjectVO> projectPost = projectServiceImpl.selectProjectPostByFieldService(pField);
		mav.addObject("projectPost", projectPost);

		return mav;
	}

	// 프로젝트 글 분야+레벨별 조회 (REST)
	@GetMapping("/project/{pField}/{level}")
	public ModelAndView viewProjectPostByLevel(@PathVariable(value = "pField") String pField,
			@PathVariable(value = "level") String level, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/project";
		mav.setViewName(url);

		// 분야의 첫 문자를 대문자로 변경 = 경로표시에 쓰기 위함
		String showField = pField.substring(0, 1).toUpperCase() + pField.substring(1, pField.length());

		// capitalized pField 값 전송
		mav.addObject("showField", showField);
		// pField 값 전송
		mav.addObject("pField", pField);
		// level 값 전송
		mav.addObject("level", level);

		// pField 수치화
		if (pField.equals("web")) {
			pField = "pField1";
		} else if (pField.equals("mobile")) {
			pField = "pField2";
		} else if (pField.equals("embedded")) {
			pField = "pField3";
		}

		// level 수치화
		if (level.equals("Beginner")) {
			level = "level1";
		} else if (level.equals("Intermediate")) {
			level = "level2";
		} else if (level.equals("Advanced")) {
			level = "level3";
		}

		// vo에 분야와 레벨 값 담기
		projectVO.setpField(pField);
		projectVO.setLevel(level);

		// 조회된 프로젝트 글 정보 전송
		List<ProjectVO> projectPost = projectServiceImpl.selectProjectPostByLevelService(projectVO);
		mav.addObject("projectPost", projectPost);

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

	// 프로젝트 모집
	@ResponseBody
	@RequestMapping(value = "/projectWrite", method = RequestMethod.POST)
	public ResponseEntity projectWrite(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> projectMap = new HashMap<String, Object>();

		// 입력된 정보를 map에 담아 쿼리문 처리
		// 쿼리문에 들어가야할 속성 하나라도 빠지면 에러 뜸 = 흰 화면만 뜸 + NullPointerException 발생
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			projectMap.put(name, value);
		}

		// 세션 불러오기
		HttpSession session = multipartRequest.getSession();

		// memberVO로 세션에 저장된 로그인한 회원의 정보를 저장
		MemberVO memberVO = (MemberVO) session.getAttribute("member");

		// id 직접 입력
		String id = memberVO.getId();
		projectMap.put("leader", id);

		// pImg 직접 입력
		String pImg = this.pImgUpload(multipartRequest);
		projectMap.put("pImg", pImg);

		// 수치화된 pField 값 기록 = 코칭 글 작성 후 이동 페이지를 위함
		String pFieldHistory = (String) projectMap.get("pField");

		// pField 값 문자열화
		if (pFieldHistory.equals("pField1")) {
			pFieldHistory = "web";
		} else if (pFieldHistory.equals("pField2")) {
			pFieldHistory = "mobile";
		} else if (pFieldHistory.equals("pField3")) {
			pFieldHistory = "embedded";
		}

		// 성공, 실패 시 알림
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			// projectNO 따라 해당 회원의 경로로 업로드
			int projectNO = projectServiceImpl.insertProjectPostService(projectMap);

			// 파일(이미지)가 유효하면 경로에도 저장
			if (pImg != null && pImg.length() != 0) {
				File srcFile = new File(PROJECT_IMAGE_REPO + "/" + "temp" + "/" + pImg);
				File destDir = new File(PROJECT_IMAGE_REPO + "/" + id + "/" + projectNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += " alert('등록이 완료되었습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/project/" + pFieldHistory + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {

			// 기존 파일 존재시 덮어씌우기
			File srcFile = new File(PROJECT_IMAGE_REPO + "/" + "temp" + "/" + pImg);
			srcFile.delete();

			message = " <script>";
			message += " alert('등록에 실패했습니다. 다시 시도해주세요.');');";
			message += " location.href='" + multipartRequest.getContextPath() + "/project/goProjectWrite';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		return resEnt;
	}

	// 파일 저장하기 = 경로에 이미지 다운로드
	private String pImgUpload(MultipartHttpServletRequest multipartRequest) throws IOException {

		String pImg = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {

			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			pImg = mFile.getOriginalFilename();
			File file = new File(PROJECT_IMAGE_REPO + "/" + "temp" + "/" + fileName);

			// 이미지가 유효하면 경로 생성 및 저장
			if (mFile.getSize() != 0) {
				if (!file.exists()) {
					if (file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(PROJECT_IMAGE_REPO + "/" + "temp" + "/" + pImg));
			}
		}
		return pImg;
	}

	// 파일 불러오기 = 경로에 저장된 이미지를 썸네일로 가져오기
	@RequestMapping("/pImgLoad")
	public void pImgLoad(@RequestParam("leader") String leader, @RequestParam("projectNO") int projectNO,
			@RequestParam("pImg") String pImg, HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		// 경로 = "코치-글번호-이미지명" 3단계로 찾아서 불러오기
		String downFile = PROJECT_IMAGE_REPO + "/" + leader + "/" + projectNO + "/" + pImg;
		File file = new File(downFile);

		if (file.exists()) {
			// 원본 이미지에 대한 썸네일 이미지를 생성한 후 OutputStream 객체에 할당
			Thumbnails.of(file).size(1024, 1024).outputFormat("png").toOutputStream(out);
		}
		// 썸네일 이미지를 OutputStream 객체를 이용해 뷰로 전송
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
	}

	// 지도 검색 = Ajax
	@ResponseBody
	@RequestMapping(value = "/searchMap", method = RequestMethod.GET, produces = "application/text; charset=UTF-8")
	public void searchMapByAddr(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("utf-8");
		String addr = request.getParameter("addr");
		response.setContentType("text/text; charset=utf-8");
		
		// 검색 후 응답 결과(JSON) 저장
		String result = projectServiceImpl.searchMapByAddrService(addr);
		response.getWriter().print(result);
	}
}
