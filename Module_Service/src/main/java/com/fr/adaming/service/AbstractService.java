package com.fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.dto.ServiceResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Classe Abstraite regroupant les méthodes communes aux classes de la couche service</p>
 * 
 * @author Grégoire Brebner
 *
 * @param E l'entité
 */
@Slf4j
public abstract class AbstractService<E> implements IService<E> {

	@Autowired
	protected JpaRepository<E, Integer> dao;
	
	
	@Override
	public ServiceResponse<Page<E>> readAll(int p) {
		log.debug("Service: readAll pour la page: " + p);
		Pageable pageable = PageRequest.of(p, 20);
		Page<E> entityList = dao.findAll(pageable);
		ServiceResponse<Page<E>> serviceResponse = new ServiceResponse<Page<E>>();
		serviceResponse.setBody(entityList);
		return serviceResponse;
	}

	@Override
	public ServiceResponse<E> readById(Integer id) {
		log.debug("Service: readById de l'entité: " + id);
		E entity = dao.findById(id).orElse(null);		
		ServiceResponse<E> serviceResponse = new ServiceResponse<E>();
		serviceResponse.setBody(entity);
		
		if( entity == null ) { 
			serviceResponse.setMessage("Une entité avec cet ID n'existe pas dans la base de données");
			log.warn("readById: Une entité avec cet ID n'existe pas dans la base de données");
		} 
		
		return serviceResponse;
	}

	@Override
	public boolean existsById(Integer id) {
		log.debug("Service: existsById pour l'entité: " + id);
		return dao.existsById(id);
	}

	@Override
	public boolean deleteById(Integer id) {

		log.debug("Service: deleteById de l'entité: " + id);
		
		if (!dao.existsById(id)) {
			log.warn("deleteById: Aucune entité existe avec cet id dans la base de données");
			return false;
		}
		dao.deleteById(id);
		return true;

	}

}
