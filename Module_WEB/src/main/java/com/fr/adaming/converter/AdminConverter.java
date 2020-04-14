package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.AdminCreateDto;
import com.fr.adaming.dto.AdminUpdateDto;
import com.fr.adaming.entity.Admin;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Converter pour l'entite Admin en dto et inversement <br>
 * Implements IConverter.
 * </p>
 * 
 * @author Isaline MILLET
 * @since 0.0.1
 *
 */
@Component
@Slf4j
public class AdminConverter implements IConverter<AdminCreateDto, AdminUpdateDto, Admin>{

	@Override
	public Admin convertCreateDtoToEntity(AdminCreateDto createDto) {
		log.info("Converter admin : méthode create dto vers admin appelée");
		if (createDto == null) {
			log.info("Conversion vers admin non réalisée : create dto null");
			return null;
		}
		log.info("Conversion vers admin OK");
		Admin entity = new Admin();
		entity.setEmail(createDto.getMail());
		entity.setMdp(createDto.getPwd());
		entity.setPseudonyme(createDto.getPseudo());
		return entity;
	}

	@Override
	public AdminCreateDto convertEntityToCreateDto(Admin entity) {
		log.info("Converter admin : méthode admin vers create dto appelée");
		if (entity == null) {
			log.info("Conversion vers create dto non réalisée : admin null");
			return null;
		}
		log.info("Conversion vers create dto OK");
		AdminCreateDto createDto = new AdminCreateDto();
		createDto.setMail(entity.getEmail());
		createDto.setPwd(entity.getMdp());
		createDto.setPseudo(entity.getPseudonyme());
		return createDto;
	}

	@Override
	public Admin convertUpdateDtoToEntity(AdminUpdateDto updateDto) {
		log.info("Converter admin : méthode update dto vers admin appelée");
		if (updateDto == null) {
			log.info("Conversion vers admin non réalisée : update dto null ");
			return null;
		}
		log.info("Conversion vers entite OK");
		Admin entity = new Admin();
		entity = convertCreateDtoToEntity(updateDto);
		entity.setId(updateDto.getIdentifier());
		return entity;
	}

	@Override
	public AdminUpdateDto convertEntityToUpdateDto(Admin entity) {
		log.info("Converter admin : méthode admin vers update dto appelée");
		if (entity == null) {
			log.info("Conversion vers update dto non réalisée : admin null");
			return null;
		}
		log.info("Conversion vers update dto OK");
		AdminUpdateDto updateDto = new AdminUpdateDto();
		updateDto.setMail(entity.getEmail());
		updateDto.setPseudo(entity.getPseudonyme());
		updateDto.setPwd(entity.getMdp());
		updateDto.setIdentifier(entity.getId());;
		return updateDto;
	}

	@Override
	public List<Admin> convertListCreateDtoToEntity(List<AdminCreateDto> listeCreateDto) {
		log.info("Converter admin : méthode liste create dto vers liste admin appelée");
		List<Admin> listeRetour = new ArrayList<>();
		if (listeCreateDto != null) {
			for (AdminCreateDto a : listeCreateDto) {
				listeRetour.add(convertCreateDtoToEntity(a));
			}
		}
		return listeRetour;
	}

	@Override
	public List<AdminCreateDto> convertListEntityToCreateDto(List<Admin> listeEntity) {
		log.info("Converter admin : méthode liste admin vers liste create dto appelée");
		List<AdminCreateDto> listeRetour = new ArrayList<>();
		if (listeEntity != null) {
			for (Admin a : listeEntity) {
				listeRetour.add(convertEntityToCreateDto(a));
			}
		}
		return listeRetour;
	}

	@Override
	public List<Admin> convertListUpdateDtoToEntity(List<AdminUpdateDto> listeUpdateDto) {
		log.info("Converter admin : méthode liste update dto vers liste admin appelée");
		List<Admin> listeRetour = new ArrayList<>();
		if (listeUpdateDto != null) {
			for (AdminUpdateDto a : listeUpdateDto) {
				listeRetour.add(convertUpdateDtoToEntity(a));
			}
		}
		return listeRetour;
	}

	@Override
	public List<AdminUpdateDto> convertListEntityToUpdateDto(List<Admin> listeEntity) {
		log.info("Converter admin : méthode liste admin vers liste update dto appelée");
		List<AdminUpdateDto> listeRetour = new ArrayList<>();
		if (listeEntity != null) {
			for (Admin a : listeEntity) {
				listeRetour.add(convertEntityToUpdateDto(a));
			}
		}
		return listeRetour;
	}

	@Override
	public Page<Admin> convertPageCreateDtoToEntity(Page<AdminCreateDto> pageCreateDto) {
		log.info("Converter admin : méthode page create dto vers page admin appelée");
		return pageCreateDto == null ? new PageImpl<Admin>(new ArrayList<Admin>())
				: pageCreateDto.map(this::convertCreateDtoToEntity);
	}

	@Override
	public Page<AdminCreateDto> convertPageEntityToCreateDto(Page<Admin> pageEntity) {
		log.info("Converter admin : méthode page admin vers page create dto appelée");
		return pageEntity == null ? new PageImpl<AdminCreateDto>(new ArrayList<AdminCreateDto>())
				: pageEntity.map(this::convertEntityToCreateDto);
	}

	@Override
	public Page<Admin> convertPageUpdateDtoToEntity(Page<AdminUpdateDto> pageUpdateDto) {
		log.info("Converter admin : méthode page update dto vers page admin appelée");
		return pageUpdateDto == null ? new PageImpl<Admin>(new ArrayList<Admin>())
				: pageUpdateDto.map(this::convertUpdateDtoToEntity);
	}

	@Override
	public Page<AdminUpdateDto> convertPageEntityToUpdateDto(Page<Admin> pageEntity) {
		log.info("Converter admin : méthode page admin vers page update dto appelée");
		return pageEntity == null ? new PageImpl<AdminUpdateDto>(new ArrayList<AdminUpdateDto>())
				: pageEntity.map(this::convertEntityToUpdateDto);
	}
	
	

}
