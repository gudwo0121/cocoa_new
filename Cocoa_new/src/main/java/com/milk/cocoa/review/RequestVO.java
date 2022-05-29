package com.milk.cocoa.review;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component("requestVO")
public class RequestVO {

	private int reqNO;
	private String req;
	private String res;
	private String rTitle;
	private String rContents;
	private Timestamp rDate;
	private String rImg;
	private String status;
	private int realPrice;
	private String contact;
	private String notice;
	private int paid;

	public RequestVO() {

	}

	public RequestVO(int reqNO, String req, String res, String rTitle, String rContents, Timestamp rDate, String rImg,
			String status, int realPrice, String contact, String notice, int paid) {
		this.reqNO = reqNO;
		this.req = req;
		this.res = res;
		this.rTitle = rTitle;
		this.rContents = rContents;
		this.rDate = rDate;
		this.rImg = rImg;
		this.status = status;
		this.realPrice = realPrice;
		this.contact = contact;
		this.notice = notice;
		this.paid = paid;
	}

	public int getReqNO() {
		return reqNO;
	}

	public void setReqNO(int reqNO) {
		this.reqNO = reqNO;
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getrTitle() {
		return rTitle;
	}

	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}

	public String getrContents() {
		return rContents;
	}

	public void setrContents(String rContents) {
		this.rContents = rContents;
	}

	public Timestamp getrDate() {
		return rDate;
	}

	public void setrDate(Timestamp rDate) {
		this.rDate = rDate;
	}

	public String getrImg() {
		return rImg;
	}

	public void setrImg(String rImg) {
		this.rImg = rImg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(int realPrice) {
		this.realPrice = realPrice;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}
}