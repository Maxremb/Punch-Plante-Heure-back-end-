package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.service.IJardinService;
import com.fr.adaming.service.IService;
import com.fr.adaming.service.IServiceTests;

/**
 * Cette classe teste la couche service de l'entité Jardin
 * Elle implémente une interface pour le test des méthodes CRUD (read All, read by Id, exist by Id, delete by Id) et définit ses propres méthodes de test pour les autres. 
 * 
 * @author Isaline MILLET / Clara CADET
 * @since 0.0.1
 *
 */
@SpringBootTest(classes = ModuleServiceApplication.class)
public class JardinServiceTest implements IServiceTests {

	@Autowired
	private IJardinService serviceJardin;

	@Autowired
	private IService<Jardin> service;


	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingValidId_shouldReturnTrue() {
		assertTrue(service.deleteById(1));
	}

	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertFalse(service.deleteById(2));
	}

	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadAllWithContent_shouldReturnPage() {
		assertTrue(service.readAll(0).getBody().toList().size() == 1);
		assertThat(service.readAll(0).getBody().toList().get(0)).isNotNull();
	}

	@Test
	@Override
	public void testReadAllNoContent_shouldReturnEmptyPage() {
		assertTrue(service.readAll(0).getBody().toList().isEmpty());
	}

	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadByIdValidId_shouldReturnEntity() {
		assertThat(service.readById(1).getBody()).isNotNull().hasFieldOrPropertyWithValue("nom", "nom4Test");
	}


	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertThat(service.readById(2).getBody()).isNull();
		assertThat(service.readById(2).getMessage()).isEqualTo("Une entité avec cet ID n'existe pas dans la base de données");
	}


	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertTrue(service.existsById(1));
	}


	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		assertFalse(service.existsById(2));

	}

//	/**
//	 * Cette méthode teste la création d'un jardin - conditions valides
//	 */
//	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Test
//	public void testCreateValid_ShouldReturnEntity() {
//		Utilisateur user = new Utilisateur();
//		Departement dep = new Departement();
//		Jardin jardin = new Jardin();
//		jardin.setId(1);
//		jardin.setNom("blabla");
//		jardin.setDepartement(dep);
//		jardin.setUtilisateur(user);
//		assertThat(service.create(jardin).getBody()).isEqualTo(jardin);
//		assertThat(service.create(jardin).getMessage()).isEqualTo("Success");
//
//	}

//	/**
//	 * Teste la création existence par identifiant avec identifiant inconnu
//	 */
//	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Test
//	public void testCreateNull_ShouldReturnNull() {
//		assertNull(service.create(null).getBody());
//
//	}

//	/**
//	 * Teste la création avec un id déjà connu dans la DB
//	 */
//	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Test
//	public void testCreateExistingId_ShouldReturnNull() {
//		Utilisateur user = new Utilisateur();
//		Departement dep = new Departement();
//		Jardin jardin = new Jardin();
//		jardin.setId(1);
//		jardin.setNom("blabla");
//		jardin.setDepartement(dep);
//		jardin.setUtilisateur(user);
//		assertNull(service.create(jardin).getBody());
//
//	}
	
//	/**
//	 * Teste la modification avec un id déjà connu dans la DB
//	 */
//	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Test
//	public void testUpdateExistingJardin_ShouldReturnEntite() {
//		Utilisateur user = new Utilisateur();
//		Departement dep = new Departement();
//		Jardin jardin = new Jardin();
//		jardin.setId(1);
//		jardin.setNom("blabla");
//		jardin.setDepartement(dep);
//		jardin.setUtilisateur(user);
//		assertThat(service.update(jardin).getMessage()).isEqualTo("Success");
//		assertThat(service.update(jardin).getBody()).hasFieldOrPropertyWithValue("nom", "blabla");
//
//	}
	
//	/**
//	 * Teste la modification avec un id inconnu dans la DB
//	 */
//	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Test
//	public void testUpdateNotExistingJardin_ShouldReturnEntite() {
//		Utilisateur user = new Utilisateur();
//		Departement dep = new Departement();
//		Jardin jardin = new Jardin();
//		jardin.setId(1);
//		jardin.setNom("blabla");
//		jardin.setDepartement(dep);
//		jardin.setUtilisateur(user);
//		assertNull(service.update(jardin).getBody());
//
//	}
	
//	/**
//	 * Teste la modification avec une entité null
//	 */
//	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Test
//	public void testUpdateNullJardin_ShouldReturnEntite() {
//		assertNull(service.update(null).getBody());
//
//	}
	
	
	/**
	 * Cette méthode teste la récupération d'un jardin via son nom - conditions valides
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadNameExistingName_ShouldReturnPage() {
		assertThat(serviceJardin.readByNom(0, "nom4Test")).isNotNull();
		assertThat(serviceJardin.readByNom(0, "nom4Test").getBody().toList().get(0)).isNotNull().hasFieldOrPropertyWithValue("nom", "nom4Test");
	}
	
	/**
	 * Cette méthode teste la récupération d'un jardin via son nom - conditions invalides (nom inexistant)
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadNameNotExistingName_ShouldReturnEmptyPage() {
		assertThat(serviceJardin.readByNom(0, "nomInvalid").getBody().toList()).isEmpty();
		assertThat(serviceJardin.readByNom(0, "nomInvalid").getMessage()).isEqualTo("Recherche jardin par nom");
	}
	
	/**
	 * Cette méthode teste la récupération d'un jardin via son nom - conditions invalides (nom null)
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadNameWithNullName_ShouldReturnNull() {
		assertThat(serviceJardin.readByNom(0, null).getBody()).isNull();
		assertThat(serviceJardin.readByNom(0, null).getMessage()).isEqualTo("Recherche non réalisé : nom null");
	}
	
	/**
	 * Cette méthode teste la récupération des jardins d'un utilisateur via son id - conditions valides
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadUserExistingUser_ShouldReturnPage() {
		assertTrue(serviceJardin.readByUtilisateur(0, 2).getBody().toList().size() == 1);
	}
	
	/**
	 * Cette méthode teste la récupération des jardins d'un utilisateur via son id - conditions invalides (id inexistant)
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadUserNotExistingUser_ShouldReturnEmptyPage() {
		assertThat(serviceJardin.readByUtilisateur(0, 1).getBody().toList()).isEmpty();
		assertEquals(serviceJardin.readByUtilisateur(0, 1).getMessage(), "Recherche jardin par utilisateur");
	}
	
	/**
	 * Cette méthode teste la récupération des jardins d'un utilisateur via son id - conditions invalides (id inexistant)
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadUserWithIdNull_ShouldReturnNull() {
		assertNull(serviceJardin.readByUtilisateur(0, null).getBody());
		assertEquals(serviceJardin.readByUtilisateur(0, null).getMessage(), "Recherche non réalisé : id null");
	}
	
	/**
	 * Cette méthode teste la récupération des jardins d'un département via son id - conditions valides
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadDepExistingDep_ShouldReturnPage() {
		assertTrue(serviceJardin.readByDepartement(0, 1).getBody().toList().size() == 1);
	}
	
	/**
	 * Cette méthode teste la récupération des jardins d'un département via son id - conditions invalides (id dep inexistant)
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadDepNotExistingDep_ShouldReturnEmptyPage() {
		assertThat(serviceJardin.readByDepartement(0, 2).getBody().toList()).isEmpty();
		assertEquals(serviceJardin.readByDepartement(0, 2).getMessage(), "Recherche jardin par departement");
	}
	
	/**
	 * Cette méthode teste la récupération des jardins d'un département via son id - conditions invalides (id dep null)
	 */
	@Sql(statements = "INSERT INTO Utilisateur (id, nom) VALUES (2, 'nomTestUtilisateur')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadDepWithIdNull_ShouldReturnNull() {
		assertNull(serviceJardin.readByDepartement(0, null).getBody());
		assertEquals(serviceJardin.readByDepartement(0, null).getMessage(), "Recherche non réalisé : numDep null");
	}

}

