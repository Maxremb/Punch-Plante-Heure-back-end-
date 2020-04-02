package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.JardinCreateDto;
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.UtilisateurCreateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Utilisateur;

@Component
public class JardinConverter implements IConverter<JardinCreateDto, JardinUpdateDto, Jardin> {

	@Autowired
	private IConverterDepartement<Departement, DepartementDto> convertDep;

	@Autowired
	private IConverter<UtilisateurCreateDto, UtilisateurUpdateDto, Utilisateur> convertUtil;

	@Override
	public Jardin convertCreateDtoToEntity(JardinCreateDto createDto) {

		if (createDto != null) {
			Jardin entite = new Jardin();
			entite.setDepartement(convertDep.convertDtoToEntity((createDto.getDept())));
			entite.setLargeur(createDto.getWidth());
			entite.setLongueur(createDto.getLength());
			entite.setNom(createDto.getName());
			entite.setSol(createDto.getGround());
			entite.setUtilisateur(convertUtil.convertUpdateDtoToEntity(createDto.getUser()));
			entite.setId(0);
			return entite;
		}

		return null;
	}

	@Override
	public JardinCreateDto convertEntityToCreateDto(Jardin entity) {
		if (entity != null) {
			JardinCreateDto createDto = new JardinCreateDto();
			createDto.setDept(convertDep.convertEntityToDto((entity.getDepartement())));
			createDto.setGround(entity.getSol());
			createDto.setLength(entity.getLongueur());
			createDto.setName(entity.getNom());
			createDto.setUser(convertUtil.convertEntityToUpdateDto(entity.getUtilisateur()));
			createDto.setWidth(entity.getLargeur());

			return createDto;
		}
		return null;
	}

	@Override
	public Jardin convertUpdateDtoToEntity(JardinUpdateDto updateDto) {
		if (updateDto != null) {
			Jardin entite = new Jardin();
			entite.setDepartement(convertDep.convertDtoToEntity((updateDto.getDept())));
			entite.setLargeur(updateDto.getWidth());
			entite.setLongueur(updateDto.getLength());
			entite.setNom(updateDto.getName());
			entite.setSol(updateDto.getGround());
			entite.setUtilisateur(convertUtil.convertUpdateDtoToEntity(updateDto.getUser()));
			entite.setId(updateDto.getIdentifier());
			return entite;
		}
		return null;
	}

	@Override
	public JardinUpdateDto convertEntityToUpdateDto(Jardin entity) {
		if (entity != null) {
			JardinUpdateDto updateDto = new JardinUpdateDto();
			updateDto.setDept(convertDep.convertEntityToDto((entity.getDepartement())));
			updateDto.setGround(entity.getSol());
			updateDto.setLength(entity.getLongueur());
			updateDto.setName(entity.getNom());
			updateDto.setUser(convertUtil.convertEntityToUpdateDto(entity.getUtilisateur()));
			updateDto.setWidth(entity.getLargeur());

			return updateDto;
		}
		return null;
	}

	@Override
	public List<Jardin> convertListCreateDtoToEntity(List<JardinCreateDto> listeCreateDto) {
		List<Jardin> listEntite = new ArrayList<Jardin>();
		if(listeCreateDto != null) {
			for (JardinCreateDto createDto : listeCreateDto) {
				listEntite.add(convertCreateDtoToEntity(createDto));
			}
		}
		return listEntite;
	}

	@Override
	public List<JardinCreateDto> convertListEntityToCreateDto(List<Jardin> listeEntity) {
		List<JardinCreateDto> listCreateDto = new ArrayList<JardinCreateDto>();
		if(listeEntity != null) {
			for (Jardin entite : listeEntity) {
				listCreateDto.add(convertEntityToCreateDto((entite)));
			}
		}
		return listCreateDto;
	
	}

	@Override
	public List<Jardin> convertListUpdateDtoToEntity(List<JardinUpdateDto> listeUpdateDto) {
		List<Jardin> listEntite = new ArrayList<Jardin>();
		if(listeUpdateDto != null) {
			for (JardinUpdateDto updateDto : listeUpdateDto) {
				listEntite.add(convertUpdateDtoToEntity(updateDto));
			}
		}
		return listEntite;
	
	}

	@Override
	public List<JardinUpdateDto> convertListEntityToUpdateDto(List<Jardin> listeEntity) {
		List<JardinUpdateDto> listUpdateDto = new ArrayList<JardinUpdateDto>();
		if(listeEntity != null) {
			for (Jardin entite : listeEntity) {
				listUpdateDto.add(convertEntityToUpdateDto((entite)));
			}
		}
		return listUpdateDto;
	}

}
