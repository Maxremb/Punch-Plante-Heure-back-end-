package com.fr.adaming.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fr.adaming.enums.TypePeriod;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;



/**
 * @author Gregoire
 * @since 0.0.1
 *	Relie departement et planteModel. Donne une periode entre dateDebut et dateFin approprié pour l'action specifié dans type
 *
 */
@Entity
@Getter @Setter
@EqualsAndHashCode
public class Periode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private TypePeriod type;
	@Column(nullable = false)
	private LocalDate dateDebut;
	@Column(nullable = false)
	private LocalDate dateFin;
	
	@ManyToOne
	private Departement departement;
	
	@ManyToOne
	private PlanteModel planteModel;
	
	
}
