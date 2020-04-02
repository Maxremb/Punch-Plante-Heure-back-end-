package com.fr.adaming.dto;

import java.time.LocalDate;


import com.fr.adaming.enums.EtatPlante;
import com.fr.adaming.enums.EtatSante;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanteUtilisateurCreateDto {

	private EtatPlante plantStage;
	
	private EtatSante healthStage;
	
	private LocalDate semiDate;
	
	private LocalDate plantingDate;
	
	private JardinUpdateDto garden;
	
	private PlanteModelUpdateDto modelPlant;
	
}
