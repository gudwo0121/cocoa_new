package com.milk.cocoa.coaching;

import org.springframework.stereotype.Component;

@Component("coachingVO")
public class CoachingVO {

	private int coachNO;
	private String coach;
	private String cField;
	private String tool;
	private String cImg;
	private String cTitle;
	private int basicPrice;
	private String cContents;

	public CoachingVO() {

	}

	public CoachingVO(int coachNO, String coach, String cField, String tool, String cImg, String cTitle, int basicPrice,
			String cContents) {
		this.coachNO = coachNO;
		this.coach = coach;
		this.cField = cField;
		this.tool = tool;
		this.cImg = cImg;
		this.cTitle = cTitle;
		this.basicPrice = basicPrice;
		this.cContents = cContents;
	}

	public int getCoachNO() {
		return coachNO;
	}

	public void setCoachNO(int coachNO) {
		this.coachNO = coachNO;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public String getcField() {
		return cField;
	}

	public void setcField(String cField) {
		this.cField = cField;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public String getcImg() {
		return cImg;
	}

	public void setcImg(String cImg) {
		this.cImg = cImg;
	}

	public String getcTitle() {
		return cTitle;
	}

	public void setcTitle(String cTitle) {
		this.cTitle = cTitle;
	}

	public int getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(int basicPrice) {
		this.basicPrice = basicPrice;
	}

	public String getcContents() {
		return cContents;
	}

	public void setcContents(String cContents) {
		this.cContents = cContents;
	}
}