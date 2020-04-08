package com.fr.adaming.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MeteoXlsDto {
	
	private int station;
	private String nom;
	private int longitude;
	private int latitude;
	private int altitude;
	private String date;
	private float rr;
	private float tn;
	private float tx;
	private float fxi;
	private int dxi;
	private float fxy;
	private int dxy;
	private int inst;
	private float etpmon;

}
