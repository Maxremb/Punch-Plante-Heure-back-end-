package com.fr.adaming.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.service.AbstractService;
import com.fr.adaming.service.IService;

@RequestMapping(path = "/jardin")
@RestController
public class JardinControllerImpl extends AbstractService<Jardin>{

	@Autowired
	private IService<Jardin> service;

	@Override
	public ServiceResponse<Jardin> create(Jardin entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse<Jardin> update(Jardin entite) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
