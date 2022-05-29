package com.milk.cocoa.review;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reviewService")
public class ReviewServiceImpl {

	@Autowired
	private ReviewDAOImpl reviewDAOImpl;

	// 요청 추가
	public int insertReviewService(Map reviewInfo) {
		int isInserted = 0;
		isInserted = reviewDAOImpl.insertReview(reviewInfo);
		return isInserted;
	}

}
