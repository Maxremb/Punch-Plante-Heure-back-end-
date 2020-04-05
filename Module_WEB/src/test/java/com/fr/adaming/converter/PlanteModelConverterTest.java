package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.dto.PlanteModelCreateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.enums.Sol;

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
		planteCreateDto.setPeriodes(null);
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
		plantemodel.setDates(null);
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
		planteCreateDto.setPeriodes(null);
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
		plantemodel.setDates(null);
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
		planteCreateDto.setPeriodes(null);
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
		plantemodel.setDates(null);
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
		planteCreateDto.setPeriodes(null);
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
		plantemodel.setDates(null);
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
		planteCreateDto.setPeriodes(null);
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
		plantemodel.setDates(null);
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
//		PlanteModel plantemodel = new PlanteModel();
//		PlanteModelUpdateDto planteCreateDto = new PlanteModelUpdateDto();
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
//		planteCreateDto.setIdentifiant(2);
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
//		plantemodel.setId(2);
//		
//		List<PlanteModel> listeEntree = new ArrayList<PlanteModel>();
//		listeEntree.add(plantemodel);
//		List<PlanteModelUpdateDto> listeAttendue = new ArrayList<PlanteModelUpdateDto>();
//		listeAttendue=converter.convertListEntityToUpdateDto(listeEntree);
//		
//		assertEquals(listeEntree, listeAttendue);

	}

	@Override
	@Test
	public void testConvertingListUpdateDtoToEntity_shouldReturnEntityList() {
//		PlanteModel plantemodel = new PlanteModel();
//		PlanteModelUpdateDto planteCreateDto = new PlanteModelUpdateDto();
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
//		planteCreateDto.setIdentifiant(2);
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
//		plantemodel.setId(2);
//		
//		List<PlanteModel> listeEntree = new ArrayList<PlanteModel>();
//		List<PlanteModelUpdateDto> listeAttendue = new ArrayList<PlanteModelUpdateDto>();
//		listeAttendue.add(planteCreateDto);
//		listeEntree=converter.convertListUpdateDtoToEntity(listeAttendue);
//		
//		assertEquals(listeEntree, listeAttendue);

	}

	@Override
	@Test
	public void testConvertingPageCreateDtoToEntity_shouldReturnPageOfEntities() {
		// TODO Auto-generated method stub

	}

	@Override
	@Test
	public void testConvertingPageEntityToCreateDto_shouldReturnPageOfCreateDtos() {
		// TODO Auto-generated method stub

	}

	@Override
	@Test
	public void testConvertingPageUpdateDtoToEntity_shouldReturnPageOfEntities() {
		// TODO Auto-generated method stub

	}

	@Override
	@Test
	public void testConvertingPageEntityToUpdateDto_shouldReturnPageOfUpdateDtos() {
		// TODO Auto-generated method stub

	}

}
