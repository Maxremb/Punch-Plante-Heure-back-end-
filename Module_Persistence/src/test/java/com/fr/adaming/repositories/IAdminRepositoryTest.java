package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;

/**
 * Méthode de test pour l'interface Admin Repo
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IAdminRepositoryTest {

	
	@Autowired
	private IAdminRepository repo;
	
	// paramètres admin
	private static final String pseudo = "extra terrestre";
	private static final String email = "kiki@trail.fr";
	private static final String mdp = "4TEST";
	// ID EMAIL MDP PSEUDO NON NULLABLE
	
	
	// ************************************************************
	// FIND BY EMAIL AND MDP
	


	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestFindByEmailAndMdpWithValidParam_ShouldReturnEntite() {
		assertThat(repo.findByEmailAndMdp(email, mdp)).hasFieldOrPropertyWithValue("pseudonyme", "extra terrestre");
	}

	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestFindByEmailAndMdpWithOneParamNull_ShouldReturnNull() {
		assertThat(repo.findByEmailAndMdp(email, null)).isNull();
	}

	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestFindByEmailAndMdpWithNullParam_shouldReturnNull() {
		assertThat(repo.findByEmailAndMdp(null, null)).isNull();
	}

	@Test
	public void TestFindByEmailAndMdpWithNoDB_shouldReturnNull() {
		assertThat(repo.findByEmailAndMdp(email, mdp)).isNull();
	}
	
	// ************************************************************
	// FIND BY EMAIL
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestFindByEmailWithValidEmail_ShouldReturnEntite() {
		assertThat(repo.findByEmail(email)).hasFieldOrPropertyWithValue("pseudonyme", "extra terrestre");
	}
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestFindByEmailWithNullParam_ShouldReturnNull() {
		assertThat(repo.findByEmail(null)).isNull();
	}
	
	@Test
	public void TestFindByEmailWithNoDb_ShouldReturnNull() {
		assertThat(repo.findByEmail(email));
	}
	
	// ************************************************************
	// FIND BY PSEUDONYME
	
	// ************************************************************
	// EXISTS BY EMAIL
	
	// ************************************************************
	// EXISTS BY PSEUDONYME

}