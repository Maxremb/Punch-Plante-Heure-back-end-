package com.fr.adaming.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Meteo;

@Component
public class MeteoWriter implements ItemWriter<Meteo>{

	@Override
	public void write(List<? extends Meteo> items) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
