package com.fr.adaming.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fr.adaming.enums.EtatPlante;
import com.fr.adaming.enums.EtatSante;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentative de l'entité Plante Utilisateur stockée de manière persistante
 * @author Lucie Martinez
 * @since 0.0.1
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class PlanteUtilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private EtatPlante etatPlante;
	
	@Column
	private EtatSante etatSante;
	
	@Column
	private LocalDate dateSemis;
	
	@Column
	private LocalDate datePlantation;
	
	@ManyToOne (optional = false)
	private Jardin jardin;
	
	@ManyToOne (optional = false)
	private PlanteModel planteModel;
	
	@Column
	private ArrayList<Integer> listeCoordonnees;
	
}
