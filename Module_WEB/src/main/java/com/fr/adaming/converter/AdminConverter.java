package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.AdminCreateDto;
import com.fr.adaming.dto.AdminUpdateDto;
import com.fr.adaming.entity.Admin;

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
public class AdminConverter implements IConverter<AdminCreateDto, AdminUpdateDto, Admin>{

	@Override
	public Admin convertCreateDtoToEntity(AdminCreateDto createDto) {
		if (createDto == null) {
			return null;
		}
		Admin entity = new Admin();
		entity.setEmail(createDto.getMail());
		entity.setMdp(createDto.getPwd());
		entity.setPseudonyme(createDto.getPseudo());
		return entity;
	}

	@Override
	public AdminCreateDto convertEntityToCreateDto(Admin entity) {
		if (entity == null) {
			return null;
		}
		AdminCreateDto createDto = new AdminCreateDto();
		createDto.setMail(entity.getEmail());
		createDto.setPwd(entity.getMdp());
		createDto.setPseudo(entity.getPseudonyme());
		return createDto;
	}

	@Override
	public Admin convertUpdateDtoToEntity(AdminUpdateDto updateDto) {
		if (updateDto == null) {
			return null;
		}
		Admin entity = new Admin();
		entity = convertCreateDtoToEntity(updateDto);
		entity.setId(updateDto.getIdentifier());
		return entity;
	}

	@Override
	public AdminUpdateDto convertEntityToUpdateDto(Admin entity) {
		if (entity == null) {
			return null;
		}
		AdminUpdateDto updateDto = new AdminUpdateDto();
		updateDto.setMail(entity.getEmail());
		updateDto.setPseudo(entity.getPseudonyme());
		updateDto.setPwd(entity.getMdp());
		updateDto.setIdentifier(entity.getId());;
		return updateDto;
	}

	@Override
	public List<Admin> convertListCreateDtoToEntity(List<AdminCreateDto> listeCreateDto) {
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
		return pageCreateDto == null ? new PageImpl<Admin>(new ArrayList<Admin>())
				: pageCreateDto.map(this::convertCreateDtoToEntity);
	}

	@Override
	public Page<AdminCreateDto> convertPageEntityToCreateDto(Page<Admin> pageEntity) {
		return pageEntity == null ? new PageImpl<AdminCreateDto>(new ArrayList<AdminCreateDto>())
				: pageEntity.map(this::convertEntityToCreateDto);
	}

	@Override
	public Page<Admin> convertPageUpdateDtoToEntity(Page<AdminUpdateDto> pageUpdateDto) {
		return pageUpdateDto == null ? new PageImpl<Admin>(new ArrayList<Admin>())
				: pageUpdateDto.map(this::convertUpdateDtoToEntity);
	}

	@Override
	public Page<AdminUpdateDto> convertPageEntityToUpdateDto(Page<Admin> pageEntity) {
		return pageEntity == null ? new PageImpl<AdminUpdateDto>(new ArrayList<AdminUpdateDto>())
				: pageEntity.map(this::convertEntityToUpdateDto);
	}
	
	

}
