package com.fr.adaming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.repositories.IJardinRepository;
import com.fr.adaming.service.AbstractService;
import com.fr.adaming.service.IJardinService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe Service relative à l'entité Jardin
 * 
 * @author Clara Cadet
 * @since 0.0.1-SNAPSHOT
 */
@Service
@Slf4j
public class JardinServiceImpl extends AbstractService<Jardin> implements IJardinService {

	@Autowired
	private IJardinRepository repo;

	@Override
	public ServiceResponse<Jardin> create(Jardin entity) {
		if (entity != null) {
			if (!dao.existsById(entity.getId())) {
				try {
					dao.save(entity);
					log.info("Jardin sauvegardé dans la DB");
					return new ServiceResponse<Jardin>("Success", entity);
				} catch (Exception e) {
					log.warn(e.getMessage());
					return new ServiceResponse<Jardin>("Exception lors de la création dans la DB", null);
				}

			}
			log.info("Création non réalisé : id déjà existant dans la DB");
			return new ServiceResponse<Jardin>("Id déjà connu dans la database", null);

		}
		log.info("Création non réalisé : objet en entrée null");
		return new ServiceResponse<Jardin>("Objet d'entrée null", null);
	}

	@Override
	public ServiceResponse<Jardin> update(Jardin entite) {
		if (entite != null && existsById(entite.getId())) {
			try {
				dao.save(entite);
				log.info("Jardin modifié dans la DB");
				return new ServiceResponse<Jardin>("Success", entite);
			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<Jardin>("Exception lors de la modification dans la DB", null);
			}

		}
		log.info("Modification non réalisé : id inconnu dans la database");
		return new ServiceResponse<Jardin>("Modification non réalisé : id inconnu dans la database", null);
	}

	@Override
	public ServiceResponse<Page<Jardin>> readByNom(int page, String nom) {
		try {
		if (nom != null) {
					log.info("Recherche jardin par nom dans la DB OK");
					Pageable pageable = PageRequest.of(page, 20);
					return new ServiceResponse<Page<Jardin>>("Recherche jardin par nom", repo.findByNom(pageable, nom));
			} else {
				log.info("Recherche jardin par nom non réalisée : nom null");
				return new ServiceResponse<Page<Jardin>>("Recherche non réalisé : nom null", null);
			}
		} catch (Exception e) {
			log.warn("Problème récupération d'un jardin après recherche via nom (couche service)" + e.getMessage());
			return new ServiceResponse<Page<Jardin>>("Recherche par nom non réalisée", null);
		}
		
	}

	@Override
	public ServiceResponse<Page<Jardin>> readByUtilisateur(int page, Integer id) {
		if (id != null) {
			try {
				log.info("Recherche jardin par utilisateur dans la DB OK");
				Pageable pageable = PageRequest.of(page, 20);
				return new ServiceResponse<Page<Jardin>>("Recherche jardin par utilisateur",
						repo.trouveParUtilisateur(pageable, id));

			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<Page<Jardin>>("Exception lors de la recherche jardin par utilisateur", null);
			}
		}
		log.info("Recherche jardin par utilisateur non réalisée : id null");
		return new ServiceResponse<Page<Jardin>>("Recherche non réalisé : id null", null);

	}

	@Override
	public ServiceResponse<Page<Jardin>> readByDepartement(int page, Integer numDep) {

		if (numDep != null) {
			try {
				log.info("Recherche jardin par utilisateur dans la DB OK");
				Pageable pageable = PageRequest.of(page, 20);
				return new ServiceResponse<Page<Jardin>>("Recherche jardin par departement",
						repo.trouveParDepartement(pageable, numDep));

			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<Page<Jardin>>("Exception lors de la recherche jardin par departement", null);
			}
		}
		log.info("Recherche jardin par departement non réalisée : numDep null");
		return new ServiceResponse<Page<Jardin>>("Recherche non réalisé : numDep null", null);
	}

}
