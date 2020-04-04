package com.fr.adaming.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.ResponseDto;

@AutoConfigureMockMvc
public class AbstractTestMethods<U> {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Lance la partie du code test qui utilise mockMvc et objectMapper
	 * 
	 * @param path              Le chemin vers le controller (ex: "/plantemodel")
	 * @param expectedStatus    Le statut attendu (ex: 200)
	 * @param dto               Le dto recu par le controller
	 * @param responseBodyClass La class du retour attendu dans le body de
	 *                          responseDto (ex planteUpdateDto.class)
	 * @return ResponseDto contenant un body de type
	 * @throws Exception
	 */
	protected ResponseDto<U> runMockMvc(String path, int expectedStatus, Object dto, Class<U> responseBodyClass)
			throws Exception {

		// Execution de MockMvc et recuperation de las responseDto en format string
		String responseAsString = runMockMvcLite(path, expectedStatus, dto);
		// Recuperation du type
		JavaType responseDtoType = mapper.getTypeFactory().constructParametricType(ResponseDto.class,
				responseBodyClass);
		// Conversion de responseAsString en ResponseDto
		return mapper.readValue(responseAsString, responseDtoType);

	}

	/** Lance le mockmvc et retourne la version string de responseDto
	 * @param path           Le chemin vers le controller (ex: "/plantemodel")
	 * @param expectedStatus Le statut attendu (ex: 200)
	 * @param dto            Le dto recu par le controller
	 * @return Version string du ResponseDto
	 * @throws Exception
	 */
	protected String runMockMvcLite(String path, int expectedStatus, Object dto) throws Exception {
		String dtoAsJson = mapper.writeValueAsString(dto); // Le controller prend une createDto

		// Execution de la requete
		return mockMvc.perform(post(path).contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().is(expectedStatus)).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

	}

}
