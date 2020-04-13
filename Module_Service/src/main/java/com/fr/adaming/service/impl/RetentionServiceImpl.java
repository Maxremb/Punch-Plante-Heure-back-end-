package com.fr.adaming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Retention;
import com.fr.adaming.enums.Sol;
import com.fr.adaming.repositories.IRetentionRepository;
import com.fr.adaming.service.AbstractService;
import com.fr.adaming.service.IRetentionService;

import lombok.extern.slf4j.Slf4j;

/**
 * CLasse service relative à l'entité Retention
 * 
 * @author maxime
 * @since 0.0.1-SNAPSHOT
 */
@Service
@Slf4j
public class RetentionServiceImpl extends AbstractService<Retention> implements IRetentionService {

	@Autowired
	private IRetentionRepository repo;

	@Override
	public ServiceResponse<Retention> create(Retention entity) {
		if (entity != null) {
			if (!repo.existsBySol(entity.getSol())) {
				try {
					dao.save(entity);
					log.info("Retention sauvegardé dans la BD");
					return new ServiceResponse<Retention>("Success", entity);
				} catch (DataIntegrityViolationException e) {
					log.warn(e.getMessage());
					return new ServiceResponse<Retention>("Exception lors de la création dans la DB", null);
				}
			}
			log.info("Création non réalisé : sol déjà existant dans la DB");
			return new ServiceResponse<Retention>("Sol déjà connu dans la database", null);
		}
		log.info("Création non réalisé : objet en entrée null");
		return new ServiceResponse<Retention>("Objet d'entrée null", null);
	}

	@Override
	public ServiceResponse<Retention> update(Retention entite) {
		if (entite != null && repo.existsBySol(entite.getSol())) {
			dao.save(entite);
			log.info("Retention modifié dans la DB");
			return new ServiceResponse<Retention>("Success", entite);
		}
		log.info("Modification non réalisé : sol inconnu dans la database");
		return new ServiceResponse<Retention>("Modification non réalisé : sol inconnu dans la database", null);
	}

	@Override
	public ServiceResponse<Retention> readBySol(Sol sol) {
		if (sol != null) {
			log.info("Recherche retention par type de sol dans la DB OK");
			return new ServiceResponse<Retention>("Success", repo.findBySol(sol));
		} else {
			log.info("Recherche retention par type de sol non réalisée : sol null");
			return new ServiceResponse<Retention>("Recherche non réalisée : sol null", null);
		}
	}

}
