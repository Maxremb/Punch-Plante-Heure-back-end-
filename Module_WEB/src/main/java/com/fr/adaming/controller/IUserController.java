package com.fr.adaming.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.dto.UserCreateDto;

@RequestMapping(path = "/users")
@CrossOrigin
public interface IUserController {

	@PostMapping
	public ResponseEntity<UserCreateDto> save(@RequestBody UserCreateDto dto);
}
