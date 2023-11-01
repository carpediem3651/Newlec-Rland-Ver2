package kr.co.rland.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;

@Mapper
public interface MenuRepository {
	
	//@Select("select * from menu")
	List<Menu> findAll();
	List<MenuView> findViewAll(int offset, int size, Long categoryId, String query);
	int count(); // select count(id) from ..
	
	Menu findById(long id);
	int save(Menu menu);
	int update(Menu menu);
	int delete(long id);
	Menu last();
}
