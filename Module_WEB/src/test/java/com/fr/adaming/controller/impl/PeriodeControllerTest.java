package com.fr.adaming.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

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
import com.fr.adaming.dto.PeriodeUpdateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.dto.ResponseDto;



/**
 * * Classe test de la couche controller Periode. Elle étend la classe Abstract
 * Test Method et implémente IControlelrTest
 * 
 * @since 0.0.1-SNAPSHOT
 *
 */
@SpringBootTest(classes = ModuleWebApplication.class)
@AutoConfigureMockMvc
public class PeriodeControllerTest extends AbstractTestMethods<PeriodeUpdateDto> implements IControllerTests {

	private static final String BASE_URL = "/periode";

	// paramètres periodeUpdateDTO
	private static final int identifier = 1;
	private static final LocalDate startDate = (LocalDate.parse("2020-01-01"));
	private static final LocalDate endDate = (LocalDate.parse("2020-02-01"));

	// paramtre departement
	private static final int depNum = 74;
	private static final String depName = "Haute-Savoie";
	private static final String depNameSql = "'" + depName + "'";

	// paramètre planteModelUpdate
	private static final String commun = "plante carnivore";
	private static final String commSql = "'" + commun + "'";
	private static final String scientifique = "carnivorus plantus commus";
	private static final String scientifSql = "'" + scientifique + "'";

	@Override
	@Test
	@Sql(statements = "INSERT INTO plante_model (id,nom_commun,nom_scientifique) VALUES (1," + commSql + ","
			+ scientifSql + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql(statements = "INSERT INTO periode (id,date_debut,date_fin) VALUES (1,'2020-01-01','2020-02-01') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql(statements = "INSERT INTO plante_model (id,nom_commun,nom_scientifique) VALUES (1," + commSql + ","
			+ scientifSql + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode (id,date_debut,date_fin,departement_numero_dep,plante_model_id) VALUES (1,'2020-01-01','2020-02-01',"
			+ depNum + ",1) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql(statements = "INSERT INTO plante_model (id,nom_commun,nom_scientifique) VALUES (1," + commSql + ","
			+ scientifSql + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode (id,date_debut,date_fin,departement_numero_dep,plante_model_id) VALUES (1,'2020-01-01','2020-02-01',"
			+ depNum + ",1) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingEntityWithValidId_shouldReturn200() throws Exception {
		String path = BASE_URL + "/1";

		ResponseDto<PeriodeUpdateDto> responseDto = runMockMvc("get", path, 200, PeriodeUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertThat(responseDto.getBody()).hasFieldOrPropertyWithValue("startDate", LocalDate.parse("2020-01-01"));

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
	@Sql(statements = "INSERT INTO plante_model (id,nom_commun,nom_scientifique) VALUES (1," + commSql + ","
			+ scientifSql + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode (id,date_debut,date_fin,departement_numero_dep,plante_model_id) VALUES (1,'2020-01-01','2020-02-01',"
			+ depNum + ",1) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode (id,date_debut,date_fin,departement_numero_dep,plante_model_id) VALUES (2,'2020-01-01','2020-02-01',"
			+ depNum + ",1) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingAllEntity_shouldReturn200() throws Exception {
		String path = BASE_URL + "/all/0";

		ResponseDto<Page<PeriodeUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, PeriodeUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertThat(responseDto.getBody()).hasSize(2);

	}

	// *************************************************
	// TEST READ BY DEPT ID

	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (2, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByDeptIdWithValidParam_ShouldReturn200() throws Exception {
		String path = BASE_URL + "/departement/69?page=0";

		ResponseDto<Page<PeriodeUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, PeriodeUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertThat(responseDto.getBody().toList().size()).isEqualTo(2);
	}

	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (2, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByDeptIdWithInvalidParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/departement/70?page=0";

		ResponseDto<Page<PeriodeUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, PeriodeUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertTrue(responseDto.getBody().toList().isEmpty());
	}

	@Test
	public void TestReadByDeptIdWithNullParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/departement/?page=0";

		String responseAsString = runMockMvcLite("get", path, 400);

		assertThat(responseAsString).isEmpty();
	}

	// *************************************************
	// TEST READ BY PLANTE ID

	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (2, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestREadByPlanteIdWithValidParam_ShouldReturn200() throws Exception {
		String path = BASE_URL + "/plant/1?page=0";

		ResponseDto<Page<PeriodeUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, PeriodeUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertThat(responseDto.getBody().toList().size()).isEqualTo(2);
		assertThat(responseDto.getBody().toList().get(0)).hasFieldOrPropertyWithValue("startDate", LocalDate.parse("2020-04-01"));
	}

	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (2, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestREadByPlanteIdWithinvalidParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/plant/55?page=0";

		ResponseDto<Page<PeriodeUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, PeriodeUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertTrue(responseDto.getBody().toList().isEmpty());
	}

	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (2, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestREadByPlanteIdWithNullParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/plant/?page=0";

		String responseAsString = runMockMvcLite("get", path, 400);

		assertThat(responseAsString).isEmpty();
	}

	// *************************************************
	// TEST READ BY DEPT ID AND PLANTE ID

	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (2, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByDeptIdAndPlantIdWithValidParam_ShouldReturn200() throws Exception {
		String path = BASE_URL + "/alltypes?depId=69&plantId=1";

		ResponseDto<List<PeriodeUpdateDto>> responseDto = runMockMvc4Lists("get", path, 200, PeriodeUpdateDto.class);

		assertThat(responseDto.getBody().size()).isEqualTo(2);

	}

	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (2, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByDeptIdandPlantIdWithinvalidDept_ShouldReturn400() throws Exception {

		String path = BASE_URL + "/alltypes?depId=125&plantId=1";

		ResponseDto<List<PeriodeUpdateDto>> responseDto = runMockMvc4Lists("get", path, 200, PeriodeUpdateDto.class);

		assertThat(responseDto.getBody()).isEmpty();
	}

	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (2, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByDeptIdandPlantIdWithinvalidPlante_ShouldReturn400() throws Exception {

		String path = BASE_URL + "/alltypes?depId=500&plantId=500";

		ResponseDto<List<PeriodeUpdateDto>> responseDto = runMockMvc4Lists("get", path, 200, PeriodeUpdateDto.class);

		assertThat(responseDto.getBody()).isEmpty();
	}

	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (2, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByDeptIdAndPlantIdWithNullParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/alltypes";

		String responseAsString = runMockMvcLite("get", path, 400);

		assertThat(responseAsString).isEmpty();
	}

	// *************************************************
	// TEST READ BY DEPT ID AND PLANTE ID AND TYPE

//	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (2, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestByReadByDeptIdAndPlanteIdAndTypeWithValidParam_ShouldReturn200() throws Exception {
		
//		String path = BASE_URL + "/alltypes?depId=1&plantId=50&type=FLORAISON";
//
//		ResponseDto<PeriodeUpdateDto> responseDto = runMockMvc("get", path, 200, PeriodeUpdateDto.class);
//
//		assertThat(responseDto.getBody()).isNotNull().hasFieldOrPropertyWithValue("startDate", LocalDate.parse("2020-04-01"));
	}

	@Test
	@Sql(statements = "INSERT INTO plante_model (id,nom_commun,nom_scientifique) VALUES (1," + commSql + ","
			+ scientifSql + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode (id,date_debut,date_fin,departement_numero_dep,plante_model_id) VALUES (1,'2020-01-01','2020-02-01',"
			+ depNum + ",1) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode (id,date_debut,date_fin,departement_numero_dep,plante_model_id) VALUES (2,'2020-01-01','2020-02-01',"
			+ depNum + ",1) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestByReadByDeptIdAndPlanteIdAndTypeWithInvalidParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/onetype?depId=500&plantId=500&type=500";

		String responseAsString = runMockMvcLite("get", path, 400);

		assertThat(responseAsString).isEmpty();
	}

	@Test
	@Sql(statements = "INSERT INTO plante_model (id,nom_commun,nom_scientifique) VALUES (1," + commSql + ","
			+ scientifSql + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode (id,date_debut,date_fin,departement_numero_dep,plante_model_id) VALUES (1,'2020-01-01','2020-02-01',"
			+ depNum + ",1) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode (id,date_debut,date_fin,departement_numero_dep,plante_model_id) VALUES (2,'2020-01-01','2020-02-01',"
			+ depNum + ",1) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestByReadByDeptIdAndPlanteIdAndTypeWithNullParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/alltypes?depId=&plantId=&type=";

		String responseAsString = runMockMvcLite("get", path, 400);

		assertThat(responseAsString).isEmpty();
	}

	// *************************************************
	// TEST READ BY JARDIN

	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (2, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByJardinWithValidParam_ShouldReturn200() throws Exception {
		
		String path = BASE_URL + "/jardin?jardinId=1&depId=69";

		ResponseDto<List<PeriodeUpdateDto>> responseDto = runMockMvc4Lists("get", path, 200, PeriodeUpdateDto.class);

		assertThat(responseDto.getBody()).isNotNull();
	}

	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (2, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByJardinWithinvalidParam_ShouldReturn400() throws Exception {
		
		String path = BASE_URL + "/alltypes?depId=125&plantId=12";

		ResponseDto<List<PeriodeUpdateDto>> responseDto = runMockMvc4Lists("get", path, 200, PeriodeUpdateDto.class);

		assertTrue(responseDto.getBody().isEmpty());
	}

	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (2, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByJardinWithnullParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/jardin?jardinId=&depId=&";

		String responseAsString = runMockMvcLite("get", path, 400);

		assertThat(responseAsString).isEmpty();
	}

	// *** Méthodes privés ***

	/**
	 * Méthode permettant de générer un objet de type PeriodeUpdateDto
	 * 
	 * @return L'objet crée dans la méthode
	 */
	private PeriodeUpdateDto makeNewUpdateDto() {
		// Creation du dto qu'on va utiliser pour la requete et aussi la comparaison

		DepartementDto deptDto = new DepartementDto();
		deptDto.setDepNum(depNum);
		deptDto.setName(depName);

		PlanteModelUpdateDto planteDto = new PlanteModelUpdateDto();
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
