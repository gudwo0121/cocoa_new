package com.milk.cocoa.review;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("reviewDAO")
public class ReviewDAOImpl {

	@Autowired
	private SqlSession sqlSession;

	// 후기 작성
	public int insertReview(Map reviewInfo) {
		int isInserted = 0;
		int revNO = this.reviewNumbering();
		reviewInfo.put("revNO", revNO);
		isInserted = sqlSession.insert("mapper.review.insertReview", reviewInfo);
		return isInserted;
	}

	// 후기 개수
	public int reviewCount(String target) {
		int reviewCount = 0;
		System.out.println(target);
		reviewCount = sqlSession.selectOne("mapper.review.reviewCount", target);
		return reviewCount;
	}

	// 후기 넘버링 = revNO을 순차적으로 증가시키기 위함
	private int reviewNumbering() {
		return sqlSession.selectOne("mapper.review.reviewNumbering");
	}

}
