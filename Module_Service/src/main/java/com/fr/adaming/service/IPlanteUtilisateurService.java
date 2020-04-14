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

	public ServiceResponse<Page<PlanteUtilisateur>> readByJardin(int idJardin, int p);
	
	public ServiceResponse<List<PlanteUtilisateur>> readByJardin(int idJardin);
	
	public boolean deleteByJardin(int idJardin);
}
