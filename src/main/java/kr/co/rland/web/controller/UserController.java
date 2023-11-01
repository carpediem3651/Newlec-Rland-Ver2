package kr.co.rland.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kr.co.rland.web.entity.Member;
import kr.co.rland.web.service.MemberService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private MemberService service;
	
	@GetMapping("login")
	public String login() {
		
		return "user/login";
	}
	
	@GetMapping("signup")
	public String signup() {
		
		return "user/signup";
	}
	
	@PostMapping("signup")
	public String signup(
				String name,
				String username,
				String password,
				String email
			) {
		
		Member member = Member
							.builder()
							.email(email)
							.name(name)
							.userName(username)
							.password(password)
						.build();
		
		service.signup(member);
		
		return "redirect:./login";
	}
	
//	@PostMapping("login")
//	public String login(
//			String username, 
//			String password,
//			@RequestParam("return-url") String returnURL,
//			HttpSession session) {
//		
//		// 로그인 처리하는 실질적인 코드 영역
//		if(service.isValid(username, password)){
//			session.setAttribute("username", username);
//			
//			if(returnURL != null && !returnURL.equals(""))
//				return "redirect:"+returnURL;
//			
//			return "redirect:/index";
//			// 두 가지 경로 : 
//			// 1.가다가 걸려서 왔으면 인증하고 거기로 다시 returnURL
//			// 2.걸린 것이 없이 자발적으로 여기를 요청한 거라면 ?/index 
//		}
//		
//		return "redirect:./login?error";
//	}
}
