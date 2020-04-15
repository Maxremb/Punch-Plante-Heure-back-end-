package com.fr.adaming.metier;

import java.util.Set;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Meteo;

public interface ICalculMetier {

	public Set<Jardin> calculRU(Meteo meteo);

	public ServiceResponse<Jardin> reinitArrosJardin(Integer id);

}
