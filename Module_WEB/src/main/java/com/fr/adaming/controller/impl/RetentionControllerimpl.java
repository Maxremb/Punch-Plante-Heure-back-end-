package com.fr.adaming.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.RetentionCreateDto;
import com.fr.adaming.dto.RetentionUpdateDto;
import com.fr.adaming.entity.Retention;
import com.fr.adaming.service.IRetentionService;

@RequestMapping (path = "/retention")
@RestController
@CrossOrigin
public class RetentionControllerimpl{ // extends AbstractController<RetentionCreateDto, RetentionUpdateDto, Retention>{ // Faire l'heritage quand converter existe!
	
	@Autowired
	private IRetentionService serviceRetention;
	
	
	
	
}
