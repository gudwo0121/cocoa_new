package com.milk.cocoa.member;

import java.io.File;
import java.io.FileInputStream;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("memberController")
public class MemberControllerImpl {

	// proImg 다운로드 경로 (FTP시 "/opt/cocoa/image/profile") = 기본이 로컬 C드라이브고 그 뒤 경로 입력
	private static final String PROFILE_IMAGE_REPO = "/cocoaRepo/profileImg";
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
	public int signUp(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = memberServiceImpl.insertMemberListService(memberVO);
		return result;
	}

	// 아이디 중복확인 (java.lang.integer 에러 = ResponseBody 미스)
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int idCheck(MemberVO memberVO) {
		int result = 0;
		result = memberServiceImpl.selectCountByIdService(memberVO);
		return result;
	}

	// 로그인 검증 = Ajax
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public MemberVO login(MemberVO memberVO, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) {
		MemberVO isMember = memberServiceImpl.selectMemberByIdService(memberVO);

		// 로그인시 세션에 로그인 정보 저장 = 나중에 쓰여서 일부러 MemberVO로 받음
		if (isMember != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", isMember);
			session.setAttribute("isLogOn", true);
		} else {
			// rAttr은 세션에 일회성으로 저장 후 소멸한다.
			rAttr.addAttribute("result", "loginFailed");
		}
		return isMember;
	}

	// 로그아웃 = 세션삭제
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/home";
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		mav.setViewName(url);
		return mav;
	}

	// 내 프로필 화면 이동
	@GetMapping("/profile")
	public ModelAndView viewProfileById(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String url = "/myProfile";

		// Session을 통해 MemberVO-toString으로 id만 뽑아오기
		HttpSession session = request.getSession();
		String id = session.getAttribute("member").toString();

		MemberVO memberInfo = memberServiceImpl.selectMemberInfoByIdService(id);
		mav.addObject("profile", memberInfo);
		mav.setViewName(url);
		return mav;
	}

	// 파일 저장하기 = 경로에 이미지 다운로드
	private String proImgUpload(MultipartHttpServletRequest multipartRequest) throws Exception {

		String proImg = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {

			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			proImg = mFile.getOriginalFilename();

			File file = new File(PROFILE_IMAGE_REPO + "/" + "temp" + "/" + fileName);

			if (mFile.getSize() != 0) {
				if (!file.exists()) {
					if (file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(PROFILE_IMAGE_REPO + "/" + "temp" + "/" + proImg));
			}
		}
		return proImg;
	}

	// 파일 불러오기 = 경로에 저장된 이미지를 썸네일로 가져오기
	@RequestMapping("/proImgLoad")
	protected void proImgLoad(@RequestParam("id") String id, @RequestParam("proImg") String proImg,
			HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		MemberVO memberInfo = memberServiceImpl.selectMemberInfoByIdService(id);
		String profileImg = memberInfo.getproImg();
		String filePath = PROFILE_IMAGE_REPO + "/" + id + "/" + profileImg;
		File image = new File(filePath);

		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + profileImg);
		FileInputStream in = new FileInputStream(image);
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

	// 프로필 작성 (수정 포함)
	@ResponseBody
	@RequestMapping(value = "/modMyProfile", method = RequestMethod.POST)
	public ResponseEntity modMyProfile(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> profileInfo = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			profileInfo.put(name, value);
		}
		HttpSession session = multipartRequest.getSession();

		String proImg = proImgUpload(multipartRequest);
		profileInfo.put("proImg", proImg);

		String id = (String) profileInfo.get("id");

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			int result = memberServiceImpl.updateProfileByIdService(profileInfo);

			if (proImg != null && proImg.length() != 0 && result != 0) {
				String defaultImg = (String) profileInfo.get("defaultImg");
				File oldFile = new File(PROFILE_IMAGE_REPO + "/" + id + "/" + defaultImg);
				oldFile.delete();

				File srcFile = new File(PROFILE_IMAGE_REPO + "/" + "temp" + "/" + proImg);
				File destDir = new File(PROFILE_IMAGE_REPO + "/" + id);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
			message = "<script>";
			message += " alert('작성 내용이 반영되었습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/profile';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {

			File srcFile = new File(PROFILE_IMAGE_REPO + "/" + "temp" + "/" + proImg);
			srcFile.delete();

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해주세요.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/profile';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.BAD_REQUEST);
		}
		return resEnt;
	}

}
