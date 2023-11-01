package kr.co.rland.web.entity;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Category {
	private Long id;
	private String name ;
	
}
