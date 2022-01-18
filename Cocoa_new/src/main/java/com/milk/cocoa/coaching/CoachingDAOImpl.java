package com.milk.cocoa.coaching;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("coachingDAO")
public class CoachingDAOImpl {

	@Autowired
	private SqlSession sqlSession;

	// 코칭 리스트 전체 조회
	public List selectCoachingList() {
		List<CoachingVO> coachingList = sqlSession.selectList("mapper.coach.selectCoachingList");
		return coachingList;
	}

}
