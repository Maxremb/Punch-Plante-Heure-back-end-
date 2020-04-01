package com.fr.adaming.dto;

import java.util.List;

import com.fr.adaming.entity.Meteo;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartementCreateDto {
	
	@NotNull
	private String name; 
	
	private List<Meteo> weatherDep;

}
