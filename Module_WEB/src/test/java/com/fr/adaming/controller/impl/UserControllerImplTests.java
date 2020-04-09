package com.fr.adaming.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.dto.UserCreateDto;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.impl.UserServiceImpl;

@ContextConfiguration(classes = ModuleWebApplication.class)
@WebMvcTest(controllers = UserControllerImpl.class)
public class UserControllerImplTests {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@MockBean
	private UserServiceImpl mockService;

	@Test
	public void testUserCreatingWebServiceWithMockito() throws UnsupportedEncodingException, Exception {

		// Préparer les inputs
		UserCreateDto dtoInput = new UserCreateDto();
		dtoInput.setFirstName("firstName4Test");
		dtoInput.setLastName("lastName4Test");

		// Préparer le retour du Mock service
		User expectedMockedUser = new User();
		expectedMockedUser.setNom("mockFirstName4Test");
		expectedMockedUser.setPrenom("mockLastName4Test");

		// Convertir le dto sous format JSON
		String inputAsJson = mapper.writeValueAsString(dtoInput);

		// Préparer le mock
		when(mockService.create(Mockito.any(User.class))).thenReturn(expectedMockedUser);

		String responseAsString = mockMvc
				.perform(post("/users/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputAsJson))
				.andDo(print()).andExpect(status().is(200)).andReturn().getResponse().getContentAsString();

		assertNotNull(responseAsString);
		
		UserCreateDto returnedDto = mapper.readValue(responseAsString, UserCreateDto.class);

		assertThat(returnedDto).isNotNull();
		assertThat(returnedDto).hasFieldOrPropertyWithValue("firstName", expectedMockedUser.getPrenom());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("lastName", expectedMockedUser.getNom());
	}
}
