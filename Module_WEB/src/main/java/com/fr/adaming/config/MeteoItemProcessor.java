package com.fr.adaming.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Meteo;

@Component
public class MeteoItemProcessor implements ItemProcessor<Meteo, Meteo> {

	@Override
	public Meteo process(Meteo item) throws Exception {
		
		
		
		return null;
	}

}
