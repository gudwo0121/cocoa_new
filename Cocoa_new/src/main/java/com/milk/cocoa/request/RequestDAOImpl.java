package com.milk.cocoa.request;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.milk.cocoa.coaching.CoachingVO;

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

	// 보낸 요청 조회
	public List selectRequestByReq(String req) {
		List<RequestVO> requestList = sqlSession.selectList("mapper.request.selectRequestByReq", req);
		return requestList;
	}

	// 받은 요청 조회
	public List selectRequestByRes(String res) {
		List<RequestVO> requestList = sqlSession.selectList("mapper.request.selectRequestByRes", res);
		return requestList;
	}

	// 요청 상세 조회
	public RequestVO selectRequestByNum(int reqNO) {
		RequestVO requestInfo = sqlSession.selectOne("mapper.request.selectRequestByNum", reqNO);
		return requestInfo;
	}

	// 요청 넘버링 = reqNO을 순차적으로 증가시키기 위함
	private int requestNumbering() {
		return sqlSession.selectOne("mapper.request.requestNumbering");
	}
}
