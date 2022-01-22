package com.milk.cocoa.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.fabric.xmlrpc.base.Member;

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
		int result = 0;
		result = sqlSession.selectOne("mapper.member.selectCountById", memberVO);
		return result;
	}

	// 로그인 검증
	public MemberVO selectMemberById(MemberVO memberVO) {
		MemberVO loginMember = sqlSession.selectOne("mapper.member.selectMemberById", memberVO);
		return loginMember;
	}
}
