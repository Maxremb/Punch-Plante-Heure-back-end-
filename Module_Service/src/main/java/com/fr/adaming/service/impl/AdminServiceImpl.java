package com.fr.adaming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.repositories.IAdminRepository;
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
public class AdminServiceImpl implements IAdminService {

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
				return new ServiceResponse<Admin>("Recherche Admin par email",
						adminRepo.findByEmail(email));
			} else {
				log.info("Recherche Admin par email non réalisée : email null");
				return new ServiceResponse<Admin>("Recherche non réalisée : email null", null);
			}
		} catch (Exception e) {
			log.warn("Problème récupération d'un Admin après recherche via email (couche service)"
					+ e.getMessage());
			return new ServiceResponse<Admin>("Recherche par email non réalisée", null);
		}
	}

	@Override
	public ServiceResponse<Admin> readByEmailAndMdp(String email, String mdp) {
		try {
			if (email != null && mdp != null) {
				log.info("Recherche Admin par email et mdp dans la DB OK");
				return new ServiceResponse<Admin>("Recherche Utilisateur par email et mdp",
						adminRepo.findByEmailAndMdp(email, mdp));
			} else {
				log.info("Recherche Utilisateur par email et mdp non réalisée : email et/ou mdp null");
				return new ServiceResponse<Admin>("Recherche non réalisée : email et/ou mdp null", null);
			}
		} catch (Exception e) {
			log.warn("Problème récupération d'un Admin après recherche via email et mdp (couche service)"
					+ e.getMessage());
			return new ServiceResponse<Admin>("Recherche par email et mdp non réalisée", null);
		}
	}

	@Override
	public ServiceResponse<Boolean> existsByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse<Boolean> existsByPseudonyme(String pseudonyme) {
		// TODO Auto-generated method stub
		return null;
	}

}
