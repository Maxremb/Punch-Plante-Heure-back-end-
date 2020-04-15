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
import com.fr.adaming.dto.AdminUpdateDto;
import com.fr.adaming.dto.ResponseDto;

/**
 * Classe test de la couche controller ADMIN. Elle étend la classe Abstract Test
 * Method et implémente IControlelrTest
 * 
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest(classes = ModuleWebApplication.class)
@AutoConfigureMockMvc
public class AdminControllerTest extends AbstractTestMethods<AdminUpdateDto> implements IControllerTests {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	private static final String BASE_URL = "/admin";

	// paramètres admin
	private static final Integer identifier = 10;
	private static final String pseudo = "extra terrestre";
	private static final String mail = "kiki@trail.fr";
	private static final String pwd = "4TEST";
	// ID EMAIL MDP PSEUDO NON NULLABLE

	@Override
	@Test
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEntityWithValidBody_shouldReturn200() throws Exception {
		AdminUpdateDto dto = makeNewUpdateDto();

		ResponseDto<AdminUpdateDto> responseDto = runMockMvc("post", BASE_URL, 200, dto, AdminUpdateDto.class);

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
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeletingEntityWithValidId_shouldReturn200() throws Exception {
		String path = BASE_URL + "/10";

		ResponseDto<AdminUpdateDto> responseDto = runMockMvc("delete", path, 200, AdminUpdateDto.class);

		assertThat(responseDto).isNotNull();
		assertThat(responseDto.isError()).isFalse();
	}

	@Override
	@Test
	public void testDeletingEntityWithInvalidId_shouldReturn400() throws Exception {
		String path = BASE_URL + "/1";

		ResponseDto<AdminUpdateDto> responseDto = runMockMvc("delete", path, 400, AdminUpdateDto.class);

		assertThat(responseDto).isNotNull();
		assertThat(responseDto.isError()).isTrue();
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdatingEntityWithValidId_shouldReturn200() throws Exception {
		AdminUpdateDto dto = makeNewUpdateDto();
		dto.setMail("max@max.fr");

		ResponseDto<AdminUpdateDto> responseDto = runMockMvc("put", BASE_URL, 200, dto, AdminUpdateDto.class);

		assertThat(responseDto.getBody()).hasFieldOrPropertyWithValue("mail", dto.getMail());
		assertThat(responseDto.isError()).isFalse();
	}

	@Override
	@Test
	public void testUpdatingEntityWithInvalidId_shouldReturn400() throws Exception {
		AdminUpdateDto dto = makeNewUpdateDto();

		ResponseDto<AdminUpdateDto> responseDto = runMockMvc("put", BASE_URL, 400, dto, AdminUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingEntityWithValidId_shouldReturn200() throws Exception {

		String path = BASE_URL + "/10";

		ResponseDto<AdminUpdateDto> responseDto = runMockMvc("get", path, 200, AdminUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertThat(responseDto.getBody()).hasFieldOrPropertyWithValue("pseudo", pseudo);
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingEntityWithInvalidId_shouldReturn400() throws Exception {
		String path = BASE_URL + "/1";

		ResponseDto<AdminUpdateDto> responseDto = runMockMvc("get", path, 400, AdminUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();

	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (20,'françois@trail.fr','4TEST4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingAllEntity_shouldReturn200() throws Exception {
		String path = BASE_URL + "/all/0";

		ResponseDto<Page<AdminUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, AdminUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
		assertThat(responseDto.getBody()).hasSize(2);
	}

	// ********************************************************
	// Test READ BY PSEUDO

	/**
	 * Méthode test read by pseudo avec param OK Retourne 200
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByPseudoithValidParam_ShouldReturn200() throws Exception {

		String path = BASE_URL + "/pseudo?pseudo=" + pseudo;

		ResponseDto<AdminUpdateDto> responseDto = runMockMvc("get", path, 200, AdminUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
	}

	/**
	 * Méthode test read by pseudo avec param invalid retourne 400
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByPseudoWithInvalidParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/pseudo?pseudo=abc";

		ResponseDto<AdminUpdateDto> responseDto = runMockMvc("get", path, 400, AdminUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();
	}

	/**
	 * Méthode test read by pseudo avec param null retourne 400
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByPseudoWithNullParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/pseudo?pseudo=" + null;

		ResponseDto<AdminUpdateDto> responseDto = runMockMvc("get", path, 400, AdminUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();
	}

	// ********************************************************
	// Test READ BY EMAIL

	/**
	 * Méthode test read by email avec param OK retourne 200
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByEmailValidParam_ShouldReturn200() throws Exception {

		String path = BASE_URL + "/mail?mail=" + mail;

		ResponseDto<AdminUpdateDto> responseDto = runMockMvc("get", path, 200, AdminUpdateDto.class);

		assertThat(responseDto.isError()).isFalse();
	}

	/**
	 * Méthode test read by email avec param invalid Retourne 400
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByEmailWithInvalidParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/mail?mail=abc";

		ResponseDto<AdminUpdateDto> responseDto = runMockMvc("get", path, 400, AdminUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();
	}

	/**
	 * Méthode test read by email avec param null Retourne 400
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByEmailWithNullParam_ShouldReturn400() throws Exception {
		String path = BASE_URL + "/mail?mail=" + null;

		ResponseDto<AdminUpdateDto> responseDto = runMockMvc("get", path, 400, AdminUpdateDto.class);

		assertThat(responseDto.isError()).isTrue();
	}

	// ********************************************************
	// Test EXISTS BY EMAIL

	/**
	 * Méthode test exists by email avec param OK. 
	 * Retourne 200
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailWithValidParam_ShouldReturn200() throws UnsupportedEncodingException, Exception {
		String path = BASE_URL + "/exists/mail?mail=" + mail;

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto<?> responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		assertThat(responseDto.isError()).isFalse();
	}

	/**
	 * Méthode test exists by email avec param invalid
	 * Retourne 400
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailWithInvalidParam_ShouldReturn400() throws UnsupportedEncodingException, Exception {
		String path = BASE_URL + "/exists/mail?mail=acbbc";

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto<?> responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		assertThat(responseDto.isError()).isTrue();
	}

	/**
	 * Méthode test exists by email avec null param
	 * Retourne 400
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailWithNullParam_ShouldReturn400() throws UnsupportedEncodingException, Exception {
		String path = BASE_URL + "/exists/mail?mail=" + null;

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto<?> responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		assertThat(responseDto.isError()).isTrue();
	}

	// ********************************************************
	// Test EXISTS BY PSEUDO

	/**
	 * Méthode test exists by pseudo avec param OK
	 * Retourne 200
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByPseudoWithValidParam_ShouldReturn200() throws UnsupportedEncodingException, Exception {
		String path = BASE_URL + "/exists/pseudo?pseudo=" + pseudo;

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto<?> responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		assertThat(responseDto.isError()).isFalse();
	}

	/**
	 * Méthode test exists by pseudo avec param invalid
	 * Retourne 400
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByPseudoWithhInvalidParam_ShouldReturn400() throws UnsupportedEncodingException, Exception {
		String path = BASE_URL + "/exists/pseudo?pseudo=abccc";

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto<?> responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		assertThat(responseDto.isError()).isTrue();
	}

	/**
	 * Méthode test exists by pseudo avec null param
	 * Retourne 400
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByPseudoWithNullParam_ShouldReturn400() throws UnsupportedEncodingException, Exception {
		String path = BASE_URL + "/exists/pseudo?pseudo=" + null;

		String responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);
		// convertir la reponse JSON en DTO
		ResponseDto<?> responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		assertThat(responseDto.isError()).isTrue();
	}

	// ********************************************************
	// Test EXISTS BY EMAIL AND MDP

//	@Test
//	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	public void TestExistsByMailAndPwdWithValidAdminParam_ShouldReturn200() throws Exception {
//		String path = BASE_URL + "/mailAndPwd";
//		String tableau = "[" + mail + "," + pwd + "]";
//
//		String responseAsString = mockMvc
//				.perform(post(path).contentType(MediaType.APPLICATION_JSON_VALUE).content(tableau))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
//		// convertir la reponse JSON en DTO
//		ConnexionDto responseDto = mapper.readValue(responseAsString, ConnexionDto.class);
//
//		assertThat(responseDto.isUser()).isFalse();
//		assertThat(responseDto.getBodyAdmin()).hasFieldOrPropertyWithValue("pseudo", pseudo);
//		assertThat(responseDto.getBodyUtil()).isNull();
//
//	}
//
//	@Test
//	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',true) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	public void TestExistsByMailAndPwdWithValidUserParam_ShouldReturn200()
//			throws UnsupportedEncodingException, Exception {
//		String path = BASE_URL + "/mailAndPwd";
//		String tableau = "[" + mail + "," + pwd + "]";
//
//		String responseAsString = mockMvc
//				.perform(post(path).contentType(MediaType.APPLICATION_JSON_VALUE).content(tableau))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
//		// convertir la reponse JSON en DTO
//		ConnexionDto responseDto = mapper.readValue(responseAsString, ConnexionDto.class);
//
//		assertThat(responseDto.isUser()).isTrue();
//		assertThat(responseDto.getBodyAdmin()).isNull();
//		assertThat(responseDto.getBodyUtil()).hasFieldOrPropertyWithValue("pseudo", pseudo);
//	}
//
//	@Test
//	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	public void TestExistsByMailAndPwdWithInvalidParam_ShouldReturn400() throws Exception {
//		String path = BASE_URL + "/mailAndPwd";
//		String param = null;
//		
//		String responseAsString = mockMvc.perform(post(path).contentType(MediaType.APPLICATION_JSON_VALUE).content(param))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
//		// convertir la reponse JSON en DTO
//		ConnexionDto responseDto = mapper.readValue(responseAsString, ConnexionDto.class);
//		
//		assertThat(responseDto.isUser()).isFalse();
//		assertThat(responseDto.getBodyAdmin()).isNull();
//		assertThat(responseDto.getBodyUtil()).isNull();
//		
//	}
//
//	@Test
//	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	public void TestExistsByMailAndPwdWithoutParam_ShouldReturn400() throws Exception {
//		String path = BASE_URL + "/mailAndPwd";
//		
//		String responseAsString = mockMvc.perform(post(path).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
//		// convertir la reponse JSON en DTO
//		ConnexionDto responseDto = mapper.readValue(responseAsString, ConnexionDto.class);
//		
//		assertThat(responseDto.isUser()).isFalse();
//		assertThat(responseDto.getBodyAdmin()).isNull();
//		assertThat(responseDto.getBodyUtil()).isNull();
//	}

	// *** Méthodes privés ***

	/**
	 * Méthode permettant de définir un objet adminUpdateDto
	 * @return L'objet AdminUpdateDto vers la méthode appelée
	 */
	private AdminUpdateDto makeNewUpdateDto() {
		// Creation du dto qu'on va utiliser pour la requete et aussi la comparaison

		AdminUpdateDto dto = new AdminUpdateDto();

		dto.setIdentifier(identifier);
		dto.setMail(mail);
		dto.setPseudo(pseudo);
		dto.setPwd(pwd);

		return dto;

	}

}
