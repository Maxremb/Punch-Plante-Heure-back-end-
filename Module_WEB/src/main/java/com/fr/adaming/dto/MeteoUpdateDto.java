package com.fr.adaming.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class MeteoUpdateDto extends MeteoCreateDto {

	@NotNull
	@Min (value = 1)
	private int identifier;
}
