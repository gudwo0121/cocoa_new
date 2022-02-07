package com.milk.cocoa.coaching;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
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

	// 코칭 등록
	public int insertCoachingList(Map coachingMap) {
		int coachNO = this.coachingListNumbering();
		coachingMap.put("coachNO", coachNO);
		// 코칭 넘버링으로 생성된 번호를 coachNO으로 삽입
		sqlSession.insert("mapper.coach.insertCoachingList", coachingMap);
		return coachNO;
	}

	// 코칭 넘버링 = coachNO을 순차적으로 증가시키기 위함
	private int coachingListNumbering() {
		return sqlSession.selectOne("mapper.coach.coachingListNumbering");
	}
}
