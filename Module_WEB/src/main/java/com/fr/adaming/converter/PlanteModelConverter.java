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

/**
 * <p>
 * Converter pour l'entite PlanteModel en dto et inversement <br>
 * converti aussi depuis et vers reducedDto <br>
 * Implements IConverter.
 * </p>
 * 
 * @author Léa Coston
 * @since 0.0.1
 *
 */
@Component
public class PlanteModelConverter implements IConverter<PlanteModelCreateDto, PlanteModelUpdateDto, PlanteModel> {

	@Autowired
	private PeriodeConverter periodeConverter;

	@Override
	public PlanteModel convertCreateDtoToEntity(PlanteModelCreateDto createDto) {

		if (createDto == null) {
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
		entity.setSolOpti(createDto.getSol());
		entity.setFeuillage(createDto.getFeuille());
		entity.setHauteur(createDto.getHeight());
		entity.setVegetation(createDto.getVeget());
		return entity;
	}

	@Override
	public PlanteModelCreateDto convertEntityToCreateDto(PlanteModel entity) {
		if (entity == null) {
			return null;
		}

		PlanteModelCreateDto createDto = new PlanteModelCreateDto();
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
		createDto.setSol(entity.getSolOpti());
		createDto.setFeuille(entity.getFeuillage());
		createDto.setHeight(entity.getHauteur());
		createDto.setVeget(entity.getVegetation());
		return createDto;
	}

	@Override
	public PlanteModel convertUpdateDtoToEntity(PlanteModelUpdateDto updateDto) {
		if (updateDto == null) {
			return null;
		}
		PlanteModel entity = new PlanteModel();
		entity = convertCreateDtoToEntity(updateDto);
		entity.setId(updateDto.getIdentifiant());

		return entity;
	}

	@Override
	public PlanteModelUpdateDto convertEntityToUpdateDto(PlanteModel entity) {
		if (entity == null) {
			return null;
		}
		PlanteModelUpdateDto createDto = new PlanteModelUpdateDto();
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
		createDto.setSol(entity.getSolOpti());
		createDto.setHeight(entity.getHauteur());
		createDto.setVeget(entity.getVegetation());
		createDto.setFeuille(entity.getFeuillage());
		return createDto;

	}

	@Override
	public Page<PlanteModel> convertPageCreateDtoToEntity(Page<PlanteModelCreateDto> pageCreateDto) {
		return pageCreateDto == null ? new PageImpl<PlanteModel>(new ArrayList<PlanteModel>())
				: pageCreateDto.map(this::convertCreateDtoToEntity);
	}

	@Override
	public Page<PlanteModelCreateDto> convertPageEntityToCreateDto(Page<PlanteModel> pageEntity) {
		return pageEntity == null ? new PageImpl<PlanteModelCreateDto>(new ArrayList<PlanteModelCreateDto>())
				: pageEntity.map(this::convertEntityToCreateDto);
	}

	@Override
	public Page<PlanteModel> convertPageUpdateDtoToEntity(Page<PlanteModelUpdateDto> pageUpdateDto) {
		return pageUpdateDto == null ? new PageImpl<PlanteModel>(new ArrayList<PlanteModel>())
				: pageUpdateDto.map(this::convertUpdateDtoToEntity);
	}

	@Override
	public Page<PlanteModelUpdateDto> convertPageEntityToUpdateDto(Page<PlanteModel> listeEntity) {
		return listeEntity == null ? new PageImpl<PlanteModelUpdateDto>(new ArrayList<PlanteModelUpdateDto>())
				: listeEntity.map(this::convertEntityToUpdateDto);
	}

	/**
	 * 
	 * Converter Entity to reducedDto
	 * 
	 * @param entity l'entité a convertir
	 * @return une instance de planteModelReducedDto
	 * @author Léa Coston
	 * @since 0.0.1
	 *
	 */
	public PlanteModelReducedDto convertEntityToReducedDto(PlanteModel entity) {
		if (entity == null) {
			return null;
		}
		PlanteModelReducedDto reducedDto = new PlanteModelReducedDto();
		reducedDto.setIdentifiant(entity.getId());
		reducedDto.setCommun(entity.getNomCommun());
		reducedDto.setScientifique(entity.getNomScientifique());
		reducedDto.setPicture(entity.getPhoto());
		return reducedDto;
	}

	/**
	 * 
	 * Converter reducedDto to entity
	 * 
	 * @param reducedDto la dto a convertir
	 * @return une instance de planteModel
	 * @author Léa Coston
	 * @since 0.0.1
	 *
	 */
	public PlanteModel convertReducedDtoToEntity(PlanteModelReducedDto reducedDto) {
		if (reducedDto == null) {
			return null;
		}
		PlanteModel entity = new PlanteModel();
		entity.setNomCommun(reducedDto.getCommun());
		entity.setNomScientifique(reducedDto.getScientifique());
		entity.setPhoto(reducedDto.getPicture());
		entity.setId(reducedDto.getIdentifiant());

		return entity;
	}

	/**
	 * 
	 * Converter page entity to page reduced dto
	 * 
	 * @param listeReducedDto la page dto a convertir
	 * @return une page de planteModel
	 * @author Léa Coston
	 * @since 0.0.1
	 *
	 */
	public Page<PlanteModel> convertListReducedDtoToEntity(Page<PlanteModelReducedDto> listeReducedDto) {
		Page<PlanteModel> retour = listeReducedDto.map(this::convertReducedDtoToEntity);
		return retour;

	}

	/**
	 * 
	 * Converter page reducedDto to page entity
	 * 
	 * @param listeEntity la page a convertir
	 * @return une page de planteModelReducedDto
	 * @author Léa Coston
	 * @since 0.0.1
	 *
	 */
	public Page<PlanteModelReducedDto> convertListEntityToReducedDto(Page<PlanteModel> listeEntity) {
		Page<PlanteModelReducedDto> retour = listeEntity == null
				? new PageImpl<PlanteModelReducedDto>(new ArrayList<PlanteModelReducedDto>())
				: listeEntity.map(this::convertEntityToReducedDto);
		return retour;
	}

	@Override
	public List<PlanteModel> convertListCreateDtoToEntity(List<PlanteModelCreateDto> listeCreateDto) {
		List<PlanteModel> listeRetour = new ArrayList<PlanteModel>();
		for (PlanteModelCreateDto p : listeCreateDto) {
			listeRetour.add(convertCreateDtoToEntity(p));
		}
		return listeRetour;
	}

	@Override
	public List<PlanteModelCreateDto> convertListEntityToCreateDto(List<PlanteModel> listeEntity) {
		List<PlanteModelCreateDto> listeRetour = new ArrayList<PlanteModelCreateDto>();
		for (PlanteModel p : listeEntity) {
			listeRetour.add(convertEntityToCreateDto(p));
		}
		return listeRetour;
	}

	@Override
	public List<PlanteModel> convertListUpdateDtoToEntity(List<PlanteModelUpdateDto> listeUpdateDto) {
		List<PlanteModel> listeRetour = new ArrayList<PlanteModel>();
		for (PlanteModelUpdateDto p : listeUpdateDto) {
			listeRetour.add(convertUpdateDtoToEntity(p));
		}
		return listeRetour;
	}

	@Override
	public List<PlanteModelUpdateDto> convertListEntityToUpdateDto(List<PlanteModel> listeEntity) {
		List<PlanteModelUpdateDto> listeRetour = new ArrayList<PlanteModelUpdateDto>();
		for (PlanteModel p : listeEntity) {
			listeRetour.add(convertEntityToUpdateDto(p));
		}
		return listeRetour;
	}

}
