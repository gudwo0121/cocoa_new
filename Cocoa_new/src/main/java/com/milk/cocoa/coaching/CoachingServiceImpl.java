package com.milk.cocoa.coaching;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("coachingService")
public class CoachingServiceImpl {

	@Autowired
	private CoachingDAOImpl coachingDAOImpl;

	// 코칭 리스트 전체 조회
	public List selectCoachingListService() {
		List<CoachingVO> coachingList = coachingDAOImpl.selectCoachingList();
		return coachingList;
	}

	// 코칭 등록
	public int insertCoachingListService(Map coachMap) {
		return coachingDAOImpl.insertCoachingList(coachMap);
	}

}
