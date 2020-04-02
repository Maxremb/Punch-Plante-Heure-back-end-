package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.dto.ServiceResponse;

/**
 * <p>Classe Abstraite des classes service permettant la communication avec la
 * BD</p>
 * 
 * @author Grégoire Brebner
 *
 * @param E l'entité
 */
public abstract class AbstractService<E> implements IService<E> {

	@Autowired
	protected JpaRepository<E, Integer> dao;

	@Override
	public ServiceResponse<List<E>> readAll() {
		List<E> entityList = dao.findAll();
		ServiceResponse<List<E>> serviceResponse = new ServiceResponse<List<E>>();
		serviceResponse.setBody(entityList);
		return serviceResponse;
	}

	@Override
	public ServiceResponse<E> readById(Integer id) {
		
		E entity = dao.findById(id).orElse(null);		
		ServiceResponse<E> serviceResponse = new ServiceResponse<E>();
		serviceResponse.setBody(entity);
		
		if( entity == null ) { 
			serviceResponse.setMessage("Une entité avec cet ID n'existe pas dans la base de données");
		} 
		
		return serviceResponse;
	}

	@Override
	public boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	@Override
	public boolean deleteById(Integer id) {

		if (!dao.existsById(id)) {
			return false;
		}
		dao.deleteById(id);
		return true;

	}

}
