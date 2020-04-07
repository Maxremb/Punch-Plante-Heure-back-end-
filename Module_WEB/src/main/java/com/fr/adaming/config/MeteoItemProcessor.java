package com.fr.adaming.config;

import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.MeteoXlsDto;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.metier.ICalculMetier;

/**<p>Le processeur qui est exectuté quand on ajoute des entités meteo.</p>
 * <p>Appele les méthodes de calcul pour trouver le reservoir d'eau dans les jardins du departement et envoie un email si ce reservoir est sous un soeuil critique</p>  
 * @author Gregoire
 *@since 0.0.1
 */
@Component
public class MeteoItemProcessor implements ItemProcessor<MeteoXlsDto, Meteo> {

	@Autowired
	private ICalculMetier calcul;
	
//	@Autowired
//	private IConverterMeteo converter;
	
	@Override
	public Meteo process(MeteoXlsDto meteoXls) throws Exception {
		
		Meteo meteo = null; // = converter....
		
		Set<Jardin> jardinSet = calcul.calculRU(meteo);
		
		for (Jardin j : jardinSet) {
			// Utilisateur util = getUtilisateurByJardin(j);
			// send email to util.getEmail avec nom du jardin et type de sol.
		}
		
		return meteo;
	}

}
