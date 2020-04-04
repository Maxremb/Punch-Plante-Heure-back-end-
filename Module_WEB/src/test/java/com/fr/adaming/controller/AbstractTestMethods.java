package com.fr.adaming.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.ResponseDto;

import lombok.extern.slf4j.Slf4j;

@AutoConfigureMockMvc
@Slf4j
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
		String responseAsString = runMockMvcLite("post", path, expectedStatus, dto);
		// Recuperation du type
		JavaType responseDtoType = mapper.getTypeFactory().constructParametricType(ResponseDto.class,
				responseBodyClass);
		// Conversion de responseAsString en ResponseDto
		return mapper.readValue(responseAsString, responseDtoType);

	}

	/**
	 * Lance la partie du code test qui utilise mockMvc et objectMapper
	 * 
	 * @param requestType       Le type de la requete html
	 * @param path              Le chemin vers le controller (ex: "/plantemodel")
	 * @param expectedStatus    Le statut attendu (ex: 200)
	 * @param dto               Le dto recu par le controller
	 * @param responseBodyClass La class du retour attendu dans le body de
	 *                          responseDto (ex planteUpdateDto.class)
	 * @return ResponseDto contenant un body de type
	 * @throws Exception
	 */
	protected ResponseDto<U> runMockMvc(String requestType, String path, int expectedStatus, Object dto,
			Class<U> responseBodyClass) throws Exception {

		// Execution de MockMvc et recuperation de las responseDto en format string
		String responseAsString = runMockMvcLite(requestType, path, expectedStatus, dto);
		// Recuperation du type
		JavaType responseDtoType = mapper.getTypeFactory().constructParametricType(ResponseDto.class,
				responseBodyClass);
		// Conversion de responseAsString en ResponseDto
		return mapper.readValue(responseAsString, responseDtoType);

	}

	/**
	
	 * 
	 */

	/**
	 * Lance le mockmvc et retourne la version string de responseDto
	 * 
	 * @param requestType    Le type de la requete html
	 * @param path           Le chemin vers le controller (ex: "/plantemodel")
	 * @param expectedStatus Le statut attendu (ex: 200)
	 * @param dto            Le dto recu par le controller
	 * @return Version string du ResponseDto
	 * @throws Exception
	 */
	protected String runMockMvcLite(String requestType, String path, int expectedStatus, Object dto) throws Exception {
		String dtoAsJson = mapper.writeValueAsString(dto); // Le controller prend une createDto
		String responseAsString = null;

		// Execution de la requete

		switch (requestType) {

		case "post":
			responseAsString = mockMvc
					.perform(post(path).contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().is(expectedStatus)).andReturn().getResponse()
					.getContentAsString(StandardCharsets.UTF_8);
			break;

		case "delete":
			responseAsString = mockMvc.perform(delete(path)).andExpect(status().is(expectedStatus)).andReturn()
					.getResponse().getContentAsString(StandardCharsets.UTF_8);
			break;

		case "get":
			responseAsString = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().is(expectedStatus)).andReturn().getResponse()
					.getContentAsString(StandardCharsets.UTF_8);
			break;

		case "put":
			responseAsString = mockMvc
					.perform(put(path).contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().is(expectedStatus)).andReturn().getResponse()
					.getContentAsString(StandardCharsets.UTF_8);
			break;
		default:
			log.error("type de requete non-reconnu");
		}
		return responseAsString;

	}

}
