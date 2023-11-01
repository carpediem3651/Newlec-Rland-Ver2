package kr.co.rland.web.config.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.rland.web.entity.Member;
import kr.co.rland.web.entity.MemberRole;
import kr.co.rland.web.entity.MemberRoleView;
import kr.co.rland.web.repository.MemberRepository;
import kr.co.rland.web.repository.MemberRoleRepository;

@Service
public class RlandUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberRoleRepository memberRoleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Member member = memberRepository.findByUserName(username);
		List<MemberRoleView> memberRoleViewList
			= memberRoleRepository.findViewAllByMemberId(member.getId());
		
		RlandUserDetails userDetails = new RlandUserDetails();
		System.out.println(username);
		userDetails.setUsername(username);
		userDetails.setPassword(member.getPassword());
		userDetails.setId(member.getId());
		userDetails.setEmail(member.getEmail());
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(MemberRoleView mr : memberRoleViewList)
			authorities.add(new SimpleGrantedAuthority(mr.getRoleName()));		
		
		userDetails.setAuthorities(authorities);		
		
		return userDetails;
	}
		

}
