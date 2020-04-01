package com.fr.adaming.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fr.adaming.enums.TypePeriod;

import lombok.Getter;
import lombok.Setter;



/**
 * @author Gregoire
 *
 *	Relie departement et planteModel. Donne une periode entre dateDebut et dateFin approprié pour l'action specifié dans type
 *
 */
@Entity
@Getter @Setter
public class Periode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	TypePeriod type;
	@Column(nullable = false)
	LocalDate dateDebut;
	@Column(nullable = false)
	LocalDate dateFin;
	
	@ManyToOne
	Departement departement;
	
	@ManyToOne
	PlanteModel planteModel;
	
	
}
