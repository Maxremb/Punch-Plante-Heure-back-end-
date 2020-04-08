package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fr.adaming.enums.Sol;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Contient les attributs de retention d'eau des sols pour chaque type définit.
 * 
 * @author maxime
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Setter
@Entity
public class Retention {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column (unique = true)
	private Sol sol;
	@Column
	private double coeffRemplissage;

}
