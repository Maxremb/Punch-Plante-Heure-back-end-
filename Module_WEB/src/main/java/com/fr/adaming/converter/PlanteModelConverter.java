package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
		entity.setDates(null);
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
		entity.setAssoNegative(createDto.getNegative());
		entity.setAssoPositive(createDto.getPositive());
		entity.setFamille(createDto.getMifa());
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
		createDto.setPeriodes(null);
		createDto.setArrosage(entity.getIntervalArrosage());
		createDto.setEnsoleillement(entity.getEnsoleillementOpti());
		createDto.setHumidite(entity.getHumiditeopti());
		createDto.setRepiquage(entity.getRepiquage());
		createDto.setMin(entity.getTemperatureMin());
		createDto.setMax(entity.getTemperatureMax());
		createDto.setDesc(entity.getDescription());
		createDto.setToxi(entity.isToxicite());
		createDto.setPicture(entity.getPhoto());
		createDto.setNegative(entity.getAssoNegative());
		createDto.setPositive(entity.getAssoPositive());
		createDto.setMifa(entity.getFamille());
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
		PlanteModelUpdateDto createDto= new PlanteModelUpdateDto();
		createDto.setCommun(entity.getNomCommun());
		createDto.setScientifique(entity.getNomScientifique());
		createDto.setPeriodes(null);
		createDto.setArrosage(entity.getIntervalArrosage());
		createDto.setEnsoleillement(entity.getEnsoleillementOpti());
		createDto.setHumidite(entity.getHumiditeopti());
		createDto.setRepiquage(entity.getRepiquage());
		createDto.setMin(entity.getTemperatureMin());
		createDto.setMax(entity.getTemperatureMax());
		createDto.setDesc(entity.getDescription());
		createDto.setToxi(entity.isToxicite());
		createDto.setPicture(entity.getPhoto());
		createDto.setNegative(entity.getAssoNegative());
		createDto.setPositive(entity.getAssoPositive());
		createDto.setMifa(entity.getFamille());
		createDto.setIdentifiant(entity.getId());
		return createDto;
		
	}

	@Override
	public Page<PlanteModel> convertListCreateDtoToEntity(Page<PlanteModelCreateDto> listeCreateDto) {
		
		
		List<PlanteModel> listeRetour = new ArrayList<PlanteModel>();
		for(PlanteModelCreateDto p : listeCreateDto.toList()) {
			
			listeRetour.add(convertCreateDtoToEntity(p));
		}
		
		Page<PlanteModel> pageRetour= new PageImpl<PlanteModel>(listeRetour);
		return pageRetour;
	}

	@Override
	public Page<PlanteModelCreateDto> convertListEntityToCreateDto(Page<PlanteModel> listeEntity) {
		List<PlanteModelCreateDto> listeRetour = new ArrayList<PlanteModelCreateDto>();
		for(PlanteModel p: listeEntity.toList()) {
			listeRetour.add(convertEntityToCreateDto(p));
		}
		
		Page<PlanteModelCreateDto> pageRetour = new PageImpl<PlanteModelCreateDto>(listeRetour);
		return pageRetour;
	}

	@Override
	public Page<PlanteModel> convertListUpdateDtoToEntity(Page<PlanteModelUpdateDto> listeUpdateDto) {
		List<PlanteModel> listeRetour = new ArrayList<PlanteModel>();
		for(PlanteModelUpdateDto p:listeUpdateDto.toList()) {
			listeRetour.add(convertUpdateDtoToEntity(p));
		}
		
		Page<PlanteModel> pageRetour= new PageImpl<PlanteModel>(listeRetour);
		return pageRetour;
	}

	@Override
	public Page<PlanteModelUpdateDto> convertListEntityToUpdateDto(Page<PlanteModel> listeEntity) {
		List<PlanteModelUpdateDto> listeRetour = new ArrayList<PlanteModelUpdateDto>();
		for(PlanteModel p: listeEntity.toList()) {
			listeRetour.add(convertEntityToUpdateDto(p));
		}
		Page<PlanteModelUpdateDto> pageRetour = new PageImpl<PlanteModelUpdateDto>(listeRetour);
		return pageRetour;
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
	
	public Page<PlanteModel> convertListReducedDtoToEntity(Page<PlanteModelReducedDto> listeReducedDto) {
		Page<PlanteModel> listeRetour = new PageImpl<PlanteModel>(null);
		for(PlanteModelReducedDto p:listeReducedDto) {
			listeRetour.and(convertReducedDtoToEntity(p));
		}
		return listeRetour;
	}

	public Page<PlanteModelReducedDto> convertListEntityToReducedDto(Page<PlanteModel> listeEntity) {
		Page<PlanteModelReducedDto> listeRetour = new PageImpl<PlanteModelReducedDto>(null);
		for(PlanteModel p: listeEntity) {
			listeRetour.and(convertEntityToReducedDto(p));
		}
		return listeRetour;
	} 

}
