package com.milk.cocoa.coaching;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("coachingService")
public class CoachingServiceImpl {

	@Autowired
	private CoachingDAOImpl coachingDAOImpl;

	// 코칭 글 분야별 조건 조회
	public List selectCoachingPostByFieldService(String cField) {
		List<CoachingVO> coachingPost = coachingDAOImpl.selectCoachingPostByField(cField);
		return coachingPost;
	}

	// 코칭 글 개발툴별 조건 조회
	public List selectCoachingPostByToolService(String tool) {
		List<CoachingVO> coachingPost = coachingDAOImpl.selectCoachingPostByTool(tool);
		return coachingPost;
	}

	// 코칭 글 넘버별 조건 조회
	public CoachingVO selectCoachingPostByNumService(int coachNO) {
		CoachingVO coachingInfo = coachingDAOImpl.selectCoachingPostByNum(coachNO);
		return coachingInfo;
	}

	// 코칭 글 작성
	public int insertCoachingPostService(Map coachMap) {
		return coachingDAOImpl.insertCoachingPost(coachMap);
	}
	
	// 코칭 글 수정
	public int updateCoachingPostByNumService(Map coachingInfo) {
		int isUpdated = 0;
		isUpdated = coachingDAOImpl.updateCoachingPostByNum(coachingInfo);
		return isUpdated;
	}

}
