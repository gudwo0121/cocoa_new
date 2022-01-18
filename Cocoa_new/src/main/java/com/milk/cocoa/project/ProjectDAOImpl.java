package com.milk.cocoa.project;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("projectDAO")
public class ProjectDAOImpl {

	@Autowired
	private SqlSession sqlSession;

	// 프로젝트 리스트 전체 조회
	public List selectProjectList() throws DataAccessException {
		List<ProjectVO> projectList = sqlSession.selectList("mapper.project.selectProjectList");
		return projectList;
	}
}
