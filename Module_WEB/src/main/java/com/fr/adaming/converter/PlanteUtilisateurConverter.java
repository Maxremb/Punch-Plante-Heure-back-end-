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

			planteUtilisateur.setListeCoordonnees(createDto.getCoordonnees());
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

			planteUtilisateurDto.setCoordonnees(entity.getListeCoordonnees());
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
			planteUtilisateur.setListeCoordonnees(updateDto.getCoordonnees());
			planteUtilisateur.setId(updateDto.getIdentifiant());
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
			planteUtilisateurDto.setCoordonnees(entity.getListeCoordonnees());
			planteUtilisateurDto.setIdentifiant(entity.getId());
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
	public Page<PlanteUtilisateur> convertPageCreateDtoToEntity(Page<PlanteUtilisateurCreateDto> pageCreateDto) {

		return pageCreateDto == null ? new PageImpl<PlanteUtilisateur>(new ArrayList<PlanteUtilisateur>())
				: pageCreateDto.map(this::convertCreateDtoToEntity);

	}

	@Override
	public Page<PlanteUtilisateurCreateDto> convertPageEntityToCreateDto(Page<PlanteUtilisateur> pageEntity) {

		return pageEntity == null
				? new PageImpl<PlanteUtilisateurCreateDto>(new ArrayList<PlanteUtilisateurCreateDto>())
				: pageEntity.map(this::convertEntityToCreateDto);

	}

	@Override
	public Page<PlanteUtilisateur> convertPageUpdateDtoToEntity(Page<PlanteUtilisateurUpdateDto> pageUpdateDto) {

		return pageUpdateDto == null ? new PageImpl<PlanteUtilisateur>(new ArrayList<PlanteUtilisateur>())
				: pageUpdateDto.map(this::convertUpdateDtoToEntity);

	}

	@Override
	public Page<PlanteUtilisateurUpdateDto> convertPageEntityToUpdateDto(Page<PlanteUtilisateur> pageEntity) {

		return pageEntity == null
				? new PageImpl<PlanteUtilisateurUpdateDto>(new ArrayList<PlanteUtilisateurUpdateDto>())
				: pageEntity.map(this::convertEntityToUpdateDto);

	}

	@Override
	public List<PlanteUtilisateur> convertListCreateDtoToEntity(List<PlanteUtilisateurCreateDto> listeCreateDto) {

		return listeCreateDto == null ? new ArrayList<PlanteUtilisateur>()
				: listeCreateDto.stream().map(this::convertCreateDtoToEntity).collect(Collectors.toList());

	}

	@Override
	public List<PlanteUtilisateurCreateDto> convertListEntityToCreateDto(List<PlanteUtilisateur> listeEntity) {

		return listeEntity == null ? new ArrayList<PlanteUtilisateurCreateDto>()
				: listeEntity.stream().map(this::convertEntityToCreateDto).collect(Collectors.toList());

	}

	@Override
	public List<PlanteUtilisateur> convertListUpdateDtoToEntity(List<PlanteUtilisateurUpdateDto> listeUpdateDto) {

		return listeUpdateDto == null ? new ArrayList<PlanteUtilisateur>()
				: listeUpdateDto.stream().map(this::convertUpdateDtoToEntity).collect(Collectors.toList());

	}

	@Override
	public List<PlanteUtilisateurUpdateDto> convertListEntityToUpdateDto(List<PlanteUtilisateur> listeEntity) {

		return listeEntity == null ? new ArrayList<PlanteUtilisateurUpdateDto>()
				: listeEntity.stream().map(this::convertEntityToUpdateDto).collect(Collectors.toList());

	}

}
