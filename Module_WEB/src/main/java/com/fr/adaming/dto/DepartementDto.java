package com.fr.adaming.dto;

import java.util.List;

import javax.validation.constraints.Positive;

import com.fr.adaming.entity.Meteo;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartementDto {
	
	@Positive
	private Integer depNum;
	
	@NotNull
	private String name; 
	
	private List<Meteo> weatherDep;

}