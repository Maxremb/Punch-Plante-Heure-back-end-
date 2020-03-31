package com.fr.adaming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.User;
import com.fr.adaming.repositories.IUserRepository;
import com.fr.adaming.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepository repo;
	
	@Override
	public User create(User user) {
		return repo.save(user);
	}

}
