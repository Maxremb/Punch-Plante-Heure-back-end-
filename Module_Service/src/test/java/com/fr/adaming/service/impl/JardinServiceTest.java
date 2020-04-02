package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

@SpringBootTest(classes = ModuleServiceApplication.class)
public class JardinServiceTest implements IServiceTests {

	@Autowired
	private IJardinService serviceJardin;

	@Autowired
	private IService<Jardin> service;


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

	@Override
	@Test
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertFalse(service.deleteById(1));

	}

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

	@Override
	@Test
	public void testReadAllNoContent_shouldReturnEmptyList() {
		ServiceResponse<List<Jardin>> resp = service.readAll();
		assertTrue(resp.getBody().isEmpty());
	}

	@Sql(statements = "Insert into utilisateur (id) values (1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into departement (numero_dep, nom) values (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into jardin (id, nom, utilisateur_id, departement_numero_dep) values (1, 'Jardinblabla', 1, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from jardin, Delete from utilisateur, Delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testReadByIdValidId_shouldReturnEntity() {
		assertThat(service.readById(1).getBody()).isNotNull().hasFieldOrPropertyWithValue("nom", "Jardinblabla");
	}


	@Override
	@Test
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertThat(service.readById(1).getBody()).isNull();
		assertThat(service.readById(1).getMessage())
				.isEqualTo("Une entité avec cet ID n'existe pas dans la base de données");

	}


	@Sql(statements = "Insert into utilisateur (id) values (1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into departement (numero_dep, nom) values (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into jardin (id, nom, utilisateur_id, departement_numero_dep) values (1, 'Jardinblabla', 1, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from jardin, Delete from utilisateur, Delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertTrue(service.existsById(1));
	}


	@Override
	@Test
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		assertFalse(service.existsById(1));

	}

	/**
	 * Teste la création d'une entité valide 
	 */
	@Sql(statements = "Insert into utilisateur (id) values (1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into departement (numero_dep, nom) values (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from jardin, Delete from departement, Delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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

	/**
	 * Teste la création existence par identifiant avec identifiant inconnu
	 */
	@Test
	public void testCreateNull_ShouldReturnNull() {
		assertNull(service.create(null).getBody());

	}

	/**
	 * Teste la création avec un id déjà connu dans la DB
	 */
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
	
	/**
	 * Teste la modification avec un id déjà connu dans la DB
	 */
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
	
	/**
	 * Teste la modification avec un id inconnu dans la DB
	 */
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
	
	/**
	 * Teste la modification avec une entité null
	 */
	@Test
	public void testUpdateNullJardin_ShouldReturnEntite() {
		assertNull(service.update(null).getBody());

	}
	
	
	/**
	 * Teste la lecture par nom avec un nom existant
	 */
	@Sql(statements = "Insert into utilisateur (id) values (1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into departement (numero_dep, nom) values (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Insert into jardin (id, nom, utilisateur_id, departement_numero_dep) values (1, 'Jardinblabla', 1, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from jardin, Delete from utilisateur, Delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadNameExistingName_ShouldReturnEntite() {
		ServiceResponse<List<Jardin>> resp = serviceJardin.readByNom("Jardinblabla");
		assertThat(resp.getBody().get(0)).hasFieldOrPropertyWithValue("nom", "Jardinblabla");

	}
	
	/**
	 * Teste la lecture par nom avec un nom non existant
	 */
	@Test
	public void testReadNameNotExistingName_ShouldReturnEntite() {
		assertTrue(serviceJardin.readByNom("Jardinblabla").getBody().isEmpty());

	}
	
	/**
	 * Teste la lecture par nom avec un nom vide
	 */
	@Test
	public void testReadNameWithBlankName_ShouldReturnEntite() {
		assertNull(serviceJardin.readByNom("").getBody());

	}
	
	/**
	 * Teste la lecture par nom avec un nom null
	 */
	@Test
	public void testReadNameWithNullName_ShouldReturnEntite() {
		assertNull(serviceJardin.readByNom(null).getBody());

	}
	
	/**
	 * Teste la lecture par utilisateur avec un utilisateur existant
	 */
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
	
	/**
	 * Teste la lecture par utilisateur avec un utilisateur non existant
	 */
	@Test
	public void testReadUserNotExistingUser_ShouldReturnEntite() {
		assertTrue(serviceJardin.readByUtilisateur(1).getBody().isEmpty());

	}
	
	/**
	 * Teste la lecture par utilisateur avec un id utilisateur null
	 */
	@Test
	public void testReadUserWithIdNull_ShouldReturnEntite() {
		assertNull(serviceJardin.readByUtilisateur(null).getBody());

	}
	
	/**
	 * Teste la lecture par département avec un département existant
	 */
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
	
	/**
	 * Teste la lecture par département avec un département non existant
	 */
	@Test
	public void testReadDepNotExistingDep_ShouldReturnEntite() {
		assertTrue(serviceJardin.readByDepartement(1).getBody().isEmpty());

	}
	
	/**
	 * Teste la lecture par département avec un numéro département null
	 */
	@Test
	public void testReadDepWithIdNull_ShouldReturnEntite() {
		assertNull(serviceJardin.readByDepartement(null).getBody());

	}

}

