package kr.co.rland.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MemberController {

	@GetMapping("index")
	public String index(HttpSession session) {
		
//		Object username_ = session.getAttribute("username");
//				
//		// 너 인증은 하고 왔니?
//		if(username_ == null)
//			return "redirect:../user/login?returnURL=/member/index";
//		
		return "member/index";
	}
}
