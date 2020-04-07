package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.entity.Utilisateur;

/**
 * Classe responsable des test repository pour l'entité Jardin
 * @author Clara Cadet / Isaline MILLET
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IJardinRepositoryTests {

	@Autowired
	private IJardinRepository repo;
	
	/**
	 * Permet de tester la recherche par un nom existant dans la base de données
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (1, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByNom_shouldReturnListOfOneJardin() {
		//préparer les inputs
		String nomInput = "nom4Test";
		Meteo meteo = new Meteo();
		meteo.setId(1);
		meteo.setPluie(5);
		meteo.setDate(LocalDate.parse("2020-02-20"));
		meteo.setTemperatureMax(20);
		List<Meteo> listMeteo = new ArrayList<>();
		listMeteo.add(meteo);
		Departement dep = new Departement();
		dep.setNumeroDep(1);
		dep.setNom("nomTestDepartement");
		Utilisateur util = new Utilisateur();
		util.setId(1);
		util.setNom("nomTestUtilisateur");
		
		//invoquer l'appli
		Pageable pageable = PageRequest.of(0, 6);
		Page<Jardin> result = repo.findByNom(pageable, nomInput);
		
		//assertion
		assertThat(result).isNotNull();
	    assertThat(result.toList()).isNotEmpty().hasSize(1);
	    assertThat(result.toList().get(0)).hasFieldOrPropertyWithValue("nom", nomInput);
	    assertThat(result.toList().get(0)).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(result.toList().get(0).getUtilisateur()).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(result.toList().get(0).getUtilisateur()).hasFieldOrPropertyWithValue("nom", "nomTestUtilisateur");
	    assertThat(result.toList().get(0).getDepartement()).hasFieldOrPropertyWithValue("numeroDep", 1);
	    assertThat(result.toList().get(0).getDepartement()).hasFieldOrPropertyWithValue("nom", "nomTestDepartement");
	}
	
	/**
	 * Permet de tester la recherche par un nom non existant dans la base de données
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (1, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByNotExistingNom_shouldReturnEmptyList() {
		//préparer les inputs
		String nomInput = "nomInexistant";
		
		//invoquer l'appli
		Pageable pageable = PageRequest.of(0, 6);
		Page<Jardin> result = repo.findByNom(pageable, nomInput);
		
		//assertion
	    assertThat(result).isNotNull();
	    assertThat(result.toList()).isEmpty();
	}
	
	/**
	 * Permet de tester la recherche par un identifiant utilisateur existant dans la base de données
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (1, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByUtilisateur_shouldReturnListOfOneJardin() {
		//préparer les inputs
		String nomInput = "nom4Test";
		Meteo meteo = new Meteo();
		meteo.setId(1);
		meteo.setPluie(5);
		meteo.setDate(LocalDate.parse("2020-02-20"));
		meteo.setTemperatureMax(20);
		List<Meteo> listMeteo = new ArrayList<>();
		listMeteo.add(meteo);
		Departement dep = new Departement();
		dep.setNumeroDep(1);
		dep.setNom("nomTestDepartement");
		Utilisateur util = new Utilisateur();
		util.setId(1);
		util.setNom("nomTestUtilisateur");
		
		//invoquer l'appli
		Pageable pageable = PageRequest.of(0, 6);
		Page<Jardin> result = repo.trouveParUtilisateur(pageable, 1);
		
		//assertion
		assertThat(result).isNotNull();
	    assertThat(result.toList()).isNotEmpty().hasSize(1);
	    assertThat(result.toList().get(0)).hasFieldOrPropertyWithValue("nom", nomInput);
	    assertThat(result.toList().get(0)).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(result.toList().get(0).getUtilisateur()).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(result.toList().get(0).getUtilisateur()).hasFieldOrPropertyWithValue("nom", "nomTestUtilisateur");
	    assertThat(result.toList().get(0).getDepartement()).hasFieldOrPropertyWithValue("numeroDep", 1);
	    assertThat(result.toList().get(0).getDepartement()).hasFieldOrPropertyWithValue("nom", "nomTestDepartement");
	}
	
	/**
	 * Permet de tester la recherche par un identifiant utilisateur non existant dans la base de données
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (1, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByNotExistingUtilisateur_shouldReturnEmptyList() {
		//invoquer l'appli
		Pageable pageable = PageRequest.of(0, 6);
		Page<Jardin> result = repo.trouveParUtilisateur(pageable, 2);
		
		//assertion
		assertThat(result).isNotNull();
	    assertThat(result.toList()).isEmpty();
	}
	
	/**
	 * Permet de tester la recherche par un numéro de département existant dans la base de données
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (1, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByDepartement_shouldReturnListOfOneJardin() {
		//préparer les inputs
		String nomInput = "nom4Test";
		Meteo meteo = new Meteo();
		meteo.setId(1);
		meteo.setPluie(5);
		meteo.setDate(LocalDate.parse("2020-02-20"));
		meteo.setTemperatureMax(20);
		List<Meteo> listMeteo = new ArrayList<>();
		listMeteo.add(meteo);
		Departement dep = new Departement();
		dep.setNumeroDep(1);
		dep.setNom("nomTestDepartement");
		Utilisateur util = new Utilisateur();
		util.setId(1);
		util.setNom("nomTestUtilisateur");
		
		//invoquer l'appli
		Pageable pageable = PageRequest.of(0, 6);
		Page<Jardin> result = repo.trouveParDepartement(pageable, 1);
		
		//assertion
		assertThat(result).isNotNull();
	    assertThat(result.toList()).isNotEmpty().hasSize(1);
	    assertThat(result.toList().get(0)).hasFieldOrPropertyWithValue("nom", nomInput);
	    assertThat(result.toList().get(0)).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(result.toList().get(0).getUtilisateur()).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(result.toList().get(0).getUtilisateur()).hasFieldOrPropertyWithValue("nom", "nomTestUtilisateur");
	    assertThat(result.toList().get(0).getDepartement()).hasFieldOrPropertyWithValue("numeroDep", 1);
	    assertThat(result.toList().get(0).getDepartement()).hasFieldOrPropertyWithValue("nom", "nomTestDepartement");
	}
	
	/**
	 * Permet de tester la recherche par un numéro de département non existant dans la base de données
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (1, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByNotExistingDepartement_shouldReturnListOfOneJardin() {
		//invoquer l'appli
		Pageable pageable = PageRequest.of(0, 6);
		Page<Jardin> result = repo.trouveParDepartement(pageable, 2);
		
		//assertion
		assertThat(result).isNotNull();
	    assertThat(result.toList()).isEmpty();
	}
	
}
