package com.fr.adaming.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(callSuper = true)
public class RetentionUpdateDto extends RetentionCreateDto {
	
	private Integer identifier;

}
