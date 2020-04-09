package com.fr.adaming.converter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.UtilisateurCreateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.entity.Utilisateur;

@Component
public class UtilisateurConverter implements IConverter<UtilisateurCreateDto, UtilisateurUpdateDto, Utilisateur>{

	@Override
	public Utilisateur convertCreateDtoToEntity(UtilisateurCreateDto createDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UtilisateurCreateDto convertEntityToCreateDto(Utilisateur entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur convertUpdateDtoToEntity(UtilisateurUpdateDto updateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UtilisateurUpdateDto convertEntityToUpdateDto(Utilisateur entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> convertListCreateDtoToEntity(List<UtilisateurCreateDto> listeCreateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UtilisateurCreateDto> convertListEntityToCreateDto(List<Utilisateur> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> convertListUpdateDtoToEntity(List<UtilisateurUpdateDto> listeUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UtilisateurUpdateDto> convertListEntityToUpdateDto(List<Utilisateur> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Utilisateur> convertPageCreateDtoToEntity(Page<UtilisateurCreateDto> listeCreateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UtilisateurCreateDto> convertPageEntityToCreateDto(Page<Utilisateur> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Utilisateur> convertPageUpdateDtoToEntity(Page<UtilisateurUpdateDto> listeUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UtilisateurUpdateDto> convertPageEntityToUpdateDto(Page<Utilisateur> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
