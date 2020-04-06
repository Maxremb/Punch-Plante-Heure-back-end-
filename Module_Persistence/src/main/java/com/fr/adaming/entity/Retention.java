package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fr.adaming.enums.Sol;

import lombok.Data;

/**
 * Contient les attributs de retention d'eau des sols pour chaque type définit.
 * 
 * @author maxime
 * @since 0.0.1-SNAPSHOT
 */
@Data
@Entity
public class Retention {
	
	@Column
	private Sol sol;
	@Column
	private double retention;
	

}
