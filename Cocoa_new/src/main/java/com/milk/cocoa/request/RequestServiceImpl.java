package com.milk.cocoa.request;

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
}
