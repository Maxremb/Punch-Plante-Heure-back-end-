package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentative de l'entité persistante Utilisateur héritant de la classe Admin
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode (callSuper = false)
public class Utilisateur extends Admin {

	@Column (nullable = false)
	private String nom;
	@Column (nullable = false)
	private String prenom;
	@Column
	private String description;
	@Column 
	private Integer telephone;
	@Column
	private String reputation;
	@Column (columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean newsletter ;
	@Column (columnDefinition = "BOOLEAN DEFAULT TRUE")
	private Boolean actif;
	@Column
	private String photo;
}
