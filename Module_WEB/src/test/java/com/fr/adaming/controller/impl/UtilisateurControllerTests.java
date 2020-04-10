package com.fr.adaming.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.controller.AbstractTestMethods;
import com.fr.adaming.controller.IControllerTests;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;

@SpringBootTest(classes = ModuleWebApplication.class)
@AutoConfigureMockMvc
public class UtilisateurControllerTests extends AbstractTestMethods<UtilisateurUpdateDto> implements IControllerTests{
	
	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();
	
	private static final String BASE_URL = "/utilisateur";
	
	//paramètres pour user
	private static final Integer identifier = 1;
	private static final String lastname = "jornet";
	private static final String firstname = "kilian";
	private static final String pseudo = "extra terrestre";
	private static final String mail = "kiki@trail.fr";
	private static final String pwd = "4TEST";

	@Override
	@Test
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEntityWithValidBody_shouldReturn200() throws Exception {
		UtilisateurUpdateDto userDto = makeNewUpdateDto();	
		
		ResponseDto<UtilisateurUpdateDto> responseDto = runMockMvc("post", BASE_URL, 200, userDto, UtilisateurUpdateDto.class);
		
		assertThat(responseDto).isNotNull();
		assertThat(responseDto.isError()).isFalse();
		assertThat(responseDto.getMessage()).isEqualTo("Success");
		assertThat(responseDto.getBody()).isNotNull();
	}

	@Override
	@Test
	public void testCreatingEntityWithInvalidBody_shouldReturn400() throws Exception {
		
		String responseAsString = runMockMvcLite("post", BASE_URL, 400, null);

		assertThat(responseAsString).isEmpty();
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeletingEntityWithValidId_shouldReturn200() throws Exception {
		UtilisateurUpdateDto dto = makeNewUpdateDto();
		
		String path = BASE_URL + "/1";
		
		ResponseDto<UtilisateurUpdateDto> responseDto = runMockMvc("delete", path, 200, UtilisateurUpdateDto.class);

		assertThat(responseDto).isNotNull();
		assertThat(responseDto.isError()).isFalse();
		
	}

	@Override
	@Test
	public void testDeletingEntityWithInvalidId_shouldReturn400() throws Exception {
		String path = BASE_URL + "/1";

		ResponseDto<UtilisateurUpdateDto> responseDto = runMockMvc("delete", path, 400, UtilisateurUpdateDto.class);

		assertThat(responseDto).isNotNull();
		assertThat(responseDto.isError()).isTrue();
		
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdatingEntityWithValidId_shouldReturn200() throws Exception {
		UtilisateurUpdateDto dto = makeNewUpdateDto();
		dto.setMail("max@max.fr");
		
		ResponseDto<UtilisateurUpdateDto> responseDto = runMockMvc("put", BASE_URL, 200, dto, UtilisateurUpdateDto.class);

		assertThat(responseDto.getBody()).hasFieldOrPropertyWithValue("mail", dto.getMail());
		assertThat(responseDto.isError()).isFalse();
		
	}

	@Override
	@Test
	public void testUpdatingEntityWithInvalidId_shouldReturn400() throws Exception {
		UtilisateurUpdateDto dto = makeNewUpdateDto();

		ResponseDto<UtilisateurUpdateDto> responseDto = runMockMvc("put", BASE_URL, 400, dto, UtilisateurUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();
		
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingEntityWithValidId_shouldReturn200() throws Exception {
		
		String path = BASE_URL + "/1";

		ResponseDto<UtilisateurUpdateDto> responseDto = runMockMvc("get", path, 200, UtilisateurUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertThat(responseDto.getBody()).hasFieldOrPropertyWithValue("lastName", lastname);
		
	}

	@Override
	@Test
	public void testReadingEntityWithInvalidId_shouldReturn400() throws Exception {
		String path = BASE_URL + "/1";

		ResponseDto<UtilisateurUpdateDto> responseDto = runMockMvc("get", path, 400, UtilisateurUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();
		
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingAllEntity_shouldReturn200() throws Exception {
		
	}
	
	
	//***********************************
	//Test READ BY NOM AND PRENOM
	
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByNomAndPrenomWithValidParam_ShouldReturn200 () throws Exception {
		
		
	}
	
	
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByNomAndPrenomWithInvalidParam_ShouldReturn400 () {
		
	}
	
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByNomAndPrenomWithNullParam_ShouldReturn400 () {
		
	}
	
	//************************
	//Test IS ACTIF
	
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestIsActifWithActifUser_ShouldReturn200AndTrueBody() {
		
	}
	
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestIsActifWithDesactifUser_ShouldReturn200AndFalseBody() {
		
	}
	
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestIsActifWithInvalidParam_ShouldReturn400() {
		
	}
	
	
	//***************************
	//Test Deactivate Utilisateur
	
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestDesactivateUserWithActifUser_ShouldReturn200AndTrueBody() {
		
	}
	
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestDesactivateUserWithDesactifUser_ShouldReturn400() {
		
	}
	
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestDesactivateUserWithInvalidParam_ShouldReturn400() {
		
	}
	
	
	//************************************
	//Test activate Utilisateur
	
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestActivateUserWithDesactifUser_ShouldReturn200AndTrueBody() {
		
	}
	
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestActivateUserWithActifUser_ShouldReturn400() {
		
	}
	
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestActivateUserWithInvalidParam_ShouldReturn400() {
		
	}
	
	
	
	//********************************************************
	// *** Méthodes privés ***

		private UtilisateurUpdateDto makeNewUpdateDto() {
			// Creation du dto qu'on va utiliser pour la requete et aussi la comparaison

			UtilisateurUpdateDto userDto = new UtilisateurUpdateDto();

			userDto.setLastName(lastname);
			userDto.setIdentifier(identifier);
			userDto.setFirstName(firstname);
			userDto.setMail(mail);
			userDto.setPseudo(pseudo);
			userDto.setPwd(pwd);

			return userDto;

		}

}
