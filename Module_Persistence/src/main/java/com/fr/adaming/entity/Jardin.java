package com.fr.adaming.entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fr.adaming.enums.Sol;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentative de l'entité Jardin stockée de manière persistante
 * @author clara
 * @since 1.0.x
 */
@Getter @Setter @NoArgsConstructor
@Entity
public class Jardin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Enum<Sol> sol;
	@Column
	private String nom;
	@Column
	private ArrayList<PlanteUtilisateur> planteUtilisateur;
	@Column
	private Float longueur;
	@Column
	private Float largeur;
	@ManyToOne
	private Departement departement;
	@ManyToOne
	private Utilisateur utilisateur;
	
}
