package com.fr.adaming.dto;

import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartementUpdateDto extends DepartementCreateDto {
	
	@Positive
	private Integer depNum;

}
