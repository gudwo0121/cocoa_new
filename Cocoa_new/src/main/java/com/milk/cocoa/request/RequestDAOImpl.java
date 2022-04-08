package com.milk.cocoa.request;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("requestDAO")
public class RequestDAOImpl {

	@Autowired
	private SqlSession sqlSession;

	// 요청 추가
	public int insertRequest(Map requestInfo) {
		int isInserted = 0;
		int reqNO = this.requestNumbering();
		requestInfo.put("reqNO", reqNO);
		isInserted = sqlSession.insert("mapper.request.insertRequest", requestInfo);
		return isInserted;
	}

	// 요청 넘버링 = reqNO을 순차적으로 증가시키기 위함
	private int requestNumbering() {
		return sqlSession.selectOne("mapper.request.requestNumbering");
	}
}
