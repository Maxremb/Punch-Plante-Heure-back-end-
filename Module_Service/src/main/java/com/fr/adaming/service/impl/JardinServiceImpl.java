package com.fr.adaming.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		if (existsById(entite.getId())) {
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

	/**
	 * Methode permettant la recherche de jardins par nom
	 * 
	 * @param nom du jardin au format String
	 * @return un ServiceReponse constitué d'un string "success" et d'une liste de
	 *         jardin (peut être vide), si cela fonctionne sinon un autre string et
	 *         un objet null
	 * @author Clara Cadet
	 */
	@Override
	public ServiceResponse<List<Jardin>> readByNom(String nom) {
		if (nom != null) {
			if (nom.length() > 0) {
				try {
					log.info("Recherche jardin par nom dans la DB OK");
					return new ServiceResponse<List<Jardin>>("Success", repo.findByNom(nom));
				} catch (Exception e) {
					log.warn(e.getMessage());
					return new ServiceResponse<List<Jardin>>("Exception lors de la recherche jardin par nom", null);
				}

			}
			log.info("Recherche jardin par nom non réalisée : nom vide");
			return new ServiceResponse<List<Jardin>>("Recherche non réalisé : nom vide", null);
		}
		log.info("Recherche jardin par nom non réalisée : nom null");
		return new ServiceResponse<List<Jardin>>("Recherche non réalisé : nom null", null);
	}

	/**
	 * Methode permettant la recherche de jardins par un identifiant utilisateur
	 * 
	 * @param id de l'utilisateur des jardins à rechercher
	 * @return un ServiceReponse constitué d'un string "success" et d'une liste de
	 *         jardin (peut être vide), si cela fonctionne sinon un autre string et
	 *         un objet null
	 * @author Clara Cadet
	 */
	@Override
	public ServiceResponse<List<Jardin>> readByUtilisateur(Integer id) {
		if (id != null) {
			try {
				log.info("Recherche jardin par utilisateur dans la DB OK");
				return new ServiceResponse<List<Jardin>>("Recherche jardin par utilisateur",
						repo.trouveParUtilisateur(id));

			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<List<Jardin>>("Exception lors de la recherche jardin par utilisateur", null);
			}
		}
		log.info("Recherche jardin par utilisateur non réalisée : id null");
		return new ServiceResponse<List<Jardin>>("Recherche non réalisé : id null", null);

	}

	/**
	 * Methode permettant la recherche de jardins par un numéro de département
	 * 
	 * @param numDep Numéro de département unique des jardins qu'on souhaite
	 *               cherché.
	 * @return un ServiceReponse constitué d'un string "success" et d'une liste de
	 *         jardin (peut être vide), si cela fonctionne sinon un autre string et
	 *         un objet null
	 * @author Clara Cadet
	 */
	@Override
	public ServiceResponse<List<Jardin>> readByDepartement(Integer numDep) {

		if (numDep != null) {
			try {
				log.info("Recherche jardin par utilisateur dans la DB OK");
				return new ServiceResponse<List<Jardin>>("Recherche jardin par departement ok",
						repo.trouveParDepartement(numDep));

			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<List<Jardin>>("Exception lors de la recherche jardin par departement", null);
			}
		}
		log.info("Recherche jardin par departement non réalisée : numDep null");
		return new ServiceResponse<List<Jardin>>("Recherche non réalisé : numDep null", null);

	}

}
