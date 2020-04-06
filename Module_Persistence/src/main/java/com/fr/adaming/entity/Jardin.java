package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fr.adaming.enums.Sol;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentative de l'entité persistante Jardin
 * @author Clara Cadet
 * @since 0.0.1-SNAPSHOT
 */
@Getter @Setter @NoArgsConstructor
@Entity @EqualsAndHashCode
public class Jardin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column 
	private Sol sol;
	@Column (nullable = false)
	private String nom;
	@Column
	private Float longueur;
	@Column
	private Float largeur;
	@Column
	private Float ProfSol;
	@Column
	private float reserveUtile;
	@Column
	private boolean estArroser ;
	
	@ManyToOne (optional = false, fetch = FetchType.EAGER)
	private Departement departement;  // Ne devrait pas être nul 
	
	@ManyToOne ( fetch = FetchType.EAGER)//optional = false,
	private Utilisateur utilisateur; // Ne devrait pas être nul
	
}
