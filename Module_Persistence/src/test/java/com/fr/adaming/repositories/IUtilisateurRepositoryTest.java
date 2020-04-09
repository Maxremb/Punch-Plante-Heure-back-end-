package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;
import com.fr.adaming.entity.Utilisateur;

/**
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IUtilisateurRepositoryTest {

	@Autowired
	private IUtilisateurRepository repo;

	// paramètres utilisateur
	private static final String nom = "jornet";
	private static final String prenom = "kilian";
	private static final String pseudo = "extra terrestre";
	// ID EMAIL MDP PSEUDO NOM PRENOM NON NULLABLE


	/**
	 * Test de READ BY NOM AND PRENOM avec valeurs correcte
	 * retourne l'entite
	 */
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByNomAndPrenomWithValidParam_ShouldreturnEntite() {
		Utilisateur user = repo.findByNomAndPrenom(nom, prenom);

		assertThat(user).isNotNull().hasFieldOrPropertyWithValue("email", "kiki@trail.fr");
	}

	/**
	 * Test de READ BY NOM AND PRENOM avec tout les param null
	 * retourne null
	 */
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByEmailAndMdpWithNullParam_ShouldReturnNull() {
		assertThat((repo.findByNomAndPrenom(null, null))).isNull();
	}

	/**
	 * Test de READ BY NOM AND PRENOM avec un param null
	 * retourne 
	 */
	@Test
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByEmailAndMdpWithOneNullParam_ShouldReturnNull() {
		Utilisateur user = repo.findByNomAndPrenom(nom, null);

		assertThat(user).isNull();
	}

	/**
	 * Test de READ BY NOM AND PRENOM avec DB vide
	 * retourne null
	 */
	@Test
	public void TestReadByEmailAndMdpNoDB_shouldReturnNull() {
		Utilisateur user = repo.findByNomAndPrenom(nom, prenom);

		assertThat(user).isNull();
	}

}
