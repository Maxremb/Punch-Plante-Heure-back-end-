package com.fr.adaming.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fr.adaming.enums.EtatPlante;
import com.fr.adaming.enums.EtatSante;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Classe représentative de l'entité Plante Utilisateur stockée de manière persistante
 * @author lucie
 * @since 1.0.x
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class PlanteUtilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private EtatPlante etatPlante;
	
	@Column
	private EtatSante etatSante;
	
	@Column
	private Date dateSemis;
	
	@Column
	private Date datePlantation;
	
	@ManyToOne
	private Jardin jardin;
	
	@ManyToOne
	private PlanteModel planteModel;
	
}
