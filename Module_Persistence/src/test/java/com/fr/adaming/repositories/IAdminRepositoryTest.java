package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;

/**
 * Classe de tests pour l'interface Admin Repo
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
		
	// ************************************************************
	// FIND BY EMAIL AND MDP
	
	
	/**
	 * Cette méthode teste le find by email et mdp - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestFindByEmailAndMdpWithValidParam_ShouldReturnEntite() {
		assertThat(repo.findByEmailAndMdp(email, mdp)).hasFieldOrPropertyWithValue("pseudonyme", "extra terrestre");
	}

	/**
	 * Cette méthode teste le find by email et mdp - conditions invalides (sans mdp)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestFindByEmailAndMdpWithOneParamNull_ShouldReturnNull() {
		assertThat(repo.findByEmailAndMdp(email, null)).isNull();
	}

	/**
	 * Cette méthode teste le find by email et mdp - conditions invalides (sans mdp ni email)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestFindByEmailAndMdpWithNullParam_shouldReturnNull() {
		assertThat(repo.findByEmailAndMdp(null, null)).isNull();
	}

	/**
	 * Cette méthode teste le find by email et mdp - conditions invalides (db vide)
	 */
	@Test
	public void TestFindByEmailAndMdpWithNoDB_shouldReturnNull() {
		assertThat(repo.findByEmailAndMdp(email, mdp)).isNull();
	}
	
	// ************************************************************
	// FIND BY EMAIL
	
	/**
	 * Cette méthode teste le find by email - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestFindByEmailWithValidEmail_ShouldReturnEntite() {
		assertThat(repo.findByEmail(email)).hasFieldOrPropertyWithValue("pseudonyme", "extra terrestre");
	}
	
	/**
	 * Cette méthode teste le find by email - conditions invalides (email null)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestFindByEmailWithNullParam_ShouldReturnNull() {
		assertThat(repo.findByEmail(null)).isNull();
	}
	
	/**
	 * Cette méthode teste le find by email - conditions invalides (db vide)
	 */
	@Test
	public void TestFindByEmailWithNoDb_ShouldReturnNull() {
		assertThat(repo.findByEmail(email)).isNull();
	}
	
	// ************************************************************
	// FIND BY PSEUDONYME
	
	/**
	 * Cette méthode teste le find by pseudo - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestFindByPseudoNymeWithValidPseudo_ShouldReturnEntite() {
		assertThat(repo.findByPseudonyme(pseudo)).hasFieldOrPropertyWithValue("email", "kiki@trail.fr");
	}
	
	/**
	 * Cette méthode teste le find by pseudo - conditions invalides (pseudo null)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestFindByPseudoNymeWithNullParam_ShouldReturnNull() {
		assertThat(repo.findByPseudonyme(null)).isNull();
	}
	
	/**
	 * Cette méthode teste le find by pseudo - conditions invalides (db vide)
	 */
	@Test
	public void TestFindByPseudoNymeWithNoDb_ShouldReturnNull() {
		assertThat(repo.findByPseudonyme(pseudo)).isNull();
	}
	
	// ************************************************************
	// EXISTS BY EMAIL
	
	/**
	 * Cette méthode teste le exist by email - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailWithValidParam_ShouldReturnTrue() {
		assertThat(repo.existsByEmail(email)).isTrue();
	}
	
	/**
	 * Cette méthode teste le exist by email - conditions invalides (email null)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailWithNullParam_ShouldReturnFalse() {
		assertThat(repo.existsByEmail(null)).isFalse();
	}
	
	/**
	 * Cette méthode teste le exist by email - conditions invalides (db vide)
	 */
	@Test
	public void TestExistsByEmailWithNoDB_ShouldReturnFalse() {
		assertThat(repo.existsByEmail(email)).isFalse();
	}
		
	// ************************************************************
	// EXISTS BY PSEUDONYME

	/**
	 * Cette méthode teste le exist by pseudo - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByPseudonymeWithValidParam_ShouldReturnTrue() {
		assertThat(repo.existsByPseudonyme(pseudo)).isTrue();
	}
	
	/**
	 * Cette méthode teste le exist by pseudo - conditions invalides (pseudo null)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByPseudonymeWithNullParam_ShouldReturnFalse() {
		assertThat(repo.existsByPseudonyme(null)).isFalse();
	}
	
	/**
	 * Cette méthode teste le exist by pseudo - conditions invalides (db vide)
	 */
	@Test
	public void TestExistsByPseudonymeWithNoDB_ShouldReturnFalse() {
		assertThat(repo.existsByPseudonyme(pseudo)).isFalse();
	}
	
}
