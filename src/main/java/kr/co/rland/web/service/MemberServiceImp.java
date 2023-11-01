package kr.co.rland.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.rland.web.entity.Member;
import kr.co.rland.web.entity.MemberRole;
import kr.co.rland.web.repository.MemberRepository;
import kr.co.rland.web.repository.MemberRoleRepository;

@Service
public class MemberServiceImp implements MemberService {

	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private MemberRoleRepository memberRoleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public boolean isValid(String username, String password) {
		// TODO Auto-generated method stub
		
		//repository.findById(1L);
		Member member = repository.findByUserName(username);
		
		if(member == null)
			return false;
		else if(!member.getPassword().equals(password))
			return false;
		
		return true;
	}

	@Override
	public Member signup(Member member) {
		
		String plainPwd = member.getPassword();
		String hashedPwd = encoder.encode(plainPwd);
		System.out.println(hashedPwd);
		
		member.setPassword(hashedPwd);		
		repository.save(member);
		
		Member newOne = repository.last();
		
		MemberRole mr = MemberRole.builder()
									.memberId(newOne.getId())
									//.roleId(2L) // DB에서 기본 값 2로 설정
								.build();
		memberRoleRepository.save(mr);
		
		return newOne;
	}

}





