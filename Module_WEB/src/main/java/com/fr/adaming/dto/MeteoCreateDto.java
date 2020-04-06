package com.fr.adaming.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class MeteoCreateDto {

	private double temp; 
	
	private double rain;	
	
	private double radiation;
	
	private double etp;
	
	private double etr;
	
	private int humidity;
	
	@Column(nullable = false)
	private LocalDate dateMeteo;
	
	@Column(nullable = false)
	private DepartementDto departement;
	
	
}
