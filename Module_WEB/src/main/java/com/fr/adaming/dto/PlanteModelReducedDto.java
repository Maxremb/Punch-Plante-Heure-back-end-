package com.fr.adaming.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PlanteModelReducedDto {
	
	private String commun;
	private String scientifique;
	int identifiant;
	private String picture;

}
