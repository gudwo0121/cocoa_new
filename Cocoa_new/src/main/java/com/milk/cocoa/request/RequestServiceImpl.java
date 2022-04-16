package com.milk.cocoa.request;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milk.cocoa.coaching.CoachingVO;

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
}
