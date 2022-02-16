package com.milk.cocoa.coaching;

import java.io.File;
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

import net.coobird.thumbnailator.Thumbnails;

@RestController("coachingController")
public class CoachingControllerImpl {

	// cImg 다운로드 경로 (FTP시 "/opt/cocoa/image/coaching") = 기본이 로컬 C드라이브고 그 뒤 경로 입력
	private static final String COACH_IMAGE_REPO = "/cocoaRepo/coachImg";

	@Autowired
	private CoachingVO coachingVO;
	@Autowired
	private CoachingServiceImpl coachingServiceImpl;

	// 코칭 리스트 이동
	@GetMapping("/coaching/{field}")
	public ModelAndView goCoachingList(@PathVariable(value = "field") String field, HttpServletRequest request,
			HttpServletResponse response) {
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

		// id 직접 입력
		String id = memberVO.getId();
		coachingMap.put("coach", id);

		// cImg 직접 입력
		String cImg = this.cImgUpload(multipartRequest);
		coachingMap.put("cImg", cImg);

		// 성공, 실패 시 알림
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			// coachNO 따라 해당 회원의 경로로 업로드
			int coachNO = coachingServiceImpl.insertCoachingListService(coachingMap);

			// 파일(이미지)가 유효하면 경로에도 저장
			if (cImg != null && cImg.length() != 0) {
				File srcFile = new File(COACH_IMAGE_REPO + "/" + "temp" + "/" + cImg);
				File destDir = new File(COACH_IMAGE_REPO + "/" + id + "/" + coachNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += " alert('등록이 완료되었습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/goCoachingList'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {

			// 기존 파일 존재시 덮어씌우기
			File srcFile = new File(COACH_IMAGE_REPO + "/" + "temp" + "/" + cImg);
			srcFile.delete();

			message = " <script>";
			message += " alert('등록에 실패했습니다. 다시 시도해주세요.');');";
			message += " location.href='" + multipartRequest.getContextPath() + "/goCoachingList'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
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

		if (file.exists()) {
			// 원본 이미지에 대한 썸네일 이미지를 생성한 후 OutputStream 객체에 할당
			Thumbnails.of(file).size(1024, 1024).outputFormat("png").toOutputStream(out);
		}
		// 썸네일 이미지를 OutputStream 객체를 이용해 뷰로 전송
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
	}
}
