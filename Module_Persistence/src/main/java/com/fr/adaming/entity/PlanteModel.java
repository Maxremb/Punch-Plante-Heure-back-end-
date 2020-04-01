package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PlanteModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(length = 30)
	String nomCommun;
	
	@Column(length = 30)
	String nomScientifique;
	
	@Column
	List<Periode> dates;
	
	@Column
	int intervalArrosage;
	
	@Column
	String ensoleillementOpti;
	
	@Column
	int humiditeopti;
	
	@Column
	String solOpti;
	
	@Column
	int repiquage;
	
	@Column
	int temperatureMin;
	
	@Column
	int temperatureMax;
	
	@Column
	String description;
	
	@Column
	boolean toxicite;
	
	@Column
	String photo;

}
