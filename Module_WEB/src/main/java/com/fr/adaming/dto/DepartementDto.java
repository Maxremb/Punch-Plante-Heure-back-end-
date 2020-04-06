package com.fr.adaming.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe représente le DTO de l'entité Departement. Un seul DTO avec l'ID car le numéro du département sera tjrs transmis
 * 
 * @author Isaline MILLET
 * @since 0.0.1
 */
@Getter @Setter @EqualsAndHashCode
public class DepartementDto {
	
	@Positive @NotNull
	private Integer depNum;
	
	@NotNull
	private String name; 
	
	private List<MeteoUpdateDto> weatherDep = new ArrayList<MeteoUpdateDto>();

}
