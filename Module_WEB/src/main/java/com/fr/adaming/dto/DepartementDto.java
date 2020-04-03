package com.fr.adaming.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartementDto {
	
	@Positive
	private Integer depNum;
	
	@NotNull
	private String name; 
	
	private List<MeteoUpdateDto> weatherDep = new ArrayList<MeteoUpdateDto>();

}
