package kr.co.rland.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.rland.web.entity.Like;
import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.service.LikeService;
import kr.co.rland.web.service.MenuService;

@RestController("apiLikeController")
@RequestMapping("/api/likes")
public class LikeController {
	
	@Autowired
	private LikeService service;
		
	@DeleteMapping("{ids}") // /api/likes/2-menuid,3-memberid
	public Boolean delete(
			@PathVariable Long[] ids){
		
		Long menuId = ids[0];
		Long memberId = ids[1];
		
		System.out.println(ids[0]);
		
		boolean result = service.delete(menuId, memberId);
		
		return result;
	}
	
	// 메뉴를 등록하는 API
	@PostMapping
	public Like reg(
			@RequestBody Like like){	
		
		//like.setMemberId(1L);
		Like newOne = service.add(like);
		
		return newOne;
	}
	
}
















