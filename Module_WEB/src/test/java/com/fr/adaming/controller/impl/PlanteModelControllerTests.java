package com.fr.adaming.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.controller.AbstractTestMethods;
import com.fr.adaming.controller.IControllerTests;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.enums.Sol;

/** Testes du controller pour plante model
 * @author Gregoire
 *
 */
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
		assertEquals("Création de la plante modele réussie", responseDto.getMessage());
		assertNotNull(responseDto.getBody());
	}

	@Test
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testCreatingEntityWithInvalidBody_shouldReturn400() throws Exception {

		PlanteModel plante = new PlanteModel(identifiant, commun, scientifique, picture);
		
		String responseAsString = runMockMvcLite("post", BASE_URL, 400, plante);
		
		assertThat(responseAsString).isEmpty();

	}
	
	/** Teste de la creation avec un nom scientifique null
	 * @throws Exception
	 */
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
	public void testDeletingEntityWithValidId_shouldReturn200() throws Exception {

		String path = BASE_URL + "/1";
		ResponseDto<PlanteModelUpdateDto> responseDto = runMockMvc("delete", path, 200, PlanteModelUpdateDto.class);
		
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertNull(responseDto.getBody());
		assertNotNull(responseDto.getMessage());
	}

	@Test
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testDeletingEntityWithInvalidId_shouldReturn400() throws Exception {


		String path = BASE_URL + "/9001";
		ResponseDto<PlanteModelUpdateDto> responseDto = runMockMvc("delete", path, 400, PlanteModelUpdateDto.class);
		
		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertNull(responseDto.getBody());
		assertNotNull(responseDto.getMessage());

	}

	@Test
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testUpdatingEntityWithValidId_shouldReturn200() throws Exception {

		PlanteModelUpdateDto dto = makeNewUpdateDto();
		dto.setCommun("bob");
		
		ResponseDto<PlanteModelUpdateDto> responseDto = runMockMvc("put", BASE_URL, 200, dto, PlanteModelUpdateDto.class);		
		
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertNotNull(responseDto.getBody());
		assertNotNull(responseDto.getMessage());

	}


	@Test
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testUpdatingEntityWithInvalidId_shouldReturn400() throws Exception {

		PlanteModelUpdateDto dto = makeNewUpdateDto();
		dto.setCommun("bob");
		dto.setIdentifiant(12345);
		
		ResponseDto<PlanteModelUpdateDto> responseDto = runMockMvc("put", BASE_URL, 400, dto, PlanteModelUpdateDto.class);
		
		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertEquals("Mise à jour non réalisée : cet id n'existe pas dans la base de donnée", responseDto.getMessage());
		assertNull(responseDto.getBody());

	}

	@Test
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testReadingEntityWithValidId_shouldReturn200() throws Exception {

		String path=BASE_URL + "/1";
		
		ResponseDto<PlanteModelUpdateDto> responseDto = runMockMvc("get", path, 200, PlanteModelUpdateDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Success",responseDto.getMessage());
		assertNotNull(responseDto.getBody());

	}

	@Test
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testReadingEntityWithInvalidId_shouldReturn400() throws Exception {
		
		String path=BASE_URL + "/100000";
		
		ResponseDto<PlanteModelUpdateDto> responseDto = runMockMvc("get", path, 400, PlanteModelUpdateDto.class);
		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertEquals("Une entité avec cet ID n'existe pas dans la base de données",responseDto.getMessage());
		assertNull(responseDto.getBody());

	}


	@Test
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (2, 'Alice', 'Alicium')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testReadingAllEntity_shouldReturn200() throws Exception {

String path=BASE_URL + "/all/0";
		
		ResponseDto<Page<PlanteModelUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, PlanteModelUpdateDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Succes",responseDto.getMessage());
		assertNotNull(responseDto.getBody());

	}
	
	/** Teste de readByNom avec un nom qui existe dans la base de données
	 * @throws Exception
	 */
	@Test
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (2, 'Alice', 'Alicium')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByNom_shouldReturn200() throws Exception {
		
		String path = BASE_URL + "/nom?page=0&nom=Alicium";
		
		ResponseDto<Page<PlanteModelUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, PlanteModelUpdateDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Success",responseDto.getMessage());
		assertThat(responseDto.getBody().toList()).asList().isNotEmpty();
		assertTrue(responseDto.getBody().toList().size() == 1);
		
	}

	
	
	// *** Méthodes privés ***
	
	/** Créé une nouvelle dto avec les valeurs des attributs par défaut
	 * @return Une PlanteModelUpdateDto
	 */
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

}
