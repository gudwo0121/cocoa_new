package com.milk.cocoa.member;

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
}
