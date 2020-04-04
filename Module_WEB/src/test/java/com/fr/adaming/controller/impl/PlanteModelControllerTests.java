package com.fr.adaming.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.controller.IControllerTests;
import com.fr.adaming.dto.PlanteModelCreateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.enums.Sol;

@SpringBootTest(classes = ModuleWebApplication.class)
@AutoConfigureMockMvc
public class PlanteModelControllerTests implements IControllerTests {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();

	// Parametres par defaut

	private static final int identifiant = 1;
	private static final int arrosage = 1;
	private static final String commun = "bob";
	private static final String desc = "Une plante";
	private static final String ensoleillement = "jamais";
	private static final int humidite = 1;
	private static final int min = 1;
	private static final int max = 10;
	private static final String mifa = "Mon Insect Favorise l'Aventure";
	private static final String[] negative = { "arbres", "tomates" };
	private static final String[] positive = { "Choux", "Celeri" };
	private static final String picture = "http://this.org/is/a/path";
	private static final int repiquage = 1;
	private static final String scientifique = "Bobus Maximus";
	private static final Sol sol = Sol.Argileux;
	private static final boolean toxi = true;

	// Méthodes

	@Test
	@Override
	public void testCreatingEntityWithValidBody_shouldReturn200() throws Exception {

		// Creation du dto qu'on va utiliser pour la requete et aussi la comparaison
		PlanteModelUpdateDto dto = new PlanteModelUpdateDto();
		dto.setIdentifiant(identifiant);
		dto.setArrosage(arrosage);
		dto.setCommun(commun);
		dto.setDesc(desc);
		dto.setEnsoleillement(ensoleillement);
		dto.setHumidite(humidite);
		dto.setMin(min);
		dto.setMax(max);
		dto.setMifa(mifa);
		dto.setNegative(negative);
		dto.setPositive(positive);
		dto.setPicture(picture);
		dto.setRepiquage(repiquage);
		dto.setScientifique(scientifique);
		dto.setSol(sol);
		dto.setToxi(toxi);

		String dtoAsJson = mapper.writeValueAsString((PlanteModelCreateDto) dto); // Le controller prend une createDto

		// Execution de la requete
		String responseAsString = mockMvc
				.perform(post("/plantemodel").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

		ResponseDto<PlanteModelUpdateDto> responseDto = mapper.readValue(responseAsString,  new TypeReference<ResponseDto<PlanteModelUpdateDto>>() {});

		// Verifier la responseDto
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Succes de la création", responseDto.getMessage());
//		assertEquals(dto, responseDto.getBody()); //ne marche pas :(
		// comparaison manuelle
		updateDtoCompare(dto, responseDto.getBody());

	}

	@Override
	public void testCreatingEntityWithInvalidBody_shouldReturn400() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testDeletingEntityWithValidId_shouldReturn200() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testDeletingEntityWithInvalidId_shouldReturn400() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testDeletingEntityWithNegativeId_shouldReturn400() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testUpdatingEntityWithValidId_shouldReturn200() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testUpdatingEntityWithInvalidId_shouldReturn400() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testReadingEntityWithValidId_shouldReturn200() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testReadingEntityWithInvalidId_shouldReturn400() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testReadingEntityWithNegativeId_shouldReturn400() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testReadingAllEntity_shouldReturn200() {
		// TODO Auto-generated method stub

	}

	/**
	 * Pour une raison que je ne comprend pas bien, assertEquals utilise le toString
	 * de expected dto dans la comparaison. Cette méthode existe pour resoudre ce
	 * probleme, et pour limiter la quantité de code
	 * 
	 * @param expectedDto Dto qu'on attend
	 * @param returnedDto Dto qu'on recoit
	 */
	private void updateDtoCompare(PlanteModelUpdateDto expectedDto, PlanteModelUpdateDto returnedDto) {

		assertEquals(expectedDto.getIdentifiant(), returnedDto.getIdentifiant());
		assertEquals(expectedDto.getArrosage(), returnedDto.getArrosage());
		assertEquals(expectedDto.getCommun(), returnedDto.getCommun());
		assertEquals(expectedDto.getDesc(), returnedDto.getDesc());
		assertEquals(expectedDto.getEnsoleillement(), returnedDto.getEnsoleillement());
		assertEquals(expectedDto.getHumidite(), returnedDto.getHumidite());
		assertEquals(expectedDto.getMin(), returnedDto.getMin());
		assertEquals(expectedDto.getMax(), returnedDto.getMax());
		assertEquals(expectedDto.getMifa(), returnedDto.getMifa());
		assertEquals(expectedDto.getPicture(), returnedDto.getPicture());
		assertEquals(expectedDto.getRepiquage(), returnedDto.getRepiquage());
		assertEquals(expectedDto.getScientifique(), returnedDto.getScientifique());
		assertEquals(expectedDto.getSol(), returnedDto.getSol());
		assertEquals(expectedDto.isToxi(), returnedDto.isToxi());
		
		assertEquals(expectedDto.getNegative().length, returnedDto.getNegative().length);
		assertEquals(expectedDto.getPositive().length, returnedDto.getPositive().length);
		
		compareStringArrays(expectedDto.getNegative(), returnedDto.getNegative());
		compareStringArrays(expectedDto.getPositive(), returnedDto.getPositive());
		
	}
	
	/**Pour les comparaisons des deux tableaux de string dans updateDtoCompare
	 * @param expected Tableau de strings attendu
	 * @param returned Tableau de strings recu
	 */
	private void compareStringArrays(String[] expected, String[] returned) {
		
		assertEquals(expected.length, returned.length);
		
		//pas besoin de if else si on fait un assert avant
		int i=0;
		for(String n: expected) {
			assertEquals(n, returned[i]);
			i++;
		}
		
	}

}
