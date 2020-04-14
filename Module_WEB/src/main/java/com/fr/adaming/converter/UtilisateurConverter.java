package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.UtilisateurCreateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.entity.Utilisateur;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class UtilisateurConverter implements IConverter<UtilisateurCreateDto, UtilisateurUpdateDto, Utilisateur>{

	@Override
	public Utilisateur convertCreateDtoToEntity(UtilisateurCreateDto createDto) {
		log.info("Converter utilisateur : méthode conversion create dto vers utilisateur");
		if (createDto == null) {
			log.info("Conversion non réalisée : create dto null");
			return null;
		}
		log.info("Conversion OK");
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
		log.info("Converter utilisateur : méthode conversion utilisateur vers create dto");
		if (entity == null) {
			log.info("Conversion non réalisée : utilisateur null");
			return null;
		}
		log.info("Conversion OK");
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
		log.info("Converter utilisateur : méthode conversion update dto vers utilisateur");
		if (updateDto == null) {
			log.info("Conversion non réalisée : update dto null");
			return null;
		}
		log.info("Conversion OK");
		Utilisateur entity = new Utilisateur();
		entity = convertCreateDtoToEntity(updateDto);
		entity.setId(updateDto.getIdentifier());
		return entity;
	}

	@Override
	public UtilisateurUpdateDto convertEntityToUpdateDto(Utilisateur entity) {
		log.info("Converter utilisateur : méthode conversion utilisateur vers update dto");
		if (entity == null) {
			log.info("Conversion non réalisée : utilisateur null");
			return null;
		}
		log.info("Conversion OK");
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
		log.info("Converter utilisateur : méthode conversion liste create dto vers liste utilisaeur");
		List<Utilisateur> listeRetour = new ArrayList<>();
		if (listeCreateDto != null) {
			log.info("Conversion OK");
			for (UtilisateurCreateDto a : listeCreateDto) {
				listeRetour.add(convertCreateDtoToEntity(a));
			}
		}
		return listeRetour;
	}

	@Override
	public List<UtilisateurCreateDto> convertListEntityToCreateDto(List<Utilisateur> listeEntity) {
		log.info("Converter utilisateur : méthode conversion liste utilisateur vers create dto");
		List<UtilisateurCreateDto> listeRetour = new ArrayList<>();
		if (listeEntity != null) {
			log.info("Conversion OK");
			for (Utilisateur a : listeEntity) {
				listeRetour.add(convertEntityToCreateDto(a));
			}
		}
		return listeRetour;
	}

	@Override
	public List<Utilisateur> convertListUpdateDtoToEntity(List<UtilisateurUpdateDto> listeUpdateDto) {
		log.info("Converter utilisateur : méthode conversion liste update dto vers liste utilisateur");
		List<Utilisateur> listeRetour = new ArrayList<>();
		if (listeUpdateDto != null) {
			log.info("Conversion OK");
			for (UtilisateurUpdateDto a : listeUpdateDto) {
				listeRetour.add(convertUpdateDtoToEntity(a));
			}
		}
		return listeRetour;
	}

	@Override
	public List<UtilisateurUpdateDto> convertListEntityToUpdateDto(List<Utilisateur> listeEntity) {
		log.info("Converter utilisateur : méthode conversion liste utilisateur vers liste update dto");
		List<UtilisateurUpdateDto> listeRetour = new ArrayList<>();
		if (listeEntity != null) {
			log.info("Conversion OK");
			for (Utilisateur a : listeEntity) {
				listeRetour.add(convertEntityToUpdateDto(a));
			}
		}
		return listeRetour;
	}

	@Override
	public Page<Utilisateur> convertPageCreateDtoToEntity(Page<UtilisateurCreateDto> pageCreateDto) {
		log.info("Converter utilisateur : méthode conversion page create dto vers page utilisateur");
		return pageCreateDto == null ? new PageImpl<Utilisateur>(new ArrayList<Utilisateur>())
				: pageCreateDto.map(this::convertCreateDtoToEntity);
	}

	@Override
	public Page<UtilisateurCreateDto> convertPageEntityToCreateDto(Page<Utilisateur> pageEntity) {
		log.info("Converter utilisateur : méthode conversion page utilisateur vers page create dto");
		return pageEntity == null ? new PageImpl<UtilisateurCreateDto>(new ArrayList<UtilisateurCreateDto>())
				: pageEntity.map(this::convertEntityToCreateDto);
	}

	@Override
	public Page<Utilisateur> convertPageUpdateDtoToEntity(Page<UtilisateurUpdateDto> pageUpdateDto) {
		log.info("Converter utilisateur : méthode conversion page update dto vers page utilisateur");
		return pageUpdateDto == null ? new PageImpl<Utilisateur>(new ArrayList<Utilisateur>())
				: pageUpdateDto.map(this::convertUpdateDtoToEntity);
	}

	@Override
	public Page<UtilisateurUpdateDto> convertPageEntityToUpdateDto(Page<Utilisateur> pageEntity) {
		log.info("Converter utilisateur : méthode conversion page utilisateur vers page update dto");
		return pageEntity == null ? new PageImpl<UtilisateurUpdateDto>(new ArrayList<UtilisateurUpdateDto>())
				: pageEntity.map(this::convertEntityToUpdateDto);
	}

	
}
