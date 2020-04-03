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
	public Page<Periode> convertListCreateDtoToEntity(Page<PeriodeCreateDto> listeCreateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PeriodeCreateDto> convertListEntityToCreateDto(Page<Periode> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Periode> convertListUpdateDtoToEntity(Page<PeriodeUpdateDto> listeUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PeriodeUpdateDto> convertListEntityToUpdateDto(Page<Periode> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
