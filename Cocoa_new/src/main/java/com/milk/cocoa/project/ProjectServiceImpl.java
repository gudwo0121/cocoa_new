package com.milk.cocoa.project;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projectService")
public class ProjectServiceImpl {

	@Autowired
	private ProjectDAOImpl projectDAOImpl;

	// 프로젝트 글 분야별 조건 조회
	public List selectProjectPostByFieldService(String pField) {
		List<ProjectVO> projectPost = projectDAOImpl.selectProjectPostByField(pField);
		return projectPost;
	}

	// 프로젝트 글 분야+레벨별 조건 조회
	public List selectProjectPostByLevelService(ProjectVO projectVO) {
		List<ProjectVO> projectPost = projectDAOImpl.selectProjectPostByLevel(projectVO);
		return projectPost;
	}

	// 프로젝트 글 작성
	public int insertProjectPostService(Map projectMap) {
		return projectDAOImpl.insertProjectPost(projectMap);

	}
}
