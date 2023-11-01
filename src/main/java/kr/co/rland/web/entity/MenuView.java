package kr.co.rland.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MenuView{
	private Long id;
	private String korName;
	private String engName;
	private Integer price;
	private String img;
	private Date regDate;
	private Integer hit;
	private Integer amount;
	private Long memberId;
	private Integer likeCount;
	private Boolean isLike;
}
