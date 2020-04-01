package com.fr.adaming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.repositories.IDepartementRepository;
import com.fr.adaming.service.AbstractService;

import lombok.extern.slf4j.Slf4j;

/**
 * Cette classe implémente une classe Service abstraite qui implémente
 * une interface pour gérer les méthodes CRUD de la couche service. Cette classe
 * manage la couche service de l'entité Département et définie des méthodes
 * propres à ce service (create / update / read personnalisés).
 * 
 * @author Isaline MILLET
 * @since 0.0.1
 *
 */
@Service
@Slf4j
public class DepartementServiceImpl extends AbstractService<Departement> {

	@Override
	public ServiceResponse<Departement> create(Departement departement) {
		if (departement != null) {
			if (!dao.existsById(departement.getNumeroDep())) {
				log.info("Un département a été créé et enregistré dans la BD via la couche service.");
				return new ServiceResponse<Departement>("Création d'un département via couche service OK", dao.save(departement));
			} else {
				log.info("Tentative échouée de création d'un département avec un ID déjà enregistré dans la BD via couche service");
				return new ServiceResponse<Departement>("Un département est déjà enregistré avec cet ID", null);
			}
		} else {
			log.info("Tentative échouée de création d'un département null via couche service");
			return new ServiceResponse<Departement>("Tentative de création d'un département NULL", null);
		}
	}

	@Override
	public ServiceResponse<Departement> update(Departement departement) {
		if (departement != null) {
			if (dao.existsById(departement.getNumeroDep())) {
				log.info("Un département a été modifié dans la BD via la couche service.");
				return new ServiceResponse<Departement>("Modification d'un département via couche service OK", dao.save(departement));
			} else {
				log.info("Tentative échouée de modification d'un département inexistant dans la BD via couche service");
				return new ServiceResponse<Departement>("Aucun département n'existe avec cet ID", null);
			}
		} else {
			log.info("Tentative échouée de modification d'un département NULL");
			return new ServiceResponse<Departement>("Tentative de modification d'un département NULL", null);
		}
	}

	@Autowired
	protected IDepartementRepository depRepo;
	
	/**
	 * Méthode permettant de récupérer la liste des départements après recherche par nom
	 * @param nom Nom du département recherché
	 * @return liste de départements
	 */
	public ServiceResponse<List<Departement>> readDepartementByNom(String nom) {
		log.info("Récupération d'une liste de département après recherche par nom");
		return new ServiceResponse<List<Departement>>("Récupération d'une liste de département après recherche par nom", depRepo.findDepartementByNom(nom));
	}

	/**
	 * Méthode permettant de récupérer la liste des conditions météo pour un département
	 * @param numDep numéro du département concerné
	 * @return liste d'objets météo
	 */
	public ServiceResponse<List<Meteo>> readMeteoByNumeroDep(Integer numDep) {
		log.info("Récupération d'une liste de conditions météo après recherche par département");
		return new ServiceResponse<List<Meteo>>("Récupération d'une liste de conditions météo par département", depRepo.findMeteoByNumeroDep(numDep));
	}

}
