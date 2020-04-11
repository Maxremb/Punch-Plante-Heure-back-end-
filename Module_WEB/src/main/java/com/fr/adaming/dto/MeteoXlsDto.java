package com.fr.adaming.dto;

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
	
	
	public void setLongitude(String longitude) {
		this.longitude = Integer.valueOf(longitude.replace(",", "").replace(".", ""));
	}
	public void setLatitude(String latitude) {
		this.latitude = Integer.valueOf(latitude.replace(",", "").replace(".", ""));
	}
	public void setRr(String rr) {
		this.rr = Float.valueOf(rr.replace(",", "."));
	}
	public void setTn(String tn) {
		this.tn = Float.valueOf(tn.replace(",", "."));
	}
	public void setTx(String tx) {
		this.tx = Float.valueOf(tx.replace(",", "."));
	}
	public void setFxi(String fxi) {
		this.fxi = Float.valueOf(fxi.replace(",", "."));
	}
	public void setFxy(String fxy) {
		this.fxy = Float.valueOf(fxy.replace(",", "."));
	}
	public void setEtpmon(String etpmon) {
		this.etpmon = Float.valueOf(etpmon.replace(",", "."));
	}
	
	

}
