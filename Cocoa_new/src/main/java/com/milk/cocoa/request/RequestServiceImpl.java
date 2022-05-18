package com.milk.cocoa.request;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("requestService")
public class RequestServiceImpl {

	@Autowired
	private RequestDAOImpl requestDAOImpl;

	// 요청 추가
	public int insertRequestService(Map requestInfo) {
		int isInserted = 0;
		isInserted = requestDAOImpl.insertRequest(requestInfo);
		return isInserted;
	}

	// 보낸 요청 조회
	public List selectRequestByReqService(String req) {
		List<RequestVO> requestList = requestDAOImpl.selectRequestByReq(req);
		return requestList;
	}

	// 받은 요청 조회
	public List selectRequestByResService(String res) {
		List<RequestVO> requestList = requestDAOImpl.selectRequestByRes(res);
		return requestList;
	}

	// 요청 상세 조회
	public RequestVO selectRequestByNumService(int reqNO) {
		RequestVO requestInfo = requestDAOImpl.selectRequestByNum(reqNO);
		return requestInfo;
	}

	// 보낸 요청 수정
	public int updateRequestByNumService(Map requestInfo) {
		int isUpdated = 0;
		isUpdated = requestDAOImpl.updateRequestByNum(requestInfo);
		return isUpdated;
	}

	// 보낸 요청 (대기) 삭제
	public int deleteRequestByNumService(int reqNO) {
		int isDeleted = 0;
		isDeleted = requestDAOImpl.deleteRequestByNum(reqNO);
		return isDeleted;
	}

	// 요청 수락
	public int updateAcceptInfoService(Map acceptInfo) {
		int isUpdated = 0;
		isUpdated = requestDAOImpl.updateAcceptInfo(acceptInfo);
		return isUpdated;
	}

	// 요청 거절
	public int updateRejectInfoService(Map rejectInfo) {
		int isUpdated = 0;
		isUpdated = requestDAOImpl.updateRejectInfo(rejectInfo);
		return isUpdated;
	}
}
