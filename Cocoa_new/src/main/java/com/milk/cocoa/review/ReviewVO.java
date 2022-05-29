package com.milk.cocoa.review;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component("reviewVO")
public class ReviewVO {

	private int revNO;
	private String writer;
	private String target;
	private int rate;
	private String review;
	private Timestamp reDate;

	public ReviewVO() {

	}

	public ReviewVO(int revNO, String writer, String target, int rate, String review, Timestamp reDate) {
		this.revNO = revNO;
		this.writer = writer;
		this.target = target;
		this.rate = rate;
		this.review = review;
	}

	public int getRevNO() {
		return revNO;
	}

	public void setRevNO(int revNO) {
		this.revNO = revNO;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Timestamp getReDate() {
		return reDate;
	}

	public void setReDate(Timestamp reDate) {
		this.reDate = reDate;
	}
}