package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.service.IService;
import com.fr.adaming.service.IServiceTests;

/**
 * Cette classe teste la couche service de l'entité PlanteModel
 * Elle implémente une interface pour le test des méthodes CRUD (read All, read by Id, exist by Id, delete by Id) et définit ses propres méthodes de test pour les autres. 
 * 
 * @author Léa Coston
 * @since 0.0.1
 *
 */

@SpringBootTest(classes = ModuleServiceApplication.class)
public class PlanteModelServiceTest implements IServiceTests{
	
	
	
	@Autowired
	private IService<PlanteModel> service;


	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testDeletingValidId_shouldReturnTrue() {
		assertTrue(service.deleteById(1));
		
	}

	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertFalse(service.deleteById(2));
		
	}

	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testReadAllWithContent_shouldReturnList() {
		assertNotNull(service.readAll(0));
		assertThat(service.readAll(0).getBody().getNumberOfElements()).isEqualTo(1);
		assertThat(service.readAll(0).getBody().getNumber()).isEqualTo(0);
		
	}

	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testReadAllNoContent_shouldReturnEmptyList() {
		assertNotNull(service.readAll(0));
		assertThat(service.readAll(0).getBody().getNumberOfElements()).isEqualTo(0);
		
	}

	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testReadByIdValidId_shouldReturnEntity() {
		assertThat(service.readById(1)).isNotNull();
		assertThat(service.readById(1)).isInstanceOf(PlanteModel.class);
		
	}

	@Override
	@Test
	public void testReadByIdInvalidId_shouldReturnNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void testExistsByIdValidId_ShouldReturnTrue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		// TODO Auto-generated method stub
		
	}

	
	
}
