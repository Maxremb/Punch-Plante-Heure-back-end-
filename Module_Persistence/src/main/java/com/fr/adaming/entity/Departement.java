package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe est le modèle de l'entité Département. 
 * @author Isaline MILLET
 * @since 0.0.1
 */
@Getter @Setter
@EqualsAndHashCode
@Entity
public class Departement {
	
	@Id
	private Integer numeroDep;
	
	@Column(nullable = false, length = 100, unique = true)
	private String nom;

}
