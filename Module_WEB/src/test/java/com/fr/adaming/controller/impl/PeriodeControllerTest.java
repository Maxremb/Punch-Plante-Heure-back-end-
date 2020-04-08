package com.fr.adaming.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.controller.AbstractTestMethods;
import com.fr.adaming.controller.IControllerTests;
import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.PeriodeUpdateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest(classes = ModuleWebApplication.class)
@AutoConfigureMockMvc
public class PeriodeControllerTest extends AbstractTestMethods<PeriodeUpdateDto> implements IControllerTests {

	private static final String BASE_URL = "/periode";
	
	// paramètres periodeUpdateDTO
	private static final int identifier =1;
	private static final LocalDate startDate = (LocalDate.parse("2020-01-01"));
	private static final LocalDate endDate = (LocalDate.parse("2020-02-01"));

	
	//paramtre departement
	private static final int depNum = 74;
	private static final String depName = "Haute-Savoie";
	private static final String depNameSql = "'" + depName + "'";
	
	
	//paramètre planteModelUpdate
	private static final String commun = "plante carnivore";
	private static final String commSql = "'"+commun+"'";
	private static final String scientifique = "carnivorus plantus commus";
	private static final String scientifSql = "'"+scientifique +"'";
	
	
	
	@Override
	@Test
	@Sql( statements = "INSERT INTO plante_model (id,nom_commun,nom_scientifique) VALUES (1,"+ commSql+","+ scientifSql+")",executionPhase =ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
	+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql (statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql (statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEntityWithValidBody_shouldReturn200() throws Exception {
		PeriodeUpdateDto dto = makeNewUpdateDto();
		
		ResponseDto<PeriodeUpdateDto> responseDto = runMockMvc("post", BASE_URL, 200, dto, PeriodeUpdateDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Success", responseDto.getMessage());
		assertNotNull(responseDto.getBody());
		
	}

	@Override
	@Test
	public void testCreatingEntityWithInvalidBody_shouldReturn400() throws Exception {
		PeriodeUpdateDto dto = new PeriodeUpdateDto();

		String responseAsString = runMockMvcLite("post", BASE_URL, 400, dto);

		assertThat(responseAsString).isEmpty();

	}

	@Override
	@Test
	@Sql (statements = "INSERT INTO periode (id,date_debut,date_fin) VALUES (1,'2020-01-01','2020-02-01') ",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM periode",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeletingEntityWithValidId_shouldReturn200() throws Exception {
		
		String path = BASE_URL + "/1";
		
		ResponseDto<PeriodeUpdateDto> responseDto = runMockMvc("delete", path, 200, PeriodeUpdateDto.class);

		assertThat(responseDto).isNotNull();
		assertThat(responseDto.isError()).isFalse();

	}

	@Override
	@Test
	public void testDeletingEntityWithInvalidId_shouldReturn400() throws Exception {
		String path = BASE_URL + "/1";
		
		ResponseDto<PeriodeUpdateDto> responseDto = runMockMvc("delete", path, 400, PeriodeUpdateDto.class);

		assertThat(responseDto).isNotNull();
		assertThat(responseDto.isError()).isTrue();

	}

	@Override
	@Test
	@Sql( statements = "INSERT INTO plante_model (id,nom_commun,nom_scientifique) VALUES (1,"+ commSql+","+ scientifSql+")",executionPhase =ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
	+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "INSERT INTO periode (id,date_debut,date_fin,departement_numero_dep,plante_model_id) VALUES (1,'2020-01-01','2020-02-01',"+depNum+",1) ",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql (statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql (statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdatingEntityWithValidId_shouldReturn200() throws Exception {
		PeriodeUpdateDto dto = makeNewUpdateDto();

		dto.setEndDate(LocalDate.parse("2021-10-01"));

		ResponseDto<PeriodeUpdateDto> responseDto = runMockMvc("put", BASE_URL, 200, dto, PeriodeUpdateDto.class);

		assertThat(responseDto.getBody()).hasFieldOrPropertyWithValue("endDate", dto.getEndDate());
		assertThat(responseDto.isError()).isFalse();

	}

	@Override
	@Test
	public void testUpdatingEntityWithInvalidId_shouldReturn400() throws Exception {
		PeriodeUpdateDto dto = makeNewUpdateDto();

		dto.setEndDate(LocalDate.parse("2021-10-01"));

		ResponseDto<PeriodeUpdateDto> responseDto = runMockMvc("put", BASE_URL, 400, dto, PeriodeUpdateDto.class);

		assertThat(responseDto.getBody()).isNull();
		assertThat(responseDto.isError()).isTrue();

	}

	@Override
	@Test
	@Sql( statements = "INSERT INTO plante_model (id,nom_commun,nom_scientifique) VALUES (1,"+ commSql+","+ scientifSql+")",executionPhase =ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
	+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "INSERT INTO periode (id,date_debut,date_fin,departement_numero_dep,plante_model_id) VALUES (1,'2020-01-01','2020-02-01',"+depNum+",1) ",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql (statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql (statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingEntityWithValidId_shouldReturn200() throws Exception {
		String path = BASE_URL + "/1";

		ResponseDto<PeriodeUpdateDto> responseDto = runMockMvc("get", path, 200, PeriodeUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertThat(responseDto.getBody()).hasFieldOrPropertyWithValue("startDate",LocalDate.parse("2020-01-01"));

	}

	@Override
	@Test
	public void testReadingEntityWithInvalidId_shouldReturn400() throws Exception {
		
		String path = BASE_URL + "/1";

		ResponseDto<PeriodeUpdateDto> responseDto = runMockMvc("get", path, 400, PeriodeUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();

	}

	@Override
	@Test
	@Sql( statements = "INSERT INTO plante_model (id,nom_commun,nom_scientifique) VALUES (1,"+ commSql+","+ scientifSql+")",executionPhase =ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
	+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "INSERT INTO periode (id,date_debut,date_fin,departement_numero_dep,plante_model_id) VALUES (1,'2020-01-01','2020-02-01',"+depNum+",1) ",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "INSERT INTO periode (id,date_debut,date_fin,departement_numero_dep,plante_model_id) VALUES (2,'2020-01-01','2020-02-01',"+depNum+",1) ",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql (statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql (statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingAllEntity_shouldReturn200() throws Exception {
		String path = BASE_URL + "/all/0";

		ResponseDto<Page<PeriodeUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, PeriodeUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertThat(responseDto.getBody()).hasSize(2);

	}

	// *** Méthodes privés ***

	private PeriodeUpdateDto makeNewUpdateDto() {
		// Creation du dto qu'on va utiliser pour la requete et aussi la comparaison

		DepartementDto deptDto= new DepartementDto();
		deptDto.setDepNum(depNum);
		deptDto.setName(depName);
		
		PlanteModelUpdateDto planteDto= new PlanteModelUpdateDto();
		planteDto.setIdentifiant(identifier);
		planteDto.setCommun(commun);
		planteDto.setScientifique(scientifique);
		
		PeriodeUpdateDto dto = new PeriodeUpdateDto();
		dto.setIdentity(identifier);
		dto.setEndDate(endDate);
		dto.setStartDate(startDate);
		dto.setCounty(deptDto);
		dto.setPlantSpecies(planteDto);
		
		return dto;

	}

}
