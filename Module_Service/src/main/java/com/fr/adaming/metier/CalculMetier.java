package com.fr.adaming.metier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
public class CalculMetier implements ICalculMetier{

	@Autowired
	private IJardinRepository jardinRepo;



	/**
	 * Methode qui accepte un objet météo et calcule l'evapotranspiration dépendante
	 * des conditions Fonctionne pour une météo journalière, hebdomadaire,
	 * mensuelle, annuelle, ..
	 * 
	 * @param meteo correspond à la météo à traiter
	 */
	public Meteo calculEtpEtRU(Meteo meteo) {
		// recupere meteo du jour convertit

		// calculer etp et ajouter l'attribut à meteo

		if (meteo.getHumidite() > 50) {
			double etp = 0.013 * (meteo.getRayonnement() + 50)
					* ((meteo.getTemperature()) / (meteo.getTemperature() + 15));
			meteo.setEvapoTranspirationPotentielle(etp);
		} else {
			double etp = 0.013 * (meteo.getRayonnement() + 50)
					* ((meteo.getTemperature()) / (meteo.getTemperature() + 15))
					* (1 + (50 - meteo.getHumidite() / (70)));
			meteo.setEvapoTranspirationPotentielle(etp);
		}

		// appeller methode calculEtrJour lorsqu'implementer
		// calculEtrJour();

		return meteo;
	}

	/**
	 * 
	 * @param meteo correspond à la météo à traiter
	 * @return un set contenant la liste des jardins à arroser
	 */
	public Set<Jardin> calculRU(Meteo meteo) {
		// reçoit la meteo du jour d'un dept

		Departement dept = meteo.getDepartement();

		// liste de tout les jardins
		List<Jardin> listeJardinsDept = jardinRepo.trouveListJardinParDepartement(dept.getNumeroDep());

		// Set de jardins à arroser pour 1 dept
		Set<Jardin> setJardinsforOneDept = new HashSet<>();

		// parcour la liste des jardins de ce dept
		for (Jardin jardin : listeJardinsDept) {

			// calcule la nouvelle RU du jardin
			// ETP à remplacer par ETR lorsque celle ci sera implenté
			jardin.setReserveUtile(jardin.getReserveUtile() - meteo.getEvapoTranspirationPotentielle());

			// determine le seuil d'arrosage définit à 20% de la réserve totale

			if (jardin.getReserveUtile() < (0.2 * jardin.getRESERVE_MAX_EAU())) {

				// creer un set des jardins à arroser
				setJardinsforOneDept.add(jardin);
			}

		}

		// renvoyer le set vers methode envoyer email
		return setJardinsforOneDept;
	}
	
	

	// methode pour calculer ETR
	/**
	 * Methode qui calcule l'evapotranspiration réelle
	 */
	// public void calculEtrJour() {
	// pour l'instant inutile
	// correspond à ETP * coefficient (qui dépend globalement du jardin)

	/*
	 * au moment de l'implénter : ajouter attribut coeffMoyen au jardin ajouter
	 * attribut Etr au jardin
	 */

	/*
	 * Jardin jardin = new Jardin(); jardin.setEtr =
	 * meteo.getEvapoTranspirationPotentielle * coeffMoyen
	 */
	// }

}
