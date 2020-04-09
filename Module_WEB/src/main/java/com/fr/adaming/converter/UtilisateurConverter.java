package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.UtilisateurCreateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.dto.UtilisateurCreateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.entity.Utilisateur;

/**
 * <p>
 * Converter pour l'entite Utilisateur en dto et inversement <br>
 * Implements IConverter.
 * </p>
 * 
 * @author Isaline MILLET
 * @since 0.0.1
 *
 */
@Component
public class UtilisateurConverter implements IConverter<UtilisateurCreateDto, UtilisateurUpdateDto, Utilisateur>{

	@Override
	public Utilisateur convertCreateDtoToEntity(UtilisateurCreateDto createDto) {
		if (createDto == null) {
			return null;
		}
		Utilisateur entity = new Utilisateur();
		entity.setMdp(createDto.getPwd());
		entity.setPseudonyme(createDto.getPseudo());
		entity.setEmail(createDto.getMail());
		entity.setNom(createDto.getLastName());
		entity.setPrenom(createDto.getFirstName());
		entity.setDescription(createDto.getDesc());
		entity.setTelephone(createDto.getPhone());
		entity.setReputation(createDto.getReput());
		entity.setNewsletter(createDto.getNews());
		entity.setActif(createDto.getActive());
		entity.setPhoto(createDto.getPicture());
		return entity;
	}

	@Override
	public UtilisateurCreateDto convertEntityToCreateDto(Utilisateur entity) {
		if (entity == null) {
			return null;
		}
		UtilisateurCreateDto createDto = new UtilisateurCreateDto();
		createDto.setPwd(entity.getMdp());
		createDto.setPseudo(entity.getPseudonyme());
		createDto.setMail(entity.getEmail());
		createDto.setLastName(entity.getNom());
		createDto.setFirstName(entity.getPrenom());
		createDto.setDesc(entity.getDescription());
		createDto.setPhone(entity.getTelephone());
		createDto.setReput(entity.getReputation());
		createDto.setNews(entity.getNewsletter());
		createDto.setActive(entity.getActif());
		createDto.setPicture(entity.getPhoto());
		return createDto;
	}

	@Override
	public Utilisateur convertUpdateDtoToEntity(UtilisateurUpdateDto updateDto) {
		if (updateDto == null) {
			return null;
		}
		Utilisateur entity = new Utilisateur();
		entity = convertCreateDtoToEntity(updateDto);
		entity.setId(updateDto.getIdentifier());
		return entity;
	}

	@Override
	public UtilisateurUpdateDto convertEntityToUpdateDto(Utilisateur entity) {
		if (entity == null) {
			return null;
		}
		UtilisateurUpdateDto updateDto = new UtilisateurUpdateDto();
		updateDto.setIdentifier(entity.getId());
		updateDto.setPwd(entity.getMdp());
		updateDto.setPseudo(entity.getPseudonyme());
		updateDto.setMail(entity.getEmail());
		updateDto.setLastName(entity.getNom());
		updateDto.setFirstName(entity.getPrenom());
		updateDto.setDesc(entity.getDescription());
		updateDto.setPhone(entity.getTelephone());
		updateDto.setReput(entity.getReputation());
		updateDto.setNews(entity.getNewsletter());
		updateDto.setActive(entity.getActif());
		updateDto.setPicture(entity.getPhoto());
		return updateDto;
	}

	@Override
	public List<Utilisateur> convertListCreateDtoToEntity(List<UtilisateurCreateDto> listeCreateDto) {
		List<Utilisateur> listeRetour = new ArrayList<>();
		if (listeCreateDto != null) {
			for (UtilisateurCreateDto a : listeCreateDto) {
				listeRetour.add(convertCreateDtoToEntity(a));
			}
		}
		return listeRetour;
	}

	@Override
	public List<UtilisateurCreateDto> convertListEntityToCreateDto(List<Utilisateur> listeEntity) {
		List<UtilisateurCreateDto> listeRetour = new ArrayList<>();
		if (listeEntity != null) {
			for (Utilisateur a : listeEntity) {
				listeRetour.add(convertEntityToCreateDto(a));
			}
		}
		return listeRetour;
	}

	@Override
	public List<Utilisateur> convertListUpdateDtoToEntity(List<UtilisateurUpdateDto> listeUpdateDto) {
		List<Utilisateur> listeRetour = new ArrayList<>();
		if (listeUpdateDto != null) {
			for (UtilisateurUpdateDto a : listeUpdateDto) {
				listeRetour.add(convertUpdateDtoToEntity(a));
			}
		}
		return listeRetour;
	}

	@Override
	public List<UtilisateurUpdateDto> convertListEntityToUpdateDto(List<Utilisateur> listeEntity) {
		List<UtilisateurUpdateDto> listeRetour = new ArrayList<>();
		if (listeEntity != null) {
			for (Utilisateur a : listeEntity) {
				listeRetour.add(convertEntityToUpdateDto(a));
			}
		}
		return listeRetour;
	}

	@Override
	public Page<Utilisateur> convertPageCreateDtoToEntity(Page<UtilisateurCreateDto> pageCreateDto) {
		return pageCreateDto == null ? new PageImpl<Utilisateur>(new ArrayList<Utilisateur>())
				: pageCreateDto.map(this::convertCreateDtoToEntity);
	}

	@Override
	public Page<UtilisateurCreateDto> convertPageEntityToCreateDto(Page<Utilisateur> pageEntity) {
		return pageEntity == null ? new PageImpl<UtilisateurCreateDto>(new ArrayList<UtilisateurCreateDto>())
				: pageEntity.map(this::convertEntityToCreateDto);
	}

	@Override
	public Page<Utilisateur> convertPageUpdateDtoToEntity(Page<UtilisateurUpdateDto> pageUpdateDto) {
		return pageUpdateDto == null ? new PageImpl<Utilisateur>(new ArrayList<Utilisateur>())
				: pageUpdateDto.map(this::convertUpdateDtoToEntity);
	}

	@Override
	public Page<UtilisateurUpdateDto> convertPageEntityToUpdateDto(Page<Utilisateur> pageEntity) {
		return pageEntity == null ? new PageImpl<UtilisateurUpdateDto>(new ArrayList<UtilisateurUpdateDto>())
				: pageEntity.map(this::convertEntityToUpdateDto);
	}

	
}
