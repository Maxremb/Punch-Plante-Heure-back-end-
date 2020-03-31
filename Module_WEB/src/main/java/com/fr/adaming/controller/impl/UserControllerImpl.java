package com.fr.adaming.controller.impl;

import static com.fr.adaming.converter.UserConverter.convertDtoToUser;
import static com.fr.adaming.converter.UserConverter.convertUserToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.IUserController;
import com.fr.adaming.dto.UserCreateDto;
import com.fr.adaming.service.IUserService;

@RestController
public class UserControllerImpl implements IUserController{

	@Autowired
	private IUserService service;
	
	@Override
	public ResponseEntity<UserCreateDto> save(UserCreateDto dto) {
		return ResponseEntity.ok(convertUserToDto(service.create(convertDtoToUser(dto))));
	}

}
