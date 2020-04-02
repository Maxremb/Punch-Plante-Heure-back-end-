package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;

/**
 * Cette interface manage les méthodes différentes des CRUD de la couche Service de l'entité Département
 * @author Isaline MILLET
 * @since 0.0.1
 * 
 * @param <E> entité Département
 * @param <ME> entité Météo
 */
public interface IDepartementService<E, ME> {
	
	/**
	 * Méthode permettant de récupérer la liste des départements après recherche par nom
	 * @param nom Nom du département recherché
	 * @return Dto Service Response avec message selon succes/fail + liste de départements en body
	 */
	public ServiceResponse<List<E>> readDepartementByNom(String nom);

	/**
	 * Méthode permettant de récupérer la liste des conditions météo pour un département
	 * @param numDep numéro du département concerné
	 * @return Dto Service Response avec message selon succes/fail + liste d'objets météo en body 
	 */
	public ServiceResponse<List<ME>> readMeteoByNumeroDep(Integer numDep);
	
}
