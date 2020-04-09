package com.fr.adaming.dto;

import com.fr.adaming.enums.Sol;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe représente le DTO de l'entité Retention.
 * 
 * @author maxime
 * @since 0.0.1-SNAPSHOT
 */
@Getter @Setter @EqualsAndHashCode
public class RetentionCreateDto {
	
	private Sol ground;
	
	private double CoeffSol;
	

}
