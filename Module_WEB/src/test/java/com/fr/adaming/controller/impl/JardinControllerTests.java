package com.fr.adaming.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.enums.Sol;

@SpringBootTest(classes = ModuleWebApplication.class)
@AutoConfigureMockMvc
public class JardinControllerTests extends AbstractTestMethods<JardinUpdateDto> implements IControllerTests {

	// TODO rajouter user quand converter user existe

	private static final String BASE_URL = "/jardin";

	// Parametres par defaut pour Jardin

	private static final int identifier = 1;
	private static final Sol ground = Sol.Argileux;
	private static final String name = "Bob";
	private static final Float length = 123.1f;
	private static final Float width = 123.1f;
	private static final Float depthGround = 0.5f;

	private static final String nameSql = "'" + name + "'";

	// Parametres pour user

	private static final String nom = "Alice";

	private static final String nomSql = "'" + nom + "'";

	// Parametres pour departement

	private static final int depNum = 38;
	private static final String depName = "Isere";
	private static final String depNameSql = "'" + depName + "'";

	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testCreatingEntityWithValidBody_shouldReturn200() throws Exception {

		JardinUpdateDto jardinDto = makeNewUpdateDto();

		ResponseDto<JardinUpdateDto> responseDto = runMockMvc("post", BASE_URL, 200, jardinDto, JardinUpdateDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Success", responseDto.getMessage());
		assertNotNull(responseDto.getBody());

	}

	@Test
	@Override
	public void testCreatingEntityWithInvalidBody_shouldReturn400() throws Exception {

		JardinUpdateDto dto = new JardinUpdateDto();

		String responseAsString = runMockMvcLite("post", BASE_URL, 400, dto);

		assertThat(responseAsString).isEmpty();
	}

	@Test
	@Override
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep) VALUES (1,'Jardin4TEST'," + depNum
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeletingEntityWithValidId_shouldReturn200() throws Exception {

		String path = BASE_URL + "/1";

		ResponseDto<JardinUpdateDto> responseDto = runMockMvc("delete", path, 200, JardinUpdateDto.class);

		assertThat(responseDto).isNotNull();
		assertThat(responseDto.isError()).isFalse();
	}

	@Test
	@Override
	public void testDeletingEntityWithInvalidId_shouldReturn400() throws Exception {
		String path = BASE_URL + "/1";

		ResponseDto<JardinUpdateDto> responseDto = runMockMvc("delete", path, 400, JardinUpdateDto.class);

		assertThat(responseDto).isNotNull();
		assertThat(responseDto.isError()).isTrue();

	}

	@Test
	@Override
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep) VALUES (1,'Bob'," + depNum
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdatingEntityWithValidId_shouldReturn200() throws Exception {
		JardinUpdateDto dto = makeNewUpdateDto();

		dto.setName("4TEST");

		ResponseDto<JardinUpdateDto> responseDto = runMockMvc("put", BASE_URL, 200, dto, JardinUpdateDto.class);

		assertThat(responseDto.getBody()).hasFieldOrPropertyWithValue("name", dto.getName());
		assertThat(responseDto.isError()).isFalse();
	}

	@Test
	@Override
	public void testUpdatingEntityWithInvalidId_shouldReturn400() throws Exception {
		JardinUpdateDto dto = makeNewUpdateDto();

		ResponseDto<JardinUpdateDto> responseDto = runMockMvc("put", BASE_URL, 400, dto, JardinUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();

	}

	@Test
	@Override
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep) VALUES (1,'Bob'," + depNum
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingEntityWithValidId_shouldReturn200() throws Exception {

		String path = BASE_URL + "/1";

		ResponseDto<JardinUpdateDto> responseDto = runMockMvc("get", path, 200, JardinUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertThat(responseDto.getBody()).hasFieldOrPropertyWithValue("name", name);

	}

	@Test
	@Override
	public void testReadingEntityWithInvalidId_shouldReturn400() throws Exception {

		String path = BASE_URL + "/1";

		ResponseDto<JardinUpdateDto> responseDto = runMockMvc("get", path, 400, JardinUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();

	}

	@Test
	@Override
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep) VALUES (1,'Mon jardin'," + depNum
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep) VALUES (2,'Jardin de mamie'," + depNum
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingAllEntity_shouldReturn200() throws Exception {

		String path = BASE_URL + "/all/0";

		ResponseDto<Page<JardinUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, JardinUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertThat(responseDto.getBody()).hasSize(2);

	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep) VALUES (1,'Bob'," + depNum
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingExistingEntityByName_shouldReturn200() throws Exception {

		String path = BASE_URL + "/name?nom=Bob&page=0";

		ResponseDto<Page<JardinUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, JardinUpdateDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertTrue(responseDto.getBody().getContent().size() == 1);
		assertThat(responseDto.getBody().getContent().get(0)).hasFieldOrPropertyWithValue("name", name);

	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep) VALUES (1,'Bob'," + depNum
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingInexistantEntityByName_shouldReturn200() throws Exception {

		String path = BASE_URL + "/name?nom=jardin&page=0";

		ResponseDto<Page<JardinUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, JardinUpdateDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertThat(responseDto.getBody().getContent()).isEmpty();

	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO utilisateur (id, email, mdp, pseudonyme, nom, prenom) values (5, 'natasha@avengers.com', 'NatLoveHulk1', 'Nat', 'Romanof', 'Natasha')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep, utilisateur_id) VALUES (1,'Bob'," + depNum
			+ ",5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingEntityByUser_shouldReturn200() throws Exception {

		String path = BASE_URL + "/user?identifier=5&page=0";

		ResponseDto<Page<JardinUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, JardinUpdateDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertThat(responseDto.getBody().getContent().size() == 1);
		assertThat(responseDto.getBody().getContent().get(0)).hasFieldOrPropertyWithValue("name", name);

	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO utilisateur (id, email, mdp, pseudonyme, nom, prenom) values (5, 'natasha@avengers.com', 'NatLoveHulk1', 'Nat', 'Romanof', 'Natasha')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep, utilisateur_id) VALUES (1,'Bob'," + depNum
			+ ",5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingEntityByInexistantUser_shouldReturn200() throws Exception {

		String path = BASE_URL + "/user?identifier=2&page=0";

		ResponseDto<Page<JardinUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, JardinUpdateDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertThat(responseDto.getBody().getContent().isEmpty());

	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO utilisateur (id, email, mdp, pseudonyme, nom, prenom) values (5, 'natasha@avengers.com', 'NatLoveHulk1', 'Nat', 'Romanof', 'Natasha')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep, utilisateur_id) VALUES (1,'Bob'," + depNum
			+ ",5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep, utilisateur_id) VALUES (2,'Bobby'," + depNum
			+ ",5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep, utilisateur_id) VALUES (3,'Bobbylou', 69 ,5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingEntityByDep_shouldReturn200() throws Exception {

		String path = BASE_URL + "/departement?numDep=38&page=0";

		ResponseDto<Page<JardinUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, JardinUpdateDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertThat(responseDto.getBody().getContent().size() == 2);
		assertThat(responseDto.getBody().getContent().get(0)).hasFieldOrPropertyWithValue("name", name);
		assertThat(responseDto.getBody().getContent().get(1)).hasFieldOrPropertyWithValue("name", "Bobby");

	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO utilisateur (id, email, mdp, pseudonyme, nom, prenom) values (5, 'natasha@avengers.com', 'NatLoveHulk1', 'Nat', 'Romanof', 'Natasha')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep, utilisateur_id) VALUES (1,'Bob'," + depNum
			+ ",5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep, utilisateur_id) VALUES (2,'Bobby'," + depNum
			+ ",5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep, utilisateur_id) VALUES (3,'Bobbylou', 69 ,5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingEntityByInexistantDep_shouldReturn200() throws Exception {

		String path = BASE_URL + "/departement?numDep=321&page=0";

		ResponseDto<Page<JardinUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, JardinUpdateDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertThat(responseDto.getBody().getContent().isEmpty());

	}
	
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO utilisateur (id, email, mdp, pseudonyme, nom, prenom) values (5, 'natasha@avengers.com', 'NatLoveHulk1', 'Nat', 'Romanof', 'Natasha')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep, utilisateur_id) VALUES (1,'Bob'," + depNum
			+ ",5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep, utilisateur_id) VALUES (2,'Bobby'," + depNum
			+ ",5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep, utilisateur_id) VALUES (3,'Bobbylou', 69 ,5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReinitArossJardin_shouldReturn200() throws Exception {

		String path = BASE_URL + "/arrosage?id=1";

		ResponseDto<JardinUpdateDto> responseDto = runMockMvc("get", path, 200, JardinUpdateDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());

	}
	
	

	// *** Méthodes privés ***

	private JardinUpdateDto makeNewUpdateDto() {
		// Creation du dto qu'on va utiliser pour la requete et aussi la comparaison

		DepartementDto depDto = new DepartementDto();

		depDto.setDepNum(depNum);
		depDto.setName(depName);

		UtilisateurUpdateDto userDto = new UtilisateurUpdateDto();

		userDto.setLastName(nom);
		userDto.setIdentifier(identifier);

		JardinUpdateDto dto = new JardinUpdateDto();

		dto.setIdentifier(identifier);
		dto.setGround(ground);
		dto.setLength(length);
		dto.setName(name);
		dto.setWidth(width);
		dto.setArrosed(false);
		dto.setDepthGround(depthGround);

//		A Rajouter lorsque utilisateur converter creer
//		dto.setUser(userDto);
		dto.setDept(depDto);

		return dto;

	}

}
