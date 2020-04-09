package com.fr.adaming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.repositories.IAdminRepository;
import com.fr.adaming.service.IAdminService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements IAdminService{

	@Autowired
	private IAdminRepository adminRepo;

	@Override
	public ServiceResponse<Admin> readByPseudonyme(String pseudonyme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse<Admin> readByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse<Admin> readByEmailAndMdp(String email, String mdp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse<Boolean> existsByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse<Boolean> existsByPseudonyme(String pseudonyme) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
