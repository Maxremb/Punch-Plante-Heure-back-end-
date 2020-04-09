package com.fr.adaming.service.impl;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.service.AbstractService;
import com.fr.adaming.service.IUtilisateurService;

/**
 * Classe de la couche Service pour l'entité Utilisateur
 * Elle étend la methode AbstractService et implémente l'interface IUtilisateurService
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */

public class UtilisateurServiceImpl extends AbstractService<Utilisateur> implements IUtilisateurService{

	@Override
	public ServiceResponse<Utilisateur> create(Utilisateur entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse<Utilisateur> update(Utilisateur entite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse<Utilisateur> readByNomAndPrenom(String nom, String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse<Utilisateur> isActif(String pseudonyme) {
		// TODO Auto-generated method stub
		return null;
	}

}
