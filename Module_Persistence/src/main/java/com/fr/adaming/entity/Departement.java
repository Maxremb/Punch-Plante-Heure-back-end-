package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe est le modèle de l'entité Département. 
 * @author Isaline MILLET
 *
 */
@Getter @Setter
@Entity
public class Departement {
	
	@Id
	private Integer numeroDep;
	
	private String nom;
	
	@OneToMany
	@JoinColumn(name = "departement_id")
	private List<Meteo> meteoDep;

}
