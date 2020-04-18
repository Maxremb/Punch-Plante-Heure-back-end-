package com.fr.adaming.converter;

import java.util.List;

import com.fr.adaming.entity.Jardin;

/**Interface pour les méthodes spécifiques de jardinConverter
 * 
 * @author Gregoire
 *
 */
public interface IJardinConverter {
	
	/**
	 * Prend une liste de jardins et retourne la liste de leurs identifiants.
	 * @param jardinList Liste de jardins
	 * @return Une liste d'Integer
	 */
	public List<Integer> convertJardinListToId(List<Jardin> jardinList);

}
