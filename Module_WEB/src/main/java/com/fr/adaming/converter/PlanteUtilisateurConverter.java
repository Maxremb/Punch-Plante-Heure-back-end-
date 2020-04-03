package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	public Page<PlanteUtilisateur> convertListCreateDtoToEntity(Page<PlanteUtilisateurCreateDto> listeCreateDto) {
		List<PlanteUtilisateur> listeRetour = new ArrayList<PlanteUtilisateur>();
		for(PlanteUtilisateurCreateDto p : listeCreateDto.toList()) {
			
			listeRetour.add(convertCreateDtoToEntity(p));
		}
		
		Page<PlanteUtilisateur> pageRetour= new PageImpl<PlanteUtilisateur>(listeRetour);
		return pageRetour;
	}

	@Override
	public Page<PlanteUtilisateurCreateDto> convertListEntityToCreateDto(Page<PlanteUtilisateur> listeEntity) {
		List<PlanteUtilisateurCreateDto> listeRetour = new ArrayList<PlanteUtilisateurCreateDto>();
		for(PlanteUtilisateur p: listeEntity.toList()) {
			listeRetour.add(convertEntityToCreateDto(p));
		}
		
		Page<PlanteUtilisateurCreateDto> pageRetour = new PageImpl<PlanteUtilisateurCreateDto>(listeRetour);
		return pageRetour;
	}

	@Override
	public Page<PlanteUtilisateur> convertListUpdateDtoToEntity(Page<PlanteUtilisateurUpdateDto> listeUpdateDto) {
		List<PlanteUtilisateur> listeRetour = new ArrayList<PlanteUtilisateur>();
		for(PlanteUtilisateurUpdateDto p:listeUpdateDto.toList()) {
			listeRetour.add(convertUpdateDtoToEntity(p));
		}
		
		Page<PlanteUtilisateur> pageRetour= new PageImpl<PlanteUtilisateur>(listeRetour);
		return pageRetour;
	}

	@Override
	public Page<PlanteUtilisateurUpdateDto> convertListEntityToUpdateDto(Page<PlanteUtilisateur> listeEntity) {
		List<PlanteUtilisateurUpdateDto> listeRetour = new ArrayList<PlanteUtilisateurUpdateDto>();
		for(PlanteUtilisateur p: listeEntity.toList()) {
			listeRetour.add(convertEntityToUpdateDto(p));
		}
		Page<PlanteUtilisateurUpdateDto> pageRetour = new PageImpl<PlanteUtilisateurUpdateDto>(listeRetour);
		return pageRetour;
	}

}
