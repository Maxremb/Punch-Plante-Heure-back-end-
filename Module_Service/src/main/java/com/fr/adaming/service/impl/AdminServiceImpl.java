package com.fr.adaming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.repositories.IAdminRepository;
import com.fr.adaming.service.AbstractService;
import com.fr.adaming.service.IAdminService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe de la couche Service pour l'entité Admin Elle implémente l'interface
 * IAdminService
 * 
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class AdminServiceImpl extends AbstractService<Admin> implements IAdminService {

	@Autowired
	private IAdminRepository adminRepo;

	@Override
	public ServiceResponse<Admin> readByPseudonyme(String pseudonyme) {
		try {
			if (pseudonyme != null) {
				log.info("Recherche Admin par pseudonyme dans la DB OK");
				return new ServiceResponse<Admin>("Recherche Admin par nom et prenom",
						adminRepo.findByPseudonyme(pseudonyme));
			} else {
				log.info("Recherche Admin par nom et prenom non réalisée : nom ou prenom null");
				return new ServiceResponse<Admin>("Recherche non réalisée : pseudonyme null", null);
			}
		} catch (Exception e) {
			log.warn("Problème récupération d'un Admin après recherche via pseudonyme (couche service)"
					+ e.getMessage());
			return new ServiceResponse<Admin>("Recherche par pseudonyme non réalisée", null);
		}
	}

	@Override
	public ServiceResponse<Admin> readByEmail(String email) {
		try {
			if (email != null) {
				log.info("Recherche Admin par email dans la DB OK");
				return new ServiceResponse<Admin>("Recherche Admin par email", adminRepo.findByEmail(email));
			} else {
				log.info("Recherche Admin par email non réalisée : email null");
				return new ServiceResponse<Admin>("Recherche non réalisée : email null", null);
			}
		} catch (Exception e) {
			log.warn("Problème récupération d'un Admin après recherche via email (couche service)" + e.getMessage());
			return new ServiceResponse<Admin>("Recherche par email non réalisée", null);
		}
	}

	@Override
	public Boolean existsByEmail(String email) {
		if (email != null) {
			try {
				log.info("Recherche d'un admin par mail OK");
				return adminRepo.existsByEmail(email);
			} catch (Exception e) {
				log.warn("Problème recherche d'un Admin via email (couche service)" + e.getMessage());
				return false;
			}

		}
		log.info("Recherche admin via email non réalisée : email null");
		return false;
	}

	@Override
	public Boolean existsByPseudonyme(String pseudonyme) {
		if (pseudonyme != null) {
			try {
				log.info("Recherche d'un admin par pseudonyme OK");
				return adminRepo.existsByPseudonyme(pseudonyme);
			} catch (Exception e) {
				log.warn("Problème recherche d'un Admin via pseudonyme (couche service)" + e.getMessage());
				return false;
			}
		}
		log.info("Recherche admin via pseudonyme non réalisée : pseudonyme null");
		return false;
	}

	@Override
	public ServiceResponse<Admin> create(Admin entity) {
		if (entity != null) {
			if (!adminRepo.existsByEmail(entity.getEmail()) && !adminRepo.existsByPseudonyme(entity.getPseudonyme())) {
				try {
					dao.save(entity);
					log.info("Admin sauvegardé dans la DB");
					return new ServiceResponse<Admin>("Success", entity);
				} catch (Exception e) {
					log.warn(e.getMessage());
					return new ServiceResponse<Admin>("Exception lors de la création dans la DB", null);
				}
			}
			log.info(" Création non réalisé : Un admin possède déjà cet email ou ce pseudo");
			return new ServiceResponse<Admin>("Email ou pseudo deja utilisé", null);
		}
		log.info("Création non réalisé : objet en entrée null");
		return new ServiceResponse<Admin>("Objet d'entrée null", null);
	}

	@Override
	public ServiceResponse<Admin> update(Admin entite) {
		if (entite != null && dao.existsById(entite.getId())) {
			try {
				dao.save(entite);
				log.info("Admin modifié dans la DB");
				return new ServiceResponse<Admin>("Success", entite);
			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<Admin>("Exception lors de la modification dans la DB", null);
			}

		}
		log.info("Modification non réalisée : id inconnu dans la database ou entité nulle");
		return new ServiceResponse<Admin>(
				"Modification non réalisée : id inconnu dans la database ou entité nulle", null);
	}

	@Override
	public ServiceResponse<Admin> existsByEmailAndMdp(String email, String mdp) {

		if ( email != null && mdp != null ) {
			try {
				log.info("Vérification existence mail et mdp dans DB OK");
				if (adminRepo.findByEmailAndMdp(email, mdp) != null) {
					Admin entite = adminRepo.findByEmailAndMdp(email, mdp);
					return new ServiceResponse<Admin>("Success", entite);
				}
			} catch (Exception e) {
				log.warn("Problème recherche d'un Admin via mail et mdp (couche service)" + e.getMessage());
				return new ServiceResponse<Admin>("Exception lors de la recherche par email and mdp", null);
			}
		}
		log.info("Recherche non réalisée : email ou mdp null");
		return new ServiceResponse<Admin>("Recherche non réalisée : email ou mdp null", null);
	}

}
