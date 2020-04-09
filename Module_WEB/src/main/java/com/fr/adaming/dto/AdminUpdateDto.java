package com.fr.adaming.dto;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

public class AdminUpdateDto extends AdminCreateDto {

	@NotNull @Positive
	private Integer identifier;
	
}
