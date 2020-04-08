package com.fr.adaming.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.controller.AbstractTestMethods;
import com.fr.adaming.controller.IControllerTests;
import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.UserCreateDto;
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
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id, nom,departement_numero_dep) VALUES (1,'Bob'," + depNum+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdatingEntityWithValidId_shouldReturn200() throws Exception {
		JardinUpdateDto dto =   makeNewUpdateDto();

		dto.setName("4TEST");

		ResponseDto<JardinUpdateDto> responseDto = runMockMvc("put", BASE_URL, 200, dto, JardinUpdateDto.class);

		assertThat(responseDto.getBody()).hasFieldOrPropertyWithValue("name", dto.getName());
		assertThat(responseDto.isError()).isFalse();
	}

	@Test
	@Override
	public void testUpdatingEntityWithInvalidId_shouldReturn400() throws Exception {
		JardinUpdateDto dto =   makeNewUpdateDto();

		ResponseDto<JardinUpdateDto> responseDto = runMockMvc("put", BASE_URL, 400, dto, JardinUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();

	}

	@Test
	@Override
	public void testReadingEntityWithValidId_shouldReturn200() throws Exception {
		// TODO Auto-generated method stub

	}

	@Test
	@Override
	public void testReadingEntityWithInvalidId_shouldReturn400() throws Exception {
		// TODO Auto-generated method stub

	}

	@Test
	@Override
	public void testReadingAllEntity_shouldReturn200() throws Exception {
		// TODO Auto-generated method stub

	}

	// *** Méthodes privés ***

	private JardinUpdateDto makeNewUpdateDto() {
		// Creation du dto qu'on va utiliser pour la requete et aussi la comparaison

		DepartementDto depDto = new DepartementDto();

		depDto.setDepNum(depNum);
		depDto.setName(depName);

		UtilisateurUpdateDto userDto = new UtilisateurUpdateDto();

		userDto.setNom(nom);
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
