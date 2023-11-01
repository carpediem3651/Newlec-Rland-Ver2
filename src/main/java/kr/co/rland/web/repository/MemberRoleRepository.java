package kr.co.rland.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.rland.web.entity.MemberRole;
import kr.co.rland.web.entity.MemberRoleView;

@Mapper
public interface MemberRoleRepository {

	void save(MemberRole memberRole);

	List<MemberRoleView> findViewAllByMemberId(Long memberId);

}
