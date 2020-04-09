package com.fr.adaming.service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Retention;
import com.fr.adaming.enums.Sol;

/**
 * Interface de Retention Service définissant les méthodes propres à Retention
 * service
 * 
 * @author maxime
 * @since 0.0.1-SNAPSHOT
 */
public interface IRetentionService {

	/**
	 * Méthode permettant la recherche de retention par type de sol
	 * 
	 * @param sol du jardin au format String
	 * @return un ServiceReponse constitué d'un message, d'un booleen erreur et d'un
	 *         objet Retention
	 */
	public ServiceResponse<Retention> readBySol(Sol sol);

}
