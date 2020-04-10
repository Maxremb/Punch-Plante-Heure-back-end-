package com.fr.adaming.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fr.adaming.dto.ServiceResponse;

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
	public ServiceResponse<E> readDepartementByNom(String nom);

	/**
	 * Méthode permettant de récupérer la liste des conditions météo pour un département
	 * @param numDep numéro du département concerné
	 * @return Dto Service Response avec message selon succes/fail + liste d'objets météo en body 
	 */
	public ServiceResponse<Page<ME>> readMeteoByNumeroDep(Pageable pageable, Integer numDep);
	
	/**
	 * Méthode permettant de récupérer la liste des départements
	 * @return Dto Service Response avec message selon succes/fail + liste d'objets départements en body 
	 */
	public ServiceResponse<List<E>> readAllList();
	
}
