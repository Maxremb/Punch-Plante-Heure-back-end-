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

import lombok.extern.slf4j.Slf4j;

/**
 * Couche converter pour le jardin qui implement IConverter
 * 
 * @author Clara Cadet
 * @since 0.0.1-SNAPSHOT
 */
@Component
@Slf4j
public class JardinConverter implements IConverter<JardinCreateDto, JardinUpdateDto, Jardin>, IJardinConverter {

	@Autowired
	private IConverterDepartement<Departement, DepartementDto> convertDep;

	@Autowired
	private IConverter<UtilisateurCreateDto, UtilisateurUpdateDto, Utilisateur> convertUtil;

	@Override
	public Jardin convertCreateDtoToEntity(JardinCreateDto createDto) {
		log.info("Converter Jardin : méthode de conversion create dto vers jardin");
		if (createDto != null) {
			log.info("Conversion OK");
			Jardin entite = new Jardin();
			entite.setEstArroser(createDto.isArrosed());
			entite.setProfSol(createDto.getDepthGround());
			entite.setDepartement(convertDep.convertDtoToEntity((createDto.getDept())));
			entite.setLargeur(createDto.getWidth());
			entite.setLongueur(createDto.getLength());
			entite.setNom(createDto.getName());
			entite.setSol(createDto.getGround());
			entite.setUtilisateur(convertUtil.convertUpdateDtoToEntity(createDto.getUser()));
			return entite;
		}
		log.info("Conversion non réalisée : create dto null");
		return null;
	}

	@Override
	public JardinCreateDto convertEntityToCreateDto(Jardin entity) {
		log.info("Converter jardin : méthode de conversion jardin vers create dto");
		if (entity != null) {
			log.info("Conversion OK");
			JardinCreateDto createDto = new JardinCreateDto();
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
		log.info("Conversion non réalisée : jardin null");
		return null;
	}

	@Override
	public Jardin convertUpdateDtoToEntity(JardinUpdateDto updateDto) {
		log.info("Converter jardin : méthode conversion update dto vers jardin");
		if (updateDto != null) {
			log.info("Conversion OK");
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
		log.info("Conversion non réalisée : update dto null");
		return null;
	}

	@Override
	public JardinUpdateDto convertEntityToUpdateDto(Jardin entity) {
		log.info("Converter jardin : méthode conversion jardin vers update dto");
		if (entity != null) {
			log.info("Conversion OK");
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
		log.info("Conversion non réalisée : jardin null");
		return null;
	}

	@Override
	public Page<Jardin> convertPageCreateDtoToEntity(Page<JardinCreateDto> pageCreateDto) {
		log.info("Converter jardin : méthode conversion page create dto vers page jardin");
		if (pageCreateDto != null) {
			log.info("Conversion OK");
			return pageCreateDto.map(this::convertCreateDtoToEntity);
		} else {
			log.info("Conversion non réalisée : page create dto null");
			return new PageImpl<Jardin>(new ArrayList<Jardin>());
		}
	}

	@Override
	public Page<JardinCreateDto> convertPageEntityToCreateDto(Page<Jardin> pageEntity) {
		log.info("Converter jardin : méthode conversion page jardin vers page create dto");
		if (pageEntity != null) {
			log.info("Conversion OK");
			return pageEntity.map(this::convertEntityToCreateDto);
		} else {
			log.info("Conversion non réalisée : page jardin null");
			return new PageImpl<JardinCreateDto>(new ArrayList<JardinCreateDto>());
		}

	}

	@Override
	public Page<Jardin> convertPageUpdateDtoToEntity(Page<JardinUpdateDto> pageUpdateDto) {
		log.info("Converter jardin : méthode conversion page update dto vers page jardin");
		if (pageUpdateDto != null) {
			log.info("Conversion OK");
			return pageUpdateDto.map(this::convertUpdateDtoToEntity);
		} else {
			log.info("Conversion non réalisée : page update dto null");
			return new PageImpl<Jardin>(new ArrayList<Jardin>());
		}

	}

	@Override
	public Page<JardinUpdateDto> convertPageEntityToUpdateDto(Page<Jardin> pageEntity) {
		log.info("Converter jardin : méthode conversion page jardin vers update dto");
		if (pageEntity != null) {
			log.info("Conversion OK");
			return pageEntity.map(this::convertEntityToUpdateDto);
		} else {
			log.info("Conversion non réalisée : page jardin null");
			return new PageImpl<JardinUpdateDto>(new ArrayList<JardinUpdateDto>());
		}
	}

	@Override
	public List<Jardin> convertListCreateDtoToEntity(List<JardinCreateDto> listeCreateDto) {
		log.info("Converter jardin : méthode conversion liste create dto vers liste jardin");

		List<Jardin> listeRetour = new ArrayList<Jardin>();
		if (listeCreateDto != null) {
			log.info("Conversion OK");
			for (JardinCreateDto j : listeCreateDto) {
				listeRetour.add(convertCreateDtoToEntity(j));
			}
		}
		return listeRetour;

	}

	@Override
	public List<JardinCreateDto> convertListEntityToCreateDto(List<Jardin> listeEntity) {
		log.info("Converter jardin : méthode conversion list jardin vers liste create dto");
		List<JardinCreateDto> listeRetour = new ArrayList<JardinCreateDto>();
		if (listeEntity != null) {
			log.info("Conversion OK");
			for (Jardin j : listeEntity) {
				listeRetour.add(convertEntityToCreateDto(j));
			}
		}
		return listeRetour;
	}

	@Override
	public List<Jardin> convertListUpdateDtoToEntity(List<JardinUpdateDto> listeUpdateDto) {
		log.info("Converter jardin : méthode conversion list update dto vers liste jardin");
		List<Jardin> listeRetour = new ArrayList<Jardin>();
		if (listeUpdateDto != null) {
			log.info("Conversion OK");
			for (JardinUpdateDto j : listeUpdateDto) {
				listeRetour.add(convertUpdateDtoToEntity(j));
			}
		}
		return listeRetour;
	}

	@Override
	public List<JardinUpdateDto> convertListEntityToUpdateDto(List<Jardin> listeEntity) {
		log.info("Converter jardin : méthode conversion list jardin vers update dto");
		List<JardinUpdateDto> listeRetour = new ArrayList<JardinUpdateDto>();
		if (listeEntity != null) {
			log.info("Conversion OK");
			for (Jardin j : listeEntity) {
				listeRetour.add(convertEntityToUpdateDto(j));
			}
		}
		return listeRetour;
	}

	@Override
	public List<Integer> convertJardinListToId(List<Jardin> jardinList) {

		List<Integer> idList = new ArrayList<Integer>();

		if (jardinList != null) {

			for (Jardin j : jardinList) {
				idList.add(j.getId());
			}
			
		}

		return idList;

	}

}
