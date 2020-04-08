package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
			entite.setRESERVE_MAX_EAU(createDto.getMaxReserve());
			entite.setReserveUtile(createDto.getUsefullReserve());
			entite.setEstArroser(createDto.isArrosed());
			entite.setProfSol(createDto.getDepthGround());
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
			createDto.setMaxReserve(entity.getRESERVE_MAX_EAU());
			createDto.setUsefullReserve(entity.getReserveUtile());
			createDto.setArrosed(entity.isEstArroser());
			createDto.setDepthGround(entity.getProfSol());
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
			entite.setRESERVE_MAX_EAU(updateDto.getMaxReserve());
			entite.setReserveUtile(updateDto.getUsefullReserve());
			entite.setEstArroser(updateDto.isArrosed());
			entite.setProfSol(updateDto.getDepthGround());
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
			updateDto.setMaxReserve(entity.getRESERVE_MAX_EAU());
			updateDto.setUsefullReserve(entity.getReserveUtile());
			updateDto.setArrosed(entity.isEstArroser());
			updateDto.setDepthGround(entity.getProfSol());
			updateDto.setDept(convertDep.convertEntityToDto((entity.getDepartement())));
			updateDto.setGround(entity.getSol());
			updateDto.setLength(entity.getLongueur());
			updateDto.setName(entity.getNom());
			updateDto.setUser(convertUtil.convertEntityToUpdateDto(entity.getUtilisateur()));
			updateDto.setWidth(entity.getLargeur());
			updateDto.setIdentifier(entity.getId());
			;

			return updateDto;
		}
		return null;
	}

	@Override
	public Page<Jardin> convertPageCreateDtoToEntity(Page<JardinCreateDto> pageCreateDto) {
		if (pageCreateDto != null) {
			return pageCreateDto.map(this::convertCreateDtoToEntity);
		} else {
			return new PageImpl<Jardin>(new ArrayList<Jardin>());
		}
	}

	@Override
	public Page<JardinCreateDto> convertPageEntityToCreateDto(Page<Jardin> pageEntity) {
		if (pageEntity != null) {
			return pageEntity.map(this::convertEntityToCreateDto);
		} else {
			return new PageImpl<JardinCreateDto>(new ArrayList<JardinCreateDto>());
		}

	}

	@Override
	public Page<Jardin> convertPageUpdateDtoToEntity(Page<JardinUpdateDto> pageUpdateDto) {
		if (pageUpdateDto != null) {
			return pageUpdateDto.map(this::convertUpdateDtoToEntity);
		} else {
			return new PageImpl<Jardin>(new ArrayList<Jardin>());
		}

	}

	@Override
	public Page<JardinUpdateDto> convertPageEntityToUpdateDto(Page<Jardin> pageEntity) {
		if (pageEntity != null) {
			return pageEntity.map(this::convertEntityToUpdateDto);
		} else {
			return new PageImpl<JardinUpdateDto>(new ArrayList<JardinUpdateDto>());
		}
	}

	@Override
	public List<Jardin> convertListCreateDtoToEntity(List<JardinCreateDto> listeCreateDto) {

		List<Jardin> listeRetour = new ArrayList<Jardin>();
		if (listeCreateDto != null) {
			for (JardinCreateDto j : listeCreateDto) {
				listeRetour.add(convertCreateDtoToEntity(j));
			}
		}
		return listeRetour;

	}

	@Override
	public List<JardinCreateDto> convertListEntityToCreateDto(List<Jardin> listeEntity) {
		List<JardinCreateDto> listeRetour = new ArrayList<JardinCreateDto>();
		if (listeEntity != null) {
			for (Jardin j : listeEntity) {
				listeRetour.add(convertEntityToCreateDto(j));
			}
		}
		return listeRetour;
	}

	@Override
	public List<Jardin> convertListUpdateDtoToEntity(List<JardinUpdateDto> listeUpdateDto) {
		List<Jardin> listeRetour = new ArrayList<Jardin>();
		if (listeUpdateDto != null) {
		for (JardinUpdateDto j : listeUpdateDto) {
			listeRetour.add(convertUpdateDtoToEntity(j));
		}}
		return listeRetour;
	}

	@Override
	public List<JardinUpdateDto> convertListEntityToUpdateDto(List<Jardin> listeEntity) {
		List<JardinUpdateDto> listeRetour = new ArrayList<JardinUpdateDto>();
		if(listeEntity != null) {
		for (Jardin j : listeEntity) {
			listeRetour.add(convertEntityToUpdateDto(j));
		}}
		return listeRetour;
	}

}
