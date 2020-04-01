package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO Adapter si les besoins/retours sont differents

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
	public List<E> readAll() {
		return dao.findAll();
	}

	@Override
	public E readById(Integer id) {
		return dao.findById(id).orElse(null);
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
