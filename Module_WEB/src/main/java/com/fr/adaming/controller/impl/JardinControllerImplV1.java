package com.fr.adaming.controller.impl;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
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
@Deprecated
@RequestMapping(path = "/jardin")
@RestController
public class JardinControllerImplV1 extends AbstractController<JardinCreateDto, JardinUpdateDto, Jardin> {

	@Autowired
	private IJardinService serviceJardin;
	
	@Autowired
	private JardinConverter conv;
	

	@GetMapping(path = "/name/{nom}")
	public ResponseEntity<ResponseDto<List<JardinUpdateDto>>> readByName(@PathVariable(name = "nom") @NotBlank String nom) {
		ResponseDto<List<JardinUpdateDto>> respDto = new ResponseDto<List<JardinUpdateDto>>();
		try {
			ServiceResponse<List<Jardin>> resp = serviceJardin.readByNom(nom);
			if(resp.getBody()!= null) {
				respDto.setError(false);
				respDto.setMessage("Success");
				conv.convertListEntityToUpdateDto(resp.getBody());
				respDto.setBody(conv.convertListEntityToUpdateDto(resp.getBody()));
				return new ResponseEntity<ResponseDto<List<JardinUpdateDto>>>(respDto, HttpStatus.OK);
			}
			respDto.setMessage(resp.getMessage());
			respDto.setError(true);
			respDto.setBody(null);
			return new ResponseEntity<ResponseDto<List<JardinUpdateDto>>>(respDto, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.warn(e.getMessage());
			respDto.setMessage(e.getMessage());
			respDto.setError(true);
			respDto.setBody(null);
			return new ResponseEntity<ResponseDto<List<JardinUpdateDto>>>(respDto, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/user/{identifier}")
	public ResponseEntity<ResponseDto<List<JardinUpdateDto>>> readByUser(@PathVariable(name = "identifier") @Positive Integer identifier) {
		ResponseDto<List<JardinUpdateDto>> respDto = new ResponseDto<List<JardinUpdateDto>>();
		try {
			ServiceResponse<List<Jardin>> resp = serviceJardin.readByUtilisateur(identifier);
			if(resp.getBody()!= null) {
				respDto.setError(false);
				respDto.setMessage("Success");
				conv.convertListEntityToUpdateDto(resp.getBody());
				respDto.setBody(conv.convertListEntityToUpdateDto(resp.getBody()));
				return new ResponseEntity<ResponseDto<List<JardinUpdateDto>>>(respDto, HttpStatus.OK);
			}
			respDto.setMessage(resp.getMessage());
			respDto.setError(true);
			respDto.setBody(null);
			return new ResponseEntity<ResponseDto<List<JardinUpdateDto>>>(respDto, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.warn(e.getMessage());
			respDto.setMessage(e.getMessage());
			respDto.setError(true);
			respDto.setBody(null);
			return new ResponseEntity<ResponseDto<List<JardinUpdateDto>>>(respDto, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/departement/{numDep}")
	public ResponseEntity<ResponseDto<List<JardinUpdateDto>>> readByDep(@PathVariable(name = "numDep") @Positive Integer numDep) {
		ResponseDto<List<JardinUpdateDto>> respDto = new ResponseDto<List<JardinUpdateDto>>();
		try {
			ServiceResponse<List<Jardin>> resp = serviceJardin.readByDepartement(numDep);
			if(resp.getBody()!= null) {
				respDto.setError(false);
				respDto.setMessage("Success");
				conv.convertListEntityToUpdateDto(resp.getBody());
				respDto.setBody(conv.convertListEntityToUpdateDto(resp.getBody()));
				return new ResponseEntity<ResponseDto<List<JardinUpdateDto>>>(respDto, HttpStatus.OK);
			}
			respDto.setMessage(resp.getMessage());
			respDto.setError(true);
			respDto.setBody(null);
			return new ResponseEntity<ResponseDto<List<JardinUpdateDto>>>(respDto, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.warn(e.getMessage());
			respDto.setMessage(e.getMessage());
			respDto.setError(true);
			respDto.setBody(null);
			return new ResponseEntity<ResponseDto<List<JardinUpdateDto>>>(respDto, HttpStatus.BAD_REQUEST);
		}
	}

}
