package com.fr.adaming.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.controller.AbstractTestMethods;
import com.fr.adaming.controller.IControllerTests;
import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.dto.PlanteUtilisateurUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.enums.EtatPlante;
import com.fr.adaming.enums.EtatSante;

@SpringBootTest(classes = ModuleWebApplication.class)
@AutoConfigureMockMvc
public class PlanteUtilisateurControllerTests extends AbstractTestMethods<PlanteUtilisateurUpdateDto>
		implements IControllerTests {
	
	// Parametres par defaut

	private static final String BASE_URL = "/planteUtilisateur";

	private PlanteUtilisateurUpdateDto makeNewUpdateDto() {
		// Creation du dto qu'on va utiliser pour la requete et aussi la comparaison
		PlanteUtilisateurUpdateDto dto = new PlanteUtilisateurUpdateDto();
		dto.setHealthStage(EtatSante.bonneSante);
		dto.setIdentifiant(1);
		dto.setPlantStage(EtatPlante.fleuri);
		String date = "2020-04-04";
		LocalDate localDate = LocalDate.parse(date);
		dto.setPlantingDate(localDate);
		dto.setSemiDate(localDate);

		UtilisateurUpdateDto utilisateurDto = new UtilisateurUpdateDto();
		utilisateurDto.setLastName("Stark");
		utilisateurDto.setIdentifier(1);
		utilisateurDto.setFirstName("tony");
		utilisateurDto.setMail("stark@stark.fr");
		utilisateurDto.setPwd("4TEST");
		utilisateurDto.setPseudo("IronMan");
		// ID EMAIL MDP PSEUDO NOM PRENOM NON NULLABLE

		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(69);
		depDto.setName("Rhone");

		JardinUpdateDto jardinDto = new JardinUpdateDto();
		jardinDto.setDept(depDto);
		jardinDto.setUser(utilisateurDto);
		jardinDto.setIdentifier(1);
		jardinDto.setName("Jardin1");

		PlanteModelUpdateDto planteModelDto = new PlanteModelUpdateDto();
		planteModelDto.setIdentifiant(2);
		planteModelDto.setScientifique("Hibiscus");

		dto.setGarden(jardinDto);
		dto.setModelPlant(planteModelDto);

		return dto;

	}

	@Test
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, ensoleillement, evapo_transpiration_potentielle, pluie, temperature_min, temperature_max, departement_id) VALUES (1, '2020-02-20', 1, 1, 5, 20, 25, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testCreatingEntityWithValidBody_shouldReturn200() throws Exception  {
		// Creation du dto qu'on va utiliser pour la requete et aussi la comparaison			
		
		PlanteUtilisateurUpdateDto dto = makeNewUpdateDto();

		ResponseDto<PlanteUtilisateurUpdateDto> responseDto = runMockMvc(BASE_URL, 200, dto, PlanteUtilisateurUpdateDto.class);

		// Verifier la responseDto
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Succes", responseDto.getMessage());
		assertNotNull(responseDto.getBody());
		


	}

	@Test
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testCreatingEntityWithInvalidBody_shouldReturn400() throws Exception {
		PlanteUtilisateur planteUtilisateur = new PlanteUtilisateur();
		String date = "2020-04-04";
		LocalDate localDate = LocalDate.parse(date);
		planteUtilisateur.setDatePlantation(localDate);
		planteUtilisateur.setDateSemis(localDate);
		planteUtilisateur.setEtatPlante(EtatPlante.fleuri);
		planteUtilisateur.setEtatSante(EtatSante.bonneSante);
		planteUtilisateur.setId(0);

		String responseAsString = runMockMvcLite("post", BASE_URL, 400, planteUtilisateur);

		assertThat(responseAsString).isEmpty();

	}

	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, ensoleillement, evapo_transpiration_potentielle, pluie, temperature_min, temperature_max, departement_id) VALUES (1, '2020-02-20', 1, 1, 5, 20, 25, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithValidId_shouldReturn200() throws Exception {
		String path = BASE_URL + "/1";
		ResponseDto<PlanteUtilisateurUpdateDto> responseDto = runMockMvc("delete", path, 200,
				PlanteUtilisateurUpdateDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertNull(responseDto.getBody());
		assertNotNull(responseDto.getMessage());

	}

	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithInvalidId_shouldReturn400() throws Exception {
		String path = BASE_URL + "/9001";
		ResponseDto<PlanteUtilisateurUpdateDto> responseDto = runMockMvc("delete", path, 400,
				PlanteUtilisateurUpdateDto.class);

		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertNull(responseDto.getBody());
		assertNotNull(responseDto.getMessage());
	}

	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, ensoleillement, evapo_transpiration_potentielle, pluie, temperature_min, temperature_max, departement_id) VALUES (1, '2020-02-20', 1, 1, 5, 20, 25, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id) values(1,'2020-04-04', '2020-04-04', 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testUpdatingEntityWithValidId_shouldReturn200() throws Exception {	
		
		PlanteUtilisateurUpdateDto dto = makeNewUpdateDto();
		dto.setHealthStage(EtatSante.morte);

		ResponseDto<PlanteUtilisateurUpdateDto> responseDto = runMockMvc("put", BASE_URL, 200, dto, PlanteUtilisateurUpdateDto.class);

		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Succes", responseDto.getMessage());
		assertNotNull(responseDto.getBody());

	}

	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, ensoleillement, evapo_transpiration_potentielle, pluie, temperature_min, temperature_max, departement_id) VALUES (1, '2020-02-20', 1, 1, 5, 20, 25, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id) values(1,'2020-04-04', '2020-04-04', 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testUpdatingEntityWithInvalidId_shouldReturn400() {
		try {
			
		
		PlanteUtilisateurUpdateDto dto = makeNewUpdateDto();
		dto.setIdentifiant(45);

		ResponseDto<PlanteUtilisateurUpdateDto> responseDto = runMockMvc("put", BASE_URL, 400, dto, PlanteUtilisateurUpdateDto.class);
		
		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertEquals("Id inconnu dans la BD", responseDto.getMessage());
		assertNull(responseDto.getBody());
		
		} catch (MismatchedInputException e) {
				e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, ensoleillement, evapo_transpiration_potentielle, pluie, temperature_min, temperature_max, departement_id) VALUES (1, '2020-02-20', 1, 1, 5, 20, 25, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id) values(1,'2020-04-04', '2020-04-04', 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithValidId_shouldReturn200() throws Exception {
		String path = BASE_URL + "/1";

		ResponseDto<PlanteUtilisateurUpdateDto> responseDto = runMockMvc("get", path, 200, PlanteUtilisateurUpdateDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Success", responseDto.getMessage());
		assertNotNull(responseDto.getBody());

	}

	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, ensoleillement, evapo_transpiration_potentielle, pluie, temperature_min, temperature_max, departement_id) VALUES (1, '2020-02-20', 1, 1, 5, 20, 25, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithInvalidId_shouldReturn400() throws Exception {

		String path = BASE_URL + "/45";

		ResponseDto<PlanteUtilisateurUpdateDto> responseDto = runMockMvc("get", path, 400,
				PlanteUtilisateurUpdateDto.class);
		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertEquals("Une entité avec cet ID n'existe pas dans la base de données", responseDto.getMessage());
		assertNull(responseDto.getBody());

	}

	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, ensoleillement, evapo_transpiration_potentielle, pluie, temperature_min, temperature_max, departement_id) VALUES (1, '2020-02-20', 1, 1, 5, 20, 25, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(2, 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingAllEntity_shouldReturn200() throws Exception {
		String path = BASE_URL + "/all/0";

		ResponseDto<Page<PlanteUtilisateurUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, PlanteUtilisateurUpdateDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Success", responseDto.getMessage());
		assertNotNull(responseDto.getBody());

	}
	
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, ensoleillement, evapo_transpiration_potentielle, pluie, temperature_min, temperature_max, departement_id) VALUES (1, '2020-02-20', 1, 1, 5, 20, 25, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(2, 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingByJardinValidId_shouldReturn200() throws Exception {
		String path = BASE_URL + "/jardin/1?page=0";

		ResponseDto<Page<PlanteUtilisateurUpdateDto>> responseDto = runMockMvc4Pages("get", path, 200, PlanteUtilisateurUpdateDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Succes", responseDto.getMessage());
		assertNotNull(responseDto.getBody());
	}
	
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, ensoleillement, evapo_transpiration_potentielle, pluie, temperature_min, temperature_max, departement_id) VALUES (1, '2020-02-20', 1, 1, 5, 20, 25, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(2, 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingByJardinInvalidId_shouldReturn200() throws Exception {
		String path = BASE_URL + "/jardin/0?page=0";

		ResponseDto<PlanteUtilisateurUpdateDto> responseDto = runMockMvc("get", path, 200, PlanteUtilisateurUpdateDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Jardin inexistant", responseDto.getMessage());

	}
	
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, ensoleillement, evapo_transpiration_potentielle, pluie, temperature_min, temperature_max, departement_id) VALUES (1, '2020-02-20', 1, 1, 5, 20, 25, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(2, 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingListeByJardinValidId_shouldReturn200() throws Exception {
		String path = BASE_URL + "/jardin/liste/1";

		ResponseDto<List<PlanteUtilisateurUpdateDto>> responseDto = runMockMvc4Lists("get", path, 200, PlanteUtilisateurUpdateDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Succes", responseDto.getMessage());
		assertNotNull(responseDto.getBody());
	}
	
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, ensoleillement, evapo_transpiration_potentielle, pluie, temperature_min, temperature_max, departement_id) VALUES (1, '2020-02-20', 1, 1, 5, 20, 25, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(2, 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingListeByJardinInvalidId_shouldReturn200() throws Exception {
		String path = BASE_URL + "/jardin/liste/0";

		ResponseDto<List<PlanteUtilisateurUpdateDto>> responseDto = runMockMvc4Lists("get", path, 200, PlanteUtilisateurUpdateDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Jardin inexistant", responseDto.getMessage());
		
	}
	
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, ensoleillement, evapo_transpiration_potentielle, pluie, temperature_min, temperature_max, departement_id) VALUES (1, '2020-02-20', 1, 1, 5, 20, 25, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(2, 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeleteByJardinValidId_shouldReturn200() throws Exception {
		String path = BASE_URL + "/jardin/1";

		ResponseDto<PlanteUtilisateurUpdateDto> responseDto = runMockMvc("delete", path, 200, PlanteUtilisateurUpdateDto.class);
		assertNotNull(responseDto);
		assertFalse(responseDto.isError());
		assertEquals("Suppression réussie", responseDto.getMessage());
		assertNull(responseDto.getBody());
	}
	
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, ensoleillement, evapo_transpiration_potentielle, pluie, temperature_min, temperature_max, departement_id) VALUES (1, '2020-02-20', 1, 1, 5, 20, 25, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(2, 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeleteByJardinInvalidId_shouldReturn200() throws Exception {
		String path = BASE_URL + "/jardin/0";

		ResponseDto<PlanteUtilisateurUpdateDto> responseDto = runMockMvc("delete", path, 400, PlanteUtilisateurUpdateDto.class);
		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertEquals("Erreur pendant la suppression de l'entité: 0", responseDto.getMessage());
		assertNull(responseDto.getBody());
	}
	

}
