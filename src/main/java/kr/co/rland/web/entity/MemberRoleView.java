package kr.co.rland.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MemberRoleView {
	private Long memberId;
	private Long roleId;
	private String roleName;
}
