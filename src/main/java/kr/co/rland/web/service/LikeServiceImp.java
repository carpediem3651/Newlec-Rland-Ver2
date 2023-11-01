package kr.co.rland.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.rland.web.entity.Like;
import kr.co.rland.web.repository.LikeRepository;

@Service
public class LikeServiceImp implements LikeService {

	@Autowired
	private LikeRepository repository;
	
	@Override
	public Like add(Like like) {
		repository.save(like);
		Like newOne = repository.last();
		
		return newOne;
	}

	@Override
	public boolean delete(Long menuId, Long memberId) {
		
		int rowCount = repository.delete(menuId, memberId);
		
		if(rowCount == 1)
			return true;
		
		return false;
	}

}
