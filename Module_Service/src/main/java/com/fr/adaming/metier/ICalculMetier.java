package com.fr.adaming.metier;

import java.util.Set;

import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Meteo;

public interface ICalculMetier {
	
//	public Meteo calculEtpEtRU(Meteo meteo);
	public Set<Jardin> calculRU(Meteo meteo); 

}
