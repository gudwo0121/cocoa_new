package com.milk.cocoa.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class MemberDAOImpl {

	@Autowired
	private SqlSession sqlSession;

	// 회원가입
	public int insertMemberList(MemberVO memberVO) {
		int result = 0;
		result = sqlSession.insert("mapper.member.insertMemberList", memberVO);
		return result;
	}

	// 아이디 중복확인
	public int selectCountById(MemberVO memberVO) {
		int isExist = 0;
		isExist = sqlSession.selectOne("mapper.member.selectCountById", memberVO);
		return isExist;
	}

	// 로그인 검증
	public MemberVO selectMemberById(MemberVO memberVO) {
		MemberVO loginMember = null;
		loginMember = sqlSession.selectOne("mapper.member.selectMemberById", memberVO);
		return loginMember;
	}
	
	// 프로필 작성 (수정 포함)
	public int updateProfile(MemberVO memberVO) {
		int isUpdated = 0;
		isUpdated = sqlSession.update("mapper.member.updateProfileById", memberVO);
		return isUpdated;
	}
	
	// 회원정보 조회
	public MemberVO selectmemberInfoById(MemberVO memberVO) {
		MemberVO memberInfo = sqlSession.selectOne("mapper.member.selectmemberInfoById", memberVO);
		return memberInfo;
	}
}
