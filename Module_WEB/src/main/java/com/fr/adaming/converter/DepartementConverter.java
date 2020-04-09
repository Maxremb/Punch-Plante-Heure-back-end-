package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.entity.Departement;

import lombok.extern.slf4j.Slf4j;

/**
 * Implémentation de IConverterDépartement
 * 
 * @author Isaline MILLET
 * @since 0.0.1
 *
 */
@Component
@Slf4j
public class DepartementConverter implements IConverterDepartement<Departement, DepartementDto> {

	@Override
	public Departement convertDtoToEntity(DepartementDto dto) {
		try {
			if (dto == null) {
				log.info("Tentative de conversion d'un DepartementDto NULL vers entité");
				return null;
			} else {
				Departement entite = new Departement();
				entite.setNumeroDep(dto.getDepNum());
				entite.setNom(dto.getName());
				log.info("Conversion d'un département DTO en département");
				return entite;
			}
		} catch (NullPointerException npe) {
			log.warn("Erreur conversion");
			return null;
		}
	}

	@Override
	public DepartementDto convertEntityToDto(Departement dep) {
		if (dep == null) {
			log.info("Tentative de conversion d'un Departement NULL vers département DTO");
			return null;
		} else {
			DepartementDto dto = new DepartementDto();
			dto.setDepNum(dep.getNumeroDep());
			dto.setName(dep.getNom());

			log.info("Conversion d'un département en département DTO");
			return dto;
		}
	}

	@Override
	public Page<Departement> convertPageDtoToEntity(Page<DepartementDto> pageDto) {
		log.info("Conversion d'une liste départements DTO en liste départements");

		if (pageDto != null) {
			return pageDto.map(this::convertDtoToEntity);
		} else {
			return new PageImpl<Departement>(new ArrayList<Departement>());
		}

	}

	@Override
	public Page<DepartementDto> convertPageEntityToDto(Page<Departement> pageDep) {
		log.info("Conversion d'une liste départements DTO en liste départements");

		if (pageDep != null) {
			return pageDep.map(this::convertEntityToDto);
		} else {
			return new PageImpl<DepartementDto>(new ArrayList<DepartementDto>());
		}
	}

	@Override
	public List<Departement> convertListDtoToEntity(List<DepartementDto> listeDto) {
		
		if (listeDto == null || listeDto.isEmpty()) {
			log.info("Tentative conversion d'une liste département DTO vide ou null en département");
			return new ArrayList<>();
		}
		List<Departement> liste = new ArrayList<>();
		for (DepartementDto dto : listeDto) {
			liste.add(convertDtoToEntity(dto));
		}
		log.info("Conversion d'une liste départements DTO en liste départements");
		return liste;
	}

	@Override
	public List<DepartementDto> convertListEntityToDto(List<Departement> listeDep) {
		if (listeDep == null || listeDep.isEmpty()) {
			log.info("Tentative conversion d'une liste département vide en département DTO");
			return new ArrayList<>();
		}
		List<DepartementDto> liste = new ArrayList<>();
		for (Departement dep : listeDep) {
			liste.add(convertEntityToDto(dep));
		}
		log.info("Conversion d'une liste départements DTO en liste départements");
		return liste;
	}

}
