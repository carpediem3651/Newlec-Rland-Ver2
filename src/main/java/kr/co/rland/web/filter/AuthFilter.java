package kr.co.rland.web.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//@Component
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("필터라요~");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Object username_ = session.getAttribute("username");
				
		// 너 인증은 하고 왔니?
		if(username_ == null)
			request
			.getRequestDispatcher("/user/login?returnURL=/menu/list")
			.forward(request, response);
		
		chain.doFilter(request, response);
		
	}

}
