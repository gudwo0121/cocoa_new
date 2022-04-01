package com.milk.cocoa.project;

import org.springframework.stereotype.Component;

@Component("projectVO")
public class ProjectVO {

	private int projectNO;
	private String leader;
	private String pImg;
	private String pTitle;
	private int memberCount;
	private String pField;
	private String level;
	private String pContents;
	private String map;
	private String link;

	public ProjectVO() {

	}

	public ProjectVO(int projectNO, String leader, String pImg, String pTitle, int memberCount, String pField,
			String level, String pContents, String map, String link) {
		this.projectNO = projectNO;
		this.leader = leader;
		this.pImg = pImg;
		this.pTitle = pTitle;
		this.memberCount = memberCount;
		this.pField = pField;
		this.level = level;
		this.pContents = pContents;
		this.map = map;
		this.link = link;
	}

	public int getProjectNO() {
		return projectNO;
	}

	public void setProjectNO(int projectNO) {
		this.projectNO = projectNO;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getpImg() {
		return pImg;
	}

	public void setpImg(String pImg) {
		this.pImg = pImg;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public String getpField() {
		return pField;
	}

	public void setpField(String pField) {
		this.pField = pField;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getpContents() {
		return pContents;
	}

	public void setpContents(String pContents) {
		this.pContents = pContents;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getlink() {
		return link;
	}

	public void setlink(String link) {
		this.link = link;
	}
}