package com.fr.adaming.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Meteo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	double temperature; //pas dessous de -273.15
	int pluie;	//positive
	@Column(nullable = false)
	LocalDate date;

}
