package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.PeriodeCreateDto;
import com.fr.adaming.dto.PeriodeUpdateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Periode;
import com.fr.adaming.entity.PlanteModel;

@Component
public class PeriodeConverter implements IConverter<PeriodeCreateDto, PeriodeUpdateDto, Periode> {

	@Autowired
	private DepartementConverter depConverter;
	@Autowired
	private PlanteModelConverter pMConverter;

	@Override
	public Periode convertCreateDtoToEntity(PeriodeCreateDto createDto) {

		DepartementDto depDto = createDto.getCounty();
		Departement departement = null;
		if (depDto != null) {
			departement = depConverter.convertDtoToEntity(depDto);
		}

		PlanteModelUpdateDto pmDto = createDto.getPlantSpecies();
		PlanteModel planteModel = null;
		if (pmDto != null) {
			planteModel = pMConverter.convertUpdateDtoToEntity(pmDto);
		}

		Periode periode = new Periode();
		periode.setDateDebut(createDto.getStartDate());
		periode.setDateFin(createDto.getEndDate());
		periode.setType(createDto.getPeriodType());
		periode.setDepartement(departement);
		periode.setPlanteModel(planteModel);

		return periode;
	}

	@Override
	public PeriodeCreateDto convertEntityToCreateDto(Periode entity) {

		entity.setId(-1);
		return (PeriodeCreateDto) convertEntityToUpdateDto(entity);

	}

	@Override
	public Periode convertUpdateDtoToEntity(PeriodeUpdateDto updateDto) {

		Periode entity = convertCreateDtoToEntity(updateDto);
		entity.setId(updateDto.getIdentity());

		return entity;
	}

	@Override
	public PeriodeUpdateDto convertEntityToUpdateDto(Periode entity) {

		Departement departement = entity.getDepartement();
		DepartementDto depDto = null;
		if (departement != null) {
			depDto = depConverter.convertEntityToDto(departement);
		}

		PlanteModel planteModel = entity.getPlanteModel();
		PlanteModelUpdateDto pmDto = null;
		if (planteModel != null) {
			pmDto = pMConverter.convertEntityToUpdateDto(planteModel);
		}

		PeriodeUpdateDto updateDto = new PeriodeUpdateDto();
		if (entity.getId() != -1) {
			updateDto.setIdentity(entity.getId());
		}
		updateDto.setCounty(depDto);
		updateDto.setStartDate(entity.getDateDebut());
		updateDto.setEndDate(entity.getDateFin());
		updateDto.setPeriodType(entity.getType());
		updateDto.setPlantSpecies(pmDto);

		return updateDto;
	}

	@Override
	public List<Periode> convertListCreateDtoToEntity(List<PeriodeCreateDto> listeCreateDto) {

		return listeCreateDto == null ? new ArrayList<Periode>()
				: listeCreateDto.stream().map(this::convertCreateDtoToEntity).collect(Collectors.toList());

	}

	@Override
	public List<PeriodeCreateDto> convertListEntityToCreateDto(List<Periode> listeEntity) {

		if (listeEntity != null) {
			return listeEntity.stream().map(this::convertEntityToCreateDto).collect(Collectors.toList());
		} else {
			return new ArrayList<PeriodeCreateDto>();
		}

	}

	@Override
	public List<Periode> convertListUpdateDtoToEntity(List<PeriodeUpdateDto> listeUpdateDto) {

		return listeUpdateDto == null ? new ArrayList<Periode>()
				: listeUpdateDto.stream().map(this::convertUpdateDtoToEntity).collect(Collectors.toList());

	}

	@Override
	public List<PeriodeUpdateDto> convertListEntityToUpdateDto(List<Periode> listeEntity) {

		return listeEntity == null ? new ArrayList<PeriodeUpdateDto>() : listeEntity.stream().map(this::convertEntityToUpdateDto).collect(Collectors.toList());

	}

	@Override
	public Page<Periode> convertPageCreateDtoToEntity(Page<PeriodeCreateDto> pageCreateDto) {

		return pageCreateDto.map(this::convertCreateDtoToEntity);

	}

	@Override
	public Page<PeriodeCreateDto> convertPageEntityToCreateDto(Page<Periode> pageEntity) {

		return pageEntity.map(this::convertEntityToCreateDto);

	}

	@Override
	public Page<Periode> convertPageUpdateDtoToEntity(Page<PeriodeUpdateDto> pageUpdateDto) {

		if (pageUpdateDto != null) {
			return pageUpdateDto.map(this::convertUpdateDtoToEntity);
		} else {
			return new PageImpl<Periode>(new ArrayList<Periode>());
		}

	}

	@Override
	public Page<PeriodeUpdateDto> convertPageEntityToUpdateDto(Page<Periode> pageEntity) {

		return pageEntity.map(this::convertEntityToUpdateDto);

	}

}
