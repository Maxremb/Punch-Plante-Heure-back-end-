package com.fr.adaming.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;



/**
 * @author Jeanne-Marie MATHEVET
 *
 *	Classe meteo 
 */
@Entity
@Getter @Setter
@EqualsAndHashCode
public class Meteo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "DOUBLE DEFAULT 0")
	private double temperatureMax; 
	
	@Column(columnDefinition = "DOUBLE DEFAULT 0")
	private double temperatureMin; 
	
	@Column(columnDefinition = "DOUBLE DEFAULT 0")
	private double pluie;	//positive
	
	@Column(columnDefinition = "DOUBLE DEFAULT 0")
	private double ensoleillement;
	
	@Column(columnDefinition = "DOUBLE DEFAULT 0")
	private double evapoTranspirationPotentielle;
	
	@Column(nullable = false)
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "departement_id")
	private Departement departement;

}
