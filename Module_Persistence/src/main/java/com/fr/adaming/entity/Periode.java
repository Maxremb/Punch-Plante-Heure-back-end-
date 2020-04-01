package com.fr.adaming.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fr.adaming.enums.TypePeriod;

public class Periode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	TypePeriod type;
	@Column(nullable = false)
	LocalDate dateDebut;
	@Column(nullable = false)
	LocalDate dateFin;
	
	
}
