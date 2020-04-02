package com.fr.adaming.controller.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<ResponseDto<List<PlanteModelUpdateDto>>> readAll() {
		
		ServiceResponse<List<PlanteModel>> serviceResponse = servicePM.readAllReduced();
		
		return makeUpdateDtoListResponse(serviceResponse);
		
	}

}
