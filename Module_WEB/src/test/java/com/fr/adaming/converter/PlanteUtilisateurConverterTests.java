package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.dto.PlanteUtilisateurCreateDto;
import com.fr.adaming.dto.PlanteUtilisateurUpdateDto;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.enums.EtatPlante;
import com.fr.adaming.enums.EtatSante;

/**
 * <p>
 * Classe de Test pour les Converter de l'entite Plante Utilisateur.<br>
 * Implemente l'interface IConverter.
 * </p>
 * 
 * @author Lucie
 * @since 0.0.1
 *
 */
@SpringBootTest(classes = ModuleWebApplication.class)
public class PlanteUtilisateurConverterTests implements IConverterTests {

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
		assertNull(planteUtilisateurConverter.convertCreateDtoToEntity(null));

	}

	@Test
	@Override
	public void testConvertingUpdateDtoToEntity_shouldReturnEntity() {
		PlanteUtilisateurUpdateDto dto = new PlanteUtilisateurUpdateDto();

		dto.setHealthStage(EtatSante.bonneSante);
		dto.setPlantStage(EtatPlante.fleuri);
		dto.setPlantingDate(LocalDate.parse("2020-04-03"));
		dto.setPlantingDate(LocalDate.parse("2020-04-03"));

		PlanteUtilisateur returnPlanteUtilisateur = planteUtilisateurConverter.convertUpdateDtoToEntity(dto);

		assertThat(returnPlanteUtilisateur).hasFieldOrPropertyWithValue("etatSante", dto.getHealthStage());
		assertThat(returnPlanteUtilisateur).hasFieldOrPropertyWithValue("etatPlante", dto.getPlantStage());
		assertThat(returnPlanteUtilisateur).hasFieldOrPropertyWithValue("dateSemis", dto.getSemiDate());
		assertThat(returnPlanteUtilisateur).hasFieldOrPropertyWithValue("datePlantation", dto.getPlantingDate());

	}

	@Test
	@Override
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		assertNull(planteUtilisateurConverter.convertUpdateDtoToEntity(null));

	}

	@Test
	@Override
	public void testConvertingEntityToCreateDto_shouldReturnCreateDto() {
		PlanteUtilisateur planteUtilisateur = new PlanteUtilisateur();

		planteUtilisateur.setEtatSante(EtatSante.moyenne);
		planteUtilisateur.setEtatPlante(EtatPlante.plant);
		planteUtilisateur.setDatePlantation(LocalDate.parse("2020-04-04"));
		planteUtilisateur.setDateSemis(LocalDate.parse("2020-03-01"));

		PlanteUtilisateurCreateDto returnedDto = planteUtilisateurConverter.convertEntityToCreateDto(planteUtilisateur);

		assertThat(returnedDto).hasFieldOrPropertyWithValue("semiDate", planteUtilisateur.getDateSemis());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("plantingDate", planteUtilisateur.getDatePlantation());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("plantStage", planteUtilisateur.getEtatPlante());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("healthStage", planteUtilisateur.getEtatSante());
	}

	@Test
	@Override
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		assertNull(planteUtilisateurConverter.convertEntityToCreateDto(null));

	}

	@Test
	@Override
	public void testConvertingEntityToUpdateDto_shouldReturnUpdateDto() {
		PlanteUtilisateur planteUtilisateur = new PlanteUtilisateur();

		planteUtilisateur.setEtatSante(EtatSante.moyenne);
		planteUtilisateur.setEtatPlante(EtatPlante.plant);
		planteUtilisateur.setDatePlantation(LocalDate.parse("2020-04-04"));
		planteUtilisateur.setDateSemis(LocalDate.parse("2020-03-01"));

		PlanteUtilisateurUpdateDto returnedDto = planteUtilisateurConverter.convertEntityToUpdateDto(planteUtilisateur);

		assertThat(returnedDto).hasFieldOrPropertyWithValue("semiDate", planteUtilisateur.getDateSemis());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("plantingDate", planteUtilisateur.getDatePlantation());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("plantStage", planteUtilisateur.getEtatPlante());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("healthStage", planteUtilisateur.getEtatSante());

	}

	@Test
	@Override
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		assertNull(planteUtilisateurConverter.convertEntityToUpdateDto(null));

	}

	@Test
	@Override
	public void testConvertingListEntityToCreateDto_shouldReturnCreateDtoList() {
		PlanteUtilisateur planteUtilisateur1 = new PlanteUtilisateur();

		planteUtilisateur1.setEtatSante(EtatSante.moyenne);
		planteUtilisateur1.setEtatPlante(EtatPlante.plant);
		planteUtilisateur1.setDatePlantation(LocalDate.parse("2020-04-04"));
		planteUtilisateur1.setDateSemis(LocalDate.parse("2020-03-01"));

		PlanteUtilisateur planteUtilisateur2 = new PlanteUtilisateur();

		planteUtilisateur2.setEtatSante(EtatSante.bonneSante);
		planteUtilisateur2.setEtatPlante(EtatPlante.fleuri);
		planteUtilisateur2.setDatePlantation(LocalDate.parse("2020-04-04"));
		planteUtilisateur2.setDateSemis(LocalDate.parse("2020-03-01"));

		List<PlanteUtilisateur> liste = new ArrayList<>();
		liste.add(planteUtilisateur1);
		liste.add(planteUtilisateur2);

		List<PlanteUtilisateurCreateDto> listeDto = planteUtilisateurConverter.convertListEntityToCreateDto(liste);

		assertThat(listeDto).isNotNull().hasSize(2);
		assertThat(listeDto.get(0)).hasFieldOrPropertyWithValue("healthStage", planteUtilisateur1.getEtatSante());
		assertThat(listeDto.get(1)).hasFieldOrPropertyWithValue("healthStage", planteUtilisateur2.getEtatSante());
		assertThat(listeDto.get(0)).hasFieldOrPropertyWithValue("plantStage", planteUtilisateur1.getEtatPlante());
		assertThat(listeDto.get(1)).hasFieldOrPropertyWithValue("plantStage", planteUtilisateur2.getEtatPlante());
		assertThat(listeDto.get(0)).hasFieldOrPropertyWithValue("plantingDate", planteUtilisateur1.getDatePlantation());
		assertThat(listeDto.get(1)).hasFieldOrPropertyWithValue("plantingDate", planteUtilisateur2.getDatePlantation());
		assertThat(listeDto.get(0)).hasFieldOrPropertyWithValue("semiDate",planteUtilisateur1.getDateSemis());
		assertThat(listeDto.get(1)).hasFieldOrPropertyWithValue("semiDate", planteUtilisateur2.getDateSemis());
	}

	@Test
	@Override
	public void testConvertingListCreateDtoToEntity_shouldReturnEntityList() {
		PlanteUtilisateurCreateDto dto1 = new PlanteUtilisateurCreateDto();

		dto1.setHealthStage(EtatSante.bonneSante);
		dto1.setPlantStage(EtatPlante.fleuri);
		dto1.setPlantingDate(LocalDate.parse("2020-04-03"));
		dto1.setSemiDate(LocalDate.parse("2020-04-03"));

		PlanteUtilisateurCreateDto dto2 = new PlanteUtilisateurCreateDto();

		dto2.setHealthStage(EtatSante.bonneSante);
		dto2.setPlantStage(EtatPlante.fleuri);
		dto2.setPlantingDate(LocalDate.parse("2020-04-03"));
		dto2.setSemiDate(LocalDate.parse("2020-04-03"));

		List<PlanteUtilisateurCreateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);

		List<PlanteUtilisateur> liste = planteUtilisateurConverter.convertListCreateDtoToEntity(listeDto);

		assertThat(liste).isNotNull().hasSize(2);
		assertThat(liste).isNotNull().hasSize(2);
		assertThat(liste.get(0)).hasFieldOrPropertyWithValue("etatSante", dto1.getHealthStage());
		assertThat(liste.get(1)).hasFieldOrPropertyWithValue("etatSante", dto2.getHealthStage());
		assertThat(liste.get(0)).hasFieldOrPropertyWithValue("etatPlante", dto1.getPlantStage());
		assertThat(liste.get(1)).hasFieldOrPropertyWithValue("etatPlante", dto2.getPlantStage());
		assertThat(liste.get(0)).hasFieldOrPropertyWithValue("datePlantation", dto1.getPlantingDate());
		assertThat(liste.get(1)).hasFieldOrPropertyWithValue("datePlantation", dto2.getPlantingDate());
		assertThat(liste.get(0)).hasFieldOrPropertyWithValue("dateSemis", dto1.getSemiDate());
		assertThat(liste.get(1)).hasFieldOrPropertyWithValue("dateSemis", dto2.getSemiDate());
		
	}

	@Test
	@Override
	public void testConvertingListEntityToUpdateDto_shouldReturnUpdateDtoList() {
		PlanteUtilisateur planteUtilisateur1 = new PlanteUtilisateur();

		planteUtilisateur1.setEtatSante(EtatSante.moyenne);
		planteUtilisateur1.setEtatPlante(EtatPlante.plant);
		planteUtilisateur1.setDatePlantation(LocalDate.parse("2020-04-04"));
		planteUtilisateur1.setDateSemis(LocalDate.parse("2020-03-01"));

		PlanteUtilisateur planteUtilisateur2 = new PlanteUtilisateur();

		planteUtilisateur2.setEtatSante(EtatSante.bonneSante);
		planteUtilisateur2.setEtatPlante(EtatPlante.fleuri);
		planteUtilisateur2.setDatePlantation(LocalDate.parse("2020-04-04"));
		planteUtilisateur2.setDateSemis(LocalDate.parse("2020-03-01"));

		List<PlanteUtilisateur> liste = new ArrayList<>();
		liste.add(planteUtilisateur1);
		liste.add(planteUtilisateur2);

		List<PlanteUtilisateurUpdateDto> listeDto = planteUtilisateurConverter.convertListEntityToUpdateDto(liste);

		assertThat(listeDto).isNotNull().hasSize(2);
		assertThat(listeDto.get(0)).hasFieldOrPropertyWithValue("healthStage", planteUtilisateur1.getEtatSante());
		assertThat(listeDto.get(1)).hasFieldOrPropertyWithValue("healthStage", planteUtilisateur2.getEtatSante());
		assertThat(listeDto.get(0)).hasFieldOrPropertyWithValue("plantStage", planteUtilisateur1.getEtatPlante());
		assertThat(listeDto.get(1)).hasFieldOrPropertyWithValue("plantStage", planteUtilisateur2.getEtatPlante());
		assertThat(listeDto.get(0)).hasFieldOrPropertyWithValue("plantingDate", planteUtilisateur1.getDatePlantation());
		assertThat(listeDto.get(1)).hasFieldOrPropertyWithValue("plantingDate", planteUtilisateur2.getDatePlantation());
		assertThat(listeDto.get(0)).hasFieldOrPropertyWithValue("semiDate",planteUtilisateur1.getDateSemis());
		assertThat(listeDto.get(1)).hasFieldOrPropertyWithValue("semiDate", planteUtilisateur2.getDateSemis());

	}

	@Test
	@Override
	public void testConvertingListUpdateDtoToEntity_shouldReturnEntityList() {
		PlanteUtilisateurUpdateDto dto1 = new PlanteUtilisateurUpdateDto();

		dto1.setHealthStage(EtatSante.bonneSante);
		dto1.setPlantStage(EtatPlante.fleuri);
		dto1.setPlantingDate(LocalDate.parse("2020-04-03"));
		dto1.setSemiDate(LocalDate.parse("2020-04-03"));

		PlanteUtilisateurUpdateDto dto2 = new PlanteUtilisateurUpdateDto();

		dto2.setHealthStage(EtatSante.bonneSante);
		dto2.setPlantStage(EtatPlante.fleuri);
		dto2.setPlantingDate(LocalDate.parse("2020-04-03"));
		dto2.setSemiDate(LocalDate.parse("2020-04-03"));

		List<PlanteUtilisateurUpdateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);

		List<PlanteUtilisateur> liste = planteUtilisateurConverter.convertListUpdateDtoToEntity(listeDto);

		assertThat(liste).isNotNull().hasSize(2);
		assertThat(liste.get(0)).hasFieldOrPropertyWithValue("etatSante", dto1.getHealthStage());
		assertThat(liste.get(1)).hasFieldOrPropertyWithValue("etatSante", dto2.getHealthStage());
		assertThat(liste.get(0)).hasFieldOrPropertyWithValue("etatPlante", dto1.getPlantStage());
		assertThat(liste.get(1)).hasFieldOrPropertyWithValue("etatPlante", dto2.getPlantStage());
		assertThat(liste.get(0)).hasFieldOrPropertyWithValue("datePlantation", dto1.getPlantingDate());
		assertThat(liste.get(1)).hasFieldOrPropertyWithValue("datePlantation", dto2.getPlantingDate());
		assertThat(liste.get(0)).hasFieldOrPropertyWithValue("dateSemis", dto1.getSemiDate());
		assertThat(liste.get(1)).hasFieldOrPropertyWithValue("dateSemis", dto2.getSemiDate());
	}

	@Test
	@Override
	public void testConvertingPageCreateDtoToEntity_shouldReturnPageOfEntities() {
		PlanteUtilisateurCreateDto dto1 = new PlanteUtilisateurCreateDto();

		dto1.setHealthStage(EtatSante.bonneSante);
		dto1.setPlantStage(EtatPlante.fleuri);
		dto1.setPlantingDate(LocalDate.parse("2020-04-03"));
		dto1.setSemiDate(LocalDate.parse("2020-04-03"));

		PlanteUtilisateurCreateDto dto2 = new PlanteUtilisateurCreateDto();

		dto2.setHealthStage(EtatSante.bonneSante);
		dto2.setPlantStage(EtatPlante.fleuri);
		dto2.setPlantingDate(LocalDate.parse("2020-04-03"));
		dto2.setSemiDate(LocalDate.parse("2020-04-03"));

		List<PlanteUtilisateurCreateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);
		
		Page<PlanteUtilisateurCreateDto> page = new PageImpl<PlanteUtilisateurCreateDto>(listeDto);
		
		Page<PlanteUtilisateur> pagePlteUtil = planteUtilisateurConverter.convertPageCreateDtoToEntity(page);
		
		assertThat(pagePlteUtil).isNotNull();
		assertThat(pagePlteUtil.toList()).hasSize(2).hasOnlyElementsOfType(PlanteUtilisateur.class);
		assertThat(pagePlteUtil.getNumberOfElements()).isEqualTo(2);
		assertThat(pagePlteUtil.getNumber()).isEqualTo(0);
		assertThat(pagePlteUtil.toList().get(0)).hasFieldOrPropertyWithValue("etatSante", dto1.getHealthStage());
		assertThat(pagePlteUtil.toList().get(1)).hasFieldOrPropertyWithValue("etatSante", dto2.getHealthStage());
		assertThat(pagePlteUtil.toList().get(0)).hasFieldOrPropertyWithValue("etatPlante", dto1.getPlantStage());
		assertThat(pagePlteUtil.toList().get(1)).hasFieldOrPropertyWithValue("etatPlante", dto2.getPlantStage());
		assertThat(pagePlteUtil.toList().get(0)).hasFieldOrPropertyWithValue("datePlantation", dto1.getPlantingDate());
		assertThat(pagePlteUtil.toList().get(1)).hasFieldOrPropertyWithValue("datePlantation", dto2.getPlantingDate());
		assertThat(pagePlteUtil.toList().get(0)).hasFieldOrPropertyWithValue("dateSemis", dto1.getSemiDate());
		assertThat(pagePlteUtil.toList().get(1)).hasFieldOrPropertyWithValue("dateSemis", dto2.getSemiDate());

	}

	@Test
	@Override
	public void testConvertingPageEntityToCreateDto_shouldReturnPageOfCreateDtos() {
		PlanteUtilisateur planteUtilisateur1 = new PlanteUtilisateur();

		planteUtilisateur1.setEtatSante(EtatSante.moyenne);
		planteUtilisateur1.setEtatPlante(EtatPlante.plant);
		planteUtilisateur1.setDatePlantation(LocalDate.parse("2020-04-04"));
		planteUtilisateur1.setDateSemis(LocalDate.parse("2020-03-01"));

		PlanteUtilisateur planteUtilisateur2 = new PlanteUtilisateur();

		planteUtilisateur2.setEtatSante(EtatSante.bonneSante);
		planteUtilisateur2.setEtatPlante(EtatPlante.fleuri);
		planteUtilisateur2.setDatePlantation(LocalDate.parse("2020-04-04"));
		planteUtilisateur2.setDateSemis(LocalDate.parse("2020-03-01"));

		List<PlanteUtilisateur> liste = new ArrayList<>();
		liste.add(planteUtilisateur1);
		liste.add(planteUtilisateur2);
		
		Page<PlanteUtilisateur> page = new PageImpl<PlanteUtilisateur>(liste);
		
		Page<PlanteUtilisateurCreateDto> returnedPageDto = planteUtilisateurConverter.convertPageEntityToCreateDto(page);

		assertThat(returnedPageDto).isNotNull();
		assertThat(returnedPageDto.toList()).hasSize(2).hasOnlyElementsOfType(PlanteUtilisateurCreateDto.class);
		assertThat(returnedPageDto.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPageDto.getNumber()).isEqualTo(0);
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("healthStage", planteUtilisateur1.getEtatSante());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("healthStage", planteUtilisateur2.getEtatSante());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("plantStage", planteUtilisateur1.getEtatPlante());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("plantStage", planteUtilisateur2.getEtatPlante());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("plantingDate", planteUtilisateur1.getDatePlantation());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("plantingDate", planteUtilisateur2.getDatePlantation());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("semiDate",planteUtilisateur1.getDateSemis());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("semiDate", planteUtilisateur2.getDateSemis());

	}

	@Test
	@Override
	public void testConvertingPageUpdateDtoToEntity_shouldReturnPageOfEntities() {
		PlanteUtilisateurUpdateDto dto1 = new PlanteUtilisateurUpdateDto();

		dto1.setHealthStage(EtatSante.bonneSante);
		dto1.setPlantStage(EtatPlante.fleuri);
		dto1.setPlantingDate(LocalDate.parse("2020-04-03"));
		dto1.setSemiDate(LocalDate.parse("2020-04-03"));

		PlanteUtilisateurUpdateDto dto2 = new PlanteUtilisateurUpdateDto();

		dto2.setHealthStage(EtatSante.bonneSante);
		dto2.setPlantStage(EtatPlante.fleuri);
		dto2.setPlantingDate(LocalDate.parse("2020-04-03"));
		dto2.setSemiDate(LocalDate.parse("2020-04-03"));

		List<PlanteUtilisateurUpdateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);
		
		Page<PlanteUtilisateurUpdateDto> page = new PageImpl<PlanteUtilisateurUpdateDto>(listeDto);
		
		Page<PlanteUtilisateur> pagePlteUtil = planteUtilisateurConverter.convertPageUpdateDtoToEntity(page);
		
		assertThat(pagePlteUtil).isNotNull();
		assertThat(pagePlteUtil.toList()).hasSize(2).hasOnlyElementsOfType(PlanteUtilisateur.class);
		assertThat(pagePlteUtil.getNumberOfElements()).isEqualTo(2);
		assertThat(pagePlteUtil.getNumber()).isEqualTo(0);
		assertThat(pagePlteUtil.toList().get(0)).hasFieldOrPropertyWithValue("etatSante", dto1.getHealthStage());
		assertThat(pagePlteUtil.toList().get(1)).hasFieldOrPropertyWithValue("etatSante", dto2.getHealthStage());
		assertThat(pagePlteUtil.toList().get(0)).hasFieldOrPropertyWithValue("etatPlante", dto1.getPlantStage());
		assertThat(pagePlteUtil.toList().get(1)).hasFieldOrPropertyWithValue("etatPlante", dto2.getPlantStage());
		assertThat(pagePlteUtil.toList().get(0)).hasFieldOrPropertyWithValue("datePlantation", dto1.getPlantingDate());
		assertThat(pagePlteUtil.toList().get(1)).hasFieldOrPropertyWithValue("datePlantation", dto2.getPlantingDate());
		assertThat(pagePlteUtil.toList().get(0)).hasFieldOrPropertyWithValue("dateSemis", dto1.getSemiDate());
		assertThat(pagePlteUtil.toList().get(1)).hasFieldOrPropertyWithValue("dateSemis", dto2.getSemiDate());
		
		
	}

	@Test
	@Override
	public void testConvertingPageEntityToUpdateDto_shouldReturnPageOfUpdateDtos() {
		PlanteUtilisateur planteUtilisateur1 = new PlanteUtilisateur();

		planteUtilisateur1.setEtatSante(EtatSante.moyenne);
		planteUtilisateur1.setEtatPlante(EtatPlante.plant);
		planteUtilisateur1.setDatePlantation(LocalDate.parse("2020-04-04"));
		planteUtilisateur1.setDateSemis(LocalDate.parse("2020-03-01"));

		PlanteUtilisateur planteUtilisateur2 = new PlanteUtilisateur();

		planteUtilisateur2.setEtatSante(EtatSante.bonneSante);
		planteUtilisateur2.setEtatPlante(EtatPlante.fleuri);
		planteUtilisateur2.setDatePlantation(LocalDate.parse("2020-04-04"));
		planteUtilisateur2.setDateSemis(LocalDate.parse("2020-03-01"));

		List<PlanteUtilisateur> liste = new ArrayList<>();
		liste.add(planteUtilisateur1);
		liste.add(planteUtilisateur2);
		
		Page<PlanteUtilisateur> page = new PageImpl<PlanteUtilisateur>(liste);
		
		Page<PlanteUtilisateurUpdateDto> returnedPageDto = planteUtilisateurConverter.convertPageEntityToUpdateDto(page);

		assertThat(returnedPageDto).isNotNull();
		assertThat(returnedPageDto.toList()).hasSize(2).hasOnlyElementsOfType(PlanteUtilisateurUpdateDto.class);
		assertThat(returnedPageDto.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPageDto.getNumber()).isEqualTo(0);
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("healthStage", planteUtilisateur1.getEtatSante());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("healthStage", planteUtilisateur2.getEtatSante());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("plantStage", planteUtilisateur1.getEtatPlante());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("plantStage", planteUtilisateur2.getEtatPlante());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("plantingDate", planteUtilisateur1.getDatePlantation());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("plantingDate", planteUtilisateur2.getDatePlantation());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("semiDate",planteUtilisateur1.getDateSemis());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("semiDate", planteUtilisateur2.getDateSemis());
		
	}

	@Override
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testConvertingNullPageCreateDtoToEntity_shouldReturnEmptyPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testConvertingNullPageEntityToCreateDto_shouldReturnEmptyPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testConvertingNullPageUpdateDtoToEntity_shouldReturnEmptyPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testConvertingNullPageEntityToUpdateDto_shouldReturnEmptyPage() {
		// TODO Auto-generated method stub
		
	}

}
