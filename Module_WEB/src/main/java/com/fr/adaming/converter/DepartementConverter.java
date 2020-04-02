package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;

import lombok.extern.slf4j.Slf4j;

/**
 * Implémentation de IConverterDépartement
 * @author Isaline MILLET
 * @since 0.0.1
 *
 */
@Component
@Slf4j
public class DepartementConverter implements IConverterDepartement<Departement, DepartementDto> {

	@Override
	public Departement convertDtoToEntity(DepartementDto dto) {
		if (dto == null) {
			log.info("Tentative de conversion d'un DepartementDto NULL vers entité");
			return null;
		} else {
			Departement entite = new Departement();
			entite.setNumeroDep(dto.getDepNum());
			entite.setNom(dto.getName());
			if (dto.getWeatherDep().isEmpty()) {
				entite.setMeteoDep(new ArrayList<Meteo>());
			} else {
				entite.setMeteoDep(dto.getWeatherDep());
			}
			log.info("Conversion d'un département DTO en département");
			return entite;
		}
	}

	@Override
	public DepartementDto convertEntityToDto(Departement dep) {
		if (dep == null) {
			return null;
		} else {
			DepartementDto dto = new DepartementDto();
			dto.setDepNum(dep.getNumeroDep());
			dto.setName(dep.getNom());
			if (dep.getMeteoDep().isEmpty()) {
				dto.setWeatherDep(new ArrayList<Meteo>());
			} else {
				dto.setWeatherDep(dep.getMeteoDep());
			}
			return dto;
		}
	}

	@Override
	public List<Departement> convertListDtoToEntity(List<DepartementDto> listeDto) {
		if (listeDto.isEmpty()) {
			return new ArrayList<>();
		}
		List<Departement> liste = new ArrayList<>();
		for (DepartementDto dto : listeDto) {
			Departement dep = new Departement();
			dep.setNom(dto.getName());
			dep.setNumeroDep(dto.getDepNum());
			if (dto.getWeatherDep().isEmpty()) {
				dep.setMeteoDep(new ArrayList<Meteo>());
			} else {
				dep.setMeteoDep(dto.getWeatherDep());
			}
			liste.add(dep);
		}
		return liste;
	}

	@Override
	public List<DepartementDto> convertListEntityToDto(List<Departement> listeDep) {
		if (listeDep.isEmpty()) {
			return new ArrayList<>();
		}
		List<DepartementDto> liste = new ArrayList<>();
		for (Departement dep : listeDep) {
			DepartementDto dto = new DepartementDto();
			dto.setName(dep.getNom());
			dto.setDepNum(dep.getNumeroDep());
			if (dep.getMeteoDep().isEmpty()) {
				dto.setWeatherDep(new ArrayList<Meteo>());
			} else {
				dto.setWeatherDep(dep.getMeteoDep());
			}
			liste.add(dto);
		}
		return liste;
	}

}
