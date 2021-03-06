package com.fr.adaming.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest(classes = ModuleWebApplication.class)
@AutoConfigureMockMvc
public class DepartementControllerTests extends AbstractTestMethods<DepartementDto> implements IControllerTests {

	// Paramètres par défaut

	private static final String BASE_URL = "/departement";

	private DepartementDto makeNewDepartementDto() {
		// Creation du dto qu'on va utiliser pour la requete et aussi la comparaison
		DepartementDto dto = new DepartementDto();

		dto.setDepNum(1);
		dto.setName("nom4Test");

		return dto;
	}

	@Override
	@Test
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEntityWithValidBody_shouldReturn200() throws Exception {
		DepartementDto dto = makeNewDepartementDto();

		ResponseDto<DepartementDto> responseDto = runMockMvc(BASE_URL, 200, dto, DepartementDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Création d'un département via couche service OK", responseDto.getMessage());
		assertNotNull(responseDto.getBody());
	}

	@Override
	@Test
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEntityWithInvalidBody_shouldReturn400() throws Exception {
		DepartementDto wrongDto = new DepartementDto();

		String responseString = runMockMvcLite("post", BASE_URL, 400, wrongDto);

		assertThat(responseString).isEmpty();

	}

	/**
	 * Test de la méthode create avec entité invalide (sans param requis name). Doit
	 * retourner statut 400. ResponseDto doit avoir un body null.
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEntityWithNullName_shouldReturn400() throws Exception {
		DepartementDto dto = makeNewDepartementDto();
		dto.setName(null);

		String responseAsString = runMockMvcLite("post", BASE_URL, 400, dto);

		assertThat(responseAsString).isEmpty();
	}
	
	/**
	 * Test de la méthode create avec entité nulle. Doit
	 * retourner statut 400. ResponseDto doit avoir un body null.
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEntityNull_shouldReturn400() throws Exception {
		DepartementDto dto = makeNewDepartementDto();
		dto.setName(null);

		String responseAsString = runMockMvcLite("post", BASE_URL, 400, null);

		assertThat(responseAsString).isEmpty();
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeletingEntityWithValidId_shouldReturn200() throws Exception {
		String path = BASE_URL + "/1";

		ResponseDto<DepartementDto> responseDto = runMockMvc("delete", path, 200, DepartementDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertNull(responseDto.getBody());
		assertNotNull(responseDto.getMessage());
	}

	@Override
	@Test
	public void testDeletingEntityWithInvalidId_shouldReturn400() throws Exception {
		String path = BASE_URL + "/1";

		ResponseDto<DepartementDto> responseDto = runMockMvc("delete", path, 400, DepartementDto.class);

		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertNull(responseDto.getBody());
		assertNotNull(responseDto.getMessage());
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdatingEntityWithValidId_shouldReturn200() throws Exception {
		DepartementDto dto = makeNewDepartementDto();
		dto.setName("bob");

		ResponseDto<DepartementDto> responseDto = runMockMvc("put", BASE_URL, 200, dto, DepartementDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Modification d'un département via couche service OK", responseDto.getMessage());
		assertNotNull(responseDto.getBody());
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdatingEntityWithInvalidId_shouldReturn400() throws Exception {
		DepartementDto dto = makeNewDepartementDto();
		dto.setDepNum(2);

		ResponseDto<DepartementDto> responseDto = runMockMvc("put", BASE_URL, 400, dto, DepartementDto.class);

		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertEquals("Aucun département n'existe avec cet ID", responseDto.getMessage());
		assertNull(responseDto.getBody());
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingEntityWithValidId_shouldReturn200() throws Exception {
		String path = BASE_URL + "/one/1";

		ResponseDto<DepartementDto> responseDto = runMockMvc("get", path, 200, DepartementDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Success", responseDto.getMessage());
		assertNotNull(responseDto.getBody());
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingEntityWithInvalidId_shouldReturn400() throws Exception {
		String path = BASE_URL + "/one/2";

		ResponseDto<DepartementDto> responseDto = runMockMvc("get", path, 400, DepartementDto.class);
		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertEquals("Une entité avec cet ID n'existe pas dans la base de données", responseDto.getMessage());
		assertNull(responseDto.getBody());
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingAllEntity_shouldReturn200() throws Exception {
		String path = BASE_URL + "/all/0";

		ResponseDto<Page<DepartementDto>> responseDto = runMockMvc4Pages("get", path, 200, DepartementDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Success", responseDto.getMessage());
		assertNotNull(responseDto.getBody());
	}

	// Méthodes propres au controller département

	/**
	 * Test de la méthode readByName avec nom valide. Doit retourner statut 200.
	 * ResponseDto doit avoir un DepartementDto dans son body.
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingByName_shouldReturn200() throws Exception {
		String path = BASE_URL + "/nom4Test";

		ResponseDto<DepartementDto> responseDto = runMockMvc("get", path, 200, DepartementDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Récupération d'un département après recherche par nom", responseDto.getMessage());
		assertNotNull(responseDto.getBody());
	}

	/**
	 * Test de la méthode readByName avec nom invalide (inexistant en BD). Doit
	 * retourner statut 400. ResponseDto doit avoir un body null.
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingByNameInvalid_shouldReturn400() throws Exception {
		String path = BASE_URL + "/nom4TestInvalid";

		ResponseDto<DepartementDto> responseDto = runMockMvc("get", path, 400, DepartementDto.class);
		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertEquals("Récupération d'un département après recherche par nom", responseDto.getMessage());
		assertNull(responseDto.getBody());
	}

	/**
	 * Test de la méthode readMeteoByNumDep avec numDep valide. Doit retourner
	 * statut 200. ResponseDto doit avoir un MeteoUpdateDto dans son body.
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingMeteoByDepNum_shouldReturn200() throws Exception {
		String path = BASE_URL + "/meteo?elemsPerPage=20&id=1&page=0&sortName=date";

		ResponseDto<DepartementDto> responseDto = runMockMvc("get", path, 200, DepartementDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Récupération d'une liste de conditions météo par département", responseDto.getMessage());
		assertNotNull(responseDto.getBody());
	}

	/**
	 * Test de la méthode readByName avec numDep invalide (inexistant). Doit
	 * retourner statut 400. ResponseDto doit avoir un body null.
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingMeteoByDepNumInvalid_shouldReturn400() throws Exception {
		String path = BASE_URL + "/meteo?elemsPerPage=20&id=212&page=0&sortName=date";

		ResponseDto<DepartementDto> responseDto = runMockMvc("get", path, 400, DepartementDto.class);
		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertEquals("Tentative récupération d'une liste de conditions météo par département inexistant",
				responseDto.getMessage());
		assertNull(responseDto.getBody());
	}

}
