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

import lombok.extern.slf4j.Slf4j;

/**
 * Couche converter pour les périodes qui implement IConverter
 * 
 * @since 0.0.1-SNAPSHOT
 */
@Component
@Slf4j
public class PeriodeConverter implements IConverter<PeriodeCreateDto, PeriodeUpdateDto, Periode> {

	@Autowired
	private DepartementConverter depConverter;
	@Autowired
	private PlanteModelConverter pMConverter;

	@Override
	public Periode convertCreateDtoToEntity(PeriodeCreateDto createDto) {
		log.info("Converter péridoe : méthode create dto vers période");

		DepartementDto depDto = createDto.getCounty();
		Departement departement = null;
		if (depDto != null) {
			log.info("Département de la période non null");
			departement = depConverter.convertDtoToEntity(depDto);
		}

		PlanteModelUpdateDto pmDto = createDto.getPlantSpecies();
		PlanteModel planteModel = null;
		if (pmDto != null) {
			log.info("Plante modèle du dto non null");
			planteModel = pMConverter.convertUpdateDtoToEntity(pmDto);
		}
		log.info("Conversion OK");
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
		log.info("Converter période : méthode conversion periode vers create dto");
		entity.setId(-1);
		log.info("Conversion OK");
		return (PeriodeCreateDto) convertEntityToUpdateDto(entity);

	}

	@Override
	public Periode convertUpdateDtoToEntity(PeriodeUpdateDto updateDto) {
		log.info("Converter période : méthode conversion update dto vers période");
		Periode entity = convertCreateDtoToEntity(updateDto);
		entity.setId(updateDto.getIdentity());
		log.info("Conversion OK");
		return entity;
	}

	@Override
	public PeriodeUpdateDto convertEntityToUpdateDto(Periode entity) {
		log.info("Converter période : méthode conversion période vers update dto");
		Departement departement = entity.getDepartement();
		DepartementDto depDto = null;
		if (departement != null) {
			log.info("Département de la période non null");
			depDto = depConverter.convertEntityToDto(departement);
		}

		PlanteModel planteModel = entity.getPlanteModel();
		PlanteModelUpdateDto pmDto = null;
		if (planteModel != null) {
			log.info("Plante modèle de la période non null");
			pmDto = pMConverter.convertEntityToUpdateDto(planteModel);
		}

		PeriodeUpdateDto updateDto = new PeriodeUpdateDto();
		if (entity.getId() != -1) {
			log.info("Id péridoe = Id update");
			updateDto.setIdentity(entity.getId());
		}
		updateDto.setCounty(depDto);
		updateDto.setStartDate(entity.getDateDebut());
		updateDto.setEndDate(entity.getDateFin());
		updateDto.setPeriodType(entity.getType());
		updateDto.setPlantSpecies(pmDto);
		log.info("Conversion OK");
		return updateDto;
	}

	@Override
	public List<Periode> convertListCreateDtoToEntity(List<PeriodeCreateDto> listeCreateDto) {
log.info("Converter période : méthode conversion liste create dto vers liste période");
		return listeCreateDto == null ? new ArrayList<Periode>()
				: listeCreateDto.stream().map(this::convertCreateDtoToEntity).collect(Collectors.toList());

	}

	@Override
	public List<PeriodeCreateDto> convertListEntityToCreateDto(List<Periode> listeEntity) {
		log.info("Converter période : méthode conversion liste péridoe vers liste create dto");
		if (listeEntity != null) {
			log.info("Converion OK");
			return listeEntity.stream().map(this::convertEntityToCreateDto).collect(Collectors.toList());
		} else {
			log.info("Conversion non réalisée : liste null");
			return new ArrayList<PeriodeCreateDto>();
		}

	}

	@Override
	public List<Periode> convertListUpdateDtoToEntity(List<PeriodeUpdateDto> listeUpdateDto) {
		log.info("Converter période : méthode conversion liste update dto vers liste péridoe");
		return listeUpdateDto == null ? new ArrayList<Periode>()
				: listeUpdateDto.stream().map(this::convertUpdateDtoToEntity).collect(Collectors.toList());

	}

	@Override
	public List<PeriodeUpdateDto> convertListEntityToUpdateDto(List<Periode> listeEntity) {
		log.info("Converter période : méthode conversion liste période vers liste update dto");
		return listeEntity == null ? new ArrayList<PeriodeUpdateDto>()
				: listeEntity.stream().map(this::convertEntityToUpdateDto).collect(Collectors.toList());

	}

	@Override
	public Page<Periode> convertPageCreateDtoToEntity(Page<PeriodeCreateDto> pageCreateDto) {
		log.info("Converter période : méthode conversion page create dto vers page période");
		return pageCreateDto == null ? new PageImpl<Periode>(new ArrayList<Periode>())
				: pageCreateDto.map(this::convertCreateDtoToEntity);

	}

	@Override
	public Page<PeriodeCreateDto> convertPageEntityToCreateDto(Page<Periode> pageEntity) {
		log.info("Converter période : méthode conversion page période vers page create dto");
		return pageEntity == null ? new PageImpl<PeriodeCreateDto>(new ArrayList<PeriodeCreateDto>())
				: pageEntity.map(this::convertEntityToCreateDto);

	}

	@Override
	public Page<Periode> convertPageUpdateDtoToEntity(Page<PeriodeUpdateDto> pageUpdateDto) {
		log.info("Converter période : méthode conversion page update dto vers page période");
		if (pageUpdateDto != null) {
			log.info("Conversion OK");
			return pageUpdateDto.map(this::convertUpdateDtoToEntity);
		} else {
			log.info("Conversion non réalisée : page null");
			return new PageImpl<Periode>(new ArrayList<Periode>());
		}

	}

	@Override
	public Page<PeriodeUpdateDto> convertPageEntityToUpdateDto(Page<Periode> pageEntity) {
		log.info("Converter période : méthode conversion page péridoe vers page update dto");
		return pageEntity == null ? new PageImpl<PeriodeUpdateDto>(new ArrayList<PeriodeUpdateDto>())
				: pageEntity.map(this::convertEntityToUpdateDto);

	}

}
