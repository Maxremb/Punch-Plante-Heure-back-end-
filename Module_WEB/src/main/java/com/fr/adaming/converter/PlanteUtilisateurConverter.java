package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.JardinCreateDto;
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.PlanteModelCreateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.dto.PlanteUtilisateurCreateDto;
import com.fr.adaming.dto.PlanteUtilisateurUpdateDto;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.entity.PlanteUtilisateur;

/**
 * <p>
 * Converter pour l'entite Plante Utilisateur en dto et inversement <br>
 * Implemente IConverter.
 * </p>
 * 
 * @author lucie
 * @since 0.0.1
 *
 */
@Component
public class PlanteUtilisateurConverter
		implements IConverter<PlanteUtilisateurCreateDto, PlanteUtilisateurUpdateDto, PlanteUtilisateur> {

	@Autowired
	private IConverter<PlanteModelCreateDto, PlanteModelUpdateDto, PlanteModel> plantModelConverter;

	@Autowired
	private IConverter<JardinCreateDto, JardinUpdateDto, Jardin> jardinConverter;

	@Override
	public PlanteUtilisateur convertCreateDtoToEntity(PlanteUtilisateurCreateDto createDto) {
		if (createDto != null) {
			PlanteUtilisateur planteUtilisateur = new PlanteUtilisateur();

			planteUtilisateur.setDateSemis(createDto.getSemiDate());
			planteUtilisateur.setDatePlantation(createDto.getPlantingDate());
			planteUtilisateur.setEtatPlante(createDto.getPlantStage());
			planteUtilisateur.setEtatSante(createDto.getHealthStage());

			Jardin jardin = jardinConverter.convertUpdateDtoToEntity(createDto.getGarden());
			planteUtilisateur.setJardin(jardin);

			PlanteModel planteModel = plantModelConverter.convertUpdateDtoToEntity(createDto.getModelPlant());
			planteUtilisateur.setPlanteModel(planteModel);

			return planteUtilisateur;

		} else {
			return null;
		}
	}

	@Override
	public PlanteUtilisateurCreateDto convertEntityToCreateDto(PlanteUtilisateur entity) {
		if (entity != null) {
			PlanteUtilisateurCreateDto planteUtilisateurDto = new PlanteUtilisateurCreateDto();

			planteUtilisateurDto.setSemiDate(entity.getDateSemis());
			planteUtilisateurDto.setPlantingDate(entity.getDatePlantation());
			planteUtilisateurDto.setPlantStage(entity.getEtatPlante());
			planteUtilisateurDto.setHealthStage(entity.getEtatSante());

			planteUtilisateurDto.setGarden(jardinConverter.convertEntityToUpdateDto(entity.getJardin()));

			planteUtilisateurDto.setModelPlant(plantModelConverter.convertEntityToUpdateDto(entity.getPlanteModel()));

			return planteUtilisateurDto;

		} else {
			return null;
		}
	}

	@Override
	public PlanteUtilisateur convertUpdateDtoToEntity(PlanteUtilisateurUpdateDto updateDto) {
		if (updateDto != null) {
			PlanteUtilisateur planteUtilisateur = new PlanteUtilisateur();

			planteUtilisateur.setDateSemis(updateDto.getSemiDate());
			planteUtilisateur.setDatePlantation(updateDto.getPlantingDate());
			planteUtilisateur.setEtatPlante(updateDto.getPlantStage());
			planteUtilisateur.setEtatSante(updateDto.getHealthStage());

			Jardin jardin = jardinConverter.convertUpdateDtoToEntity(updateDto.getGarden());
			planteUtilisateur.setJardin(jardin);

			PlanteModel planteModel = plantModelConverter.convertUpdateDtoToEntity(updateDto.getModelPlant());
			planteUtilisateur.setPlanteModel(planteModel);

			return planteUtilisateur;

		} else {
			return null;
		}
	}

	@Override
	public PlanteUtilisateurUpdateDto convertEntityToUpdateDto(PlanteUtilisateur entity) {
		if (entity != null) {
			PlanteUtilisateurUpdateDto planteUtilisateurDto = new PlanteUtilisateurUpdateDto();

			planteUtilisateurDto.setSemiDate(entity.getDateSemis());
			planteUtilisateurDto.setPlantingDate(entity.getDatePlantation());
			planteUtilisateurDto.setPlantStage(entity.getEtatPlante());
			planteUtilisateurDto.setHealthStage(entity.getEtatSante());

			planteUtilisateurDto.setGarden(jardinConverter.convertEntityToUpdateDto(entity.getJardin()));

			planteUtilisateurDto.setModelPlant(plantModelConverter.convertEntityToUpdateDto(entity.getPlanteModel()));

			return planteUtilisateurDto;

		} else {
			return null;
		}
	}


	@Override
	public Page<PlanteUtilisateur> convertPageCreateDtoToEntity(Page<PlanteUtilisateurCreateDto> listeCreateDto) {
		
		return listeCreateDto.map(this :: convertCreateDtoToEntity);
		
	}

	@Override
	public Page<PlanteUtilisateurCreateDto> convertPageEntityToCreateDto(Page<PlanteUtilisateur> listeEntity) {
		
		return listeEntity.map(this :: convertEntityToCreateDto);
	
	}

	@Override
	public Page<PlanteUtilisateur> convertPageUpdateDtoToEntity(Page<PlanteUtilisateurUpdateDto> listeUpdateDto) {
		
		return listeUpdateDto.map(this :: convertUpdateDtoToEntity);

	}

	@Override
	public Page<PlanteUtilisateurUpdateDto> convertPageEntityToUpdateDto(Page<PlanteUtilisateur> listeEntity) {
		
		return listeEntity.map(this :: convertEntityToUpdateDto);
		
	}

	@Override
	public List<PlanteUtilisateur> convertListCreateDtoToEntity(List<PlanteUtilisateurCreateDto> listeCreateDto) {
		
		return listeCreateDto.stream().map(this :: convertCreateDtoToEntity).collect(Collectors.toList());
		
//		List<PlanteUtilisateur> listeRetour = new ArrayList<PlanteUtilisateur>();
//		for(PlanteUtilisateurCreateDto p : listeCreateDto) {
//			listeRetour.add(convertCreateDtoToEntity(p));
//		}
//		return listeRetour;
	}

	@Override
	public List<PlanteUtilisateurCreateDto> convertListEntityToCreateDto(List<PlanteUtilisateur> listeEntity) {
		
		return listeEntity.stream().map(this :: convertEntityToCreateDto).collect(Collectors.toList());
		
//		List<PlanteUtilisateurCreateDto> listeRetour = new ArrayList<PlanteUtilisateurCreateDto>();
//		for(PlanteUtilisateur p: listeEntity) {
//			listeRetour.add(convertEntityToCreateDto(p));
//		}
//		return listeRetour;
	}

	@Override
	public List<PlanteUtilisateur> convertListUpdateDtoToEntity(List<PlanteUtilisateurUpdateDto> listeUpdateDto) {
		
		return listeUpdateDto.stream().map(this :: convertUpdateDtoToEntity).collect(Collectors.toList());
		
//		List<PlanteUtilisateur> listeRetour = new ArrayList<PlanteUtilisateur>();
//		for(PlanteUtilisateurUpdateDto p: listeUpdateDto) {
//			listeRetour.add(convertUpdateDtoToEntity(p));
//		}
//		return listeRetour;
	}

	@Override
	public List<PlanteUtilisateurUpdateDto> convertListEntityToUpdateDto(List<PlanteUtilisateur> listeEntity) {
		
		return listeEntity.stream().map(this :: convertEntityToUpdateDto).collect(Collectors.toList());
		
//		List<PlanteUtilisateurUpdateDto> listeRetour = new ArrayList<PlanteUtilisateurUpdateDto>();
//		for(PlanteUtilisateur p: listeEntity) {
//			listeRetour.add(convertEntityToUpdateDto(p));
//		}
//		return listeRetour;
	}

}
