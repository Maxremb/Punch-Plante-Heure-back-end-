package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.PlanteModelCreateDto;
import com.fr.adaming.dto.PlanteModelReducedDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.entity.PlanteModel;

@Component
public class PlanteModelConverter implements IConverter<PlanteModelCreateDto, PlanteModelUpdateDto, PlanteModel> {

	@Autowired
	private PeriodeConverter periodeConverter;
	
	
	
	@Override
	public PlanteModel convertCreateDtoToEntity(PlanteModelCreateDto createDto) {
		
		if(createDto==null) {
			return null;
		}
		PlanteModel entity = new PlanteModel();
		entity.setNomCommun(createDto.getCommun());
		entity.setNomScientifique(createDto.getScientifique());
		entity.setDates(periodeConverter.convertListUpdateDtoToEntity(createDto.getPeriodes()));
		entity.setDescription(createDto.getDesc());
		entity.setEnsoleillementOpti(createDto.getEnsoleillement());
		entity.setHumiditeopti(createDto.getHumidite());
		entity.setIntervalArrosage(createDto.getArrosage());
		entity.setPhoto(createDto.getPicture());
		entity.setRepiquage(createDto.getRepiquage());
		entity.setToxicite(createDto.isToxi());
		entity.setTemperatureMax(createDto.getMax());
		entity.setTemperatureMin(createDto.getMin());
		entity.setHumiditeopti(createDto.getHumidite());
		entity.setAssoNegative(convertListReducedDtoToEntity(createDto.getNegative()));
		entity.setAssoPositive(convertListReducedDtoToEntity(createDto.getPositive()));
		
		return entity;
	}

	@Override
	public PlanteModelCreateDto convertEntityToCreateDto(PlanteModel entity) {
		if(entity==null) {
			return null;
		}
		
		PlanteModelCreateDto createDto= new PlanteModelCreateDto();
		createDto.setCommun(entity.getNomCommun());
		createDto.setScientifique(entity.getNomScientifique());
		createDto.setPeriodes(periodeConverter.convertListEntityToUpdateDto(entity.getDates()));
		createDto.setArrosage(entity.getIntervalArrosage());
		createDto.setEnsoleillement(entity.getEnsoleillementOpti());
		createDto.setHumidite(entity.getHumiditeopti());
		createDto.setRepiquage(entity.getRepiquage());
		createDto.setMin(entity.getTemperatureMin());
		createDto.setMax(entity.getTemperatureMax());
		createDto.setDesc(entity.getDescription());
		createDto.setToxi(entity.isToxicite());
		createDto.setPicture(entity.getPhoto());
		createDto.setNegative(convertListEntityToReducedDto(entity.getAssoNegative()));
		createDto.setPositive(convertListEntityToReducedDto(entity.getAssoPositive()));
		return createDto;
	}

	@Override
	public PlanteModel convertUpdateDtoToEntity(PlanteModelUpdateDto updateDto) {
		if(updateDto==null) {
			return null;
		}
		PlanteModel entity = new PlanteModel();
		entity=convertCreateDtoToEntity(updateDto);
		entity.setId(updateDto.getIdentifiant());
		
		return entity;
	}

	@Override
	public PlanteModelUpdateDto convertEntityToUpdateDto(PlanteModel entity) {
		if(entity==null) {
			return null;
		}
		PlanteModelUpdateDto updateDto = new PlanteModelUpdateDto();
		updateDto=(PlanteModelUpdateDto) convertEntityToCreateDto(entity);
		updateDto.setIdentifiant(entity.getId());
			
		return updateDto;
		
	}

	@Override
	public List<PlanteModel> convertListCreateDtoToEntity(List<PlanteModelCreateDto> listeCreateDto) {
		List<PlanteModel> listeRetour = new ArrayList<PlanteModel>();
		for(PlanteModelCreateDto p:listeCreateDto) {
			listeRetour.add(convertCreateDtoToEntity(p));
		}
		return listeRetour;
	}

	@Override
	public List<PlanteModelCreateDto> convertListEntityToCreateDto(List<PlanteModel> listeEntity) {
		List<PlanteModelCreateDto> listeRetour = new ArrayList<PlanteModelCreateDto>();
		for(PlanteModel p: listeEntity) {
			listeRetour.add(convertEntityToCreateDto(p));
		}
		return listeRetour;
	}

	@Override
	public List<PlanteModel> convertListUpdateDtoToEntity(List<PlanteModelUpdateDto> listeUpdateDto) {
		List<PlanteModel> listeRetour = new ArrayList<PlanteModel>();
		for(PlanteModelUpdateDto p:listeUpdateDto) {
			listeRetour.add(convertUpdateDtoToEntity(p));
		}
		return listeRetour;
	}

	@Override
	public List<PlanteModelUpdateDto> convertListEntityToUpdateDto(List<PlanteModel> listeEntity) {
		List<PlanteModelUpdateDto> listeRetour = new ArrayList<PlanteModelUpdateDto>();
		for(PlanteModel p: listeEntity) {
			listeRetour.add(convertEntityToUpdateDto(p));
		}
		return listeRetour;
	}
	
	public PlanteModelReducedDto convertEntityToReducedDto(PlanteModel entity) {
		if(entity==null) {
			return null;
		}
		PlanteModelReducedDto reducedDto= new PlanteModelReducedDto();
		reducedDto.setIdentifiant(entity.getId());
		reducedDto.setCommun(entity.getNomCommun());
		reducedDto.setScientifique(entity.getNomScientifique());
		reducedDto.setPicture(entity.getPhoto());
		return reducedDto;
	}
	
	public PlanteModel convertReducedDtoToEntity(PlanteModelReducedDto reducedDto) {
		if(reducedDto==null) {
			return null;
		}
		PlanteModel entity = new PlanteModel();
		entity.setNomCommun(reducedDto.getCommun());
		entity.setNomScientifique(reducedDto.getScientifique());
		entity.setPhoto(reducedDto.getPicture());
		entity.setId(reducedDto.getIdentifiant());
		
		
		
		return entity;
	}
	
	public List<PlanteModel> convertListReducedDtoToEntity(List<PlanteModelReducedDto> listeReducedDto) {
		List<PlanteModel> listeRetour = new ArrayList<PlanteModel>();
		for(PlanteModelReducedDto p:listeReducedDto) {
			listeRetour.add(convertReducedDtoToEntity(p));
		}
		return listeRetour;
	}

	public List<PlanteModelReducedDto> convertListEntityToReducedDto(List<PlanteModel> listeEntity) {
		List<PlanteModelReducedDto> listeRetour = new ArrayList<PlanteModelReducedDto>();
		for(PlanteModel p: listeEntity) {
			listeRetour.add(convertEntityToReducedDto(p));
		}
		return listeRetour;
	} 

}
