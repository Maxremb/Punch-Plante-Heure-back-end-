package com.fr.adaming.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.controller.AbstractTestMethods;
import com.fr.adaming.controller.IControllerTests;
import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.MeteoCreateDto;
import com.fr.adaming.dto.MeteoUpdateDto;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest(classes = ModuleWebApplication.class)
@AutoConfigureMockMvc
public class MeteoControllerTests extends AbstractTestMethods<MeteoUpdateDto> implements IControllerTests {

	// Parametres par defaut
	private static final String BASE_URL = "/meteo";
	
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testCreatingEntityWithValidBody_shouldReturn200() throws Exception {
		DepartementDto dtoD = new DepartementDto();
		dtoD.setDepNum(71);
		dtoD.setName("saoneetloire");
		MeteoCreateDto dto = new MeteoCreateDto();
		dto.setTempMax(25d);
		dto.setTempMin(20d);
		dto.setRain(0);
		dto.setRadiation(0);
		dto.setEtp(0);
		dto.setDateMeteo(LocalDate.parse("2020-01-01"));
		dto.setDepartement(dtoD);
		
		ResponseDto<MeteoUpdateDto> response = runMockMvc("post", BASE_URL, 200, dto, MeteoUpdateDto.class);
		
		assertNotNull(response);
		assertFalse(response.isError());
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("tempMax", 25d);
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("tempMin", 20d);
		assertThat(response.getMessage()).isEqualTo("Création d'une météo dans la base de données");
	}
	
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testCreatingEntityWithInvalidBody_shouldReturn400() throws Exception {
		DepartementDto dtoD = new DepartementDto();
		dtoD.setDepNum(71);
		dtoD.setName("saoneetloire");
		MeteoCreateDto dto = new MeteoCreateDto();
		dto.setTempMax(25d);
		dto.setTempMin(20d);
		dto.setRain(0);
		dto.setRadiation(0);
		dto.setEtp(0);
		dto.setDateMeteo(null);
		dto.setDepartement(dtoD);
		
		ResponseDto<MeteoUpdateDto> response = runMockMvc("post", BASE_URL, 400, dto, MeteoUpdateDto.class);
		
		assertNotNull(response);
		assertTrue(response.isError());
		assertNull(response.getBody());
		assertThat(response.getMessage()).isEqualTo("Echec lors de la création d'une météo : la date est NULLE");
		
	}
	
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithValidId_shouldReturn200() throws Exception {
		ResponseDto<MeteoUpdateDto> response = runMockMvc("delete", BASE_URL+"/1", 200, MeteoUpdateDto.class);
		
		assertNotNull(response);
		assertFalse(response.isError());
		assertNull(response.getBody());
		assertThat(response.getMessage()).isEqualTo("Suppression réussie");
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithInvalidId_shouldReturn400() throws Exception {
		ResponseDto<MeteoUpdateDto> response = runMockMvc("delete", BASE_URL+"/2", 400, MeteoUpdateDto.class);
		
		assertNotNull(response);
		assertTrue(response.isError());
		assertNull(response.getBody());
		assertThat(response.getMessage()).isEqualTo("Erreur pendant la suppression de l'entité: 2");
		
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testUpdatingEntityWithValidId_shouldReturn200() throws Exception {
		DepartementDto dtoD = new DepartementDto();
		dtoD.setDepNum(69);
		dtoD.setName("rhone");
		MeteoUpdateDto dto = new MeteoUpdateDto();
		dto.setIdentifier(1);
		dto.setTempMax(30d);
		dto.setTempMin(22d);
		dto.setRain(5d);
		dto.setRadiation(120d);
		dto.setEtp(5d);
		dto.setDateMeteo(LocalDate.parse("2020-01-01"));
		dto.setDepartement(dtoD);
		
		ResponseDto<MeteoUpdateDto> response = runMockMvc("put", BASE_URL, 200, dto, MeteoUpdateDto.class);
		
		assertNotNull(response);
		assertFalse(response.isError());
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("tempMax", 30d);
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("tempMin", 22d);
		assertThat(response.getMessage()).isEqualTo("Mise à jour de la météo dans la base de données");
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testUpdatingEntityWithInvalidId_shouldReturn400() throws Exception {
		DepartementDto dtoD = new DepartementDto();
		dtoD.setDepNum(69);
		dtoD.setName("rhone");
		MeteoUpdateDto dto = new MeteoUpdateDto();
		dto.setIdentifier(2);
		dto.setTempMax(30d);
		dto.setTempMin(22d);
		dto.setRain(5d);
		dto.setRadiation(120d);
		dto.setEtp(5d);
		dto.setDateMeteo(LocalDate.parse("2020-01-01"));
		dto.setDepartement(dtoD);
		
		ResponseDto<MeteoUpdateDto> response = runMockMvc("put", BASE_URL, 400, dto, MeteoUpdateDto.class);
		
		assertNotNull(response);
		assertTrue(response.isError());
		assertThat(response.getMessage()).isEqualTo("Echec lors de la mise à jour d'une météo : l'ID est inexistant");
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithValidId_shouldReturn200() throws Exception {
		ResponseDto<MeteoUpdateDto> response = runMockMvc("get", BASE_URL+"/1", 200, MeteoUpdateDto.class);
		
		assertNotNull(response);
		assertFalse(response.isError());
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("tempMax", 25d);
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("identifier", 1);
		
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithInvalidId_shouldReturn400() throws Exception {
		ResponseDto<MeteoUpdateDto> response = runMockMvc("get", BASE_URL+"/2", 400, MeteoUpdateDto.class);
		
		assertNotNull(response);
		assertTrue(response.isError());
		assertNull(response.getBody());
		assertThat(response.getMessage()).isEqualTo("Une entité avec cet ID n'existe pas dans la base de données");
		
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 28, 20, 5, 100, 5, '2020-05-05', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingAllEntity_shouldReturn200() throws Exception {
		ResponseDto<Page<MeteoUpdateDto>> response = runMockMvc4Pages("get", BASE_URL+"/all/0", 200, MeteoUpdateDto.class);
		
		assertNotNull(response);
		assertFalse(response.isError());
		assertTrue(response.getBody().getContent().size() == 2);
		assertThat(response.getBody().getContent().get(0)).hasFieldOrPropertyWithValue("tempMax", 25d);
		assertThat(response.getBody().getContent().get(1)).hasFieldOrPropertyWithValue("tempMax", 28d);
	}
	
	/**
	 * Methode de test visant à tester l'affichage des meteos par date donnée en paramètre
	 * @throws Exception exception
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 28, 20, 5, 100, 5, '2020-05-05', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (3, 30, 22, 5, 100, 5, '2020-05-05', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingAllEntityByDate_shouldReturn200() throws Exception {
		ResponseDto<Page<MeteoUpdateDto>> response = runMockMvc4Pages("get", BASE_URL+"/date?date=2020-05-05&page=0", 200, MeteoUpdateDto.class);
		
		assertNotNull(response);
		assertFalse(response.isError());
		assertTrue(response.getBody().getContent().size() == 2);
		assertThat(response.getBody().getContent().get(0)).hasFieldOrPropertyWithValue("tempMax", 28d);
		assertThat(response.getBody().getContent().get(1)).hasFieldOrPropertyWithValue("tempMax", 30d);
	}
	
	/**
	 * Methode de test visant à tester l'affichage des meteos si la date donnée en paramètre est inconnu
	 * @throws Exception exception
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 28, 20, 5, 100, 5, '2020-05-05', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (3, 30, 22, 5, 100, 5, '2020-05-05', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingAllEntityByDateInvalid_shouldReturn200() throws Exception {
		ResponseDto<Page<MeteoUpdateDto>> response = runMockMvc4Pages("get", BASE_URL+"/date?date=2020-02-02&page=0", 200, MeteoUpdateDto.class);
		
		assertNotNull(response);
		assertFalse(response.isError());
		assertThat(response.getBody().getContent()).isEmpty();
		
	}
	
	/**
	 * Methode de test visant à tester l'affichage d'une meteo pour une date et un departement donnés en paramètres
	 * @throws Exception exception
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 28, 20, 5, 100, 5, '2020-05-05', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (3, 30, 22, 5, 100, 5, '2020-05-05', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingEntityByDateAndDepartement_shouldReturn200() throws Exception {
		ResponseDto<MeteoUpdateDto> response = runMockMvc("get", BASE_URL+"/datedepartement?date=2020-05-05&numero=69", 200, MeteoUpdateDto.class);
		
		assertNotNull(response);
		assertFalse(response.isError());
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("identifier", 2);
		assertThat(response.getMessage()).isEqualTo("Recuperation de la meteo du departement indiqué et à la date indiquée");
		
	}
	
	/**
	 * Methode de test visant à tester l'affichage d'une meteo si le num de departement est inconnu
	 * @throws Exception exception
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 28, 20, 5, 100, 5, '2020-05-05', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (3, 30, 22, 5, 100, 5, '2020-05-05', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingEntityByDateAndDepartementInvalid_shouldReturn400() throws Exception {
		ResponseDto<MeteoUpdateDto> response = runMockMvc("get", BASE_URL+"/datedepartement?date=2020-05-05&numero=75", 400, MeteoUpdateDto.class);
		
		assertNotNull(response);
		assertTrue(response.isError());
		assertNull(response.getBody());
		assertThat(response.getMessage()).isEqualTo("Echec lors de la récupération  de la météo : la date est NULLE et/ou le département n'existe pas dans la base de données");
		
	}

}
