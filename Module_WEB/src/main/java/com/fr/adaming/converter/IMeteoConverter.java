package com.fr.adaming.converter;

import com.fr.adaming.dto.MeteoXlsDto;
import com.fr.adaming.entity.Meteo;

public interface IMeteoConverter {
	
	public Meteo convertMeteoXlsDtoToEntity(MeteoXlsDto xlsDto);

}
