package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;
import com.fr.adaming.entity.Retention;
import com.fr.adaming.enums.Sol;

/**
 * Classe de test de l'interface Retention repository
 * @author maxime
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest (classes = ModulePersistenceApplication.class)
public class IRetentionRepoTest {
	
	@Autowired
	private IRetentionRepository repo;
	
	
	/**
	 * Test de la méthode existsBySol avec sol existant dans la DB et requête correcte
	 * Should return true
	 */
	@Test
	@Sql (statements = "INSERT INTO retention (sol,coeff_remplissage) VALUES (0,0.5)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM retention", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsBySolWithRightSol_ShouldReturnTrue() {
		boolean resp = repo.existsBySol(Sol.Sableux);
		assertThat(resp).isTrue();
	}
	
	
	/**
	 * Test de la méthode existsBySol avec sol existant dans la DB et requête incorrecte
	 * Should return false
	 */
	@Test
	@Sql (statements = "INSERT INTO retention (sol,coeff_remplissage) VALUES (5,0.5)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM retention", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsBySolWithWringSol_ShouldReturnFalse() {
		boolean resp = repo.existsBySol(Sol.Sableux);
		assertThat(resp).isFalse();
		}
	
	/**
	 * test de la méthode find By sol avec sol existant 
	 * Should return entite
	 */
	@Test
	@Sql (statements ="INSERT INTO retention (sol,coeff_remplissage) VALUES (0,0.5)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM retention", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestFindBySolWithExistingSol_ShouldReturnEntity() {
		Retention resp = repo.findBySol(Sol.Sableux);
		assertThat(resp).hasFieldOrPropertyWithValue("sol", Sol.Sableux);
		assertThat(resp).hasFieldOrPropertyWithValue("coeffRemplissage", 0.5);
	}
	
	/**
	 * test de la méthode find By sol avec sol inexistant
	 * Should return null
	 */
	@Test
	public void TestFindBySolWithNoSol_ShouldReturnNull() {
		Retention resp = repo.findBySol(Sol.Sableux);
		assertThat(resp).isNull();
	}
	
	

}
