package com.fr.adaming.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

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
	 * Methode permettant de récupérer l'ensemble des meteos des departements à la date donnée en paramètre d'entrée
	 * @param date date à laquelle on veut connaitre la meteo
	 * @param page page à afficher
	 * @return un dto de type ServiceResponse contenant une page de type Meteo ou un objet null
	 */
	public ServiceResponse<Page<Meteo>> readByDate (LocalDate date, int page);
	
	/**
	 * Methode permettant de récupérer la météo du département donné en paramètre et à la date donnée en paramètre d'entrée
	 * @param date date à laquelle on veut connaitre la meteo
	 * @param numDepartement numero du departement duquel on veut connaitre la meteo
	 * @return un dto de type ServiceResponse contenant un objet de type Meteo ou un objet null
	 */
	public ServiceResponse<Meteo> readByDateAndDepartement (LocalDate date, int numDepartement);
	
	/** 
	 * Recupere toutes les entités meteo pour les dates contenus dans un mois
	 * @param mois le numero du mois recherché
	 * @param numDepartement le numéro du département
	 * @return Une liste de meteos
	 * 
	 * @author Gregoire
	 */
	public ServiceResponse<List<Meteo>> readByMonthAndDepartement (int annee, int mois, int numDepartement);

}
