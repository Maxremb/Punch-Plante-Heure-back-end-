package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.service.IMeteoService;
import com.fr.adaming.service.IService;
import com.fr.adaming.service.IServiceTests;

/**
 * Classe implémentant l'interface IServiceTests visant à tester les méthodes de MeteoService
 * @author Jeanne-Marie MATHEVET
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest(classes = ModuleServiceApplication.class)
public class MeteoServiceTests implements IServiceTests {
	
	@Autowired
	private IMeteoService servicemeteo;
	
	@Autowired
	private IService<Meteo> service;

	/**
	 * Methode visant à tester la suppression avec un ID valide
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override	
	public void testDeletingValidId_shouldReturnTrue() {
		assertTrue(service.deleteById(1));
		
	}
	/**
	 * Methode visant à tester la suppression avec ID non valide
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertFalse(service.deleteById(2));
		
	}
	
	/**
	 * Methode visant à tester la méthode READ ALL avec contenu
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadAllWithContent_shouldReturnPage() {
		assertTrue(service.readAll(0).getBody().toList().size() == 1);
		assertThat(service.readAll(0).getBody().toList().get(0)).isNotNull();
		
	}
	
	/**
	 * Methode visant à tester la methode READ ALL avec aucun contenu
	 */
	@Test
	@Override
	public void testReadAllNoContent_shouldReturnEmptyPage() {
		assertTrue(service.readAll(0).getBody().toList().isEmpty());
		
	}
	/**
	 * Methode visant à tester la methode READ BY ID avec id valide
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadByIdValidId_shouldReturnEntity() {
		assertThat(service.readById(1)).isNotNull();
		assertThat(service.readById(1).getBody()).isInstanceOf(Meteo.class);
		assertThat(service.readById(1).getBody()).hasFieldOrPropertyWithValue("temperatureMax", 25d);
	}
	
	/**
	 * Methode visant à tester la methode READ BY ID avec id non valide
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertThat(service.readById(2).getBody()).isNull();
		assertThat(service.readById(2).getMessage()).isEqualTo("Une entité avec cet ID n'existe pas dans la base de données");
		
	}
	
	/**
	 * Methode visant à tester la méthode EXIST BY ID avec id valide
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertTrue(service.existsById(1));
		
	}

	/**
	 * Methode visant à tester la méthode EXIST BY ID avec id valide
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		assertFalse(service.existsById(2));
		
	}
	
	/**
	 * Methode visant à tester la methode CREATE avec objet valide
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateValid_ShouldReturnEntity() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		
		Meteo meteo = new Meteo();
		meteo.setId(1);
		meteo.setTemperatureMax(25d);
		meteo.setTemperatureMin(20d);
		meteo.setPluie(5d);
		meteo.setEnsoleillement(120d);
		meteo.setEvapoTranspirationPotentielle(5d);
		meteo.setDate(LocalDate.parse("2020-01-01"));
		meteo.setDepartement(departement);
		
		ServiceResponse<Meteo> response = service.create(meteo);
		
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("id", 1);
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("temperatureMax", 25d);
		assertThat(response.getMessage()).isEqualTo("Création d'une météo dans la base de données");
	}
	
	/**
	 * Methode visant à tester la méthode CREATE avec un objet null
	 */
	@Test
	public void testCreateNull_ShouldReturnNull() {
		ServiceResponse<Meteo> response = service.create(null);
		
		assertThat(response.getBody()).isNull();
		assertThat(response.getMessage()).isEqualTo("Echec lors de la création d'une météo : entité NULLE");
	}
	
	/**
	 * Methode visant à tester la methode CREATE avec une date nulle
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateWithDateNull_ShouldReturnNull() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		
		Meteo meteo = new Meteo();
		meteo.setId(1);
		meteo.setTemperatureMax(25d);
		meteo.setTemperatureMin(20d);
		meteo.setPluie(5d);
		meteo.setEnsoleillement(120d);
		meteo.setEvapoTranspirationPotentielle(5d);
		meteo.setDate(null);
		meteo.setDepartement(departement);
		
		ServiceResponse<Meteo> response = service.create(meteo);
		
		assertThat(response.getBody()).isNull();
		assertThat(response.getMessage()).isEqualTo("Echec lors de la création d'une météo : la date est NULLE");
	}
	
	/**
	 * Methode visant à tester la methode CREATE avec un ID existant
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateExistingId_ShouldReturnNull() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		
		Meteo meteo = new Meteo();
		meteo.setId(1);
		meteo.setTemperatureMax(30d);
		meteo.setTemperatureMin(20d);
		meteo.setPluie(4d);
		meteo.setEnsoleillement(100d);
		meteo.setEvapoTranspirationPotentielle(6d);
		meteo.setDate(LocalDate.parse("2020-01-02"));
		meteo.setDepartement(departement);
		
		ServiceResponse<Meteo> response = service.create(meteo);
		
		assertThat(response.getBody()).isNull();
		assertThat(response.getMessage()).isEqualTo("Echec lors de la création d'une météo : l'ID est déjà existant");
	}
	
	/**
	 * Methode visant à tester la methode UPDATE avec un ID valide
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingExistingMeteo_ShouldReturnMeteo() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		
		Meteo meteo = new Meteo();
		meteo.setId(1);
		meteo.setTemperatureMax(30d);
		meteo.setTemperatureMin(20d);
		meteo.setPluie(4d);
		meteo.setEnsoleillement(100d);
		meteo.setEvapoTranspirationPotentielle(6d);
		meteo.setDate(LocalDate.parse("2020-01-02"));
		meteo.setDepartement(departement);
		
		ServiceResponse<Meteo> response = service.update(meteo);
		
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("temperatureMax", 30d);
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("temperatureMin", 20d);
		assertThat(response.getMessage()).isEqualTo("Mise à jour de la météo dans la base de données");
	}
	
	/**
	 * Methode visant à tester la methode UPDATE avec un ID invalide
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingWithInvalidId_ShouldReturnNull() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		
		Meteo meteo = new Meteo();
		meteo.setId(2);
		meteo.setTemperatureMax(30d);
		meteo.setTemperatureMin(20d);
		meteo.setPluie(4d);
		meteo.setEnsoleillement(100d);
		meteo.setEvapoTranspirationPotentielle(6d);
		meteo.setDate(LocalDate.parse("2020-01-02"));
		meteo.setDepartement(departement);
		
		ServiceResponse<Meteo> response = service.update(meteo);
		
		assertNull(response.getBody());
		assertThat(response.getMessage()).isEqualTo("Echec lors de la mise à jour d'une météo : l'ID est inexistant");
	}
	
	/**
	 * Methode visant à tester la methode UPDATE avec un ID invalide
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateNull_ShouldReturnNull() {
		ServiceResponse<Meteo> response = service.update(null);
		
		assertNull(response.getBody());
		assertThat(response.getMessage()).isEqualTo("Echec lors de la mise à jour d'une météo : entité NULLE");
	}
	
	/**
	 * Methode visant à tester la methode UPDATE avec une DATE nulle
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateWithDateNull_ShouldReturnNull() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		
		Meteo meteo = new Meteo();
		meteo.setId(1);
		meteo.setTemperatureMax(30d);
		meteo.setTemperatureMin(20d);
		meteo.setPluie(4d);
		meteo.setEnsoleillement(100d);
		meteo.setEvapoTranspirationPotentielle(6d);
		meteo.setDate(null);
		meteo.setDepartement(departement);
		
		ServiceResponse<Meteo> response = service.update(meteo);
		
		assertNull(response.getBody());
		assertThat(response.getMessage()).isEqualTo("Echec lors de la mise à jour d'une météo : la date est NULLE");
	}
	
	/**
	 * Methode visant à tester la methode READ BY DATE AND DEPARTEMENT avec des champs valides
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByDateAndDepartement_ShouldReturnMeteo() {
		
		ServiceResponse<Meteo> response = servicemeteo.readByDateAndDepartement(LocalDate.parse("2020-01-01"), 69);
		
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("temperatureMax", 25d);
		assertThat(response.getMessage()).isEqualTo("Recuperation de la meteo du departement indiqué et à la date indiquée");
	}
	
	/**
	 * Methode visant à tester la methode READ BY DATE AND DEPARTEMENT avec une date nulle
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByDateNullAndDepartement_ShouldReturnNull() {
		ServiceResponse<Meteo> response = servicemeteo.readByDateAndDepartement(null, 69);

		assertThat(response.getBody()).isNull();
		assertThat(response.getMessage()).isEqualTo("Echec lors de la récupération  de la météo : la date est NULLE et/ou le département n'existe pas dans la base de données");
	}
	
	/**
	 * Methode visant à tester la methode READ BY DATE AND DEPARTEMENT avec un departement inconnu
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByDateAndDepartementInvalid_ShouldReturnNull() {
		ServiceResponse<Meteo> response = servicemeteo.readByDateAndDepartement(LocalDate.parse("2020-01-01"), 71);
		
		assertThat(response.getBody()).isNull();
		assertThat(response.getMessage()).isEqualTo("Echec lors de la récupération  de la météo : la date est NULLE et/ou le département n'existe pas dans la base de données");
	}
	
	/**
	 * Methode visant à tester la methode READ BY DATE avec une date connue
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saone_et_loire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 27, 22, 10, 100, 5, '2020-01-01', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByDateValid_ShouldReturnOnePage() {
		
		ServiceResponse<Page<Meteo>> response = servicemeteo.readByDate(LocalDate.parse("2020-01-01"), 0);
		
		assertTrue(response.getBody().toList().size() == 2);
		assertThat(response.getBody().getContent().get(0)).hasFieldOrPropertyWithValue("id", 1);
		assertThat(response.getBody().getContent().get(1)).hasFieldOrPropertyWithValue("id", 2);
		assertThat(response.getMessage()).isEqualTo("Recupération de la météo à la date indiquée");
		
		
	}
	
	/**
	 * Methode visant à tester la methode READ BY DATE avec une date nulle
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saone_et_loire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 27, 22, 10, 100, 5, '2020-01-01', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByDateNull_ShouldReturnNull() {
		
		ServiceResponse<Page<Meteo>> response = servicemeteo.readByDate(null, 0);
		
		assertNull(response.getBody());
		assertThat(response.getMessage()).isEqualTo("Echec lors de la récupération de la météo : la date est NULLE");
		
	}
	
	/**
	 * Methode visant à tester la methode READ BY DATE avec une date inconnue
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saone_et_loire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 27, 22, 10, 100, 5, '2020-01-01', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByDateWithNoData_ShouldReturnOnePageEmpty() {
		
		ServiceResponse<Page<Meteo>> response = servicemeteo.readByDate(LocalDate.parse("2020-05-05"), 0);
		
		assertTrue(response.getBody().isEmpty());
	}
	
	/**
	 * Methode visant à tester la methode READ BY MONTH AND DEPARTEMENT pour un mois et un departement valides
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saone_et_loire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-03', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 27, 22, 10, 100, 5, '2020-01-07', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (3, 25, 20, 5, 120, 5, '2019-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (4, 27, 22, 10, 100, 5, '2020-05-05', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByMonthAndDepartement_ShouldReturnOneListOfMeteo() {
		
		ServiceResponse<List<Meteo>> response = servicemeteo.readByMonthAndDepartement(2020, 1, 69);
		
		assertNotNull(response);
		assertTrue(response.getBody().size() == 2);
		assertThat(response.getBody().get(0)).hasFieldOrPropertyWithValue("id", 1);
		assertThat(response.getBody().get(1)).hasFieldOrPropertyWithValue("id", 2);
	}
	
	/**
	 * Methode visant à tester la methode READ BY MONTH AND DEPARTEMENT pour un mois invalide
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saone_et_loire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-03', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 27, 22, 10, 100, 5, '2020-01-07', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (3, 25, 20, 5, 120, 5, '2019-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (4, 27, 22, 10, 100, 5, '2020-05-05', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByMonthInvalidAndDepartement_ShouldReturnABodyNull() {
		
		ServiceResponse<List<Meteo>> response = servicemeteo.readByMonthAndDepartement(2020, 15, 69);
		
		assertNotNull(response);
		assertNull(response.getBody());
		assertThat(response.getMessage()).isEqualTo("Echec lors de la récupération des meteos : le mois est invalide");
	}
	
	/**
	 * Methode visant à tester la methode READ BY MONTH AND DEPARTEMENT pour un mois invalide
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saone_et_loire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-03', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 27, 22, 10, 100, 5, '2020-01-07', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (3, 25, 20, 5, 120, 5, '2019-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (4, 27, 22, 10, 100, 5, '2020-05-05', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByMonthInvalid2AndDepartement_ShouldReturnABodyNull() {
		
		ServiceResponse<List<Meteo>> response = servicemeteo.readByMonthAndDepartement(2020, 0, 69);
		
		assertNotNull(response);
		assertNull(response.getBody());
		assertThat(response.getMessage()).isEqualTo("Echec lors de la récupération des meteos : le mois est invalide");
	}
	
	/**
	 * Methode visant à tester la methode READ BY MONTH AND DEPARTEMENT pour un departement invalide
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saone_et_loire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-03', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 27, 22, 10, 100, 5, '2020-01-07', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (3, 25, 20, 5, 120, 5, '2019-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (4, 27, 22, 10, 100, 5, '2020-05-05', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByMonthAndDepartementInvalid_ShouldReturnABodyNull() {
		
		ServiceResponse<List<Meteo>> response = servicemeteo.readByMonthAndDepartement(2020, 1, 75);
		
		assertNotNull(response);
		assertNull(response.getBody());
		assertThat(response.getMessage()).isEqualTo("Echec lors de la récupération des meteos : le departement est inconnu");
	}
}
