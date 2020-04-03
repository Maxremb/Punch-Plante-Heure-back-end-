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
import org.springframework.data.domain.Sort;
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
	@Sql(scripts = "classpath:plante_model_names.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
	@Sql(statements = "INSERT INTO PlanteModel (id, nomCommun, nomScientifique) VALUES (1, 'nomCommun', 'nomScientifique)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	void testExistsBynomScientifiqueExistingNom_shouldReturnTrue() {
		assertTrue(repo.existsByNomScientifique("nomScientifique"));
		
	}
	
	/**
	 * Teste la méthode existsByNomScientifique pour un objet inexistant
	 */
	@Sql(statements = "INSERT INTO PlanteModel (id, nomCommun, nomScientifique) VALUES (1, 'nomCommun', 'nomScientifique)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	void testExistsBynomScientifiqueNotExistingNom_shouldReturnFalse() {
		assertFalse(repo.existsByNomScientifique("nom"));
		
	}
	
	/**
	 * Teste la méthode findByNomScientifique pour un objet existant
	 */
	@Sql(statements = "INSERT INTO PlanteModel (id, nomCommun, nomScientifique) VALUES (1, 'nomCommun', 'nomScientifique)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	void testfindBynomScientifiqueExistingNom_shouldReturnObject() {
		assertNotNull(repo.findByNomScientifique("nomScientifique"));
		
	}
	
	/**
	 * Teste la méthode findByNomScientifique pour un objet inexistant
	 */
	@Sql(statements = "INSERT INTO PlanteModel (id, nomCommun, nomScientifique) VALUES (1, 'nomCommun', 'nomScientifique)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	void testfindBynomScientifiqueInExistingNom_shouldReturnNull() {
		assertNull(repo.findByNomScientifique("nom"));
		
	}
	
	
}
