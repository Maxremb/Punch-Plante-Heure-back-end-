package com.fr.adaming.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.converter.IMeteoConverter;
import com.fr.adaming.dto.MeteoXlsDto;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.metier.ICalculMetier;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Le processeur qui est exectuté quand on ajoute des entités meteo.
 * </p>
 * <p>
 * Appele les méthodes de calcul pour trouver le reservoir d'eau dans les
 * jardins du departement et envoie un email si ce reservoir est sous un soeuil
 * critique
 * </p>
 * 
 * @author Gregoire
 * @since 0.0.1
 */
@Component
@Slf4j
public class MeteoItemProcessor implements ItemProcessor<MeteoXlsDto, Meteo> {

	@Autowired
	private ICalculMetier calcul;

	@Autowired
	private IMeteoConverter converter;

	@Override
	public Meteo process(MeteoXlsDto meteoXls) throws Exception {

		log.debug("Processor appelé");

		Meteo meteo = converter.convertMeteoXlsDtoToEntity(meteoXls);

//		if (meteo.getDate().equals(LocalDate.now())) {

		calcul.calculRU(meteo);

//		Set<Jardin> jardinSet = calcul.calculRU(meteo);

//			for (Jardin j : jardinSet) {
//				// Utilisateur util = getUtilisateurByJardin(j);
//				// send email to util.getEmail avec nom du jardin et type de sol.
//			}

//		}

		return meteo;
	}

}
