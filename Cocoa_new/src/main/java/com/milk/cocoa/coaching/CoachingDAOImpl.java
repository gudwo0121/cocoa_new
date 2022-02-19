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

	// 코칭 글 분야별 조건 조회
	public List selectCoachingPostByField(String cField) {
		List<CoachingVO> coachingPost = sqlSession.selectList("mapper.coach.selectCoachingPostByField", cField);
		return coachingPost;
	}

	// 코칭 글 작성
	public int insertCoachingPost(Map coachingMap) {
		// 코칭 넘버링으로 생성된 번호를 coachNO으로 삽입
		int coachNO = this.coachingPostNumbering();
		coachingMap.put("coachNO", coachNO);
		// 받아온 입력과 함께 쿼리문 실행
		sqlSession.insert("mapper.coach.insertCoachingPost", coachingMap);
		return coachNO;
	}

	// 코칭 넘버링 = coachNO을 순차적으로 증가시키기 위함
	private int coachingPostNumbering() {
		return sqlSession.selectOne("mapper.coach.coachingPostNumbering");
	}
}
