package com.milk.cocoa.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projectService")
public class ProjectServiceImpl {

	@Autowired
	private ProjectDAOImpl projectDAOImpl;

	// 프로젝트 글 분야별 조건 조회
	public List selectProjectPostByFieldService(String pField) {
		List<ProjectVO> projectPost = projectDAOImpl.selectProjectPostByField(pField);
		return projectPost;
	}

	// 프로젝트 글 분야+레벨별 조건 조회
	public List selectProjectPostByLevelService(ProjectVO projectVO) {
		List<ProjectVO> projectPost = projectDAOImpl.selectProjectPostByLevel(projectVO);
		return projectPost;
	}

	// 프로젝트 글 넘버별 조건 조회
	public ProjectVO selectProjectPostByNumService(int projectNO) {
		ProjectVO projectInfo = projectDAOImpl.selectProjectPostByNum(projectNO);
		return projectInfo;
	}

	// 프로젝트 글 작성
	public int insertProjectPostService(Map projectMap) {
		return projectDAOImpl.insertProjectPost(projectMap);

	}

	// 프로젝트 글 수정
	public int updateProjectPostByNumService(Map projectInfo) {
		int isUpdated = 0;
		isUpdated = projectDAOImpl.updateProjectPostByNum(projectInfo);
		return isUpdated;
	}

	// 지도 검색
	public String searchMapByAddrService(String addr) {
		StringBuffer res = null;
		String clientId = "jp1by0tzk9";
		String clientSecret = "Tib7u9Bk51p4pora8nRwIRY46RaRujMMXsrPrOa4";

		try {
			// 입력 받은 주소를 쿼리스트링으로 URL에 전송
			String text = URLEncoder.encode(addr, "UTF-8");
			String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + text;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			// clientId와 clientSecret으로 인증 진행
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			con.setDoOutput(true);

			// URL에서 받아오는 지도정보 출력
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
		} catch (Exception e) {
		}
		// 응답 결과(JSON) 반환
		return res.toString();
	}
}
