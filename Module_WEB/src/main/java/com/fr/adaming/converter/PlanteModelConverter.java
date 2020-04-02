package com.fr.adaming.converter;

import java.util.List;

import com.fr.adaming.dto.PlanteModelCreateDto;
import com.fr.adaming.dto.PlanteModelReducedDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.entity.PlanteModel;

public class PlanteModelConverter implements IConverter<PlanteModelCreateDto, PlanteModelUpdateDto, PlanteModel> {

	@Override
	public PlanteModel convertCreateDtoToEntity(PlanteModelCreateDto createDto) {
		PlanteModel entity = new PlanteModel();
		
		
		return entity;
	}

	@Override
	public PlanteModelCreateDto convertEntityToCreateDto(PlanteModel entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanteModel convertUpdateDtoToEntity(PlanteModelUpdateDto updateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanteModelUpdateDto convertEntityToUpdateDto(PlanteModel entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanteModel> convertListCreateDtoToEntity(List<PlanteModelCreateDto> listeCreateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanteModelCreateDto> convertListEntityToCreateDto(List<PlanteModel> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanteModel> convertListUpdateDtoToEntity(List<PlanteModelUpdateDto> listeUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanteModelUpdateDto> convertListEntityToUpdateDto(List<PlanteModel> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PlanteModelReducedDto convertEntityToReducedDto(PlanteModel entity) {
		return null;
	}
	
	public PlanteModel convertReducedDtoToEntity(PlanteModelReducedDto reducedDto) {
		return null;
	}
	
	public List<PlanteModel> convertListReducedDtoToEntity(List<PlanteModelReducedDto> listeReducedDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PlanteModelReducedDto> convertListEntityToReducedDto(List<PlanteModel> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	} 

}
