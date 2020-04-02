package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@Component
public class PlanteUtilisateurConverter implements IConverter<PlanteUtilisateurCreateDto, PlanteUtilisateurUpdateDto, PlanteUtilisateur> {

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
	public List<PlanteUtilisateur> convertListCreateDtoToEntity(List<PlanteUtilisateurCreateDto> listeCreateDto) {
		if (listeCreateDto != null) {
			List<PlanteUtilisateur> planteUtilisateurListe = new ArrayList<>();

			for (PlanteUtilisateurCreateDto planteUtilisateurDto : listeCreateDto) {
				planteUtilisateurListe.add(convertCreateDtoToEntity(planteUtilisateurDto));
			}
			return planteUtilisateurListe;
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public List<PlanteUtilisateurCreateDto> convertListEntityToCreateDto(List<PlanteUtilisateur> listeEntity) {
		if (listeEntity != null) {
			List<PlanteUtilisateurCreateDto> planteUtilisateurdtolist = new ArrayList<>();

			for (PlanteUtilisateur p : listeEntity) {

				planteUtilisateurdtolist.add(convertEntityToCreateDto(p));
			}
			return planteUtilisateurdtolist;
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public List<PlanteUtilisateur> convertListUpdateDtoToEntity(List<PlanteUtilisateurUpdateDto> listeUpdateDto) {
		if (listeUpdateDto != null) {
			List<PlanteUtilisateur> planteUtilisateurListe = new ArrayList<>();

			for (PlanteUtilisateurUpdateDto planteUtilisateurDto : listeUpdateDto) {
				planteUtilisateurListe.add(convertUpdateDtoToEntity(planteUtilisateurDto));
			}
			return planteUtilisateurListe;
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public List<PlanteUtilisateurUpdateDto> convertListEntityToUpdateDto(List<PlanteUtilisateur> listeEntity) {
		if (listeEntity != null) {
			List<PlanteUtilisateurUpdateDto> planteUtilisateurdtolist = new ArrayList<>();

			for (PlanteUtilisateur p : listeEntity) {

				planteUtilisateurdtolist.add(convertEntityToUpdateDto(p));
			}
			return planteUtilisateurdtolist;
		} else {
			return Collections.emptyList();
		}
	}

}
