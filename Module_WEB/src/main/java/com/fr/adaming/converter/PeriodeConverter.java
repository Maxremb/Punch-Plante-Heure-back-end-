package com.fr.adaming.converter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.PeriodeCreateDto;
import com.fr.adaming.dto.PeriodeUpdateDto;
import com.fr.adaming.entity.Periode;

@Component
public class PeriodeConverter implements IConverter<PeriodeCreateDto, PeriodeUpdateDto, Periode> {

	@Override
	public Periode convertCreateDtoToEntity(PeriodeCreateDto createDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PeriodeCreateDto convertEntityToCreateDto(Periode entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Periode convertUpdateDtoToEntity(PeriodeUpdateDto updateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PeriodeUpdateDto convertEntityToUpdateDto(Periode entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Periode> convertListCreateDtoToEntity(List<PeriodeCreateDto> listeCreateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PeriodeCreateDto> convertListEntityToCreateDto(List<Periode> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Periode> convertListUpdateDtoToEntity(List<PeriodeUpdateDto> listeUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PeriodeUpdateDto> convertListEntityToUpdateDto(List<Periode> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Periode> convertPageCreateDtoToEntity(Page<PeriodeCreateDto> listeCreateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PeriodeCreateDto> convertPageEntityToCreateDto(Page<Periode> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Periode> convertPageUpdateDtoToEntity(Page<PeriodeUpdateDto> listeUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PeriodeUpdateDto> convertPageEntityToUpdateDto(Page<Periode> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
