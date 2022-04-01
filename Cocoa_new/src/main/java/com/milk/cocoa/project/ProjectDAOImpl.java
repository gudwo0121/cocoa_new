package com.milk.cocoa.project;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("projectDAO")
public class ProjectDAOImpl {

	@Autowired
	private SqlSession sqlSession;

	// 프로젝트 글 분야별 조건 조회
	public List selectProjectPostByField(String pField) {
		List<ProjectVO> projectPost = sqlSession.selectList("mapper.project.selectProjectPostByField", pField);
		return projectPost;
	}

	// 프로젝트 글 분야+레벨별 조건 조회
	public List selectProjectPostByLevel(ProjectVO projectVO) {
		List<ProjectVO> projectPost = sqlSession.selectList("mapper.project.selectProjectPostByLevel", projectVO);
		return projectPost;
	}

	// 프로젝트 글 넘버별 조건 조회
	public ProjectVO selectProjectPostByNum(int projectNO) {
		ProjectVO projectInfo = sqlSession.selectOne("mapper.project.selectProjectPostByNum", projectNO);
		return projectInfo;
	}

	// 프로젝트 글 작성
	public int insertProjectPost(Map projectMap) {
		// 프로젝트 넘버링으로 생성된 번호를 projectNO으로 삽입
		int projectNO = this.projectPostNumbering();
		projectMap.put("projectNO", projectNO);
		// 받아온 입력과 함께 쿼리문 실행
		sqlSession.insert("mapper.project.insertProjectPost", projectMap);
		return projectNO;
	}

	// 프로젝트 글 수정
	public int updateProjectPostByNum(Map projectInfo) {
		int isUpdated = 0;
		isUpdated = sqlSession.update("mapper.project.updateProjectPostByNum", projectInfo);
		return isUpdated;
	}

	// 프로젝트 넘버링 = projectNO을 순차적으로 증가시키기 위함
	private int projectPostNumbering() {
		return sqlSession.selectOne("mapper.project.projectPostNumbering");
	}
}
