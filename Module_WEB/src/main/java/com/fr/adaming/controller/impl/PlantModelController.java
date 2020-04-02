package com.fr.adaming.controller.impl;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.PlanteModelCreateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.service.IPlanteModelService;

public class PlantModelController extends AbstractController<PlanteModelCreateDto, PlanteModelUpdateDto	, PlanteModel>{

	private IPlanteModelService servicePM;
	
	@Override
	public ResponseEntity<ResponseDto<List<PlanteModelUpdateDto>>> readAll() {
		
		ServiceResponse<List<PlanteModel>> serviceResponse = servicePM.readAllReduced();
		
		List<PlanteModelUpdateDto> returnedList = converter.convertListEntityToUpdateDto(serviceResponse.getBody());
		ResponseDto<List<PlanteModelUpdateDto>> responseDto = new ResponseDto<List<PlanteModelUpdateDto>>();
		responseDto.setError(false);
		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(returnedList);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

}
