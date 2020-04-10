package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.service.IService;
import com.fr.adaming.service.IServiceTests;
import com.fr.adaming.service.IUtilisateurService;

/**
 * 
 * Test de la couche Service Utilisateur qui implemente une interface pour les
 * méthodes CRUD (read All, read by Id, exist by Id, delete by Id) et définit
 * ses propres méthodes de test pour les autres.
 * 
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@SpringBootTest(classes = ModuleServiceApplication.class)
public class UtilisateurServiceTest implements IServiceTests {

	@Autowired
	private IUtilisateurService userService;

	@Autowired
	private IService<Utilisateur> service;

	// paramètres utilisateur
	private static final String nom = "jornet";
	private static final String prenom = "kilian";
	private static final String pseudo = "extra terrestre";
	private static final String email = "kiki@trail.fr";
	private static final String mdp = "4TEST";
	// ID EMAIL MDP PSEUDO NOM PRENOM NON NULLABLE

	// ****************************************************************
	// Test DELETE BY ID

	@Override
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeletingValidId_shouldReturnTrue() {
		assertThat(service.deleteById(1)).isTrue();

	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertThat(service.deleteById(5)).isFalse();

	}

	// ****************************************************************
	// Test READ ALL

	@Override
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (5,'Dhaene','François','franfran@trail.fr','4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadAllWithContent_shouldReturnPage() {
		assertThat(service.readAll(0).getBody().toList().size() == 2).isTrue();
		assertThat(service.readAll(0).getBody().toList().get(0)).hasFieldOrPropertyWithValue("prenom", "kilian");
		assertThat(service.readAll(0).getBody().toList().get(1)).hasFieldOrPropertyWithValue("prenom", "François");

	}

	@Override
	@Test
	public void testReadAllNoContent_shouldReturnEmptyPage() {
		assertThat(service.readAll(0).getBody().isEmpty()).isTrue();

	}

	// ****************************************************************
	// Test READ ID

	@Override
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (10,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByIdValidId_shouldReturnEntity() {
		assertThat(service.readById(10).getBody()).hasFieldOrPropertyWithValue("prenom", "kilian");

	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertThat(service.readById(10).getBody()).isNull();

	}

	// ****************************************************************
	// Test EXISTS BY ID

	@Override
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertThat(service.existsById(1)).isTrue();

	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		assertThat(service.existsById(10)).isFalse();

	}

	// ****************************************************************
	// Test READ BY NOM AND PRENOM

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByNomAndPrenomWithValidParam_ShouldReturnEntiteBody() {
		assertThat(userService.readByNomAndPrenom(nom, prenom).getBody()).hasFieldOrPropertyWithValue("mdp", "4TEST");
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByNomAndPrenomWithNullParam_ShouldReturnNullBody() {
		assertThat(userService.readByNomAndPrenom(null, null).getMessage())
				.isEqualTo("Recherche non réalisée : nom ou prenom null");
		assertThat(userService.readByNomAndPrenom(null, null).getBody()).isNull();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByNomAndPrenomWithOneNullParam_ShouldReturnNullBody() {
		assertThat(userService.readByNomAndPrenom(nom, null).getMessage())
				.isEqualTo("Recherche non réalisée : nom ou prenom null");
		assertThat(userService.readByNomAndPrenom(nom, null).getBody()).isNull();
	}

	@Test
	public void TestReadNomAndPrenomWithNoDb_ShouldReturnNullBody() {
		assertThat(userService.readByNomAndPrenom(nom, prenom)).isNotNull();
		assertThat(userService.readByNomAndPrenom(nom, prenom).getBody()).isNull();
	}

	// ****************************************************************
	// Test IS ACTIF

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestIsActifWithUserActif_ShouldReturnTrue() {
		assertThat(userService.isActif(pseudo)).isTrue();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',false) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestIsActifWithUserNoActif_ShouldReturnFalse() {
		assertThat(userService.isActif(pseudo)).isFalse();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestIsActifWithNullParam_ShouldReturnNull() {
		assertThat(userService.isActif(null)).isNull();
	}

	@Test
	public void TestIsActifWithNoDB_ShouldReturnNull() {
		assertThat(userService.isActif(pseudo)).isNull();
	}

	// ****************************************************************
	// Test DESACTIVATE

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (5,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',true) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestDesactivateWithActifUser_ShouldReturnTrue() {
		assertThat(userService.desactivateUser(5)).isTrue();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',false) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestDesactivateWithUserNoActif_ShouldReturnFalse() {
		assertThat(userService.desactivateUser(1)).isFalse();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',true) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestDesactivateWithInvalidId_ShouldReturnFalse() {
		assertThat(userService.desactivateUser(null)).isFalse();
	}

	@Test
	public void TestDesactivateWithNoDb_ShouldReturnFalse() {
		assertThat(userService.desactivateUser(5)).isFalse();
	}

	// ****************************************************************
	// Test ACTIVATE

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (5,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',false) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestActivateWithNoActifUser_ShouldReturnTrue() {
		assertThat(userService.activateUser(5)).isTrue();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (5,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',true) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestActivateWithActifUser_ShouldReturnFalse() {
		assertThat(userService.activateUser(5)).isFalse();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (5,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',false) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestActivateWithInvalidId_ShouldReturnFalse() {
		assertThat(userService.activateUser(10)).isFalse();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (5,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',false) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestActivateWithNullId_ShouldReturnFalse() {
		assertThat(userService.activateUser(null)).isFalse();
	}

	@Test
	public void TestActivateWithNoDb_ShouldReturnFalse() {
		assertThat(userService.activateUser(1)).isFalse();
	}

	// ****************************************************************
	// Test EXISTS BY EMAIL AND MDP

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (5,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',false) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailAndMdpWithValidParam_ShouldReturnEntiteBody() {
		assertThat(userService.existsByEmailAndMdp(email, mdp).getBody()).hasFieldOrPropertyWithValue("nom", "jornet");
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (5,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',false) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailAndMdpWithNullParam_ShouldReturnNullBody() {
		assertThat(userService.existsByEmailAndMdp(email, null).getBody()).isNull();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (5,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',false) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailAndMDpWithOneNullParam_ShouldReturnNullBody() {
		assertThat(userService.existsByEmailAndMdp(null, null).getBody()).isNull();
	}

	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme,actif) VALUES (5,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre',false) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (10,'Dhaene','François','franfran@trail.fr','4TEST4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailAndMdpWithInvalidParam_ShouldReturnNullBody() {
		assertThat(userService.existsByEmailAndMdp(email, "4TEST4TEST").getBody()).isNull();
	}

	@Test
	public void testByEmailAndMdpWithNoDb_ShouldReturnNullBody() {
		assertThat(userService.existsByEmailAndMdp(email, mdp).getBody()).isNull();
	}

}
