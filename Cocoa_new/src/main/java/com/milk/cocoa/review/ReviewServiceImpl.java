package com.milk.cocoa.review;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reviewService")
public class ReviewServiceImpl {

	@Autowired
	private ReviewDAOImpl reviewDAOImpl;

	// 후기 작성
	public int insertReviewService(Map reviewInfo) {
		int isInserted = 0;
		isInserted = reviewDAOImpl.insertReview(reviewInfo);
		return isInserted;
	}

	// 후기 개수
	public int reviewCountService(String target) {
		int reviewCount = 0;
		reviewCount = reviewDAOImpl.reviewCount(target);
		return reviewCount;
	}

}
