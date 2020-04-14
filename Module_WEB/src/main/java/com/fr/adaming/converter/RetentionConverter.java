package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.RetentionCreateDto;
import com.fr.adaming.dto.RetentionUpdateDto;
import com.fr.adaming.entity.Retention;

import lombok.extern.slf4j.Slf4j;

/**
 * Couche converter pour les rétention qui implement IConverter
 * 
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@Component
@Slf4j
public class RetentionConverter implements IConverter<RetentionCreateDto, RetentionUpdateDto, Retention> {

	@Override
	public Retention convertCreateDtoToEntity(RetentionCreateDto createDto) {
		log.info("Converter retention : méthode conversion create dto vers retention");
		if (createDto != null) {
			log.info("Conversion OK");
			Retention entite = new Retention();
			entite.setCoeffRemplissage(createDto.getCoeffSol());
			entite.setSol(createDto.getGround());
			entite.setId(0);
			return entite;
		}
		log.info("Conversion non réalisée : create dto null");
		return null;
	}

	@Override
	public RetentionCreateDto convertEntityToCreateDto(Retention entity) {
		log.info("Converter retention : méthode conversion retention vers create dto");
		if (entity != null) {
			log.info("Conversion OK");
			RetentionCreateDto createDto = new RetentionCreateDto();
			createDto.setCoeffSol(entity.getCoeffRemplissage());
			createDto.setGround(entity.getSol());
			return createDto;
		}
		log.info("Conversion non réalisée : retention null");
		return null;
	}

	@Override
	public Retention convertUpdateDtoToEntity(RetentionUpdateDto updateDto) {
		log.info("Converter retention : méthode conversion update dto vers retention");
		if (updateDto != null) {
			log.info("Conversion OK");
			Retention entite = new Retention();
			entite.setCoeffRemplissage(updateDto.getCoeffSol());
			entite.setSol(updateDto.getGround());
			entite.setId(updateDto.getIdentifier());
			return entite;
		}
		log.info("Conversion non réalisée : update dto null");
		return null;
	}

	@Override
	public RetentionUpdateDto convertEntityToUpdateDto(Retention entity) {
		log.info("Converter retention : méthode conversion retention vers update dto");
		if (entity != null) {
			log.info("Conversion OK");
			RetentionUpdateDto updateDto = new RetentionUpdateDto();
			updateDto.setCoeffSol(entity.getCoeffRemplissage());
			updateDto.setGround(updateDto.getGround());
			updateDto.setIdentifier(entity.getId());
			return updateDto;
		}
		log.info("Conversion non réalisée : retention null");
		return null;
	}

	@Override
	public List<Retention> convertListCreateDtoToEntity(List<RetentionCreateDto> listeCreateDto) {
		log.info("Converter retention : méthode conversion liste create dto vers liste retention");
		List<Retention> listeRetour = new ArrayList<>();
		for (RetentionCreateDto e : listeCreateDto) {
			listeRetour.add(convertCreateDtoToEntity(e));
		}
		return listeRetour;
	}

	@Override
	public List<RetentionCreateDto> convertListEntityToCreateDto(List<Retention> listeEntity) {
		log.info("Converter retention : méthode conversion liste retention vers liste create dto");
		List<RetentionCreateDto> listeRetour = new ArrayList<>();
		for (Retention e : listeEntity) {
			listeRetour.add(convertEntityToCreateDto(e));
		}
		return listeRetour;
	}

	@Override
	public List<Retention> convertListUpdateDtoToEntity(List<RetentionUpdateDto> listeUpdateDto) {
		log.info("Converter retention : méthode conversion liste update dto vers liste retention");
		List<Retention> listeRetour = new ArrayList<>();
		for (RetentionUpdateDto e : listeUpdateDto) {
			listeRetour.add(convertUpdateDtoToEntity(e));
		}
		return listeRetour;
	}

	@Override
	public List<RetentionUpdateDto> convertListEntityToUpdateDto(List<Retention> listeEntity) {
		log.info("Converter retention : méthode conversion liste retention vers liste update dto");
		List<RetentionUpdateDto> listeRetour = new ArrayList<>();
		for (Retention e : listeEntity) {
			listeRetour.add(convertEntityToUpdateDto(e));
		}
		return listeRetour;
	}

	@Override
	public Page<Retention> convertPageCreateDtoToEntity(Page<RetentionCreateDto> pageCreateDto) {
		log.info("Converter retention : méthode conversion page create dto vers page retention");
		if (pageCreateDto != null) {
			log.info("Conversion OK");
			return pageCreateDto.map(this::convertCreateDtoToEntity);
		} else {
			log.info("Conversion non réalisée : page create dto null");
			return new PageImpl<Retention>(new ArrayList<Retention>());
		}
	}

	@Override
	public Page<RetentionCreateDto> convertPageEntityToCreateDto(Page<Retention> pageEntity) {
		log.info("Converter retention : méthode conversion page retention vers page create dto");
		if (pageEntity != null) {
			log.info("Conversion OK");
			return pageEntity.map(this::convertEntityToCreateDto);
		} else {
			log.info("Conversion non réalisée : page retention null");
			return new PageImpl<RetentionCreateDto>(new ArrayList<RetentionCreateDto>());
		}
	}

	@Override
	public Page<Retention> convertPageUpdateDtoToEntity(Page<RetentionUpdateDto> pageUpdateDto) {
		log.info("Converter retention : méthode conversion page update dto vers page retenton");
		if (pageUpdateDto != null) {
			log.info("Conversion OK");
			return pageUpdateDto.map(this::convertUpdateDtoToEntity);
		} else {
			log.info("Conversion non réalisée : page update dto null");
			return new PageImpl<Retention>(new ArrayList<Retention>());
		}
	}

	@Override
	public Page<RetentionUpdateDto> convertPageEntityToUpdateDto(Page<Retention> pageEntity) {
		log.info("Converter retention : méthode conversion page retention vers page update dto");
		if (pageEntity != null) {
			log.info("Conversion OK");
			return pageEntity.map(this::convertEntityToUpdateDto);
		} else {
			log.info("Conversion non réalisée : page retention null");
			return new PageImpl<RetentionUpdateDto>(new ArrayList<RetentionUpdateDto>());
		}
	}

}
