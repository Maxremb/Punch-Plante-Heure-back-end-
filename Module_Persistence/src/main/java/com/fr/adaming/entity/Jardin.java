package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fr.adaming.enums.Sol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentative de l'entité persistante Jardin
 * @author Clara Cadet
 * @since 0.0.1-SNAPSHOT
 */
@Getter @Setter @NoArgsConstructor
@Entity
public class Jardin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column 
	private Enum<Sol> sol;
	@Column (nullable = false)
	private String nom;
	@Column
	private Float longueur;
	@Column
	private Float largeur;
	
	@ManyToOne (optional = false)
	private Departement departement;  // Ne devrais pas être nul 
	
	@ManyToOne (optional = false)
	private Utilisateur utilisateur; // Ne devrais pas être nul
	
}
