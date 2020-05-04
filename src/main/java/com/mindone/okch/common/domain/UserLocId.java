package com.mindone.okch.common.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserLocId implements Serializable {
	
	private static final long serialVersionUID = 1371263687060768033L;

	@Id
	@Column(name = "USER_ID")
	private String userId;
	
	@Id	
	@Column(name = "LOC_SN")
	private Integer locSn;
	
}
