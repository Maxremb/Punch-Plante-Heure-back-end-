package com.fr.adaming.controller.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.PlanteModelCreateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.service.IPlanteModelService;

@RestController
@CrossOrigin
@RequestMapping(path="/plantemodel")
public class PlantModelControllerImpl extends AbstractController<PlanteModelCreateDto, PlanteModelUpdateDto	, PlanteModel>{

	@Autowired
	private IPlanteModelService servicePM;
	
	@Override
	public ResponseEntity<ResponseDto<Page<PlanteModelUpdateDto>>> readAll(int p) {
		
		ServiceResponse<Page<PlanteModel>> serviceResponse = servicePM.readAllReduced(p);
		
		return makeUpdateDtoListResponse(serviceResponse);
		
	}
	
	@GetMapping(path = "/nom")
	public ResponseEntity<ResponseDto<Page<PlanteModelUpdateDto>>> readByNom(@RequestParam(name = "page") int page,@RequestParam(name = "nom") String nom){
		
		ServiceResponse<Page<PlanteModel>> serviceResponse = servicePM.findByNom(page, nom);
		
		return makeUpdateDtoListResponse(serviceResponse);
		
	}

}
