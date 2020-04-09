package com.fr.adaming.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Meteo;
import com.fr.adaming.repositories.IMeteoRepository;

/** Ecrit la liste de meteos dans la base de données
 * @author Gregoire
 * @since 0.0.1
 */
@Component
public class MeteoWriter implements ItemWriter<Meteo>{
	
	@Autowired
	private IMeteoRepository dao;

	@Override
	public void write(List<? extends Meteo> items) throws Exception {
		
		for (Meteo m: items) {
			if(m != null) {
				dao.save(m);
			}
		}
		
	}
	
	

}
