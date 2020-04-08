package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.PlanteModelCreateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.enums.Sol;

// TODO reparer les testes en commentaires

@SpringBootTest(classes = ModuleWebApplication.class)
public class PlanteModelConverterTest implements IConverterTests {

	@Autowired
	private PlanteModelConverter converter;

	@Test
	@Override
	public void testConvertingCreateDtoToEntity_shouldReturnEntity() {
		PlanteModel plantemodel = new PlanteModel();
		PlanteModelCreateDto planteCreateDto = new PlanteModelCreateDto();

		planteCreateDto.setCommun("nom");
		planteCreateDto.setDesc("comme ça");
		planteCreateDto.setScientifique("science");
		planteCreateDto.setArrosage(0);
		planteCreateDto.setEnsoleillement("soleil");
		planteCreateDto.setHumidite(0);
		planteCreateDto.setMax(0);
		planteCreateDto.setMifa("mifa");
		planteCreateDto.setMin(0);
		planteCreateDto.setNegative(null);
		planteCreateDto.setPicture("picture");
		planteCreateDto.setRepiquage(1);
		planteCreateDto.setToxi(true);
		planteCreateDto.setPositive(null);
		planteCreateDto.setSol(Sol.Argileux);

		plantemodel.setNomCommun("nom");
		plantemodel.setNomScientifique("science");
		plantemodel.setDescription("comme ça");
		plantemodel.setIntervalArrosage(0);
		plantemodel.setEnsoleillementOpti("soleil");
		plantemodel.setHumiditeopti(0);
		plantemodel.setTemperatureMax(0);
		plantemodel.setFamille("mifa");
		plantemodel.setTemperatureMin(0);
		plantemodel.setAssoNegative(null);
		plantemodel.setPhoto("picture");
		plantemodel.setRepiquage(1);
		plantemodel.setToxicite(true);
		plantemodel.setAssoPositive(null);
		plantemodel.setSolOpti(Sol.Argileux);

		assertEquals(plantemodel, converter.convertCreateDtoToEntity(planteCreateDto));

	}

	@Override
	@Test
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
		assertNull(converter.convertCreateDtoToEntity(null));

	}

	@Override
	@Test
	public void testConvertingUpdateDtoToEntity_shouldReturnEntity() {
		PlanteModel plantemodel = new PlanteModel();
		PlanteModelUpdateDto planteCreateDto = new PlanteModelUpdateDto();

		planteCreateDto.setCommun("nom");
		planteCreateDto.setDesc("comme ça");
		planteCreateDto.setScientifique("science");
		planteCreateDto.setArrosage(0);
		planteCreateDto.setEnsoleillement("soleil");
		planteCreateDto.setHumidite(0);
		planteCreateDto.setMax(0);
		planteCreateDto.setMifa("mifa");
		planteCreateDto.setMin(0);
		planteCreateDto.setNegative(null);
		planteCreateDto.setPicture("picture");
		planteCreateDto.setRepiquage(1);
		planteCreateDto.setToxi(true);
		planteCreateDto.setPositive(null);
		planteCreateDto.setSol(Sol.Argileux);
		planteCreateDto.setIdentifiant(2);

		plantemodel.setNomCommun("nom");
		plantemodel.setNomScientifique("science");
		plantemodel.setDescription("comme ça");
		plantemodel.setIntervalArrosage(0);
		plantemodel.setEnsoleillementOpti("soleil");
		plantemodel.setHumiditeopti(0);
		plantemodel.setTemperatureMax(0);
		plantemodel.setFamille("mifa");
		plantemodel.setTemperatureMin(0);
		plantemodel.setAssoNegative(null);
		plantemodel.setPhoto("picture");
		plantemodel.setRepiquage(1);
		plantemodel.setToxicite(true);
		plantemodel.setAssoPositive(null);
		plantemodel.setSolOpti(Sol.Argileux);
		plantemodel.setId(2);

		assertEquals(plantemodel, converter.convertUpdateDtoToEntity(planteCreateDto));

	}

	@Override
	@Test
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		assertNull(converter.convertUpdateDtoToEntity(null));

	}

	@Override
	@Test
	public void testConvertingEntityToCreateDto_shouldReturnCreateDto() {
		PlanteModel plantemodel = new PlanteModel();
		PlanteModelCreateDto planteCreateDto = new PlanteModelCreateDto();

		planteCreateDto.setCommun("nom");
		planteCreateDto.setDesc("comme ça");
		planteCreateDto.setScientifique("science");
		planteCreateDto.setArrosage(0);
		planteCreateDto.setEnsoleillement("soleil");
		planteCreateDto.setHumidite(0);
		planteCreateDto.setMax(0);
		planteCreateDto.setMifa("mifa");
		planteCreateDto.setMin(0);
		planteCreateDto.setNegative(null);
		planteCreateDto.setPicture("picture");
		planteCreateDto.setRepiquage(1);
		planteCreateDto.setToxi(true);
		planteCreateDto.setPositive(null);
		planteCreateDto.setSol(Sol.Argileux);

		plantemodel.setNomCommun("nom");
		plantemodel.setNomScientifique("science");
		plantemodel.setDescription("comme ça");
		plantemodel.setIntervalArrosage(0);
		plantemodel.setEnsoleillementOpti("soleil");
		plantemodel.setHumiditeopti(0);
		plantemodel.setTemperatureMax(0);
		plantemodel.setFamille("mifa");
		plantemodel.setTemperatureMin(0);
		plantemodel.setAssoNegative(null);
		plantemodel.setPhoto("picture");
		plantemodel.setRepiquage(1);
		plantemodel.setToxicite(true);
		plantemodel.setAssoPositive(null);
		plantemodel.setSolOpti(Sol.Argileux);


		assertThat(planteCreateDto).isEqualTo(converter.convertEntityToCreateDto(plantemodel));

	}

	@Override
	@Test
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		assertNull(converter.convertEntityToCreateDto(null));

	}

	@Override
	@Test
	public void testConvertingEntityToUpdateDto_shouldReturnUpdateDto() {
		PlanteModel plantemodel = new PlanteModel();
		PlanteModelUpdateDto planteCreateDto = new PlanteModelUpdateDto();

		planteCreateDto.setCommun("nom");
		planteCreateDto.setDesc("comme ça");
		planteCreateDto.setScientifique("science");
		planteCreateDto.setArrosage(0);
		planteCreateDto.setEnsoleillement("soleil");
		planteCreateDto.setHumidite(0);
		planteCreateDto.setMax(0);
		planteCreateDto.setMifa("mifa");
		planteCreateDto.setMin(0);
		planteCreateDto.setNegative(null);
		planteCreateDto.setPicture("picture");
		planteCreateDto.setRepiquage(1);
		planteCreateDto.setToxi(true);
		planteCreateDto.setPositive(null);
		planteCreateDto.setSol(Sol.Argileux);
		planteCreateDto.setIdentifiant(2);

		plantemodel.setNomCommun("nom");
		plantemodel.setNomScientifique("science");
		plantemodel.setDescription("comme ça");
		plantemodel.setIntervalArrosage(0);
		plantemodel.setEnsoleillementOpti("soleil");
		plantemodel.setHumiditeopti(0);
		plantemodel.setTemperatureMax(0);
		plantemodel.setFamille("mifa");
		plantemodel.setTemperatureMin(0);
		plantemodel.setAssoNegative(null);
		plantemodel.setPhoto("picture");
		plantemodel.setRepiquage(1);
		plantemodel.setToxicite(true);
		plantemodel.setAssoPositive(null);
		plantemodel.setSolOpti(Sol.Argileux);
		plantemodel.setId(2);

		assertEquals(planteCreateDto, converter.convertEntityToUpdateDto(plantemodel));

	}

	@Override
	@Test
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		assertNull(converter.convertEntityToUpdateDto(null));

	}

	@Override
	@Test
	public void testConvertingListEntityToCreateDto_shouldReturnCreateDtoList() {
		PlanteModel plantemodel = new PlanteModel();
		PlanteModelCreateDto planteCreateDto = new PlanteModelCreateDto();

		planteCreateDto.setCommun("nom");
		planteCreateDto.setDesc("comme ça");
		planteCreateDto.setScientifique("science");
		planteCreateDto.setArrosage(0);
		planteCreateDto.setEnsoleillement("soleil");
		planteCreateDto.setHumidite(0);
		planteCreateDto.setMax(0);
		planteCreateDto.setMifa("mifa");
		planteCreateDto.setMin(0);
		planteCreateDto.setNegative(null);
		planteCreateDto.setPicture("picture");
		planteCreateDto.setRepiquage(1);
		planteCreateDto.setToxi(true);
		planteCreateDto.setPositive(null);
		planteCreateDto.setSol(Sol.Argileux);

		plantemodel.setNomCommun("nom");
		plantemodel.setNomScientifique("science");
		plantemodel.setDescription("comme ça");
		plantemodel.setIntervalArrosage(0);
		plantemodel.setEnsoleillementOpti("soleil");
		plantemodel.setHumiditeopti(0);
		plantemodel.setTemperatureMax(0);
		plantemodel.setFamille("mifa");
		plantemodel.setTemperatureMin(0);
		plantemodel.setAssoNegative(null);
		plantemodel.setPhoto("picture");
		plantemodel.setRepiquage(1);
		plantemodel.setToxicite(true);
		plantemodel.setAssoPositive(null);
		plantemodel.setSolOpti(Sol.Argileux);
		
		List<PlanteModel> listeEntree = new ArrayList<PlanteModel>();
		listeEntree.add(plantemodel);
		List<PlanteModelCreateDto> listeAttendue = new ArrayList<PlanteModelCreateDto>();
		listeAttendue=converter.convertListEntityToCreateDto(listeEntree);
		
		assertEquals(listeEntree, listeAttendue);

	}

	@Override
	@Test
	public void testConvertingListCreateDtoToEntity_shouldReturnEntityList() {
//		PlanteModel plantemodel = new PlanteModel();
//		PlanteModelCreateDto planteCreateDto = new PlanteModelCreateDto();
//
//		planteCreateDto.setCommun("nom");
//		planteCreateDto.setDesc("comme ça");
//		planteCreateDto.setScientifique("science");
//		planteCreateDto.setArrosage(0);
//		planteCreateDto.setEnsoleillement("soleil");
//		planteCreateDto.setHumidite(0);
//		planteCreateDto.setMax(0);
//		planteCreateDto.setMifa("mifa");
//		planteCreateDto.setMin(0);
//		planteCreateDto.setNegative(null);
//		planteCreateDto.setPeriodes(null);
//		planteCreateDto.setPicture("picture");
//		planteCreateDto.setRepiquage(1);
//		planteCreateDto.setToxi(true);
//		planteCreateDto.setPositive(null);
//		planteCreateDto.setSol(Sol.Argileux);
//
//		plantemodel.setNomCommun("nom");
//		plantemodel.setNomScientifique("science");
//		plantemodel.setDescription("comme ça");
//		plantemodel.setIntervalArrosage(0);
//		plantemodel.setEnsoleillementOpti("soleil");
//		plantemodel.setHumiditeopti(0);
//		plantemodel.setTemperatureMax(0);
//		plantemodel.setFamille("mifa");
//		plantemodel.setTemperatureMin(0);
//		plantemodel.setAssoNegative(null);
//		plantemodel.setDates(null);
//		plantemodel.setPhoto("picture");
//		plantemodel.setRepiquage(1);
//		plantemodel.setToxicite(true);
//		plantemodel.setAssoPositive(null);
//		plantemodel.setSolOpti(Sol.Argileux);
//		
//		List<PlanteModel> listeEntree = new ArrayList<PlanteModel>();
//		List<PlanteModelCreateDto> listeAttendue = new ArrayList<PlanteModelCreateDto>();
//		listeAttendue.add(planteCreateDto);
//		listeEntree=converter.convertListCreateDtoToEntity(listeAttendue);
//		for(int i=0; i<listeAttendue.size()-1;i++) {
//			assertEquals(listeEntree.get, listeAttendue.get(1)));
//		}
//		

	}

	@Override
	@Test
	public void testConvertingListEntityToUpdateDto_shouldReturnUpdateDtoList() {
		PlanteModel plantemodel = new PlanteModel();
		PlanteModelUpdateDto planteCreateDto = new PlanteModelUpdateDto();

		planteCreateDto.setCommun("nom");
		planteCreateDto.setDesc("comme ça");
		planteCreateDto.setScientifique("science");
		planteCreateDto.setArrosage(0);
		planteCreateDto.setEnsoleillement("soleil");
		planteCreateDto.setHumidite(0);
		planteCreateDto.setMax(0);
		planteCreateDto.setMifa("mifa");
		planteCreateDto.setMin(0);
		planteCreateDto.setNegative(null);
		planteCreateDto.setPicture("picture");
		planteCreateDto.setRepiquage(1);
		planteCreateDto.setToxi(true);
		planteCreateDto.setPositive(null);
		planteCreateDto.setSol(Sol.Argileux);
		planteCreateDto.setIdentifiant(2);

		plantemodel.setNomCommun("nom");
		plantemodel.setNomScientifique("science");
		plantemodel.setDescription("comme ça");
		plantemodel.setIntervalArrosage(0);
		plantemodel.setEnsoleillementOpti("soleil");
		plantemodel.setHumiditeopti(0);
		plantemodel.setTemperatureMax(0);
		plantemodel.setFamille("mifa");
		plantemodel.setTemperatureMin(0);
		plantemodel.setAssoNegative(null);
		plantemodel.setPhoto("picture");
		plantemodel.setRepiquage(1);
		plantemodel.setToxicite(true);
		plantemodel.setAssoPositive(null);
		plantemodel.setSolOpti(Sol.Argileux);
		plantemodel.setId(2);
		
		List<PlanteModel> listeEntree = new ArrayList<PlanteModel>();
		listeEntree.add(plantemodel);
		List<PlanteModelUpdateDto> listeAttendue = new ArrayList<PlanteModelUpdateDto>();
		listeAttendue=converter.convertListEntityToUpdateDto(listeEntree);
		
		assertEquals(listeEntree, listeAttendue);

	}

	@Override
	@Test
	public void testConvertingListUpdateDtoToEntity_shouldReturnEntityList() {
		PlanteModel plantemodel = new PlanteModel();
		PlanteModelUpdateDto planteCreateDto = new PlanteModelUpdateDto();

		planteCreateDto.setCommun("nom");
		planteCreateDto.setDesc("comme ça");
		planteCreateDto.setScientifique("science");
		planteCreateDto.setArrosage(0);
		planteCreateDto.setEnsoleillement("soleil");
		planteCreateDto.setHumidite(0);
		planteCreateDto.setMax(0);
		planteCreateDto.setMifa("mifa");
		planteCreateDto.setMin(0);
		planteCreateDto.setNegative(null);
		planteCreateDto.setPicture("picture");
		planteCreateDto.setRepiquage(1);
		planteCreateDto.setToxi(true);
		planteCreateDto.setPositive(null);
		planteCreateDto.setSol(Sol.Argileux);
		planteCreateDto.setIdentifiant(2);

		plantemodel.setNomCommun("nom");
		plantemodel.setNomScientifique("science");
		plantemodel.setDescription("comme ça");
		plantemodel.setIntervalArrosage(0);
		plantemodel.setEnsoleillementOpti("soleil");
		plantemodel.setHumiditeopti(0);
		plantemodel.setTemperatureMax(0);
		plantemodel.setFamille("mifa");
		plantemodel.setTemperatureMin(0);
		plantemodel.setAssoNegative(null);
		plantemodel.setPhoto("picture");
		plantemodel.setRepiquage(1);
		plantemodel.setToxicite(true);
		plantemodel.setAssoPositive(null);
		plantemodel.setSolOpti(Sol.Argileux);
		plantemodel.setId(2);
		
		List<PlanteModel> listeEntree = new ArrayList<PlanteModel>();
		List<PlanteModelUpdateDto> listeAttendue = new ArrayList<PlanteModelUpdateDto>();
		listeAttendue.add(planteCreateDto);
		listeEntree=converter.convertListUpdateDtoToEntity(listeAttendue);
		
		assertEquals(listeEntree, listeAttendue);

	}

	@Override
	@Test
	public void testConvertingPageCreateDtoToEntity_shouldReturnPageOfEntities() {
		// TODO Auto-generated method stub

	}

	@Override
	@Test
	public void testConvertingPageEntityToCreateDto_shouldReturnPageOfCreateDtos() {
		
		PlanteModel plantemodel = new PlanteModel();
		PlanteModel plantemodel2 = new PlanteModel();


		plantemodel.setNomCommun("nom");
		plantemodel.setNomScientifique("science");
		plantemodel.setDescription("comme ça");
		plantemodel.setIntervalArrosage(0);
		plantemodel.setEnsoleillementOpti("soleil");
		plantemodel.setHumiditeopti(0);
		plantemodel.setTemperatureMax(0);
		plantemodel.setFamille("mifa");
		plantemodel.setTemperatureMin(0);
		plantemodel.setAssoNegative(null);
		plantemodel.setPhoto("picture");
		plantemodel.setRepiquage(1);
		plantemodel.setToxicite(true);
		plantemodel.setAssoPositive(null);
		plantemodel.setSolOpti(Sol.Argileux);

		plantemodel2.setNomCommun("nom");
		plantemodel2.setNomScientifique("science");
		plantemodel2.setDescription("comme ça");
		plantemodel2.setIntervalArrosage(0);
		plantemodel2.setEnsoleillementOpti("soleil");
		plantemodel2.setHumiditeopti(0);
		plantemodel2.setTemperatureMax(0);
		plantemodel2.setFamille("mifa");
		plantemodel2.setTemperatureMin(0);
		plantemodel2.setAssoNegative(null);
		plantemodel2.setPhoto("picture");
		plantemodel2.setRepiquage(1);
		plantemodel2.setToxicite(true);
		plantemodel2.setAssoPositive(null);
		plantemodel2.setSolOpti(Sol.Argileux);
		
		List<PlanteModel> liste = new ArrayList<>();
		liste.add(plantemodel);
		liste.add(plantemodel2);
		Page<PlanteModel> page = new PageImpl<PlanteModel>(liste);

		Page<PlanteModelCreateDto> returnedPageDto = converter.convertPageEntityToCreateDto(page);

		assertThat(returnedPageDto).isNotNull();
		assertThat(returnedPageDto.toList()).hasSize(2).hasOnlyElementsOfType(PlanteModelCreateDto.class);
		assertThat(returnedPageDto.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPageDto.getNumber()).isEqualTo(0);
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("commun", plantemodel.getNomCommun());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("scientifique", plantemodel.getNomScientifique());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("desc", plantemodel.getDescription());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("arrosage", plantemodel.getIntervalArrosage());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("ensoleillement", plantemodel.getEnsoleillementOpti());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("humidite", plantemodel.getHumiditeopti());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("max", plantemodel.getTemperatureMax());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("min", plantemodel.getTemperatureMin());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("mifa", plantemodel.getFamille());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("negative", plantemodel.getAssoNegative());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("positive", plantemodel.getAssoPositive());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("picture", plantemodel.getPhoto());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("repiquage", plantemodel.getRepiquage());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("toxi", plantemodel.isToxicite());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("sol", plantemodel.getSolOpti());
		
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("commun", plantemodel2.getNomCommun());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("scientifique", plantemodel2.getNomScientifique());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("desc", plantemodel2.getDescription());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("arrosage", plantemodel2.getIntervalArrosage());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("ensoleillement", plantemodel2.getEnsoleillementOpti());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("humidite", plantemodel2.getHumiditeopti());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("max", plantemodel2.getTemperatureMax());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("min", plantemodel2.getTemperatureMin());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("mifa", plantemodel2.getFamille());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("negative", plantemodel2.getAssoNegative());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("positive", plantemodel2.getAssoPositive());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("picture", plantemodel2.getPhoto());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("repiquage", plantemodel2.getRepiquage());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("toxi", plantemodel2.isToxicite());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("sol", plantemodel2.getSolOpti());
		


	}

	@Override
	@Test
	public void testConvertingPageUpdateDtoToEntity_shouldReturnPageOfEntities() {
		// TODO Auto-generated method stub

	}

	@Override
	@Test
	public void testConvertingPageEntityToUpdateDto_shouldReturnPageOfUpdateDtos() {
		
		PlanteModel plantemodel = new PlanteModel();
		PlanteModel plantemodel2 = new PlanteModel();


		plantemodel.setNomCommun("nom");
		plantemodel.setNomScientifique("science");
		plantemodel.setDescription("comme ça");
		plantemodel.setIntervalArrosage(0);
		plantemodel.setEnsoleillementOpti("soleil");
		plantemodel.setHumiditeopti(0);
		plantemodel.setTemperatureMax(0);
		plantemodel.setFamille("mifa");
		plantemodel.setTemperatureMin(0);
		plantemodel.setAssoNegative(null);
		plantemodel.setPhoto("picture");
		plantemodel.setRepiquage(1);
		plantemodel.setToxicite(true);
		plantemodel.setAssoPositive(null);
		plantemodel.setSolOpti(Sol.Argileux);
		plantemodel.setId(1);

		plantemodel2.setNomCommun("nom");
		plantemodel2.setNomScientifique("science");
		plantemodel2.setDescription("comme ça");
		plantemodel2.setIntervalArrosage(0);
		plantemodel2.setEnsoleillementOpti("soleil");
		plantemodel2.setHumiditeopti(0);
		plantemodel2.setTemperatureMax(0);
		plantemodel2.setFamille("mifa");
		plantemodel2.setTemperatureMin(0);
		plantemodel2.setAssoNegative(null);
		plantemodel2.setPhoto("picture");
		plantemodel2.setRepiquage(1);
		plantemodel2.setToxicite(true);
		plantemodel2.setAssoPositive(null);
		plantemodel2.setSolOpti(Sol.Argileux);
		plantemodel2.setId(2);
		
		List<PlanteModel> liste = new ArrayList<>();
		liste.add(plantemodel);
		liste.add(plantemodel2);
		Page<PlanteModel> page = new PageImpl<PlanteModel>(liste);

		Page<PlanteModelUpdateDto> returnedPageDto = converter.convertPageEntityToUpdateDto(page);

		assertThat(returnedPageDto).isNotNull();
		assertThat(returnedPageDto.toList()).hasSize(2).hasOnlyElementsOfType(PlanteModelUpdateDto.class);
		assertThat(returnedPageDto.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPageDto.getNumber()).isEqualTo(0);
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("commun", plantemodel.getNomCommun());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("scientifique", plantemodel.getNomScientifique());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("desc", plantemodel.getDescription());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("arrosage", plantemodel.getIntervalArrosage());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("ensoleillement", plantemodel.getEnsoleillementOpti());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("humidite", plantemodel.getHumiditeopti());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("max", plantemodel.getTemperatureMax());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("min", plantemodel.getTemperatureMin());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("mifa", plantemodel.getFamille());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("negative", plantemodel.getAssoNegative());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("positive", plantemodel.getAssoPositive());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("picture", plantemodel.getPhoto());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("repiquage", plantemodel.getRepiquage());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("toxi", plantemodel.isToxicite());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("sol", plantemodel.getSolOpti());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("identifiant", plantemodel.getId());
		
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("commun", plantemodel2.getNomCommun());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("scientifique", plantemodel2.getNomScientifique());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("desc", plantemodel2.getDescription());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("arrosage", plantemodel2.getIntervalArrosage());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("ensoleillement", plantemodel2.getEnsoleillementOpti());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("humidite", plantemodel2.getHumiditeopti());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("max", plantemodel2.getTemperatureMax());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("min", plantemodel2.getTemperatureMin());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("mifa", plantemodel2.getFamille());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("negative", plantemodel2.getAssoNegative());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("positive", plantemodel2.getAssoPositive());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("picture", plantemodel2.getPhoto());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("repiquage", plantemodel2.getRepiquage());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("toxi", plantemodel2.isToxicite());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("sol", plantemodel2.getSolOpti());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("identifiant", plantemodel2.getId());
		

	}

	@Override
	@Test
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
		
		List<PlanteModelCreateDto> planteModel = converter.convertListEntityToCreateDto(null);
		assertNotNull(planteModel);
		assertThat(planteModel).isEmpty();
		
	}

	@Override
	@Test
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {
		
		List<PlanteModel> planteModel = converter.convertListCreateDtoToEntity(null);
		assertNotNull(planteModel);
		assertThat(planteModel).isEmpty();
		
	}

	@Override
	@Test
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {
		
		List<PlanteModel> planteModel = converter.convertListUpdateDtoToEntity(null);
		assertNotNull(planteModel);
		assertThat(planteModel).isEmpty();
		
	}

	@Override
	@Test
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {
		
		List<PlanteModel> planteModel = converter.convertListUpdateDtoToEntity(null);
		assertNotNull(planteModel);
		assertThat(planteModel).isEmpty();
		
	}

	@Override
	@Test
	public void testConvertingNullPageCreateDtoToEntity_shouldReturnEmptyPage() {
		
		Page<PlanteModel> planteModel = converter.convertPageCreateDtoToEntity(null);
		assertNotNull(planteModel);
		assertThat(planteModel.getContent()).asList().isEmpty();
		
	}

	@Override
	@Test
	public void testConvertingNullPageEntityToCreateDto_shouldReturnEmptyPage() {
		
		Page<PlanteModelCreateDto> planteModel = converter.convertPageEntityToCreateDto(null);
		assertNotNull(planteModel);
		assertThat(planteModel.getContent()).asList().isEmpty();
		
	}

	@Override
	@Test
	public void testConvertingNullPageUpdateDtoToEntity_shouldReturnEmptyPage() {
		
		Page<PlanteModel> planteModel = converter.convertPageUpdateDtoToEntity(null);
		assertNotNull(planteModel);
		assertThat(planteModel.getContent()).asList().isEmpty();
		
	}

	@Override
	@Test
	public void testConvertingNullPageEntityToUpdateDto_shouldReturnEmptyPage() {
		
		Page<PlanteModelUpdateDto> planteModel = converter.convertPageEntityToUpdateDto(null);
		assertNotNull(planteModel);
		assertThat(planteModel.getContent()).asList().isEmpty();
		
	}

}
