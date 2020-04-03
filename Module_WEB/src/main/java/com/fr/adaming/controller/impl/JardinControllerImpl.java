package com.fr.adaming.controller.impl;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.converter.JardinConverter;
import com.fr.adaming.dto.JardinCreateDto;
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
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
	

	@GetMapping(path = "/name/{nom}")
	public ResponseEntity<ResponseDto<Page<JardinUpdateDto>>> readByName(@PathVariable(name = "nom") @NotBlank String nom) {

		ServiceResponse<Page<Jardin>> resp = serviceJardin.readByNom(nom);
		
		return makeUpdateDtoListResponse(resp);
		
	}

	@GetMapping(path = "/user/{identifier}")
	public ResponseEntity<ResponseDto<Page<JardinUpdateDto>>> readByUser(@PathVariable(name = "identifier") @Positive Integer identifier) {
		
		ServiceResponse<Page<Jardin>> resp = serviceJardin.readByUtilisateur(identifier);
		
		return makeUpdateDtoListResponse(resp);
		
	}

	@GetMapping(path = "/departement/{numDep}")
	public ResponseEntity<ResponseDto<Page<JardinUpdateDto>>> readByDep(@PathVariable(name = "numDep") @Positive Integer numDep) {
		
		ServiceResponse<Page<Jardin>> resp = serviceJardin.readByDepartement(numDep);
		
		return makeUpdateDtoListResponse(resp);
	}

}
