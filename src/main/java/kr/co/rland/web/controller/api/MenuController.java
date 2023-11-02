package kr.co.rland.web.controller.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.service.MenuService;

@RestController("apiMenuController")
@RequestMapping("/api/menus")
public class MenuController {

	@Autowired
	private MenuService service;
	
	// 목록을 제공하는 API
	//@ResponseBody
	@GetMapping 
	//@CrossOrigin(origins = "http://127.0.0.1:5503/") // 메서드에서 설정
	public List<MenuView> list(
		@RequestParam(name = "p", defaultValue = "1") Integer page,
		@RequestParam(name = "q", required = false) String query,
		@RequestParam(name = "c", required = false) Long categoryId
		){
		
		List<MenuView> list = service.getViewList(page, categoryId, query);
		return list;
	}
	
	// 메뉴를 제공하는 API
	//@GetMapping("{id}/kor-name/{name}")  // /api/menus/{id}/kor-name/{name}
	@GetMapping("{id}")  // /api/menus/{id}/kor-name/{name}
	public Menu detail(
			@PathVariable long id
			//, @PathVariable String name
			) {
		
		Menu menu = service.getById(id);
		
		return menu;
	}
	
	// 메뉴를 등록하는 API
	@PostMapping
	public Menu reg(
			HttpServletRequest request,
			Menu menu, MultipartFile imgFile) throws IllegalStateException, IOException {
		
		// 저장될 경로를 지정한다.
		// 서블릿의 홈 디렉토리는 webapp
		String strPath = request.getServletContext().getRealPath("/image/menu");
		
		// ------- 파일 디렉토리가 없다면, 디렉토리를 만들어준다. -------
		File path = new File(strPath);
		if(!path.exists())
			path.mkdirs();
		// ---------
		
		// ------------ imgFile을 file위치로 전송한다.---------
		File file = new File(strPath+File.separator+imgFile.getOriginalFilename());
		imgFile.transferTo(file);
		System.out.println("file:"+file);
		System.out.println(menu);
		
		//.setImg는 menu엔티티에 속성 값을 추가해주는 메서드다.
		menu.setImg(imgFile.getOriginalFilename());
		// db에 이미지 데이터 저장
		Menu newOne = service.add(menu);
		
		System.out.println("============");
		System.out.println(newOne);
		return newOne;
	}
	
	// 메뉴를 수정하는 API 
	@PutMapping
	public Menu edit(
			@RequestBody Menu menu){
		System.out.println("edit===============");
		System.out.println(menu);
		Menu mMenu= service.modify(menu);
		
		return mMenu;
	}
}
