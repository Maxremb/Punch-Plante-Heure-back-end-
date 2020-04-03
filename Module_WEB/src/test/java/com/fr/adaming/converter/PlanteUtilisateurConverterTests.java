package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.dto.PlanteUtilisateurCreateDto;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.enums.EtatPlante;
import com.fr.adaming.enums.EtatSante;

/**
 * <p>Classe de Test pour les Converter de l'entite Plante Utilisateur.<br>
 * Implemente l'interface IConverter.</p>
 * @author Lucie
 * @since 0.0.1
 *
 */
@SpringBootTest(classes = ModuleWebApplication.class)
public class PlanteUtilisateurConverterTests implements IConverterTests{

	@Autowired
	private PlanteUtilisateurConverter planteUtilisateurConverter;
	
	@Test
	@Override
	public void testConvertingCreateDtoToEntity_shouldReturnEntity() {
		
		PlanteUtilisateurCreateDto dto = new PlanteUtilisateurCreateDto();
		
		dto.setHealthStage(EtatSante.bonneSante);
		dto.setPlantStage(EtatPlante.fleuri);
		dto.setPlantingDate(LocalDate.parse("2020-04-03"));
		dto.setPlantingDate(LocalDate.parse("2020-04-03"));
		
		PlanteUtilisateur returnPlanteUtilisateur = planteUtilisateurConverter.convertCreateDtoToEntity(dto);
		
		assertThat(returnPlanteUtilisateur).hasFieldOrPropertyWithValue("etatSante", dto.getHealthStage());
		assertThat(returnPlanteUtilisateur).hasFieldOrPropertyWithValue("etatPlante", dto.getPlantStage());
		assertThat(returnPlanteUtilisateur).hasFieldOrPropertyWithValue("dateSemis", dto.getSemiDate());
		assertThat(returnPlanteUtilisateur).hasFieldOrPropertyWithValue("datePlantation", dto.getPlantingDate());
		
		
	}

	@Test
	@Override
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingUpdateDtoToEntity_shouldReturnEntity() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingEntityToCreateDto_shouldReturnCreateDto() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingEntityToUpdateDto_shouldReturnUpdateDto() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingListEntityToCreateDto_shouldReturnCreateDtoList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingListCreateDtoToEntity_shouldReturnEntityList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingListEntityToUpdateDto_shouldReturnUpdateDtoList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingListUpdateDtoToEntity_shouldReturnEntityList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingPageCreateDtoToEntity_shouldReturnPageOfEntities() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingPageEntityToCreateDto_shouldReturnPageOfCreateDtos() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingPageUpdateDtoToEntity_shouldReturnPageOfEntities() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingPageEntityToUpdateDto_shouldReturnPageOfUpdateDtos() {
		// TODO Auto-generated method stub
		
	}

}
