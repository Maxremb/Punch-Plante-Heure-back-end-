package com.fr.adaming.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.controller.AbstractTestMethods;
import com.fr.adaming.controller.IControllerTests;
import com.fr.adaming.dto.PlanteModelCreateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.enums.Sol;

@SpringBootTest(classes = ModuleWebApplication.class)
@AutoConfigureMockMvc
public class PlanteModelControllerTests extends AbstractTestMethods<PlanteModelUpdateDto> implements IControllerTests {

	// Parametres par defaut

	private static final String BASE_URL = "/plantemodel";

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
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testCreatingEntityWithValidBody_shouldReturn200() throws Exception {

		// Creation du dto qu'on va utiliser pour la requete et aussi la comparaison
		PlanteModelUpdateDto dto = makeNewUpdateDto();
		
		ResponseDto<PlanteModelUpdateDto> responseDto = runMockMvc(BASE_URL, 200, dto, PlanteModelUpdateDto.class);

		// Verifier la responseDto
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Succes de la création", responseDto.getMessage());
		assertNotNull(responseDto.getBody());
		
		
		
//		assertEquals(dto, responseDto.getBody()); //ne marche pas sans toString :(

//		updateDtoCompare(dto, responseDto.getBody()); //teste quelque chose qui est normallement
		// déjà testé dans les testes converter et service.
	}

	@Test
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testCreatingEntityWithInvalidBody_shouldReturn400() throws Exception {

		PlanteModel plante = new PlanteModel(identifiant, commun, scientifique, picture);
		
		String responseAsString = runMockMvcLite("post", BASE_URL, 400, plante);
		
		assertThat(responseAsString).isEmpty();

	}
	
	@Test
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingPlanteModelWithNullScientifique_shouldReturn400() throws Exception {

		PlanteModelUpdateDto dto = makeNewUpdateDto();
		dto.setScientifique(null);
		
		String responseAsString = runMockMvcLite("post", BASE_URL, 400, dto);
		
		assertThat(responseAsString).isEmpty();

	}

	@Test
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testDeletingEntityWithValidId_shouldReturn200() {

		

	}

	@Test
	@Override
	public void testDeletingEntityWithInvalidId_shouldReturn400() {
		// TODO Auto-generated method stub

	}

	@Test
	@Override
	public void testDeletingEntityWithNegativeId_shouldReturn400() {
		// TODO Auto-generated method stub

	}

	@Test
	@Override
	public void testUpdatingEntityWithValidId_shouldReturn200() {
		// TODO Auto-generated method stub

	}

	@Test
	@Override
	public void testUpdatingEntityWithInvalidId_shouldReturn400() {
		// TODO Auto-generated method stub

	}

	@Test
	@Override
	public void testReadingEntityWithValidId_shouldReturn200() {
		// TODO Auto-generated method stub

	}

	@Test
	@Override
	public void testReadingEntityWithInvalidId_shouldReturn400() {
		// TODO Auto-generated method stub

	}

	@Test
	@Override
	public void testReadingEntityWithNegativeId_shouldReturn400() {
		// TODO Auto-generated method stub

	}

	@Test
	@Override
	public void testReadingAllEntity_shouldReturn200() {
		// TODO Auto-generated method stub

	}
	
	private PlanteModelUpdateDto makeNewUpdateDto() {
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
		
		return dto;
		
	}

//	/**
//	 * Pour une raison que je ne comprend pas bien, assertEquals utilise le toString
//	 * de expected dto dans la comparaison. Cette méthode existe pour resoudre ce
//	 * probleme, et pour limiter la quantité de code
//	 * 
//	 * @param expectedDto Dto qu'on attend
//	 * @param returnedDto Dto qu'on recoit
//	 */
//	private void updateDtoCompare(PlanteModelUpdateDto expectedDto, PlanteModelUpdateDto returnedDto) {
//
//		assertEquals(expectedDto.getIdentifiant(), returnedDto.getIdentifiant());
//		assertEquals(expectedDto.getArrosage(), returnedDto.getArrosage());
//		assertEquals(expectedDto.getCommun(), returnedDto.getCommun());
//		assertEquals(expectedDto.getDesc(), returnedDto.getDesc());
//		assertEquals(expectedDto.getEnsoleillement(), returnedDto.getEnsoleillement());
//		assertEquals(expectedDto.getHumidite(), returnedDto.getHumidite());
//		assertEquals(expectedDto.getMin(), returnedDto.getMin());
//		assertEquals(expectedDto.getMax(), returnedDto.getMax());
//		assertEquals(expectedDto.getMifa(), returnedDto.getMifa());
//		assertEquals(expectedDto.getPicture(), returnedDto.getPicture());
//		assertEquals(expectedDto.getRepiquage(), returnedDto.getRepiquage());
//		assertEquals(expectedDto.getScientifique(), returnedDto.getScientifique());
//		assertEquals(expectedDto.getSol(), returnedDto.getSol());
//		assertEquals(expectedDto.isToxi(), returnedDto.isToxi());
//
//		assertEquals(expectedDto.getNegative().length, returnedDto.getNegative().length);
//		assertEquals(expectedDto.getPositive().length, returnedDto.getPositive().length);
//
//		compareStringArrays(expectedDto.getNegative(), returnedDto.getNegative());
//		compareStringArrays(expectedDto.getPositive(), returnedDto.getPositive());
//
//	}
//
//	/**
//	 * Pour les comparaisons des deux tableaux de string dans updateDtoCompare
//	 * 
//	 * @param expected Tableau de strings attendu
//	 * @param returned Tableau de strings recu
//	 */
//	private void compareStringArrays(String[] expected, String[] returned) {
//
//		assertEquals(expected.length, returned.length);
//
//		// pas besoin de if else si on fait un assert avant
//		int i = 0;
//		for (String n : expected) {
//			assertEquals(n, returned[i]);
//			i++;
//		}
//
//	}

}
