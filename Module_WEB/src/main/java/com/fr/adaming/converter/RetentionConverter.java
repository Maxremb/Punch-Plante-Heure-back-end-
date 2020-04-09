package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.RetentionCreateDto;
import com.fr.adaming.dto.RetentionUpdateDto;
import com.fr.adaming.entity.Retention;

@Component
public class RetentionConverter implements IConverter<RetentionCreateDto, RetentionUpdateDto, Retention> {

	@Override
	public Retention convertCreateDtoToEntity(RetentionCreateDto createDto) {
		if (createDto != null) {
			Retention entite = new Retention();
			entite.setCoeffRemplissage(createDto.getCoeffSol());
			entite.setSol(createDto.getGround());
			entite.setId(0);
			return entite;
		}
		return null;
	}

	@Override
	public RetentionCreateDto convertEntityToCreateDto(Retention entity) {
		if (entity != null) {
			RetentionCreateDto createDto = new RetentionCreateDto();
			createDto.setCoeffSol(entity.getCoeffRemplissage());
			createDto.setGround(entity.getSol());
			return createDto;
		}
		return null;
	}

	@Override
	public Retention convertUpdateDtoToEntity(RetentionUpdateDto updateDto) {
		if (updateDto != null) {
			Retention entite = new Retention();
			entite.setCoeffRemplissage(updateDto.getCoeffSol());
			entite.setSol(updateDto.getGround());
			entite.setId(updateDto.getIdentifier());
			return entite;
		}
		return null;
	}

	@Override
	public RetentionUpdateDto convertEntityToUpdateDto(Retention entity) {
		if (entity != null) {
			RetentionUpdateDto updateDto = new RetentionUpdateDto();
			updateDto.setCoeffSol(entity.getCoeffRemplissage());
			updateDto.setGround(updateDto.getGround());
			updateDto.setIdentifier(entity.getId());
			return updateDto;
		}
		return null;
	}

	@Override
	public List<Retention> convertListCreateDtoToEntity(List<RetentionCreateDto> listeCreateDto) {
		List<Retention> listeRetour = new ArrayList<>();
		for (RetentionCreateDto e : listeCreateDto) {
			listeRetour.add(convertCreateDtoToEntity(e));
		}
		return listeRetour;
	}

	@Override
	public List<RetentionCreateDto> convertListEntityToCreateDto(List<Retention> listeEntity) {
		List<RetentionCreateDto> listeRetour = new ArrayList<>();
		for (Retention e : listeEntity) {
			listeRetour.add(convertEntityToCreateDto(e));
		}
		return listeRetour;
	}

	@Override
	public List<Retention> convertListUpdateDtoToEntity(List<RetentionUpdateDto> listeUpdateDto) {
		List<Retention> listeRetour = new ArrayList<>();
		for (RetentionUpdateDto e : listeUpdateDto) {
			listeRetour.add(convertUpdateDtoToEntity(e));
		}
		return listeRetour;
	}

	@Override
	public List<RetentionUpdateDto> convertListEntityToUpdateDto(List<Retention> listeEntity) {
		List<RetentionUpdateDto> listeRetour = new ArrayList<>();
		for (Retention e : listeEntity) {
			listeRetour.add(convertEntityToUpdateDto(e));
		}
		return listeRetour;
	}

	@Override
	public Page<Retention> convertPageCreateDtoToEntity(Page<RetentionCreateDto> pageCreateDto) {
		if (pageCreateDto != null) {
			return pageCreateDto.map(this::convertCreateDtoToEntity);
		} else {
			return new PageImpl<Retention>(new ArrayList<Retention>());
		}
	}

	@Override
	public Page<RetentionCreateDto> convertPageEntityToCreateDto(Page<Retention> pageEntity) {
		if (pageEntity != null) {
			return pageEntity.map(this::convertEntityToCreateDto);
		} else {
			return new PageImpl<RetentionCreateDto>(new ArrayList<RetentionCreateDto>());
		}
	}

	@Override
	public Page<Retention> convertPageUpdateDtoToEntity(Page<RetentionUpdateDto> pageUpdateDto) {
		if (pageUpdateDto != null) {
			return pageUpdateDto.map(this::convertUpdateDtoToEntity);
		} else {
			return new PageImpl<Retention>(new ArrayList<Retention>());
		}
	}

	@Override
	public Page<RetentionUpdateDto> convertPageEntityToUpdateDto(Page<Retention> pageEntity) {
		if (pageEntity != null) {
			return pageEntity.map(this::convertEntityToUpdateDto);
		} else {
			return new PageImpl<RetentionUpdateDto>(new ArrayList<RetentionUpdateDto>());
		}
	}

}
