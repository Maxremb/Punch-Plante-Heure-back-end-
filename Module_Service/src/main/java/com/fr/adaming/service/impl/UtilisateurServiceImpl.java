package com.fr.adaming.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.repositories.IAdminRepository;
import com.fr.adaming.repositories.IUtilisateurRepository;
import com.fr.adaming.service.AbstractService;
import com.fr.adaming.service.IUtilisateurService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe de la couche Service pour l'entité Utilisateur Elle étend la methode
 * AbstractService et implémente l'interface IUtilisateurService
 * 
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@Slf4j
@Service
public class UtilisateurServiceImpl extends AbstractService<Utilisateur> implements IUtilisateurService {

	@Autowired
	private IUtilisateurRepository userRepo;

	@Autowired
	private IAdminRepository adminRepo;

	@Override
	public ServiceResponse<Utilisateur> create(Utilisateur entity) {
		if (entity != null) {
			if (!adminRepo.existsByEmail(entity.getEmail()) && !adminRepo.existsByPseudonyme(entity.getPseudonyme())) {
				try {
					dao.save(entity);
					log.info("Utilisateur sauvegardé dans la DB");
					return new ServiceResponse<Utilisateur>("Success", entity);
				} catch (Exception e) {
					log.warn(e.getMessage());
					return new ServiceResponse<Utilisateur>("Exception lors de la création dans la DB", null);
				}
			}
			log.info(" Création non réalisé : Un utilisateur possède déjà cet email ou ce pseudo");
			return new ServiceResponse<Utilisateur>("Email ou pseudo deja utilisé", null);
		}
		log.info("Création non réalisé : objet en entrée null");
		return new ServiceResponse<Utilisateur>("Objet d'entrée null", null);
	}

	@Override
	public ServiceResponse<Utilisateur> update(Utilisateur entite) {
		if (entite != null && dao.existsById(entite.getId())) {
			try {
				dao.save(entite);
				log.info("Utilisateur modifié dans la DB");
				return new ServiceResponse<Utilisateur>("Success", entite);
			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<Utilisateur>("Exception lors de la modification dans la DB", null);
			}

		}
		log.info("Modification non réalisée : id inconnu dans la database ou entité nulle");
		return new ServiceResponse<Utilisateur>(
				"Modification non réalisée : id inconnu dans la database ou entité nulle", null);
	}

	@Override
	public ServiceResponse<Utilisateur> readByNomAndPrenom(String nom, String prenom) {
		try {
			if (nom != null && prenom != null) {
				log.info("Recherche Utilisateur par nom et prenom dans la DB OK");
				return new ServiceResponse<Utilisateur>("Recherche Utilisateur par nom et prenom",
						userRepo.findByNomAndPrenom(nom, prenom));
			} else {
				log.info("Recherche Utilisateur par nom et prenom non réalisée : nom ou prenom null");
				return new ServiceResponse<Utilisateur>("Recherche non réalisée : nom ou prenom null", null);
			}
		} catch (Exception e) {
			log.warn("Problème récupération d'un Utilisateur après recherche via nom et prenom (couche service)"
					+ e.getMessage());
			return new ServiceResponse<Utilisateur>("Recherche par nom et prenom non réalisée", null);
		}

	}

	@Override
	public ServiceResponse<Boolean> isActif(String pseudonyme) {
		if (pseudonyme != null) {
			try {
				log.info("Vérification de l'activation d'un utilisateur OK");
				return new ServiceResponse<Boolean>("Vérification de l'activation d'un utilisateur",
						userRepo.isActif(pseudonyme));
			} catch (Exception e) {
				log.warn("Problème lors de la vérification de l'activation d'un utilisateur (couche service)"
						+ e.getMessage());
				return new ServiceResponse<Boolean>("Vérification utilisateur actif non réalisée", null);
			}
		}
		log.info("Vérificaation de l'activation d'un utilisateur non réalisée : pseudonme null");
		return new ServiceResponse<Boolean>("vérificaation de l'activation d'un utilisateur non réalisée",
				userRepo.isActif(pseudonyme));

	}

	@Override
	public ServiceResponse<Boolean> desactivateUser(Integer id) {
		if(id != null && dao.existsById(id)) {
			Optional<Utilisateur> user = dao.findById(id);
			user.orElse(null).setActif(false);
		}
		return null;
	}

}
