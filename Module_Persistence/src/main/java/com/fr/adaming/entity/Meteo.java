package com.fr.adaming.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;



/**
 * @author Gregoire
 *
 *	La température et la quantité de pluie pour une date
 *	\nJointures controllés par d'autres classes: Département
 */
@Entity
@Getter @Setter
@EqualsAndHashCode
public class Meteo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double temperature; //pas dessous de -273.15
	private double pluie;	//positive
	private double rayonnement;
	private double evapoTranspirationPotentielle;
	private double evapoTranspirationReelle;
	private int humidite;
	@Column(nullable = false, unique = true)
	private LocalDate date;

}
