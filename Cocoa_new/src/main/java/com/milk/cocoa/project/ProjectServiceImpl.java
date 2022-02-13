package com.milk.cocoa.project;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projectService")
public class ProjectServiceImpl {

	@Autowired
	private ProjectDAOImpl projectDAOImpl;

	// 프로젝트 리스트 전체 조회
	public List selectProjectListService() {
		List<ProjectVO> projectList = projectDAOImpl.selectProjectList();
		return projectList;
	}

	// 프로젝트 모집
	public int insertProjectListService(Map coachMap) {
		return projectDAOImpl.insertProjectList(coachMap);

	}
}
