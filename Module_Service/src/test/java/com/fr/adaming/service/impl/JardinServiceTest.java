package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Retention;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.enums.Sol;
import com.fr.adaming.service.IJardinService;
import com.fr.adaming.service.IService;
import com.fr.adaming.service.IServiceTests;

/**
 * Cette classe teste la couche service de l'entité Jardin. Elle implémente une
 * interface pour le test des méthodes CRUD (read All, read by Id, exist by Id,
 * delete by Id) et définit ses propres méthodes de test pour les autres.
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

	
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingValidId_shouldReturnTrue() {
		assertTrue(service.deleteById(1));
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertFalse(service.deleteById(2));
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (2, 'autreJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadAllWithContent_shouldReturnPage() {
		assertTrue(service.readAll(0).getBody().toList().size() == 2);
		assertThat(service.readAll(0).getBody().toList().get(0)).hasFieldOrPropertyWithValue("nom", "nomJardin");
		assertThat(service.readAll(0).getBody().toList().get(1)).hasFieldOrPropertyWithValue("nom", "autreJardin");
	}

	@Test
	@Override
	public void testReadAllNoContent_shouldReturnEmptyPage() {
		assertTrue(service.readAll(0).getBody().toList().isEmpty());
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (2, 'autreJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadByIdValidId_shouldReturnEntity() {
		assertThat(service.readById(1).getBody()).isNotNull().hasFieldOrPropertyWithValue("nom", "nomJardin");
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (2, 'autreJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertThat(service.readById(3).getBody()).isNull();
		assertThat(service.readById(3).getMessage())
				.isEqualTo("Une entité avec cet ID n'existe pas dans la base de données");
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (2, 'autreJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertTrue(service.existsById(1));
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (2, 'autreJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		assertFalse(service.existsById(3));

	}

	/**
	 * Cette méthode teste la récupération d'un jardin via son nom - conditions valides
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (2, 'autreJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadNameExistingName_ShouldReturnPage() {
		assertThat(serviceJardin.readByNom(0, "nomJardin")).isNotNull();
		assertThat(serviceJardin.readByNom(0, "nomJardin").getBody().toList().get(0)).isNotNull()
				.hasFieldOrPropertyWithValue("nom", "nomJardin");
	}

	/**
	 * Cette méthode teste la récupération d'un jardin via son nom - conditions valides
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (2, 'autreJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadNameNotExistingName_ShouldReturnEmptyPage() {
		assertThat(serviceJardin.readByNom(0, "nomInvalid").getBody().toList()).isEmpty();
		assertThat(serviceJardin.readByNom(0, "nomInvalid").getMessage()).isEqualTo("Recherche jardin par nom");
	}

	/**
	 * Cette méthode teste la récupération d'un jardin via son nom avec nom null
	 * 
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (2, 'autreJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadNameWithNullName_ShouldReturnNull() {
		assertThat(serviceJardin.readByNom(0, null).getBody()).isNull();
		assertThat(serviceJardin.readByNom(0, null).getMessage()).isEqualTo("Recherche non réalisée : nom null");
	}

	/**
	 * Cette méthode teste la récupération des jardins d'un utilisateur via son id -
	 * conditions valides
	 */
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (2,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature_max, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
	 * Cette méthode teste la récupération des jardins d'un utilisateur via son id -
	 * conditions invalides (id inexistant)
	 */
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (2,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature_max, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
	 * Cette méthode teste la récupération des jardins d'un utilisateur via son id -
	 * conditions invalides (id inexistant)
	 */
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (2,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature_max, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (2, 'autreJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadDepExistingDep_ShouldReturnPage() {
		assertTrue(serviceJardin.readByDepartement(0, 69).getBody().toList().size() == 2);
		assertThat(serviceJardin.readByDepartement(0, 69).getBody().getContent().get(0)).hasFieldOrPropertyWithValue("nom", "nomJardin");
		assertThat(serviceJardin.readByDepartement(0, 69).getBody().getContent().get(1)).hasFieldOrPropertyWithValue("nom", "autreJardin");
	}

	/**
	 * Cette méthode teste la récupération des jardins d'un département via son id -- id inexistant
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (2, 'autreJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadDepNotExistingDep_ShouldReturnEmptyPage() {
		assertThat(serviceJardin.readByDepartement(0, 75).getBody().toList()).isEmpty();
		assertEquals(serviceJardin.readByDepartement(0, 75).getMessage(), "Recherche jardin par departement");
	}

	/**
	 * Cette méthode teste la récupération des jardins d'un département via son id -
	 * conditions invalides (id null)
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (2, 'autreJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadDepWithIdNull_ShouldReturnNull() {
		assertNull(serviceJardin.readByDepartement(0, null).getBody());
		assertEquals(serviceJardin.readByDepartement(0, null).getMessage(), "Recherche non réalisée : numDep null");
	}

	/**
	 * Cette méthode teste la création d'un jardin - conditions valides
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateValid_ShouldReturnEntity() {
		Departement dep = new Departement();
		dep.setNumeroDep(69);
		dep.setNom("rhone");

		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		ServiceResponse<Jardin> response = service.create(jardin);

		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("nom", "nomJardin");
		assertThat(response.getMessage()).isEqualTo("Success");
	}

	/**
	 * Cette méthode teste la création d'un jardin - conditions invalides (jardin
	 * null)
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateJardinWithNameNull_ShouldReturnNull() {
		Departement dep = new Departement();
		dep.setNumeroDep(69);
		dep.setNom("rhone");

		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom(null);
		jardin.setDepartement(dep);
		
		ServiceResponse<Jardin> response = service.create(jardin);

		assertThat(response.getBody()).isNull();
		assertThat(response.getMessage()).isEqualTo("Exception lors de la création dans la DB");
	}
	
	/**
	 * Cette méthode teste la création d'un jardin - conditions invalides (nom null)
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateNull_ShouldReturnNull() {
		ServiceResponse<Jardin> response = service.create(null);

		assertThat(response.getBody()).isNull();
		assertThat(response.getMessage()).isEqualTo("Objet d'entrée null");
	}

	/**
	 * Cette méthode teste la modification d'un jardin - conditions valides
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateExistingJardin_ShouldReturnEntite() {
		Departement dep = new Departement();
		dep.setNumeroDep(69);
		dep.setNom("rhone");

		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin2");
		jardin.setDepartement(dep);
				
		ServiceResponse<Jardin> response = service.update(jardin);
		
		assertThat(response.getMessage()).isEqualTo("Success");
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("nom", "nomJardin2");
	}

	/**
	 * Cette méthode teste la modification d'un jardin - conditions invalides (id non existant dans BD)
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateNotExistingJardin_ShouldReturNull() {
		Departement dep = new Departement();
		dep.setNumeroDep(69);
		dep.setNom("rhone");

		Jardin jardin = new Jardin();
		jardin.setId(2);
		jardin.setNom("nomJardin2");
		jardin.setDepartement(dep);
				
		ServiceResponse<Jardin> response = service.update(jardin);
		
		assertNull(response.getBody());
		assertThat(response.getMessage()).isEqualTo("Modification non réalisée : id inconnu dans la database ou entité nulle");
	}

	/**
	 * Cette méthode teste la modification d'un jardin - conditions invalides (jardin null)
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateNullJardin_ShouldReturnEntite() {
		assertNull(service.update(null).getBody());
		assertThat(service.update(null).getMessage()).isEqualTo("Modification non réalisée : id inconnu dans la database ou entité nulle");
	}

//	// TODO : ERREUR SQL DANS LA REQUETE
	
	
//	@Sql(statements = "﻿INSERT INTO retention (id, coeff_remplissage, sol) VALUES (1, 0.1, 0)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Test
//	public void testCalculReserveEauMaxForSableux_shouldWork() {
//		Jardin jardin = new Jardin();
//		jardin.setId(1);
//		jardin.setNom("nomJardin");
//		jardin.setLongueur(2f);
//		jardin.setLargeur(2f);
//		jardin.setProfSol(2f);
//		jardin.setSol(Sol.Sableux);
//		
//		
//		Jardin retour = serviceJardin.calculReserveEauMax(jardin);
//		
//		assertNotNull(retour.getRESERVE_MAX_EAU());
//		assertThat(retour.getRESERVE_MAX_EAU()).isEqualTo(2f*2f*2f*0.1);
//	}
}
