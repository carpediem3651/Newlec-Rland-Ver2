package kr.co.rland.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.rland.web.entity.Category;
import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService service;
	
	//@ResponseBody
	@RequestMapping("list-dom")
	public String listDom(Model model, 
			HttpServletRequest request,
			HttpServletResponse response) {
		
		Cookie cookie = new Cookie("test", "hello");		
		if(request.getCookies().length < 1)
			response.addCookie(cookie);
		
		//request.getCookies();
		//HttpSession session = request.getSession();
		
		//List<Menu> list = service.getList();
		List<MenuView> list = service.getViewList(1, null, null);
		List<Category> categoryList = service.getCategoryList();
		System.out.println(list);
		System.out.println(categoryList);
		model.addAttribute("list", list);
		model.addAttribute("categoryList", categoryList);
				
		return "menu/list";
	}
	
	@RequestMapping("list")
	public String list() {
		return "menu/list-vue";
	}
	
	//@ResponseBody
	@RequestMapping("detail")
	public String detail(Model model,
			// HttpServletRequest request
			@CookieValue String test
		) {
		System.out.println(test);
		//MenuDetailModel mdModel= new MenuDetailModel();
		Menu menu = service.getById(20);
		//int count = service.getCount();
		//mdModel.setMenu(menu);
		//mdModel.setCount(count);
		//model.addAllAttributes("model", mdModel);
		model.addAttribute("menu", menu);
		//model.addAttribute("count", count);
		
//		Cookie[] cookies = request.getCookies();
//		for(Cookie c : cookies)
//			if(c.getName().equals("test")) {
//				System.out.println(c.getValue());
//				break;
//			}
		
		return "menu/detail";
	}
}
