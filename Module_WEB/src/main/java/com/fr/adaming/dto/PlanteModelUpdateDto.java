package com.fr.adaming.dto;



import javax.validation.constraints.Positive;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PlanteModelUpdateDto extends PlanteModelCreateDto {
	
	@Positive (message = "PlanteModelUpdateDto: Un identifiant ne peut pas etre 0 ou negatif")
	int identifiant;

}
