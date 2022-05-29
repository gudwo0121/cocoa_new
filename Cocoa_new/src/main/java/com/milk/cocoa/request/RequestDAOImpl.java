package com.milk.cocoa.request;

import java.util.List;
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

	// 보낸 요청 수정
	public int updateRequestByNum(Map requestInfo) {
		int isUpdated = 0;
		isUpdated = sqlSession.update("mapper.request.updateRequestByNum", requestInfo);
		return isUpdated;
	}

	// 보낸 요청 (대기) 삭제
	public int deleteRequestByNum(int reqNO) {
		int isDeleted = 0;
		isDeleted = sqlSession.delete("mapper.request.deleteRequestByNum", reqNO);
		return isDeleted;
	}

	// 요청 수락 (재전송 포함)
	public int updateAcceptInfo(Map acceptInfo) {
		int isUpdated = 0;
		isUpdated = sqlSession.update("mapper.request.updateAcceptInfo", acceptInfo);
		return isUpdated;
	}

	// 요청 거절
	public int updateRejectInfo(Map rejectInfo) {
		int isUpdated = 0;
		isUpdated = sqlSession.update("mapper.request.updateRejectInfo", rejectInfo);
		return isUpdated;
	}

	// 결제성공 = 완료상태
	public int updatePayInfo(RequestVO requestVO) {
		int isUpdated = 0;
		isUpdated = sqlSession.update("mapper.request.updatePayInfo", requestVO);
		return isUpdated;
	}

	// 후기작성완료 = 종료상태
	public int updateRateInfo(RequestVO requestVO) {
		int isUpdated = 0;
		isUpdated = sqlSession.update("mapper.request.updateRateInfo", requestVO);
		return isUpdated;
	}

	// 요청 넘버링 = reqNO을 순차적으로 증가시키기 위함
	private int requestNumbering() {
		return sqlSession.selectOne("mapper.request.requestNumbering");
	}

}
