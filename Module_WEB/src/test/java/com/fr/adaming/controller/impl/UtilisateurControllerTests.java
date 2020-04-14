package com.fr.adaming.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
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
public class UtilisateurControllerTests extends AbstractTestMethods<UtilisateurUpdateDto> implements IControllerTests {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	private static final String BASE_URL = "/utilisateur";

	// paramètres pour user
	private static final Integer identifier = 1;
	private static final String lastname = "jornet";
	private static final String firstname = "kilian";
	private static final String pseudo = "extra terrestre";
	private static final String mail = "kiki@trail.fr";
	private static final String pwd = "4TEST";

	@Override
	@Test
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEntityWithValidBody_shouldReturn200() throws Exception {
		UtilisateurUpdateDto userDto = makeNewUpdateDto();

		ResponseDto<UtilisateurUpdateDto> responseDto = runMockMvc("post", BASE_URL, 200, userDto,
				UtilisateurUpdateDto.class);

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
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeletingEntityWithValidId_shouldReturn200() throws Exception {

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
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdatingEntityWithValidId_shouldReturn200() throws Exception {
		UtilisateurUpdateDto dto = makeNewUpdateDto();
		dto.setMail("max@max.fr");

		ResponseDto<UtilisateurUpdateDto> responseDto = runMockMvc("put", BASE_URL, 200, dto,
				UtilisateurUpdateDto.class);

		assertThat(responseDto.getBody()).hasFieldOrPropertyWithValue("mail", dto.getMail());
		assertThat(responseDto.isError()).isFalse();

	}

	@Override
	@Test
	public void testUpdatingEntityWithInvalidId_shouldReturn400() throws Exception {
		UtilisateurUpdateDto dto = makeNewUpdateDto();

		ResponseDto<UtilisateurUpdateDto> responseDto = runMockMvc("put", BASE_URL, 400, dto,
				UtilisateurUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();

	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingAllEntity_shouldReturn200() throws Exception {

		String path = BASE_URL + "/all/0";

		ResponseDto<Page<UtilisateurUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200,
				UtilisateurUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertThat(responseDto.getBody()).hasSize(2);
	}

	// ***********************************
	// Test READ BY NOM AND PRENOM

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByNomAndPrenomWithValidParam_ShouldReturn200() throws Exception {

		String path = BASE_URL + "/nomEtPrenom?nom=" + lastname + "&prenom=" + firstname;

		ResponseDto<UtilisateurUpdateDto> responseDto = runMockMvc("get", path, 200, UtilisateurUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByNomAndPrenomWithInvalidParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/nomEtPrenom?nom=" + lastname + "&prenom=" + firstname;

		ResponseDto<UtilisateurUpdateDto> responseDto = runMockMvc("get", path, 400, UtilisateurUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByNomAndPrenomWithNullParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/nomEtPrenom?nom=" + null + "&prenom=" + null;

		ResponseDto<UtilisateurUpdateDto> responseDto = runMockMvc("get", path, 400, UtilisateurUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();
	}

	// ************************
	// Test IS ACTIF

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',true) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestIsActifWithActifUser_ShouldReturn200AndTrueBody() throws Exception {
		String path = BASE_URL + "/actif?pseudonyme=" + pseudo;

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		assertThat(responseDto.getBody().equals(true));
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',false) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestIsActifWithDesactifUser_ShouldReturn200AndFalseBody() throws Exception {
		String path = BASE_URL + "/actif?pseudonyme=" + pseudo;

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		assertThat(responseDto.getBody().equals(false));
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',true) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestIsActifWithInvalidParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/actif?pseudonyme=" + pseudo;

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		assertThat(responseDto.getBody().equals(null));
	}

	// ***************************
	// Test Deactivate Utilisateur

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',true) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestDesactivateUserWithActifUser_ShouldReturn200AndTrueBody() throws UnsupportedEncodingException, Exception {
		String path = BASE_URL + "/desactivate?id=" + identifier;

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
		
		assertThat(responseDto.isError()).isFalse();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',false) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestDesactivateUserWithDesactifUser_ShouldReturn400() throws UnsupportedEncodingException, Exception {
		String path = BASE_URL + "/desactivate?id=" + identifier;

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		assertThat(responseDto.isError()).isTrue();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',true) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestDesactivateUserWithInvalidParam_ShouldReturn400() throws UnsupportedEncodingException, Exception {
		String path = BASE_URL + "/desactivate?id=" + 10;

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		assertThat(responseDto.isError()).isTrue();
	}

	// ************************************
	// Test activate Utilisateur

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',false) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestActivateUserWithDesactifUser_ShouldReturn200AndTrueBody() throws UnsupportedEncodingException, Exception {
		String path = BASE_URL + "/activate?id=" + identifier;

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
		
		assertThat(responseDto.isError()).isFalse();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',true) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestActivateUserWithActifUser_ShouldReturn400() throws UnsupportedEncodingException, Exception {
		String path = BASE_URL + "/activate?id=" + identifier;

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
		
		assertThat(responseDto.isError()).isTrue();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',true) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestActivateUserWithInvalidParam_ShouldReturn400() throws UnsupportedEncodingException, Exception {
		String path = BASE_URL + "/activate?id=" + 10;

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
		
		assertThat(responseDto.isError()).isTrue();
	}

	// ********************************************************
	
	
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
