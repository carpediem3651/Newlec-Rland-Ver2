package kr.co.rland.web.controller.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.rland.web.config.auth.RlandUserDetails;
import kr.co.rland.web.entity.Category;
import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.service.MenuService;

@Controller("adminMenuController")
@RequestMapping("/admin/menu")
public class MenuController {

	@Autowired
	private MenuService service;
	
	//@ResponseBody
	@RequestMapping("list")
	public String list(
			Model model
			/* Principal principal */
			//Authentication authentication 
		) {
		//System.out.println(principal.getName());
		
		RlandUserDetails userDetails = (RlandUserDetails)SecurityContextHolder
										.getContext()
										.getAuthentication()
										.getPrincipal();
		
		List<MenuView> list = service.getViewList(1, null, null);
		List<Category> categoryList = service.getCategoryList();
		System.out.println(list);
		System.out.println(categoryList);
		model.addAttribute("list", list);
		model.addAttribute("categoryList", categoryList);
		
		return "/admin/menu/list";
	}
	
	//@ResponseBody
	@RequestMapping("detail")
	public String detail() {
		return "/admin/menu/detail";
	}
	
	@GetMapping("reg")
	public String reg() {
		//if(request.getMethod().equals("POST"))
			
		
		return "/admin/menu/reg";
	}
	
	@PostMapping("reg")
	public String reg(
			//
			@RequestParam("img-file") MultipartFile imgFile,
			@RequestParam(name="kor-name",required = true) String korName,
			@RequestParam(name="eng-name",required = true) String engName,
			@RequestParam(defaultValue = "0") int price,
			String description,
			boolean isSmall
			) throws IOException {
		
		Menu menu = Menu.builder()
						.korName(korName)
						.engName(engName)
						.price(price)
						.img("coffee.png")
						.isSmall(isSmall)
						.memberId(1L)
						.build();
		
		System.out.println(menu);
		service.add(menu);
		
		System.out.println(imgFile.isEmpty());
		System.out.println(imgFile.getOriginalFilename());
				
		InputStream fis = imgFile.getInputStream();
		OutputStream fos = new FileOutputStream("D:\\"+imgFile.getOriginalFilename());
		
		int size = 0;
		byte[] buf = new byte[1024];
		while((size = fis.read(buf))!=-1)
			fos.write(buf, 0, size);
		
		fis.close();
		fos.close();
		
			
		
		//if(request.getMethod().equals("POST"))
		
		return "redirect:list";
	}
	
	//service() -> doGet()/doPost()
}




