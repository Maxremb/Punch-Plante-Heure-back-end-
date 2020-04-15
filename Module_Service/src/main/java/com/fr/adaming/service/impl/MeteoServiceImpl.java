package com.fr.adaming.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.repositories.IDepartementRepository;
import com.fr.adaming.repositories.IMeteoRepository;
import com.fr.adaming.service.AbstractService;
import com.fr.adaming.service.IMeteoService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe de la couche Service pour l'entité Meteo Elle étend la methode
 * AbstractService et implémente l'interface IMeteoService
 * 
 * @author Jeanne-Marie MATHEVET
 * @since 0.0.1-SNAPSHOT
 */
@Service
@Slf4j
public class MeteoServiceImpl extends AbstractService<Meteo> implements IMeteoService {

	@Autowired
	private IMeteoRepository repo;

	@Autowired
	private IDepartementRepository repoD;

	@Override
	public ServiceResponse<Meteo> create(Meteo meteo) {
		if (meteo != null) {
			if (meteo.getDate() != null) {
				if (!dao.existsById(meteo.getId())) {
					dao.save(meteo);
					log.info("Création d'une météo dans la base de données");
					return new ServiceResponse<Meteo>("Création d'une météo dans la base de données", meteo);
				} else {
					log.info("Echec lors de la création d'une météo : l'ID est déjà existant");
					return new ServiceResponse<Meteo>("Echec lors de la création d'une météo : l'ID est déjà existant",
							null);
				}
			} else {
				log.info("Echec lors de la création d'une météo : la date est NULLE");
				return new ServiceResponse<Meteo>("Echec lors de la création d'une météo : la date est NULLE", null);
			}
		} else {
			log.info("Echec lors de la création d'une météo : entité NULLE");
			return new ServiceResponse<Meteo>("Echec lors de la création d'une météo : entité NULLE", null);
		}
	}

	@Override
	public ServiceResponse<Meteo> update(Meteo meteo) {
		if (meteo != null) {
			if (meteo.getDate() != null) {
				if (dao.existsById(meteo.getId())) {
					dao.save(meteo);
					log.info("Mise à jour de la météo dans la base de données");
					return new ServiceResponse<Meteo>("Mise à jour de la météo dans la base de données", meteo);
				} else {
					log.info("Echec lors de la mise à jour d'une météo : l'ID est inexistant");
					return new ServiceResponse<Meteo>("Echec lors de la mise à jour d'une météo : l'ID est inexistant",
							null);
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
	public ServiceResponse<Meteo> readByDateAndDepartement(LocalDate date, int numDepartement) {
		if (date != null && repoD.existsById(numDepartement)) {
			log.info("Recupération de la méteo à partir du departement et de la date entrée");
			return new ServiceResponse<Meteo>("Recuperation de la meteo du departement indiqué et à la date indiquée",
					repo.findByDateAndDepartement(date, repoD.findById(numDepartement).orElse(null)));
		} else {
			log.info(
					"Echec lors de la récupération de la météo : la date est NULLE et/ou le département n'existe pas dans la base de données");
			return new ServiceResponse<Meteo>(
					"Echec lors de la récupération  de la météo : la date est NULLE et/ou le département n'existe pas dans la base de données",
					null);
		}
	}

	@Override
	public ServiceResponse<Page<Meteo>> readByDate(LocalDate date, int page) {
		if (date != null) {
			Pageable pageable = PageRequest.of(page, 20);
			Page<Meteo> pageMeteo = repo.findByDate(date, pageable);
			log.info("Recupération de la météo a partir de la date entrée");
			return new ServiceResponse<Page<Meteo>>("Recupération de la météo à la date indiquée", pageMeteo);
		} else {
			log.info("Echec lors de la récupération de la météo : la date est NULLE");
			return new ServiceResponse<Page<Meteo>>("Echec lors de la récupération de la météo : la date est NULLE",
					null);
		}
	}

	@Override
	public ServiceResponse<List<Meteo>> readByMonthAndDepartement(int annee, int mois, int numDepartement) {
		if (mois < 13 && mois > 0) {
			if (repoD.existsById(numDepartement)) {
				log.info("Récupération des météos pour le mois et le département indiqués");
				return new ServiceResponse<List<Meteo>>(
						"Récupération des météos pour le mois et le département indiqués",
						repo.findMeteoByMonthAndDepartement(annee, mois, numDepartement));

			} else {
				log.warn("Echec lors de la récupération des meteos : le departement est inconnu");
				return new ServiceResponse<List<Meteo>>(
						"Echec lors de la récupération des meteos : le departement est inconnu", null);

			}

		} else {
			log.warn("Echec lors de la récupération des meteos : le mois est invalide");
			return new ServiceResponse<List<Meteo>>("Echec lors de la récupération des meteos : le mois est invalide",
					null);
		}

	}

}
