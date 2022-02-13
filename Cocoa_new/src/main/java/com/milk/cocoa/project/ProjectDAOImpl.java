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

	// 프로젝트 리스트 전체 조회
	public List selectProjectList() {
		List<ProjectVO> projectList = sqlSession.selectList("mapper.project.selectProjectList");
		return projectList;
	}

	// 프로젝트 모집
	public int insertProjectList(Map projectMap) {
		int projectNO = this.projectListNumbering();
		projectMap.put("projectNO", projectNO);
		// 프로젝트 넘버링으로 생성된 번호를 projectNO으로 삽입
		sqlSession.insert("mapper.project.insertprojectList", projectMap);
		return projectNO;
	}

	// 프로젝트 넘버링 = projectNO을 순차적으로 증가시키기 위함
	private int projectListNumbering() {
		return sqlSession.selectOne("mapper.project.projectListNumbering");
	}
}
