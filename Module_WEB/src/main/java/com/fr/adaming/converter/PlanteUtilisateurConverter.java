package com.fr.adaming.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.PlanteUtilisateurCreateDto;
import com.fr.adaming.dto.PlanteUtilisateurUpdateDto;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.entity.PlanteUtilisateur;

@Component
public class PlanteUtilisateurConverter implements IConverter<PlanteUtilisateurCreateDto, PlanteUtilisateurUpdateDto, PlanteUtilisateur> {

	@Autowired
	private PlanteModelConverter plantModelConverter;
	
	@Autowired
	private JardinConverter jardinConverter;
	
	
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

			planteUtilisateurDto.setName(entity.getNom());
			planteUtilisateurDto.setSurname(entity.getPrenom());
			planteUtilisateurDto.setAdress(entity.getAdresse());
			planteUtilisateurDto.setPostalCode(entity.getCp());
			planteUtilisateurDto.setCity(entity.getVille());
			planteUtilisateurDto.setS(entity.getSexe());
			planteUtilisateurDto.setIdentity(entity.getCni());
			planteUtilisateurDto.setPhone(entity.getNum());
			planteUtilisateurDto.setMail(entity.getEmail());
			
			planteUtilisateurDto.setClasse(classConverter.convertEntityToUpdateDto(entity.getClasse()));
	
			return planteUtilisateurDto;

		} else {
			return null;
		}
	}

	@Override
	public PlanteUtilisateur convertUpdateDtoToEntity(PlanteUtilisateurUpdateDto updateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanteUtilisateurUpdateDto convertEntityToUpdateDto(PlanteUtilisateur entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanteUtilisateur> convertListCreateDtoToEntity(List<PlanteUtilisateurCreateDto> listeCreateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanteUtilisateurCreateDto> convertListEntityToCreateDto(List<PlanteUtilisateur> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanteUtilisateur> convertListUpdateDtoToEntity(List<PlanteUtilisateurUpdateDto> listeUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanteUtilisateurUpdateDto> convertListEntityToUpdateDto(List<PlanteUtilisateur> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
