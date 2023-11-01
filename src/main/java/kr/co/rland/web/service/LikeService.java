package kr.co.rland.web.service;

import kr.co.rland.web.entity.Like;

public interface LikeService {

	Like add(Like like);

	boolean delete(Long menuId, Long memberId);

}
