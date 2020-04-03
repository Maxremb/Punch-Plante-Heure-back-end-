package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.service.IDepartementService;
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


	@Sql(statements = "INSERT INTO PlanteModel (id, nomCommun, nomScientifique) VALUES (1, 'nomCommun', 'nomScientifique)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM PlanteModel", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testDeletingValidId_shouldReturnTrue() {
		assertTrue(service.deleteById(1));
		
	}

	@Sql(statements = "INSERT INTO PlanteModel (id, nomCommun, nomScientifique) VALUES (1, 'nomCommun', 'nomScientifique)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM PlanteModel", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertFalse(service.deleteById(2));
		
	}

	@Sql(statements = "INSERT INTO PlanteModel (id, nomCommun, nomScientifique) VALUES (1, 'nomCommun', 'nomScientifique)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM PlanteModel", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testReadAllWithContent_shouldReturnList() {
		assertNotNull(service.readAll(0));
		assertThat(service.readAll(0).getBody().getSize()).isEqualTo(1);
		assertThat(service.readAll(0).getBody().getNumber()).isEqualTo(0);
		
	}

	@Sql(statements = "DELETE FROM PlanteModel", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testReadAllNoContent_shouldReturnEmptyList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testReadByIdValidId_shouldReturnEntity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testReadByIdInvalidId_shouldReturnNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testExistsByIdValidId_ShouldReturnTrue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		// TODO Auto-generated method stub
		
	}

	
	
}
