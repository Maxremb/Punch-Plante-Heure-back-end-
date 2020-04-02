package com.fr.adaming.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.JardinCreateDto;
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.UtilisateurCreateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Utilisateur;

public class JardinConverter implements IConverter<JardinCreateDto, JardinUpdateDto, Jardin>{
	
	@Autowired
	private IConverterDepartement<Departement, DepartementDto> convertDep;
	
	@Autowired
	private IConverter<UtilisateurCreateDto, UtilisateurUpdateDto, Utilisateur> convertUtil;

	@Override
	public Jardin convertCreateDtoToEntity(JardinCreateDto createDto) {
		Jardin entite = new Jardin();
		entite.setDepartement(convertDep.convertDtoToEntity((createDto.getDept())));
		entite.setLargeur(createDto.getWidth());
		entite.setLongueur(createDto.getLength());
		entite.setNom(createDto.getName());
		entite.setSol(createDto.getGround());
		entite.setUtilisateur(convertUtil.convertCreateDtoToEntity(createDto.getUser()));
		entite.setId(0);
		return entite;
	}

	@Override
	public JardinCreateDto convertEntityToCreateDto(Jardin entity) {
		
		
		return null;
	}

	@Override
	public Jardin convertUpdateDtoToEntity(JardinUpdateDto updateDto) {
		Jardin entite = new Jardin();
		entite.setDepartement(convertDep.convertDtoToEntity((updateDto.getDept())));
		entite.setLargeur(updateDto.getWidth());
		entite.setLongueur(updateDto.getLength());
		entite.setNom(updateDto.getName());
		entite.setSol(updateDto.getGround());
		entite.setUtilisateur(convertUtil.convertCreateDtoToEntity(updateDto.getUser()));
		entite.setId(updateDto.getIdentifier());
		return entite;
	}

	@Override
	public JardinUpdateDto convertEntityToUpdateDto(Jardin entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jardin> convertListCreateDtoToEntity(List<JardinCreateDto> listeCreateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JardinCreateDto> convertListEntityToCreateDto(List<Jardin> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jardin> convertListUpdateDtoToEntity(List<JardinUpdateDto> listeUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JardinUpdateDto> convertListEntityToUpdateDto(List<Jardin> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
