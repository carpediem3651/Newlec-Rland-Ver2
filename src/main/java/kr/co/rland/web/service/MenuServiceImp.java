package kr.co.rland.web.service;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.rland.web.entity.Category;
import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.repository.CategoryRepository;
import kr.co.rland.web.repository.MenuRepository;

@Service
public class MenuServiceImp implements MenuService {
	@Autowired
	private MenuRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
		
	@Override
	public Menu getById(long id) {
		// TODO Auto-generated method stub
		Menu menu = repository.findById(id);
		
		return menu;
	}
	@Override
	public List<MenuView> getViewList(Integer page, Long categoryId, String query) {
		// TODO Auto-generated method 		
		int size = 9;
		int offset = size*(page-1);
		List<MenuView> list = repository.findViewAll(offset, size, categoryId, query/*page, category, query*/);
		
		return list;
	}
	@Override
	public Menu add(Menu menu) {
		repository.save(menu);	
		Menu newOne = repository.last();
		return newOne;
	}
	@Override
	public Menu modify(Menu menu) {
		repository.update(menu);
		Menu mOne = repository.findById(menu.getId());
		return mOne;
	}
	@Override
	public List<Category> getCategoryList() {

		List<Category> list = categoryRepository.findAll();
		
		return list;
	}
	
}
