package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.service.IJardinService;
import com.fr.adaming.service.IService;
import com.fr.adaming.service.IServiceTests;

public class JardinServiceTest implements IServiceTests {

	@Autowired
	private IJardinService serviceJardin;

	@Autowired
	private IService<Jardin> service;

	/**
	 * Teste la suppression avec un id valide
	 */
	@Sql(statements = "Insert into utilisateur (id) values (1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into departement (numero_dep, nom) values (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into jardin (id, nom, utilisateur_id, departement_numero_dep) values (1, 'Jardinblabla', 1, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "Delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testDeletingValidId_shouldReturnTrue() {
		assertTrue(service.deleteById(1));

	}

	/**
	 * Teste la suppression avec un id inconnu
	 */
	@Override
	@Test
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertFalse(service.deleteById(1));

	}

	/**
	 * Teste la lecture de tous avec du contenu
	 */
	@Sql(statements = "Insert into utilisateur (id) values (1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into departement (numero_dep, nom) values (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into jardin (id, nom, utilisateur_id, departement_numero_dep) values (1, 'Jardinblabla', 1, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from jardin, Delete from utilisateur, Delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testReadAllWithContent_shouldReturnList() {
		ServiceResponse<List<Jardin>> resp = service.readAll();
		assertTrue(resp.getBody().size() == 1);
	}
	
	/**
	 * Teste la lecture de tous sans contenu
	 */
	@Override
	@Test
	public void testReadAllNoContent_shouldReturnEmptyList() {
		ServiceResponse<List<Jardin>> resp = service.readAll();
		assertTrue(resp.getBody().isEmpty());
	}

	/**
	 * Teste la lecture par identifiant avec identifiant valide
	 */
	@Sql(statements = "Insert into utilisateur (id) values (1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into departement (numero_dep, nom) values (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into jardin (id, nom, utilisateur_id, departement_numero_dep) values (1, 'Jardinblabla', 1, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from jardin, Delete from utilisateur, Delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testReadByIdValidId_shouldReturnEntity() {
		assertThat(service.readById(1).getBody()).isNotNull().hasFieldOrPropertyWithValue("nom", "Jardinblabla");
	}

	/**
	 * Teste la lecture par identifiant avec identifiant inconnu
	 */
	@Override
	@Test
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertThat(service.readById(1).getBody()).isNull();
		assertThat(service.readById(1).getMessage())
				.isEqualTo("Une entité avec cet ID n'existe pas dans la base de données");

	}

	/**
	 * Teste l'existence par identifiant avec identifiant valide
	 */
	@Sql(statements = "Insert into utilisateur (id) values (1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into departement (numero_dep, nom) values (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into jardin (id, nom, utilisateur_id, departement_numero_dep) values (1, 'Jardinblabla', 1, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from jardin, Delete from utilisateur, Delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertTrue(service.existsById(1));
	}

	/**
	 * Teste l'existence par identifiant avec identifiant inconnu
	 */
	@Override
	@Test
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		assertFalse(service.existsById(1));

	}

	/**
	 * Teste la création existence par identifiant avec identifiant inconnu
	 */
	@Test
	public void testCreateValid_ShouldReturnEntity() {
		Utilisateur user = new Utilisateur();
		Departement dep = new Departement();
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("blabla");
		jardin.setDepartement(dep);
		jardin.setUtilisateur(user);
		assertThat(service.create(jardin).getBody()).isEqualTo(jardin);
		assertThat(service.create(jardin).getMessage()).isEqualTo("Success");

	}

	@Test
	public void testCreateNull_ShouldReturnNull() {
		assertNull(service.create(null).getBody());

	}

	@Sql(statements = "Insert into utilisateur (id) values (1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into departement (numero_dep, nom) values (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into jardin (id, nom, utilisateur_id, departement_numero_dep) values (1, 'Jardinblabla', 1, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from jardin, Delete from utilisateur, Delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateExistingId_ShouldReturnNull() {
		Utilisateur user = new Utilisateur();
		Departement dep = new Departement();
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("blabla");
		jardin.setDepartement(dep);
		jardin.setUtilisateur(user);
		assertNull(service.create(jardin).getBody());

	}
	
	@Sql(statements = "Insert into utilisateur (id) values (1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into departement (numero_dep, nom) values (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into jardin (id, nom, utilisateur_id, departement_numero_dep) values (1, 'Jardinblabla', 1, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from jardin, Delete from utilisateur, Delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateExistingJardin_ShouldReturnEntite() {
		Utilisateur user = new Utilisateur();
		Departement dep = new Departement();
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("blabla");
		jardin.setDepartement(dep);
		jardin.setUtilisateur(user);
		assertThat(service.update(jardin).getMessage()).isEqualTo("Success");
		assertThat(service.update(jardin).getBody()).hasFieldOrPropertyWithValue("nom", "blabla");

	}
	
	@Test
	public void testUpdateNotExistingJardin_ShouldReturnEntite() {
		Utilisateur user = new Utilisateur();
		Departement dep = new Departement();
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("blabla");
		jardin.setDepartement(dep);
		jardin.setUtilisateur(user);
		assertNull(service.update(jardin).getBody());

	}
	
	@Sql(statements = "Insert into utilisateur (id) values (1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into departement (numero_dep, nom) values (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into jardin (id, nom, utilisateur_id, departement_numero_dep) values (1, 'Jardinblabla', 1, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from jardin, Delete from utilisateur, Delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadNameExistingName_ShouldReturnEntite() {
		ServiceResponse<List<Jardin>> resp = serviceJardin.readByNom("Jardinblabla");
		assertThat(resp.getBody().get(0)).hasFieldOrPropertyWithValue("nom", "Jardinblabla");

	}
	
	@Test
	public void testReadNameNotExistingName_ShouldReturnEntite() {
		assertNull(serviceJardin.readByNom("Jardinblabla").getBody());

	}
	
	@Test
	public void testReadNameWithBlankName_ShouldReturnEntite() {
		assertNull(serviceJardin.readByNom("").getBody());

	}
	
	@Sql(statements = "Insert into utilisateur (id) values (1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into departement (numero_dep, nom) values (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into jardin (id, nom, utilisateur_id, departement_numero_dep) values (1, 'Jardinblabla', 1, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from jardin, Delete from utilisateur, Delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadUserExistingUser_ShouldReturnEntite() {
		ServiceResponse<List<Jardin>> resp = serviceJardin.readByUtilisateur(1);
		assertTrue(resp.getBody().size()==1);
		assertThat(resp.getBody().get(0)).hasFieldOrPropertyWithValue("nom", "Jardinblabla");

	}
	
	@Test
	public void testReadUserNotExistingUser_ShouldReturnEntite() {
		assertNull(serviceJardin.readByUtilisateur(1).getBody());

	}
	
	@Test
	public void testReadUserWithIdNull_ShouldReturnEntite() {
		assertNull(serviceJardin.readByUtilisateur(null).getBody());

	}
	
	@Sql(statements = "Insert into utilisateur (id) values (1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into departement (numero_dep, nom) values (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into jardin (id, nom, utilisateur_id, departement_numero_dep) values (1, 'Jardinblabla', 1, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from jardin, Delete from utilisateur, Delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadDepExistingDep_ShouldReturnEntite() {
		ServiceResponse<List<Jardin>> resp = serviceJardin.readByDepartement(69);
		assertTrue(resp.getBody().size()==1);
		assertThat(resp.getBody().get(0)).hasFieldOrPropertyWithValue("nom", "Jardinblabla");

	}
	
	@Test
	public void testReadDepNotExistingDep_ShouldReturnEntite() {
		assertNull(serviceJardin.readByDepartement(1).getBody());

	}
	
	@Test
	public void testReadDepWithIdNull_ShouldReturnEntite() {
		assertNull(serviceJardin.readByDepartement(null).getBody());

	}

}

