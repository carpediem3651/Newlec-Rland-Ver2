package kr.co.rland.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {

	//@ResponseBody
	@RequestMapping("index")
	public String index(
			@RequestParam(defaultValue = "hello") String test,
			HttpServletRequest request) {
		
		//HttpSession session = request.getSession();
		//session.setAttribute("test", test);
		
		return "index";
	}
}
