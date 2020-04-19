package com.fr.adaming.service;



import java.util.List;

import org.springframework.data.domain.Page;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteUtilisateur;

/**
 * <p>Interface de Plante Utilisateur Service avec une methode supplémentaire au CRUD de base.</p>
 * @author lucie
 * @since 0.0.1
 */
public interface IPlanteUtilisateurService {

	/**
	 * Methode d'affichage d'une page de Plante Utilisateur à partir de l'id du Jardin
	 * @param idJardin : id du jardin pour lequel on souhaite afficher les Plante Utilisateur
	 * @param p : page sur laquelle on veut afficher le jardin
	 * @return Service Response contenant une page de Plante Utilisateur
	 */
	public ServiceResponse<Page<PlanteUtilisateur>> readByJardin(int idJardin, int p);
	
	/**
	 * Methode d'affichage d'une liste de Plante Utilisateur à partir de l'id du Jardin
	 * @param idJardin : id du jardin pour lequel on souhaite afficher les Plante Utilisateur
	 * @return Service Response contenant une liste de Plante Utilisateur
	 */
	public ServiceResponse<List<PlanteUtilisateur>> readByJardin(int idJardin);
	
	/**
	 * Méthode pour supprimer les plante Utilisateur d'un jardin
	 * @param idJardin : id du jardin pour lequel on souhaite supprimer les plante Utilisateur
	 * @return boolean true ou false
	 */
	public boolean deleteByJardin(int idJardin);

	/** Recupère une liste de planteUtilisateurs par utilisateur
	 * @param idUtil Id de l'utilisateur
	 * @return une liste d'Id de planteUtilisateur
	 * @author Gregoire
	 */
	public ServiceResponse<List<Integer>> readByUtilisateurId(int idUtil);
}
