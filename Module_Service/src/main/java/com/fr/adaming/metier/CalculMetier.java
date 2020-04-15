package com.fr.adaming.metier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.repositories.IJardinRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Couche métier permettant le traitement des données météos et de besoin en eau
 * d'un jardin
 * 
 * @author maxime
 * @since 0.0.1-SNAPSHOT
 */
@Component
@Service
@Slf4j
public class CalculMetier implements ICalculMetier {

	@Autowired
	private IJardinRepository jardinRepo;

	@Autowired
	protected JpaRepository<Jardin, Integer> dao;

	/**
	 * Methode calculant la reserve utile du jardin à partir de la méteo du jour et
	 * de la réserve utile de la veille
	 * 
	 * @param meteo correspond à la météo à traiter
	 * @return un set contenant la liste des jardins à arroser
	 */
	public Set<Jardin> calculRU(Meteo meteo) {
		// reçoit la meteo du jour d'un dept

		Departement dept = meteo.getDepartement();

		if (dept != null && dept.getNumeroDep() != 0) {

			// liste de tout les jardins
			List<Jardin> listeJardinsDept = jardinRepo.trouveListJardinParDepartement(dept.getNumeroDep());

			// Set de jardins à arroser pour 1 dept
			Set<Jardin> setJardinsforOneDept = new HashSet<>();

			// parcour la liste des jardins de ce dept
			for (Jardin jardin : listeJardinsDept) {

				// calcule la nouvelle RU du jardin
				// ETP à remplacer par ETR lorsque celle ci sera implenté
				jardin.setReserveUtile(
						jardin.getReserveUtile() - meteo.getEvapoTranspirationPotentielle() + meteo.getPluie());

				// empêche la reserve utile d'être supérieur à reserve max
				if (jardin.getReserveUtile() > jardin.getRESERVE_MAX_EAU()) {
					jardin.setReserveUtile(jardin.getRESERVE_MAX_EAU());
				}

				// determine le seuil d'arrosage définit à 20% de la réserve totale
				if (jardin.getReserveUtile() < (0.2 * jardin.getRESERVE_MAX_EAU())) {

					// creer un set des jardins à arroser
					setJardinsforOneDept.add(jardin);
				}
				dao.save(jardin);
			}

			// renvoyer le set vers methode envoyer email
			return setJardinsforOneDept;
		}

		// si meteo.dept = null on retourne un set vide
		else {
			Set<Jardin> emptySetJArdin = new HashSet<>();
			return emptySetJArdin;
		}
	}

	/**
	 * Méthode permettant de réinitialiser la réserve utile du jardin après
	 * arrossage de l'utilisateur
	 * 
	 * @param id du jardin en question
	 * @return un servjardin
	 */
	@Override
	public ServiceResponse<Jardin> reinitArrosJardin(Integer id) {
		try {
			Jardin jardin = jardinRepo.findById(id).orElse(null);
			// remplissage reserve utile par arrosage
			if (jardin != null) {
				if (jardin.getLongueur() != null && jardin.getLargeur() != null) {
					jardin.setReserveUtile(jardin.getRESERVE_MAX_EAU());
					jardin.setEstArroser(true);
					dao.save(jardin);
					log.info("Jardin reserve utile modifiée dans la DB");
					return new ServiceResponse<Jardin>("Success", jardin);
				} else {
					return new ServiceResponse<Jardin>("Pas de longueur et/ou largeur et/ou profondeur", null);
				}
			}
			return new ServiceResponse<Jardin>("Jardin null", null);
		} catch (InvalidDataAccessApiUsageException e) {
			log.warn("Tentative de réinitialisation jardin null");
			return new ServiceResponse<Jardin>("Tentative de réinitialisation jardin null ou absence profondeur", null);
		}
	}
}
