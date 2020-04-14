package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;
import com.fr.adaming.entity.PlanteModel;

/**
 * Cette classe teste la couche repository de l'entité PlanteModel
 * @author Léa Coston
 * @since 0.0.1
 */
@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IPlanteModelRepositoryTests {

	@Autowired
	private IPlanteModelRepository repo;

	/**
	 * Teste la méthode readAllReduced permettant la recherche de seulement 4 attribut de planteModel
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun1', 'nomScientifique1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (2, 'nomCommun2', 'nomScientifique2')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (3, 'nomCommun3', 'nomScientifique3')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (4, 'nomCommun4', 'nomScientifique4')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (5, 'nomCommun5', 'nomScientifique5')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (6, 'nomCommun6', 'nomScientifique6')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (7, 'nomCommun7', 'nomScientifique7')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (8, 'nomCommun8', 'nomScientifique8')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (9, 'nomCommun9', 'nomScientifique9')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (10, 'nomCommun10', 'nomScientifique10')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (11, 'nomCommun11', 'nomScientifique11')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	void testGettingPageOf10ReducedItems_shouldReturnPageOfTenPlants() {
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<PlanteModel> page = repo.findAll(pageable);

		assertThat(page).isNotNull();
		assertThat(page.getSize()).isEqualTo(10);
		assertThat(page.getNumber()).isEqualTo(0);
		
	}
	
	/**
	 * Teste la méthode existsByNomScientifique pour un objet existant
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	void testExistsBynomScientifiqueExistingNom_shouldReturnTrue() {
		assertTrue(repo.existsByNomScientifique("nomScientifique"));
		
	}
	
	/**
	 * Teste la méthode existsByNomScientifique pour un objet inexistant
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	void testExistsBynomScientifiqueNotExistingNom_shouldReturnFalse() {
		assertFalse(repo.existsByNomScientifique("nom"));
		
	}
	
	/**
	 * Teste la méthode findByNomScientifique pour un objet existant
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	void testfindBynomScientifiqueExistingNom_shouldReturnObject() {
		assertNotNull(repo.findByNomScientifique("nomScientifique"));
		
	}
	
	/**
	 * Teste la méthode findByNomScientifique pour un objet inexistant
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	void testfindBynomScientifiqueInExistingNom_shouldReturnNull() {
		assertNull(repo.findByNomScientifique("nom"));
		
	}
	
}
