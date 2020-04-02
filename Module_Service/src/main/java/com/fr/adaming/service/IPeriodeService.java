package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Periode;
import com.fr.adaming.enums.TypePeriod;

/**
 * Couche service pour l'entité periode
 * 
 * @author Gregoire
 * @since 0.0.1
 */
public interface IPeriodeService {
	
	/**Cherche les periodes associés à un departement dans la base de données.
	 * @param idDepartement L'identifiant du département dans la base de données 
	 * @return Un dto serviceResponse contenant une liste de periodes ou une liste vide
	 */
	public ServiceResponse<List<Periode>> readByDepartementId(int idDepartement);
	
	/**Cherche les periodes associés à un model de plante dans la base de données.
	 * @param idPlante L'identifiant de la plante
	 * @return Un dto serviceResponse contenant une liste de periodes ou une liste vide
	 */
	public ServiceResponse<List<Periode>> readByPlanteModelId(int idPlante);
	
	/**Cherche les periodes associés à un departement et à une plante dans la base de données. 
	 * @param depId L'identifiant du département dans la base de données 
	 * @param planteId L'identifiant de la plante
	 * @return Un dto serviceResponse contenant une liste comportant une periode par type d'action ou une liste vide
	 */
	public ServiceResponse<List<Periode>> readByDepartementIdAndPlanteModelId(int depId, int planteId);
	
	/**Cherche la periode d'un type particulier associé à un departement et à une plante dans la base de données
	 * @param depId L'identifiant du département dans la base de données 
	 * @param planteId L'identifiant de la plante
	 * @param type le type d'action
	 * @return Un dto serviceResponse contenant objet de type periode ou null.
	 */
	public ServiceResponse<Periode> readByDepartementIdAndPlanteModelIdAndType(int depId, int planteId, TypePeriod type);

}
