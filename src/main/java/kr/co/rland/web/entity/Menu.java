package kr.co.rland.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Menu {
	private Long id;
	private String korName;
	private String engName;
	private Integer price;
	private String img;
	private String description;
	private Date regDate;
	private Boolean isSmall;
	private Integer hit;
	private Long memberId;
		
}
