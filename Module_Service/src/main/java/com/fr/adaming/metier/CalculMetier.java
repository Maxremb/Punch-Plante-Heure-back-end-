package com.fr.adaming.metier;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.repositories.IJardinRepository;

/**
 * Couche métier permettant le traitement des données météos et de besoin en eau
 * d'un jardin
 * 
 * @author maxime
 * @since 0.0.1-SNAPSHOT
 */
@Component
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
	 * Méthode permettant de réinitialiser la réserve utile du jardin après arrossage de l'utilisateur
	 * @param id du jardin en question
	 * @return un optionnal jardin
	 */
	@Override
	public Optional<Jardin> reinitArrosJardin(Integer id) {

		Optional<Jardin> jardin = jardinRepo.findById(id);
		// remplissage reserve utile par arrosage
		if (jardin.orElse(null).getLongueur() != null && jardin.orElse(null).getLargeur() != null
				&& jardin.orElse(null).getProfSol() != null) {
			jardin.orElse(null).setReserveUtile(jardin.orElse(null).getRESERVE_MAX_EAU());
			dao.save(jardin.orElse(null));
		}
		return jardin;

	}

}
