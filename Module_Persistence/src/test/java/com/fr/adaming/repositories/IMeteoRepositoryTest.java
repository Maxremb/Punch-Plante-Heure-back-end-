package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;

/**
 * Classe visant à tester les méthodes de IMeteoRepositoryTest
 * @author Jeanne-Marie MATHEVET
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IMeteoRepositoryTest {
	
	@Autowired
	private IMeteoRepository repo;
	
	/**
	 * Methode visant à tester la recherche de météo pour une date donnée existante
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 24, 21, 8, 120, 5, '2020-01-01', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingMeteoByDate_shouldReturnAListOfMeteo() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Meteo> retour = repo.findByDate(LocalDate.parse("2020-01-01"), pageable);
		assertThat(retour).isNotNull();
		assertThat(retour.toList()).isNotEmpty().hasSize(2);
	    assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("temperatureMax", 25d);
	    assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("id", 2);
	    assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("temperatureMax", 24d);
	}
	
	/**
	 * Methode visant à tester la recherche de météo pour une date donnée non existante
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 24, 21, 8, 120, 5, '2020-01-01', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingMeteoByDateInvalid_shouldReturnAListEmpty() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Meteo> retour = repo.findByDate(LocalDate.parse("2020-05-05"), pageable);
		assertThat(retour).isNotNull();
		assertThat(retour.toList()).isEmpty();
	    
	}
	
	/**
	 * Methode visant à tester la recherche de météo pour une date donnée existante et un departement existant
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 24, 21, 8, 120, 5, '2020-01-01', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingMeteoByDateAndDepartement_shouldReturnOneMeteo() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		Meteo retour = repo.findByDateAndDepartement(LocalDate.parse("2020-01-01"), departement);
		
		assertThat(retour).isNotNull();
		assertThat(retour).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(retour).hasFieldOrPropertyWithValue("temperatureMax", 25d);
	    assertThat(retour).hasFieldOrPropertyWithValue("temperatureMin", 20d);
	}
	
	/**
	 * Methode visant à tester la recherche de météo pour une date inexistant et un departement inexistant
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 24, 21, 8, 120, 5, '2020-01-01', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingMeteoByDateAndDepartement_shouldReturnNull() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		Meteo retour = repo.findByDateAndDepartement(LocalDate.parse("2020-05-05"), departement);
		
		assertNull(retour);
		
	}
	
	/**
	 * Methode visant à tester la recherche de météo pour une date inexistant et un departement inexistant
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 24, 21, 8, 120, 5, '2020-01-04', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingMeteoByNumeroDep_shouldReturnOnePageOfMeteo() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Meteo> retour = repo.findMeteoByNumeroDep(pageable, 69);
		assertNotNull(retour);
		
		assertThat(retour.toList()).isNotEmpty().hasSize(2);
	    assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("temperatureMax", 25d);
	    assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("id", 2);
	    assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("temperatureMax", 24d);
		
	}
	
	/**
	 * Methode visant à tester la recherche de météo pour une date inexistant et un departement inexistant
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 24, 21, 8, 120, 5, '2020-01-04', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingMeteoByNumeroDep_shouldReturnNull() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Meteo> retour = repo.findMeteoByNumeroDep(pageable, 71);
		
		assertThat(retour).isNotNull();
		assertThat(retour.toList()).isEmpty();
				
	}
	
	/**
	 * Methode visant à tester la recherche de météo pour un mois et une année donné en parametre et un departement donné en paramètre
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 24, 21, 8, 120, 5, '2020-01-04', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (3, 30, 20, 7, 120, 5, '2019-01-07', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (4, 28, 21, 8, 120, 5, '2020-03-03', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingMeteoByMonthAndDepartement_shouldReturnOneList() {
		List<Meteo> retour = repo.findMeteoByMonthAndDepartement(2020, 1, 69);
		
		assertThat(retour).isNotNull();
		assertThat(retour.size()).isEqualTo(2);
		assertThat(retour.get(0)).hasFieldOrPropertyWithValue("temperatureMax", 25d);
		assertThat(retour.get(1)).hasFieldOrPropertyWithValue("temperatureMax", 24d);
		
	}
	
	/**
	 * Methode visant à tester la recherche de météo pour un mois donné en parametre et un departement inconnu
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (71, 'saoneetloire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (2, 24, 21, 8, 120, 5, '2020-01-04', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (3, 30, 20, 7, 120, 5, '2019-01-07', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (4, 28, 21, 8, 120, 5, '2020-03-03', 71)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingMeteoByMonthAndDepartementInvalid_shouldReturnOneListEmpty() {
		List<Meteo> retour = repo.findMeteoByMonthAndDepartement(2020, 1, 75);
		
		assertThat(retour).isNotNull();
		assertThat(retour).isEmpty();	
	}
	
}
