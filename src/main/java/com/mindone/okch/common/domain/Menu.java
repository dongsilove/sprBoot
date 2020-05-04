package com.mindone.okch.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "CM_MENU")
public class Menu extends AbstractTbl { // 메뉴
	
	@Id
	@ApiModelProperty(value = "메뉴코드")
	@Column(name = "MENU_CDE")
	private String menuCde;
	
	@ApiModelProperty(value = "메뉴명")
	@Column(name = "MENU_NAM")
	private String menuNam;
	
	@ApiModelProperty(value = "상위메뉴코드")
	@Column(name = "UP_MENU_CDE")
	private String upMenuCde;
	
	@ApiModelProperty(value = "순서")
	@Column(name = "ORDER_NUM")
	private Integer orderNum;
	
	@ApiModelProperty(value = "깊이")
	@Column(name = "DEPTH_NUM")
	private Integer depthNum;
	
	@ApiModelProperty(value = "API호출주소")
	@Column(name = "API")
	private String api;
	
	@ApiModelProperty(value = "사용여부")
	@Column(name = "USE_YN")
	private String useYn;
	
	@ApiModelProperty(value = "검색JSP")
	@Column(name = "SEARCH_JSP")
	private String searchJsp;
	
	
	
}
