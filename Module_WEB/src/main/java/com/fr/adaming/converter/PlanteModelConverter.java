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
	public Page<PlanteModel> convertPageCreateDtoToEntity(Page<PlanteModelCreateDto> listeCreateDto) {
		return listeCreateDto.map(this::convertCreateDtoToEntity);
	}

	@Override
	public Page<PlanteModelCreateDto> convertPageEntityToCreateDto(Page<PlanteModel> listeEntity) {
		return listeEntity.map(this::convertEntityToCreateDto);
	}

	@Override
	public Page<PlanteModel> convertPageUpdateDtoToEntity(Page<PlanteModelUpdateDto> listeUpdateDto) {
		return listeUpdateDto.map(this::convertUpdateDtoToEntity);
	}

	@Override
	public Page<PlanteModelUpdateDto> convertPageEntityToUpdateDto(Page<PlanteModel> listeEntity) {
		return listeEntity.map(this::convertEntityToUpdateDto);
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
		Page<PlanteModel> retour = listeReducedDto.map(this::convertReducedDtoToEntity);
		return retour;
		
	}

	public Page<PlanteModelReducedDto> convertListEntityToReducedDto(Page<PlanteModel> listeEntity) {
		Page<PlanteModelReducedDto> retour = listeEntity.map(this::convertEntityToReducedDto);
		return retour;
	}

	@Override
	public List<PlanteModel> convertListCreateDtoToEntity(List<PlanteModelCreateDto> listeCreateDto) {
		List<PlanteModel> listeRetour = new ArrayList<PlanteModel>();
		for(PlanteModelCreateDto p: listeCreateDto) {
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
		for(PlanteModelUpdateDto p: listeUpdateDto) {
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

	

}
