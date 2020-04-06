package com.fr.adaming.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.repositories.IMeteoRepository;
import com.fr.adaming.service.AbstractService;
import com.fr.adaming.service.IMeteoService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe de la couche Service pour l'entité Meteo
 * Elle étend la methode AbstractService et implémente l'interface IMeteoService
 * @author Jeanne-Marie MATHEVET
 * @since 0.0.1-SNAPSHOT
 */
@Service
@Slf4j
public class MeteoServiceImpl extends AbstractService<Meteo> implements IMeteoService {
	
	// Injection de dependances
	@Autowired
	private IMeteoRepository repo;
	// + dao via AbstractService d'une dépendance de l'interface JpaRepository de la couche Meteo
	
	@Override
	public ServiceResponse<Meteo> create(Meteo meteo) {
		try {
			if (meteo != null) {
				if (meteo.getDate() != null) {
					if (!dao.existsById(meteo.getId())) {
						dao.save(meteo);
						log.info("Création d'une météo dans la base de données");
						return new ServiceResponse<Meteo>("Création d'une météo dans la base de données", meteo);
					} else {
						log.info("Echec lors de la création d'une météo : l'ID est déjà existant");
						return new ServiceResponse<Meteo>("Echec lors de la création d'une météo : l'ID est déjà existant", null);
					}							
				} else {
					log.info("Echec lors de la création d'une météo : la date est NULLE");
					return new ServiceResponse<Meteo>("Echec lors de la création d'une météo : la date est NULLE", null);
				}
			} else {
				log.info("Echec lors de la création d'une météo : entité NULLE");
				return new ServiceResponse<Meteo>("Echec lors de la création d'une météo : entité NULLE", null);
			}
		} catch (DataIntegrityViolationException dive) {
			log.warn(dive.getMessage());
			return new ServiceResponse<Meteo>("Echec lors de la création d'une météo", null);
		}
	}

	@Override
	public ServiceResponse<Meteo> update(Meteo meteo) {
		if (meteo != null) {
			if (meteo.getDate() != null) {
				if (dao.existsById(meteo.getId())) {
					try {
						dao.save(meteo);
						log.info("Mise à jour de la météo dans la base de données");
						return new ServiceResponse<Meteo>("Mise à jour de la météo dans la base de données", meteo);
					} catch (Exception e) {
						log.warn(e.getMessage());
						return new ServiceResponse<Meteo>("Echec lors de la mise à jour d'une météo", null);
					}
				}
				else {
					log.info("Echec lors de la mise à jour d'une météo : l'ID est inexistant");
					return new ServiceResponse<Meteo>("Echec lors de la mise à jour d'une météo : l'ID est inexistant", null);
				}
			} else {
				log.info("Echec lors de la mise à jour d'une météo : la date est NULLE");
				return new ServiceResponse<Meteo>("Echec lors de la mise à jour d'une météo : la date est NULLE", null);
			}
		} else {
			log.info("Echec lors de la mise à jour d'une météo : entité NULLE");
			return new ServiceResponse<Meteo>("Echec lors de la mise à jour d'une météo : entité NULLE", null);
		}
	}

	@Override
	public ServiceResponse<Meteo> readByDate(LocalDate date) {
		if (date !=  null) {
			try {
				repo.findByDate(date);
				return new ServiceResponse<Meteo>("Recuperation de la meteo à la date indiquée", repo.findByDate(date));
			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<Meteo>("Echec lors de la récupération de la météo à la date indiquée", null);
			}
		} else {
			log.info("Echec lors de la récupération de la météo à la date indiquée : la date est NULLE");
			return new ServiceResponse<Meteo>("Echec lors de la récupération de la météo à la date indiquée : la date est NULLE", null);
		}
		
		
	}

}
