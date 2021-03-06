package com.fr.adaming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Retention;
import com.fr.adaming.enums.Sol;
import com.fr.adaming.repositories.IJardinRepository;
import com.fr.adaming.repositories.IRetentionRepository;
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

	@Autowired
	private IRetentionRepository retentionRepo;

	@Override
	public ServiceResponse<Jardin> create(Jardin entity) {
		if (entity != null) {
			
				try {
					entity = calculReserveEauMax(entity);
					dao.save(entity);
					log.info("Jardin sauvegardé dans la DB");
					return new ServiceResponse<Jardin>("Success", entity);
				} catch (DataIntegrityViolationException e) {
					log.warn(e.getMessage());
					return new ServiceResponse<Jardin>("Exception lors de la création dans la DB", null);
				}

		}
		log.info("Création non réalisé : objet en entrée null");
		return new ServiceResponse<Jardin>("Objet d'entrée null", null);
	}

	@Override
	public ServiceResponse<Jardin> update(Jardin entite) {
		if (entite != null && dao.existsById(entite.getId())) {
				entite = calculReserveEauMax(entite);
				dao.save(entite);
				log.info("Jardin modifié dans la DB");
				return new ServiceResponse<Jardin>("Success", entite);
		}
		log.info("Modification non réalisée : id inconnu dans la database ou entité nulle");
		return new ServiceResponse<Jardin>("Modification non réalisée : id inconnu dans la database ou entité nulle",
				null);
	}

	@Override
	public ServiceResponse<Page<Jardin>> readByNom(int page, String nom) {
			if (nom != null) {
				log.info("Recherche jardin par nom dans la DB OK");
				Pageable pageable = PageRequest.of(page, 20);
				return new ServiceResponse<Page<Jardin>>("Recherche jardin par nom", repo.findByNom(pageable, nom));
			} else {
				log.info("Recherche jardin par nom non réalisée : nom null");
				return new ServiceResponse<Page<Jardin>>("Recherche non réalisée : nom null", null);
			}
	}

	@Override
	public ServiceResponse<Page<Jardin>> readByUtilisateur(int page, Integer id) {
		if (id != null) {
				log.info("Recherche jardin par utilisateur dans la DB OK");
				Pageable pageable = PageRequest.of(page, 20);
				return new ServiceResponse<Page<Jardin>>("Recherche jardin par utilisateur",
						repo.trouveParUtilisateur(pageable, id));
		}
		log.info("Recherche jardin par utilisateur non réalisée : id null");
		return new ServiceResponse<Page<Jardin>>("Recherche non réalisé : id null", null);

	}

	@Override
	public ServiceResponse<Page<Jardin>> readByDepartement(int page, Integer numDep) {
		if (numDep != null) {
				log.info("Recherche jardin par utilisateur dans la DB OK");
				Pageable pageable = PageRequest.of(page, 20);
				return new ServiceResponse<Page<Jardin>>("Recherche jardin par departement",
						repo.trouveParDepartement(pageable, numDep));
		}
		log.info("Recherche jardin par departement non réalisée : numDep null");
		return new ServiceResponse<Page<Jardin>>("Recherche non réalisée : numDep null", null);
	}

	@Override
	public Jardin calculReserveEauMax(Jardin jardin) {
		// calculer le volume du jardin si les données existent
		if (jardin.getLongueur() != null && jardin.getLargeur() != null && jardin.getProfSol() != null && jardin.getSol() != null) {

			// calculer son volume
			double volume = jardin.getLargeur() * jardin.getLongueur() * jardin.getProfSol();

			// recuperation de toutes les entites possibles de rétention de la DB
			List<Retention> listeRetentions = retentionRepo.findAll();
			
			if (!listeRetentions.isEmpty()) {
				
				Retention solArgileux = new Retention();
				Retention solArgiloLimoneux = new Retention();
				Retention solArgiloSableux = new Retention();
				Retention solLimonoArgileux = new Retention();
				Retention solSableux = new Retention();
				
				
				// recuperation de chaque entite de la DB sous forme d'objet
				for (Retention r : listeRetentions) {
					if (r.getSol() == Sol.Argileux) {
						solArgileux = r;
					}
					if (r.getSol() == Sol.ArgiloLimoneux) {
						solArgiloLimoneux = r;
					}
					if (r.getSol() == Sol.ArgiloSableux) {
						solArgiloSableux = r;
					}
					if (r.getSol() == Sol.LimonoArgileux) {
						solLimonoArgileux = r;
					}
					if (r.getSol() == Sol.Sableux) {
						solSableux = r;
					}
				}

				switch (jardin.getSol()) {
				case Argileux:
					jardin.setRESERVE_MAX_EAU(volume * solArgileux.getCoeffRemplissage());
					break;
				case ArgiloLimoneux:
					jardin.setRESERVE_MAX_EAU(volume * solArgiloLimoneux.getCoeffRemplissage());
					break;
				case ArgiloSableux:
					jardin.setRESERVE_MAX_EAU(volume * solArgiloSableux.getCoeffRemplissage());
					break;
				case LimonoArgileux:
					jardin.setRESERVE_MAX_EAU(volume * solLimonoArgileux.getCoeffRemplissage());
					break;
				case Sableux:
					jardin.setRESERVE_MAX_EAU(volume * solSableux.getCoeffRemplissage());
					break;
				}
				
			}

		}
		log.info("Calcul de la reserve d'eau max du jardin OK");
		return jardin;
	}

}
