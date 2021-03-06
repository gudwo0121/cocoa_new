package com.milk.cocoa.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl {

	@Autowired
	private MemberDAOImpl memberDAOImpl;

	// 회원가입
	public int insertMemberListService(MemberVO memberVO) {
		int result = 0;
		result = memberDAOImpl.insertMemberList(memberVO);
		return result;
	}

	// 아이디 중복확인
	public int selectCountByIdService(MemberVO memberVO) {
		int isExist = 0;
		isExist = memberDAOImpl.selectCountById(memberVO);
		return isExist;
	}

	// 로그인 검증
	public MemberVO selectMemberByIdService(MemberVO memberVO) {
		MemberVO loginMember = null;
		loginMember = memberDAOImpl.selectMemberById(memberVO);
		return loginMember;
	}

	// 프로필 작성 (수정 포함)
	public int updateProfileByIdService(Map profileInfo) {
		int isUpdated = 0;
		isUpdated = memberDAOImpl.updateProfileById(profileInfo);
		return isUpdated;
	}

	// 회원정보 조회
	public MemberVO selectMemberInfoByIdService(String id) {
		MemberVO memberInfo = memberDAOImpl.selectMemberInfoById(id);
		return memberInfo;
	}
}
