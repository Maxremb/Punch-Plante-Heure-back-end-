package com.fr.adaming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.repositories.IDepartementRepository;
import com.fr.adaming.repositories.IMeteoRepository;
import com.fr.adaming.service.AbstractService;
import com.fr.adaming.service.IDepartementService;

import lombok.extern.slf4j.Slf4j;

/**
 * Cette classe étend une classe Service abstraite qui implémente une interface
 * pour gérer les méthodes CRUD de la couche service de l'entité Département.
 * Cette classe implémente une interface qui gère les méthodes de la couche
 * service n'appartenant pas aux méthodes CRUD de l'entité Département.
 * 
 * @author Isaline MILLET
 * @since 0.0.1
 */
@Service
@Slf4j
public class DepartementServiceImpl extends AbstractService<Departement>
		implements IDepartementService<Departement, Meteo> {
	
	@Autowired
	private IMeteoRepository metRepo;

	@Override
	public ServiceResponse<Departement> create(Departement departement) {
		try {
			if (departement != null) {
				if (!dao.existsById(departement.getNumeroDep())) {
					log.info("Un département a été créé et enregistré dans la BD via la couche service.");
					return new ServiceResponse<Departement>("Création d'un département via couche service OK",dao.save(departement));
				} else {
					log.info(
							"Tentative échouée de création d'un département avec un ID déjà enregistré dans la BD via couche service");
					return new ServiceResponse<Departement>("Un département est déjà enregistré avec cet ID", null);
				}
			} else {
				log.info("Tentative échouée de création d'un département null via couche service");
				return new ServiceResponse<Departement>("Tentative de création d'un département NULL", null);
			}
		} catch (DataIntegrityViolationException dive) {
			log.warn("Tentative échouée de création d'un département");
			return new ServiceResponse<Departement>("Tentative échouée de création d'un département", null);
		}
	}

	@Override
	public ServiceResponse<Departement> update(Departement departement) {
		try {
			if (departement != null) {
				if (dao.existsById(departement.getNumeroDep())) {
					log.info("Un département a été modifié dans la BD via la couche service.");
					return new ServiceResponse<Departement>("Modification d'un département via couche service OK",
							dao.save(departement));
				} else {
					log.info(
							"Tentative échouée de modification d'un département inexistant dans la BD via couche service");
					return new ServiceResponse<Departement>("Aucun département n'existe avec cet ID", null);
				}
			} else {
				log.info("Tentative échouée de modification d'un département NULL");
				return new ServiceResponse<Departement>("Tentative de modification d'un département NULL", null);
			}
		} catch (DataIntegrityViolationException dive) {
			log.warn("Tentative échouée de modification d'un département avec un nom déjà utilisé");
			return new ServiceResponse<Departement>("Tentative de modification d'un département avec nom déjà utilisé",
					null);
		}
	}

	@Autowired
	protected IDepartementRepository depRepo;

	public ServiceResponse<Departement> readDepartementByNom(String nom) {
		try {
			if (nom != null) {
				log.info("Récupération d'un département après recherche par nom");
				return new ServiceResponse<Departement>("Récupération d'un département après recherche par nom",
						depRepo.findDepartementByNom(nom));
			} else {
				log.info("Tentative de récupération d'un département après recherche via nom NULL");
				return new ServiceResponse<Departement>(
						"Tentative de récupération d'un département après recherche via nom NULL", null);
			}
		} catch (Exception e) {
			log.warn(
					"Problème récupération d'un département après recherche via nom (couche service)" + e.getMessage());
			return new ServiceResponse<Departement>("Pb tentative de récupération d'un département", null);
		}
	}

	public ServiceResponse<Page<Meteo>> readMeteoByNumeroDep(Pageable pageable, Integer numDep) {
//		try {
			if (!dao.existsById(numDep)) {
				log.info("Récupération d'une liste de conditions météo après recherche par département inexistant");
				return new ServiceResponse<Page<Meteo>>(
						"Tentative récupération d'une liste de conditions météo par département inexistant", null);
			} else {
				log.info("Récupération d'une liste de conditions météo après recherche par département");
				return new ServiceResponse<Page<Meteo>>("Récupération d'une liste de conditions météo par département",
						metRepo.findMeteoByNumeroDep(pageable, numDep));
			}
//		} catch (Exception e) {
//			log.warn("Problème readMeteoByNumDep");
//			log.error(e.getLocalizedMessage());
//			return new ServiceResponse<Page<Meteo>>("Problème readMeteoByNumDep", depRepo.findMeteoByNumeroDep(pageable, numDep));
//		}

	}

}
