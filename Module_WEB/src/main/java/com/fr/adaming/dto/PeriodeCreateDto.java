package com.fr.adaming.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fr.adaming.enums.TypePeriod;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class PeriodeCreateDto {
	
	private TypePeriod periodType;
	
	@NotNull(message = "La date de début ne peut pas être null")
	private LocalDate startDate;
	
	@NotNull(message = "La date de fin ne peut pas être null")
	private LocalDate endDate;
	
	@NotNull(message = "Une periode est relié à un département")
	private DepartementDto county;
	
	@NotNull(message = "Cette periode doit être relié à une plante")
	private PlanteModelUpdateDto plantSpecies;

}
