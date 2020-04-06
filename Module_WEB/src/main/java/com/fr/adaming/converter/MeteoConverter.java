package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.MeteoCreateDto;
import com.fr.adaming.dto.MeteoUpdateDto;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MeteoConverter implements IConverter<MeteoCreateDto, MeteoUpdateDto, Meteo>{

	@Autowired
	private IConverterDepartement<Departement, DepartementDto> convertDep;
	
	@Override
	public Meteo convertCreateDtoToEntity(MeteoCreateDto createDto) {
		if (createDto == null) {
			log.info("Conversion d'un objet createDto null en objet meteo null");
			return null;
		} else {
			log.info("Conversion d'un objet createDto en objet Meteo");
			Meteo meteo = new Meteo();
			meteo.setPluie(createDto.getRain());
			meteo.setTemperature(createDto.getTemp());
			meteo.setRayonnement(createDto.getRadiation());
			meteo.setEvapoTranspirationPotentielle(createDto.getEtp());
			meteo.setEvapoTranspirationReelle(createDto.getEtr());
			meteo.setHumidite(createDto.getHumidity());
			meteo.setDate(createDto.getDateMeteo());
			meteo.setDepartement(convertDep.convertDtoToEntity(createDto.getDepartement()));
			return meteo;
		}
		
	}

	@Override
	public MeteoCreateDto convertEntityToCreateDto(Meteo meteo) {
		if (meteo == null) {
			log.info("Conversion d'un objet meteo null en objet createDto null");
			return null;
		} else {
			log.info("Conversion d'un objet meteo en objet createDto");
			MeteoCreateDto dto = new MeteoCreateDto();
			dto.setRain(meteo.getPluie());
			dto.setTemp(meteo.getTemperature());
			dto.setRadiation(meteo.getRayonnement());
			dto.setEtp(meteo.getEvapoTranspirationPotentielle());
			dto.setEtr(meteo.getEvapoTranspirationReelle());
			dto.setHumidity(meteo.getHumidite());
			dto.setDateMeteo(meteo.getDate());
			dto.setDepartement(convertDep.convertEntityToDto(meteo.getDepartement()));
			return dto;
		}
	}

	@Override
	public Meteo convertUpdateDtoToEntity(MeteoUpdateDto updateDto) {
		if (updateDto == null) {
			log.info("Conversion d'un objet updateDto null en objet meteo null");
			return null;
		} else {
			log.info("Conversion d'un objet updateDto en objet meteo");
			Meteo meteo = new Meteo();
			meteo.setId(updateDto.getIdentifier());
			meteo.setPluie(updateDto.getRain());
			meteo.setTemperature(updateDto.getTemp());
			meteo.setRayonnement(updateDto.getRadiation());
			meteo.setEvapoTranspirationPotentielle(updateDto.getEtp());
			meteo.setEvapoTranspirationReelle(updateDto.getEtr());
			meteo.setHumidite(updateDto.getHumidity());
			meteo.setDate(updateDto.getDateMeteo());
			meteo.setDepartement(convertDep.convertDtoToEntity(updateDto.getDepartement()));
			return meteo;
		}
	}

	@Override
	public MeteoUpdateDto convertEntityToUpdateDto(Meteo meteo) {
		if (meteo == null) {
			log.info("Conversion d'un objet meteo null en objet updateDto null");
			return null;
		} else {
			log.info("Conversion d'un objet meteo en objet updateDto");
			MeteoUpdateDto dto = new MeteoUpdateDto();
			dto.setIdentifier(meteo.getId());
			dto.setRain(meteo.getPluie());
			dto.setTemp(meteo.getTemperature());
			dto.setRadiation(meteo.getRayonnement());
			dto.setEtp(meteo.getEvapoTranspirationPotentielle());
			dto.setEtr(meteo.getEvapoTranspirationReelle());
			dto.setHumidity(meteo.getHumidite());
			dto.setDateMeteo(meteo.getDate());
			dto.setDepartement(convertDep.convertEntityToDto(meteo.getDepartement()));
			return dto;
		}
	}

	@Override
	public List<Meteo> convertListCreateDtoToEntity(List<MeteoCreateDto> listeCreateDto) {
		if (listeCreateDto == null) {
			log.info("Conversion d'une liste CreateDto nulle en liste Meteo vide");
			return Collections.emptyList();
		} else {
			log.info("Conversion d'une liste CreateDto en liste Meteo");
			List<Meteo> listeRetour = new ArrayList<>();
			for (MeteoCreateDto dto : listeCreateDto) {
				listeRetour.add(convertCreateDtoToEntity(dto));
			}
			return listeRetour;
		}
	}

	@Override
	public List<MeteoCreateDto> convertListEntityToCreateDto(List<Meteo> listeEntity) {
		if (listeEntity == null) {
			log.info("Conversion d'une liste Meteo nulle en liste CreateDto vide");
			return Collections.emptyList();
		} else {
			log.info("Conversion d'une liste Meteo en liste CreateDto");
			List<MeteoCreateDto> listeRetour = new ArrayList<>();
			for (Meteo meteo : listeEntity) {
				listeRetour.add(convertEntityToCreateDto(meteo));
			}
			return listeRetour;
		}
	}

	@Override
	public List<Meteo> convertListUpdateDtoToEntity(List<MeteoUpdateDto> listeUpdateDto) {
		if (listeUpdateDto == null) {
			log.info("Conversion d'une liste UpdateDto nulle en liste Meteo vide");
			return Collections.emptyList();
		} else {
			log.info("Conversion d'une liste UpdateDto en liste Meteo");
			List<Meteo> listeRetour = new ArrayList<>();
			for(MeteoUpdateDto dto : listeUpdateDto) {
				listeRetour.add(convertUpdateDtoToEntity(dto));
			}
			return listeRetour;
		}
		
	}

	@Override
	public List<MeteoUpdateDto> convertListEntityToUpdateDto(List<Meteo> listeEntity) {
		if (listeEntity == null) {
			log.info("Conversion d'une liste Meteo nulle en liste UpdateDto vide");
			return Collections.emptyList();
		} else {
			log.info("Conversion d'une liste Meteo en liste UpdateDto");
			List<MeteoUpdateDto> listeRetour = new ArrayList<>();
			for(Meteo meteo : listeEntity) {
				listeRetour.add(convertEntityToUpdateDto(meteo));
			}
			return listeRetour;
		}
	}

	@Override
	public Page<Meteo> convertPageCreateDtoToEntity(Page<MeteoCreateDto> pageCreateDto) {
		if (pageCreateDto == null) {
			log.info("Conversion d'une page CreateDto nulle en page Meteo vide");
			return new PageImpl<Meteo>(new ArrayList<Meteo>());
		} else {
			log.info("Conversion d'une page CreateDto en page Meteo");
			return pageCreateDto.map(this :: convertCreateDtoToEntity);
		}
	}

	@Override
	public Page<MeteoCreateDto> convertPageEntityToCreateDto(Page<Meteo> pageEntity) {
		if (pageEntity == null) {
			log.info("Conversion d'une page Meteo nulle en page CreateDto vide");
			return new PageImpl<MeteoCreateDto>(new ArrayList<MeteoCreateDto>());
		} else {
			log.info("Conversion d'une page Meteo en page CreateDto");
			return pageEntity.map(this :: convertEntityToCreateDto);
		}
		
	}

	@Override
	public Page<Meteo> convertPageUpdateDtoToEntity(Page<MeteoUpdateDto> pageUpdateDto) {
		if (pageUpdateDto == null) {
			log.info("Conversion d'une page UpdateDto nulle en page Meteo vide");
			return new PageImpl<Meteo>(new ArrayList<Meteo>());
		} else {
			log.info("Conversion d'une page UpdateDto en page Meteo");
			return pageUpdateDto.map(this :: convertUpdateDtoToEntity);
		}
	}

	@Override
	public Page<MeteoUpdateDto> convertPageEntityToUpdateDto(Page<Meteo> pageEntity) {
		if (pageEntity == null) {
			log.info("Conversion d'une page Meteo nulle en page UpdateDto vide");
			return new PageImpl<MeteoUpdateDto>(new ArrayList<MeteoUpdateDto>());
		} else {
			log.info("Conversion d'une page Meteo en page UpdateDto");
			return pageEntity.map(this :: convertEntityToUpdateDto);
		}
		
	}

	
	
	
}
