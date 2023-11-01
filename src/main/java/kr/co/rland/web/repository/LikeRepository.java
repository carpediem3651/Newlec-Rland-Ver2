package kr.co.rland.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.rland.web.entity.Like;

@Mapper
public interface LikeRepository {

	void save(Like like);

	Like last();

	int delete(Long menuId, Long memberId);

}
