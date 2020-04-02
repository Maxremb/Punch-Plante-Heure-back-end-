package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;

/**
 * @author Isaline MILLET
 *
 */
public interface IDepartementService<E, ME> {
	
	/**
	 * Méthode permettant de récupérer la liste des départements après recherche par nom
	 * @param nom Nom du département recherché
	 * @return liste de départements
	 */
	public ServiceResponse<List<E>> readDepartementByNom(String nom);

	/**
	 * Méthode permettant de récupérer la liste des conditions météo pour un département
	 * @param numDep numéro du département concerné
	 * @return liste d'objets météo
	 */
	public ServiceResponse<List<ME>> readMeteoByNumeroDep(Integer numDep);
	
}
