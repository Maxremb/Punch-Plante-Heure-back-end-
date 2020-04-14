package com.fr.adaming.converter;

import com.fr.adaming.dto.MeteoXlsDto;
import com.fr.adaming.entity.Meteo;

/**
 * Interface IConverter pour Méteo
 * @author Jeanne-Marie MATHEVET
 * @since 0.0.1-SNAPSHOT
 *
 */
public interface IMeteoConverter {
	
	/**
	 * Méthdoe visant à convertir un fichier xls dto en entite
	 * @param xlsDto Données météos à convertir
	 * @return un objet de type Méteo
	 */
	public Meteo convertMeteoXlsDtoToEntity(MeteoXlsDto xlsDto);

}
