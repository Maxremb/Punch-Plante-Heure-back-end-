package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.PeriodeCreateDto;
import com.fr.adaming.dto.PeriodeUpdateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Periode;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.enums.Sol;
import com.fr.adaming.enums.TypePeriod;

/**
 * Classe de tests du converter Periode Implémente l'interface IConverterTests
 * 
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@SpringBootTest(classes = ModuleWebApplication.class)
public class PeriodeConverterTests implements IConverterTests {

	@Autowired
	private PeriodeConverter converter;

	@Autowired
	private DepartementConverter deptConvert;

	@Autowired
	private PlanteModelConverter planteConvert;

	@Test
	@Override
	public void testConvertingCreateDtoToEntity_shouldReturnEntity() {
		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");

		PlanteModelUpdateDto planteDto = new PlanteModelUpdateDto();
		planteDto.setCommun("nom");
		planteDto.setDesc("comme ça");
		planteDto.setScientifique("science");
		planteDto.setArrosage(0);
		planteDto.setEnsoleillement("soleil");
		planteDto.setHumidite(0);
		planteDto.setMax(0);
		planteDto.setMifa("mifa");
		planteDto.setMin(0);
		planteDto.setNegative(null);
		planteDto.setPicture("picture");
		planteDto.setRepiquage(1);
		planteDto.setToxi(true);
		planteDto.setPositive(null);
		planteDto.setSol(Sol.Argileux);

		PeriodeCreateDto dto = new PeriodeCreateDto();

		dto.setCounty(depDto);
		dto.setPeriodType(TypePeriod.FLORAISON);
		dto.setStartDate(LocalDate.parse("2020-01-27"));
		dto.setEndDate(LocalDate.parse("2020-04-15"));
		dto.setPlantSpecies(planteDto);

		Periode returnedPeriode = converter.convertCreateDtoToEntity(dto);

		assertThat(returnedPeriode.getClass()).isEqualTo(Periode.class);
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("type", dto.getPeriodType());
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("dateDebut", dto.getStartDate());
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("dateFin", dto.getEndDate());
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("departement",
				deptConvert.convertDtoToEntity(dto.getCounty()));
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("planteModel",
				planteConvert.convertUpdateDtoToEntity(dto.getPlantSpecies()));

	}

	@Test
	@Override
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
		Periode returnedPeriode = converter.convertCreateDtoToEntity(null);

		assertThat(returnedPeriode).isNull();

	}

	@Test
	@Override
	public void testConvertingUpdateDtoToEntity_shouldReturnEntity() {
		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");

		PlanteModelUpdateDto planteDto = new PlanteModelUpdateDto();
		planteDto.setCommun("nom");
		planteDto.setDesc("comme ça");
		planteDto.setScientifique("science");
		planteDto.setArrosage(0);
		planteDto.setEnsoleillement("soleil");
		planteDto.setHumidite(0);
		planteDto.setMax(0);
		planteDto.setMifa("mifa");
		planteDto.setMin(0);
		planteDto.setNegative(null);
		planteDto.setPicture("picture");
		planteDto.setRepiquage(1);
		planteDto.setToxi(true);
		planteDto.setPositive(null);
		planteDto.setSol(Sol.Argileux);

		PeriodeUpdateDto dto = new PeriodeUpdateDto();
		dto.setCounty(depDto);
		dto.setStartDate(LocalDate.parse("2020-01-27"));
		dto.setEndDate(LocalDate.parse("2020-04-15"));
		dto.setPeriodType(TypePeriod.FLORAISON);
		dto.setPlantSpecies(planteDto);

		Periode returnedPeriode = converter.convertUpdateDtoToEntity(dto);

		assertThat(returnedPeriode.getClass()).isEqualTo(Periode.class);
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("type", dto.getPeriodType());
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("dateDebut", dto.getStartDate());
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("dateFin", dto.getEndDate());
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("departement",
				deptConvert.convertDtoToEntity(dto.getCounty()));
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("planteModel",
				planteConvert.convertUpdateDtoToEntity(dto.getPlantSpecies()));

	}

	@Test
	@Override
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		Periode returnedPeriode = converter.convertUpdateDtoToEntity(null);

		assertThat(returnedPeriode).isNull();

	}

	@Test
	@Override
	public void testConvertingEntityToCreateDto_shouldReturnCreateDto() {
		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");

		Departement dept = new Departement();
		dept.setNom("nomDep4Test");
		dept.setNumeroDep(73);

		PlanteModel plante = new PlanteModel();
		plante.setDescription("comme ça");
		plante.setEnsoleillementOpti("soleil");
		plante.setFamille("mifa");
		plante.setHumiditeopti(0);
		plante.setNomCommun("nom");
		plante.setNomScientifique("scienti");
		plante.setIntervalArrosage(0);
		plante.setAssoNegative(null);
		plante.setAssoPositive(null);
		plante.setRepiquage(1);
		plante.setPhoto("picture");
		plante.setTemperatureMax(10);
		plante.setTemperatureMin(0);
		plante.setSolOpti(Sol.Argileux);
		plante.setToxicite(true);

		Periode periode = new Periode();

		periode.setDateDebut(LocalDate.parse("2020-01-27"));
		periode.setDateFin(LocalDate.parse("2020-04-15"));
		periode.setDepartement(dept);
		periode.setPlanteModel(plante);
		periode.setType(TypePeriod.FLORAISON);

		PeriodeCreateDto returnedPeriode = converter.convertEntityToCreateDto(periode);

		assertThat(returnedPeriode.getClass()).isEqualTo(PeriodeUpdateDto.class);
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("startDate", periode.getDateDebut());
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("endDate", periode.getDateFin());
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("periodType", periode.getType());
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("county",
				deptConvert.convertEntityToDto(periode.getDepartement()));
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("plantSpecies",
				planteConvert.convertEntityToUpdateDto(periode.getPlanteModel()));

	}

	@Test
	@Override
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		PeriodeCreateDto returnedPeriode = converter.convertEntityToCreateDto(null);

		assertThat(returnedPeriode).isNull();

	}

	@Test
	@Override
	public void testConvertingEntityToUpdateDto_shouldReturnUpdateDto() {
		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");

		Departement dept = new Departement();
		dept.setNom("nomDep4Test");
		dept.setNumeroDep(73);

		PlanteModel plante = new PlanteModel();
		plante.setDescription("comme ça");
		plante.setEnsoleillementOpti("soleil");
		plante.setFamille("mifa");
		plante.setHumiditeopti(0);
		plante.setNomCommun("nom");
		plante.setNomScientifique("scienti");
		plante.setIntervalArrosage(0);
		plante.setAssoNegative(null);
		plante.setAssoPositive(null);
		plante.setRepiquage(1);
		plante.setPhoto("picture");
		plante.setTemperatureMax(10);
		plante.setTemperatureMin(0);
		plante.setSolOpti(Sol.Argileux);
		plante.setToxicite(true);

		Periode periode = new Periode();

		periode.setDateDebut(LocalDate.parse("2020-01-27"));
		periode.setDateFin(LocalDate.parse("2020-04-15"));
		periode.setDepartement(dept);
		periode.setPlanteModel(plante);
		periode.setType(TypePeriod.FLORAISON);

		PeriodeUpdateDto returnedPeriode = converter.convertEntityToUpdateDto(periode);

		assertThat(returnedPeriode.getClass()).isEqualTo(PeriodeUpdateDto.class);
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("startDate", periode.getDateDebut());
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("endDate", periode.getDateFin());
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("periodType", periode.getType());
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("county",
				deptConvert.convertEntityToDto(periode.getDepartement()));
		assertThat(returnedPeriode).hasFieldOrPropertyWithValue("plantSpecies",
				planteConvert.convertEntityToUpdateDto(periode.getPlanteModel()));

	}

	@Test
	@Override
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		PeriodeUpdateDto returnedPeriode = converter.convertEntityToUpdateDto(null);

		assertThat(returnedPeriode).isNull();

	}

	@Test
	@Override
	public void testConvertingListEntityToCreateDto_shouldReturnCreateDtoList() {
		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");

		Departement dept = new Departement();
		dept.setNom("nomDep4Test");
		dept.setNumeroDep(73);

		PlanteModel plante = new PlanteModel();
		plante.setDescription("comme ça");
		plante.setEnsoleillementOpti("soleil");
		plante.setFamille("mifa");
		plante.setHumiditeopti(0);
		plante.setNomCommun("nom");
		plante.setNomScientifique("scienti");
		plante.setIntervalArrosage(0);
		plante.setAssoNegative(null);
		plante.setAssoPositive(null);
		plante.setRepiquage(1);
		plante.setPhoto("picture");
		plante.setTemperatureMax(10);
		plante.setTemperatureMin(0);
		plante.setSolOpti(Sol.Argileux);
		plante.setToxicite(true);

		Periode periode1 = new Periode();

		periode1.setDateDebut(LocalDate.parse("2020-01-27"));
		periode1.setDateFin(LocalDate.parse("2020-04-15"));
		periode1.setDepartement(dept);
		periode1.setPlanteModel(plante);
		periode1.setType(TypePeriod.FLORAISON);

		Periode periode2 = new Periode();
		periode2.setDateDebut(LocalDate.parse("2020-04-15"));
		periode2.setDateFin(LocalDate.parse("2020-05-11"));
		periode2.setDepartement(dept);
		periode2.setPlanteModel(plante);
		periode2.setType(TypePeriod.REMPOTAGE);

		List<Periode> listePeriodes = new ArrayList<>();
		listePeriodes.add(periode1);
		listePeriodes.add(periode2);

		List<PeriodeCreateDto> returnedList = converter.convertListEntityToCreateDto(listePeriodes);

		assertThat(returnedList.size()).isEqualTo(2);
		assertThat(returnedList.get(0)).hasFieldOrPropertyWithValue("startDate", periode1.getDateDebut());
		assertThat(returnedList.get(1)).hasFieldOrPropertyWithValue("startDate", periode2.getDateDebut());

	}

	@Test
	@Override
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
		List<PeriodeCreateDto> returnedList = converter.convertListEntityToCreateDto(null);

		assertThat(returnedList).isEmpty();

	}

	@Test
	@Override
	public void testConvertingListCreateDtoToEntity_shouldReturnEntityList() {
		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");

		PlanteModelUpdateDto planteDto = new PlanteModelUpdateDto();
		planteDto.setCommun("nom");
		planteDto.setDesc("comme ça");
		planteDto.setScientifique("science");
		planteDto.setArrosage(0);
		planteDto.setEnsoleillement("soleil");
		planteDto.setHumidite(0);
		planteDto.setMax(0);
		planteDto.setMifa("mifa");
		planteDto.setMin(0);
		planteDto.setNegative(null);
		planteDto.setPicture("picture");
		planteDto.setRepiquage(1);
		planteDto.setToxi(true);
		planteDto.setPositive(null);
		planteDto.setSol(Sol.Argileux);

		PeriodeCreateDto dto1 = new PeriodeCreateDto();

		dto1.setCounty(depDto);
		dto1.setPeriodType(TypePeriod.FLORAISON);
		dto1.setStartDate(LocalDate.parse("2020-01-27"));
		dto1.setEndDate(LocalDate.parse("2020-04-15"));
		dto1.setPlantSpecies(planteDto);

		PeriodeCreateDto dto2 = new PeriodeCreateDto();

		dto2.setCounty(depDto);
		dto2.setPeriodType(TypePeriod.REMPOTAGE);
		dto2.setStartDate(LocalDate.parse("2020-04-15"));
		dto2.setEndDate(LocalDate.parse("2020-05-11"));
		dto2.setPlantSpecies(planteDto);

		List<PeriodeCreateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);

		List<Periode> listePeriode = converter.convertListCreateDtoToEntity(listeDto);

		assertThat(listePeriode.size()).isEqualTo(2);
		assertThat(listePeriode.get(0)).hasFieldOrPropertyWithValue("type", dto1.getPeriodType());
		assertThat(listePeriode.get(1)).hasFieldOrPropertyWithValue("type", dto2.getPeriodType());

	}

	@Test
	@Override
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {
		List<Periode> listePeriode = converter.convertListCreateDtoToEntity(null);

		assertThat(listePeriode).isEmpty();

	}

	@Test
	@Override
	public void testConvertingListEntityToUpdateDto_shouldReturnUpdateDtoList() {
		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");

		Departement dept = new Departement();
		dept.setNom("nomDep4Test");
		dept.setNumeroDep(73);

		PlanteModel plante = new PlanteModel();
		plante.setDescription("comme ça");
		plante.setEnsoleillementOpti("soleil");
		plante.setFamille("mifa");
		plante.setHumiditeopti(0);
		plante.setNomCommun("nom");
		plante.setNomScientifique("scienti");
		plante.setIntervalArrosage(0);
		plante.setAssoNegative(null);
		plante.setAssoPositive(null);
		plante.setRepiquage(1);
		plante.setPhoto("picture");
		plante.setTemperatureMax(10);
		plante.setTemperatureMin(0);
		plante.setSolOpti(Sol.Argileux);
		plante.setToxicite(true);

		Periode periode1 = new Periode();

		periode1.setDateDebut(LocalDate.parse("2020-01-27"));
		periode1.setDateFin(LocalDate.parse("2020-04-15"));
		periode1.setDepartement(dept);
		periode1.setPlanteModel(plante);
		periode1.setType(TypePeriod.FLORAISON);

		Periode periode2 = new Periode();

		periode2.setDateDebut(LocalDate.parse("2020-04-15"));
		periode2.setDateFin(LocalDate.parse("2020-05-11"));
		periode2.setDepartement(dept);
		periode2.setPlanteModel(plante);
		periode2.setType(TypePeriod.REMPOTAGE);

		List<Periode> listePeriode = new ArrayList<>();
		listePeriode.add(periode1);
		listePeriode.add(periode2);

		List<PeriodeUpdateDto> returnedList = converter.convertListEntityToUpdateDto(listePeriode);

		assertThat(returnedList.size()).isEqualTo(2);
		assertThat(returnedList.get(0)).hasFieldOrPropertyWithValue("periodType", periode1.getType());
		assertThat(returnedList.get(1)).hasFieldOrPropertyWithValue("periodType", periode2.getType());

	}

	@Test
	@Override
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {
		List<PeriodeUpdateDto> returnedList = converter.convertListEntityToUpdateDto(null);

		assertThat(returnedList).isEmpty();

	}

	@Test
	@Override
	public void testConvertingListUpdateDtoToEntity_shouldReturnEntityList() {
		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");

		PlanteModelUpdateDto planteDto = new PlanteModelUpdateDto();
		planteDto.setCommun("nom");
		planteDto.setDesc("comme ça");
		planteDto.setScientifique("science");
		planteDto.setArrosage(0);
		planteDto.setEnsoleillement("soleil");
		planteDto.setHumidite(0);
		planteDto.setMax(0);
		planteDto.setMifa("mifa");
		planteDto.setMin(0);
		planteDto.setNegative(null);
		planteDto.setPicture("picture");
		planteDto.setRepiquage(1);
		planteDto.setToxi(true);
		planteDto.setPositive(null);
		planteDto.setSol(Sol.Argileux);

		PeriodeUpdateDto dto1 = new PeriodeUpdateDto();
		dto1.setCounty(depDto);
		dto1.setStartDate(LocalDate.parse("2020-01-27"));
		dto1.setEndDate(LocalDate.parse("2020-04-15"));
		dto1.setPeriodType(TypePeriod.FLORAISON);
		dto1.setPlantSpecies(planteDto);

		PeriodeUpdateDto dto2 = new PeriodeUpdateDto();
		dto2.setCounty(depDto);
		dto2.setStartDate(LocalDate.parse("2020-01-27"));
		dto2.setEndDate(LocalDate.parse("2020-04-15"));
		dto2.setPeriodType(TypePeriod.FLORAISON);
		dto2.setPlantSpecies(planteDto);

		List<PeriodeUpdateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);

		List<Periode> returnedList = converter.convertListUpdateDtoToEntity(listeDto);

		assertThat(returnedList.size()).isEqualTo(2);
		assertThat(returnedList.get(0)).hasFieldOrPropertyWithValue("dateDebut", dto1.getStartDate());
		assertThat(returnedList.get(1)).hasFieldOrPropertyWithValue("dateDebut", dto2.getStartDate());

	}

	@Test
	@Override
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {
		List<Periode> returnedList = converter.convertListUpdateDtoToEntity(null);

		assertThat(returnedList).isEmpty();

	}

	@Test
	@Override
	public void testConvertingPageCreateDtoToEntity_shouldReturnPageOfEntities() {
		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");

		PlanteModelUpdateDto planteDto = new PlanteModelUpdateDto();
		planteDto.setCommun("nom");
		planteDto.setDesc("comme ça");
		planteDto.setScientifique("science");
		planteDto.setArrosage(0);
		planteDto.setEnsoleillement("soleil");
		planteDto.setHumidite(0);
		planteDto.setMax(0);
		planteDto.setMifa("mifa");
		planteDto.setMin(0);
		planteDto.setNegative(null);
		planteDto.setPicture("picture");
		planteDto.setRepiquage(1);
		planteDto.setToxi(true);
		planteDto.setPositive(null);
		planteDto.setSol(Sol.Argileux);

		PeriodeCreateDto dto1 = new PeriodeCreateDto();

		dto1.setCounty(depDto);
		dto1.setPeriodType(TypePeriod.FLORAISON);
		dto1.setStartDate(LocalDate.parse("2020-01-27"));
		dto1.setEndDate(LocalDate.parse("2020-04-15"));
		dto1.setPlantSpecies(planteDto);

		PeriodeCreateDto dto2 = new PeriodeCreateDto();

		dto2.setCounty(depDto);
		dto2.setPeriodType(TypePeriod.REMPOTAGE);
		dto2.setStartDate(LocalDate.parse("2020-04-15"));
		dto2.setEndDate(LocalDate.parse("2020-05-11"));
		dto2.setPlantSpecies(planteDto);

		List<PeriodeCreateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);

		Page<PeriodeCreateDto> pageDto = new PageImpl<>(listeDto);

		Page<Periode> returnedPage = converter.convertPageCreateDtoToEntity(pageDto);

		assertThat(returnedPage.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPage.getNumber()).isEqualTo(0);
		assertThat(returnedPage.toList().get(0)).hasFieldOrPropertyWithValue("dateDebut", dto1.getStartDate());
		assertThat(returnedPage.toList().get(1)).hasFieldOrPropertyWithValue("dateDebut", dto2.getStartDate());

	}

	@Test
	@Override
	public void testConvertingNullPageCreateDtoToEntity_shouldReturnEmptyPage() {
		Page<Periode> returnedPage = converter.convertPageCreateDtoToEntity(null);

		assertThat(returnedPage).isEmpty();
	}

	@Test
	@Override
	public void testConvertingPageEntityToCreateDto_shouldReturnPageOfCreateDtos() {
		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");

		Departement dept = new Departement();
		dept.setNom("nomDep4Test");
		dept.setNumeroDep(73);

		PlanteModel plante = new PlanteModel();
		plante.setDescription("comme ça");
		plante.setEnsoleillementOpti("soleil");
		plante.setFamille("mifa");
		plante.setHumiditeopti(0);
		plante.setNomCommun("nom");
		plante.setNomScientifique("scienti");
		plante.setIntervalArrosage(0);
		plante.setAssoNegative(null);
		plante.setAssoPositive(null);
		plante.setRepiquage(1);
		plante.setPhoto("picture");
		plante.setTemperatureMax(10);
		plante.setTemperatureMin(0);
		plante.setSolOpti(Sol.Argileux);
		plante.setToxicite(true);

		Periode periode1 = new Periode();

		periode1.setDateDebut(LocalDate.parse("2020-01-27"));
		periode1.setDateFin(LocalDate.parse("2020-04-15"));
		periode1.setDepartement(dept);
		periode1.setPlanteModel(plante);
		periode1.setType(TypePeriod.FLORAISON);

		Periode periode2 = new Periode();
		periode2.setDateDebut(LocalDate.parse("2020-04-15"));
		periode2.setDateFin(LocalDate.parse("2020-05-11"));
		periode2.setDepartement(dept);
		periode2.setPlanteModel(plante);
		periode2.setType(TypePeriod.REMPOTAGE);

		List<Periode> listePeriodes = new ArrayList<>();
		listePeriodes.add(periode1);
		listePeriodes.add(periode2);

		Page<Periode> pagePeriode = new PageImpl<Periode>(listePeriodes);

		Page<PeriodeCreateDto> returnedPage = converter.convertPageEntityToCreateDto(pagePeriode);

		assertThat(returnedPage.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPage.getNumber()).isEqualTo(0);
		assertThat(returnedPage.toList().get(0)).hasFieldOrPropertyWithValue("startDate", periode1.getDateDebut());
		assertThat(returnedPage.toList().get(1)).hasFieldOrPropertyWithValue("startDate", periode2.getDateDebut());

	}

	@Test
	@Override
	public void testConvertingNullPageEntityToCreateDto_shouldReturnEmptyPage() {
		Page<PeriodeCreateDto> returnedPage = converter.convertPageEntityToCreateDto(null);

		assertThat(returnedPage).isEmpty();

	}

	@Test
	@Override
	public void testConvertingPageUpdateDtoToEntity_shouldReturnPageOfEntities() {
		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");

		PlanteModelUpdateDto planteDto = new PlanteModelUpdateDto();
		planteDto.setCommun("nom");
		planteDto.setDesc("comme ça");
		planteDto.setScientifique("science");
		planteDto.setArrosage(0);
		planteDto.setEnsoleillement("soleil");
		planteDto.setHumidite(0);
		planteDto.setMax(0);
		planteDto.setMifa("mifa");
		planteDto.setMin(0);
		planteDto.setNegative(null);
		planteDto.setPicture("picture");
		planteDto.setRepiquage(1);
		planteDto.setToxi(true);
		planteDto.setPositive(null);
		planteDto.setSol(Sol.Argileux);

		PeriodeUpdateDto dto1 = new PeriodeUpdateDto();
		dto1.setCounty(depDto);
		dto1.setStartDate(LocalDate.parse("2020-01-27"));
		dto1.setEndDate(LocalDate.parse("2020-04-15"));
		dto1.setPeriodType(TypePeriod.FLORAISON);
		dto1.setPlantSpecies(planteDto);

		PeriodeUpdateDto dto2 = new PeriodeUpdateDto();
		dto2.setCounty(depDto);
		dto2.setStartDate(LocalDate.parse("2020-01-27"));
		dto2.setEndDate(LocalDate.parse("2020-04-15"));
		dto2.setPeriodType(TypePeriod.FLORAISON);
		dto2.setPlantSpecies(planteDto);

		List<PeriodeUpdateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);

		Page<PeriodeUpdateDto> pageUpdate = new PageImpl<>(listeDto);

		Page<Periode> returnedPage = converter.convertPageUpdateDtoToEntity(pageUpdate);

		assertThat(returnedPage.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPage.getNumber()).isEqualTo(0);
		assertThat(returnedPage.toList().get(0)).hasFieldOrPropertyWithValue("dateDebut", dto1.getStartDate());
		assertThat(returnedPage.toList().get(1)).hasFieldOrPropertyWithValue("dateDebut", dto2.getStartDate());

	}

	@Test
	@Override
	public void testConvertingNullPageUpdateDtoToEntity_shouldReturnEmptyPage() {

		Page<Periode> periode = converter.convertPageUpdateDtoToEntity(null);

		assertNotNull(periode);
		assertThat(periode.getContent()).asList().isEmpty();

	}

	@Test
	@Override
	public void testConvertingPageEntityToUpdateDto_shouldReturnPageOfUpdateDtos() {
		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");

		Departement dept = new Departement();
		dept.setNom("nomDep4Test");
		dept.setNumeroDep(73);

		PlanteModel plante = new PlanteModel();
		plante.setDescription("comme ça");
		plante.setEnsoleillementOpti("soleil");
		plante.setFamille("mifa");
		plante.setHumiditeopti(0);
		plante.setNomCommun("nom");
		plante.setNomScientifique("scienti");
		plante.setIntervalArrosage(0);
		plante.setAssoNegative(null);
		plante.setAssoPositive(null);
		plante.setRepiquage(1);
		plante.setPhoto("picture");
		plante.setTemperatureMax(10);
		plante.setTemperatureMin(0);
		plante.setSolOpti(Sol.Argileux);
		plante.setToxicite(true);

		Periode periode1 = new Periode();

		periode1.setDateDebut(LocalDate.parse("2020-01-27"));
		periode1.setDateFin(LocalDate.parse("2020-04-15"));
		periode1.setDepartement(dept);
		periode1.setPlanteModel(plante);
		periode1.setType(TypePeriod.FLORAISON);

		Periode periode2 = new Periode();

		periode2.setDateDebut(LocalDate.parse("2020-04-15"));
		periode2.setDateFin(LocalDate.parse("2020-05-11"));
		periode2.setDepartement(dept);
		periode2.setPlanteModel(plante);
		periode2.setType(TypePeriod.REMPOTAGE);

		List<Periode> listePeriode = new ArrayList<>();
		listePeriode.add(periode1);
		listePeriode.add(periode2);

		Page<Periode> pagePeriode = new PageImpl<>(listePeriode);

		Page<PeriodeUpdateDto> returnedPage = converter.convertPageEntityToUpdateDto(pagePeriode);

		assertThat(returnedPage.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPage.getNumber()).isEqualTo(0);
		assertThat(returnedPage.toList().get(0)).hasFieldOrPropertyWithValue("startDate", periode1.getDateDebut());
		assertThat(returnedPage.toList().get(1)).hasFieldOrPropertyWithValue("startDate", periode2.getDateDebut());

	}

	@Test
	@Override
	public void testConvertingNullPageEntityToUpdateDto_shouldReturnEmptyPage() {
		Page<PeriodeUpdateDto> returnedPage = converter.convertPageEntityToUpdateDto(null);

		assertThat(returnedPage).isEmpty();
	}

}
