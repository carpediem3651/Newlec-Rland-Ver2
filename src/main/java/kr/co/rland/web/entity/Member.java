package kr.co.rland.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Member {
	private Long id;
	private String userName;
	private String email;
	private String password;
	private String name;
	private String phone;
	private Date regDate;	
}
