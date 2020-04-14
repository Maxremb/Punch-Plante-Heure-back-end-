package com.fr.adaming.dto;

import lombok.Data;

/**
 * Classe représentative de l'objet météo lors de la récupération des données
 * 
 * @author Jeanne-Marie MATHEVET
 * @since 0.0.1-SNAPSHOT
 */
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

	/**
	 * Méthode permettant d'attribuer une longitude d'un fichier csv en attribut java
	 * @param longitude Longitude du fichier csv
	 */
	public void setLongitude(String longitude) {
		this.longitude = Integer.valueOf(longitude.replace(",", "").replace(".", ""));
	}

	/**Méthode permettant d'attribuer une latitude d'un fichier csv en attribut java
	 * @param latitude
	 */
	public void setLatitude(String latitude) {
		this.latitude = Integer.valueOf(latitude.replace(",", "").replace(".", ""));
	}

	/**Méthode permettant d'attribuer une hauteur de précipitation d'un fichier csv en attribut java
	 * @param rr Hauteur de précipitation
	 */
	public void setRr(String rr) {
		this.rr = Float.valueOf(rr.replace(",", "."));
	}

	/**Méthode permettant d'attribuer une température minimale d'un fichier csv en attribut java
	 * @param tn Température minimale
	 */
	public void setTn(String tn) {
		this.tn = Float.valueOf(tn.replace(",", "."));
	}

	/**Méthode permettant d'attribuer une température maximale d'un fichier csv en attribut java
	 * @param tx Température maximale
	 */
	public void setTx(String tx) {
		this.tx = Float.valueOf(tx.replace(",", "."));
	}

	/**Méthode permettant d'attribuer la vitesse du vent maximale d'un fichier csv en attribut java
	 * @param fxi Vitesse du vent maximale
	 */
	public void setFxi(String fxi) {
		this.fxi = Float.valueOf(fxi.replace(",", "."));
	}

	/**Méthode permettant d'attribuer la vitesse du vent maximum moyennée sur 10 min d'un fichier csv en attribut java
	 * @param fxy Vitesse du vent max moyenné sur 10 min
	 */
	public void setFxy(String fxy) {
		this.fxy = Float.valueOf(fxy.replace(",", "."));
	}

	/**Méthode permettant d'attribuer l'évapotranspiration quotidienne d'un fichier csv en attribut java
	 * @param etpmon Evaporation quotidienne
	 */
	public void setEtpmon(String etpmon) {
		this.etpmon = Float.valueOf(etpmon.replace(",", "."));
	}

}
