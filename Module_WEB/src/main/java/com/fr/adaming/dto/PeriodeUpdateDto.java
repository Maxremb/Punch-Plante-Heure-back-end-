package com.fr.adaming.dto;

import javax.validation.constraints.Positive;

public class PeriodeUpdateDto extends PeriodeCreateDto {

	@Positive(message = "l'identifiant de l'uttilisateur doit être positif")
	private int identity;
	
}
