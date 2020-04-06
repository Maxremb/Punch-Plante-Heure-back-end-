package com.fr.adaming.service;

import java.time.LocalDate;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Meteo;

/**
 * Interface de la couche Service pour l'entité Meteo
 * Definissant une methode particulière à l'entité Meteo
 * @author Jeanne-Marie MATHEVET
 * @since 0.0.1-SNAPSHOT
 */
public interface IMeteoService {
	
	
	/** 
	 * Methode permettant de récupérer la meteo à la date donnée en paramètre d'entrée
	 * @param date date à laquelle on veut connaitre la meteo
	 * @return un dto de type ServiceResponse contenant un objet de type Meteo ou un objet null
	 */
	public ServiceResponse<Meteo> readByDate (LocalDate date);

}
