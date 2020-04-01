package com.fr.adaming.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fr.adaming.enums.EtatPlante;
import com.fr.adaming.enums.EtatSante;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class PlanteUtilisateur {

	private EtatPlante etatPlante;
	
	private EtatSante etatSante;
	
	private Date dateSemis;
	
	private Date datePlantation;
	
	@ManyToOne
	private Jardin jardin;
	
	@ManyToOne
	private PlanteModel planteModel;
	
}
