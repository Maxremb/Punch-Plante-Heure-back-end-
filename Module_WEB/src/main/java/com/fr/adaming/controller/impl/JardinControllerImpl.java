package com.fr.adaming.controller.impl;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.JardinCreateDto;
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.PageResponseDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.service.IJardinService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(path = "/jardin")
@RestController
public class JardinControllerImpl extends AbstractController<JardinCreateDto, JardinUpdateDto, Jardin> {

	@Autowired
	private IJardinService serviceJardin;
	

	@GetMapping(path = "/name")
	public ResponseEntity<PageResponseDto<Page<JardinUpdateDto>>> readByName(@RequestParam(name = "page") int page, @RequestParam(name = "nom") @NotBlank String nom) {

		ServiceResponse<Page<Jardin>> resp = serviceJardin.readByNom(page, nom);
		
		return makeUpdateDtoPageResponse(resp);
		
	}

	@GetMapping(path = "/user")
	public ResponseEntity<PageResponseDto<Page<JardinUpdateDto>>> readByUser(@RequestParam(name = "page") int page, @RequestParam(name = "identifier") @Positive Integer identifier) {
		
		ServiceResponse<Page<Jardin>> resp = serviceJardin.readByUtilisateur(page, identifier);
		
		return makeUpdateDtoPageResponse(resp);
		
	}

	@GetMapping(path = "/departement")
	public ResponseEntity<PageResponseDto<Page<JardinUpdateDto>>> readByDep(@RequestParam(name = "page") int page, @RequestParam(name = "numDep") @Positive Integer numDep) {
		
		ServiceResponse<Page<Jardin>> resp = serviceJardin.readByDepartement(page, numDep);
		
		return makeUpdateDtoPageResponse(resp);
	}

}
