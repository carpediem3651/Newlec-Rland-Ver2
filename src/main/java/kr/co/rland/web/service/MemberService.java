package kr.co.rland.web.service;

import kr.co.rland.web.entity.Member;

public interface MemberService {

	boolean isValid(String username, String password);

	Member signup(Member member);

}
