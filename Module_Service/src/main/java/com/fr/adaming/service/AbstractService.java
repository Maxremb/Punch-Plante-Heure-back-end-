package com.fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public ServiceResponse<Page<E>> readAll(int p) {
		Pageable pageable = PageRequest.of(p, 20);
		Page<E> entityList = dao.findAll(pageable);
		ServiceResponse<Page<E>> serviceResponse = new ServiceResponse<Page<E>>();
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
