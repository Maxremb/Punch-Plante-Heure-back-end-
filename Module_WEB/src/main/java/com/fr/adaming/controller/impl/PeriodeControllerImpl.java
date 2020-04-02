package com.fr.adaming.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.PeriodeCreateDto;
import com.fr.adaming.dto.PeriodeUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Periode;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.enums.TypePeriod;

@RestController
@RequestMapping(path = "/periode")
public class PeriodeControllerImpl extends AbstractController<PeriodeCreateDto, PeriodeUpdateDto, Periode>{
	
	
	public ResponseEntity<ResponseDto<List<Periode>>> readByDepartementId(Departement departement) {
		
		
		return null;
		
	}
	public ResponseEntity<ResponseDto<List<Periode>>> readByPlanteModelId(PlanteModel planteModel){
		return null;
		
	}
	public ResponseEntity<ResponseDto<List<Periode>>> readByDepartementIdAndPlanteModelId(Departement departement, PlanteModel planteModel){
		return null;
		
	}
	public ResponseEntity<ResponseDto<Periode>> readByPlanteModelIdAndDepartementIdAndType(Departement departement, PlanteModel planteModel, TypePeriod type){
		return null;
		
	}

}
