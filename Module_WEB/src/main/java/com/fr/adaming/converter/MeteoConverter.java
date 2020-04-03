package com.fr.adaming.converter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.MeteoCreateDto;
import com.fr.adaming.dto.MeteoUpdateDto;
import com.fr.adaming.entity.Meteo;

@Component
public class MeteoConverter implements IConverter<MeteoCreateDto, MeteoUpdateDto, Meteo>{

	@Override
	public Meteo convertCreateDtoToEntity(MeteoCreateDto createDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MeteoCreateDto convertEntityToCreateDto(Meteo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meteo convertUpdateDtoToEntity(MeteoUpdateDto updateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MeteoUpdateDto convertEntityToUpdateDto(Meteo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Meteo> convertListCreateDtoToEntity(Page<MeteoCreateDto> listeCreateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MeteoCreateDto> convertListEntityToCreateDto(Page<Meteo> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Meteo> convertListUpdateDtoToEntity(Page<MeteoUpdateDto> listeUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MeteoUpdateDto> convertListEntityToUpdateDto(Page<Meteo> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
