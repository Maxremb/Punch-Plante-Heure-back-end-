package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteUtilisateur;

/**
 * <p>Interface de Plante Utilisateur Service avec une methode supplémentaire au CRUD de base.</p>
 * @author lucie
 * @since 0.0.1
 */
public interface IPlanteUtilisateurService {

	public ServiceResponse<List<PlanteUtilisateur>> findByJardin(int idJardin);
}
