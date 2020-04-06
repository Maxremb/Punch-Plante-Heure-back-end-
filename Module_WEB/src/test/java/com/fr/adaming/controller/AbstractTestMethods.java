package com.fr.adaming.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.page.RestPageImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gregoire
 *
 * @param <U> le type de dto retourné par la plupart des controllers en tant que body de responseDto. Probablement updateDto 
 * @since 0.0.1
 */
@AutoConfigureMockMvc
@Slf4j
public class AbstractTestMethods<U> {

	// ***runMockMvc***
	
	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Lance la partie du code test qui utilise mockMvc et objectMapper. Version sans entrée de dto.
	 * 
	 * @param requestType       Le type de la requete html (ex: "get")
	 * @param path              Le chemin vers le controller (ex: "/plantemodel")
	 * @param expectedStatus    Le statut attendu (ex: 200)
	 * @param responseBodyClass La class du retour attendu dans le body de
	 *                          responseDto (ex planteUpdateDto.class)
	 * @return ResponseDto contenant un body de type
	 * @throws Exception
	 */
	protected ResponseDto<U> runMockMvc(String requestType, String path, int expectedStatus,
			Class<U> responseBodyClass) throws Exception {

		return runMockMvc(requestType, path, expectedStatus, null, responseBodyClass);

	}

	/**
	 * Lance la partie du code test qui utilise mockMvc et objectMapper. Version sans type de request specifié
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

		return runMockMvc("post", path, expectedStatus, dto, responseBodyClass);

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
	
	
	// *** runMockMvc4Lists *** //
	
	/**
	 * Lance la partie du code test qui utilise mockMvc et objectMapper pour les listes. Version sans entrée de dto.
	 * 
	 * @param requestType       Le type de la requete html
	 * @param path              Le chemin vers le controller (ex: "/plantemodel")
	 * @param expectedStatus    Le statut attendu (ex: 200)
	 * @param responseBodyClass La class du retour attendu dans le body de
	 *                          responseDto (ex planteUpdateDto.class)
	 * @return ResponseDto contenant un body de type
	 * @throws Exception
	 */
	protected ResponseDto<List<U>> runMockMvc4Lists(String requestType, String path, int expectedStatus,
			Class<U> responseBodyClass) throws Exception {

		return runMockMvc4Lists(requestType, path, expectedStatus, null, responseBodyClass);

	}
	
	/**
	 * Lance la partie du code test qui utilise mockMvc et objectMapper pour les listes
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
	protected ResponseDto<List<U>> runMockMvc4Lists(String requestType, String path, int expectedStatus, Object dto,
			Class<U> responseBodyClass) throws Exception {

		// Execution de MockMvc et recuperation de las responseDto en format string
		String responseAsString = runMockMvcLite(requestType, path, expectedStatus, dto);
		// Recuperation du type
		
		JavaType listType = mapper.getTypeFactory().constructParametricType(ArrayList.class, responseBodyClass);
		JavaType responseDtoType = mapper.getTypeFactory().constructParametricType(ResponseDto.class,
				listType);
		// Conversion de responseAsString en ResponseDto
		return mapper.readValue(responseAsString, responseDtoType);

	}
	
	// *** runMockMvc4Pages *** //
	
	/**
	 * Lance la partie du code test qui utilise mockMvc et objectMapper pour les Pages. Version sans entrée de dto.
	 * 
	 * @param requestType       Le type de la requete html
	 * @param path              Le chemin vers le controller (ex: "/plantemodel")
	 * @param expectedStatus    Le statut attendu (ex: 200)
	 * @param responseBodyClass La class du retour attendu dans le body de
	 *                          responseDto (ex planteUpdateDto.class)
	 * @return ResponseDto contenant un body de type
	 * @throws Exception
	 */
	protected ResponseDto<Page<U>> runMockMvc4Pages(String requestType, String path, int expectedStatus,
			Class<U> responseBodyClass) throws Exception {

		return runMockMvc4Pages(requestType, path, expectedStatus, null, responseBodyClass);

	}
	
	/**
	 * Lance la partie du code test qui utilise mockMvc et objectMapper pour les listes
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
	protected ResponseDto<Page<U>> runMockMvc4Pages(String requestType, String path, int expectedStatus, Object dto,
			Class<U> responseBodyClass) throws Exception {

		// Execution de MockMvc et recuperation de las responseDto en format string
		String responseAsString = runMockMvcLite(requestType, path, expectedStatus, dto);
		// Recuperation du type
		
		JavaType listType = mapper.getTypeFactory().constructParametricType(RestPageImpl.class, responseBodyClass);
		JavaType responseDtoType = mapper.getTypeFactory().constructParametricType(ResponseDto.class,
				listType);
		// Conversion de responseAsString en ResponseDto
		return mapper.readValue(responseAsString, responseDtoType);

	}
	
	// *** runMockMvcLite *** //
	
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
	protected String runMockMvcLite(String requestType, String path, int expectedStatus) throws Exception {

		return runMockMvcLite(requestType, path, expectedStatus, null);

	}


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
		String dtoAsJson;
		String responseAsString = null;

		// Execution de la requete

		switch (requestType) {

		case "post":
			dtoAsJson = mapper.writeValueAsString(dto);
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
			dtoAsJson = mapper.writeValueAsString(dto);
			responseAsString = mockMvc
					.perform(put(path).contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().is(expectedStatus)).andReturn().getResponse()
					.getContentAsString(StandardCharsets.UTF_8);
			break;
		default:
			log.error("AbstractTestMethods: type de requete non-reconnu");
		}
		return responseAsString;

	}

}
