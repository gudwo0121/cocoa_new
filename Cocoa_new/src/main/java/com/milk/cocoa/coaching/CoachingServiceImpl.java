package com.milk.cocoa.coaching;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("coachingService")
public class CoachingServiceImpl {

	@Autowired
	private CoachingDAOImpl coachingDAOImpl;

	// 코칭 글 조건 조회
	public List selectCoachingPostByFieldService(String cField) {
		List<CoachingVO> coachingPost = coachingDAOImpl.selectCoachingPostByField(cField);
		return coachingPost;
	}

	// 코칭 글 작성
	public int insertCoachingPostService(Map coachMap) {
		return coachingDAOImpl.insertCoachingPost(coachMap);
	}

}
