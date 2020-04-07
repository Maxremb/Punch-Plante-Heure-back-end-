package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Retention;
import com.fr.adaming.enums.Sol;
import com.fr.adaming.service.IRetentionService;
import com.fr.adaming.service.IService;
import com.fr.adaming.service.IServiceTests;

/**
 * Test de la couche Service Retention qui implemente une interface pour les
 * méthodes CRUD (read All, read by Id, exist by Id, delete by Id) et définit
 * ses propres méthodes de test pour les autres.
 * 
 * @author maxime
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest(classes = ModuleServiceApplication.class)
public class RetentionServiceTest implements IServiceTests{

	@Autowired
	private IRetentionService retentionService;

	@Autowired
	private IService<Retention> iService;
	
	// ********************************************************************************
	// TEST CREATE

	/**
	 * Test create avec bons arguments
	 * Retourne l'entite
	 */
	@Sql (statements = "DELETE FROM retention", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void TestCreatingOK_ShouldReturnEntity() {
		Retention retention = new Retention();
		retention.setCoeffRemplissage(0.5);
		retention.setSol(Sol.Argileux);
		
		ServiceResponse<Retention> resp = iService.create(retention);

		assertThat(resp.getBody()).hasFieldOrPropertyWithValue("sol", retention.getSol());
		assertThat(resp.getBody()).hasFieldOrPropertyWithValue("coeffRemplissage", retention.getCoeffRemplissage());
	}

	
	/**
	 * Test create avec un objet null
	 * Retourne null
	 */
	@Test
	@Sql (statements = "DELETE FROM retention", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestCreatingNull_ShouldReturnNull() {
		ServiceResponse<Retention> resp= iService.create(null);
		assertThat(resp.getBody()).isNull();
	}

	/**
	 * Test create avec sol deja existant
	 * Retourne null
	 */
	@Test
	@Sql (statements = "INSERT INTO retention (sol,coeff_remplissage) VALUES (4,0.5)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM retention", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestCreatingExistingSol_ShouldReturnNull() {
		Retention retention = new Retention();
		retention.setCoeffRemplissage(0.5);
		retention.setSol(Sol.Argileux);
		
		ServiceResponse<Retention> resp = iService.create(retention);
		assertThat(resp.getBody()).isNull();
	}
	
	// ********************************************************************************
	// TEST UPDATE
	
	
	
	
	
	// ********************************************************************************
	// TEST READ BY SOL
	
	
	
	
	// ********************************************************************************
	// TEST DELETE BY ID

	/**
	 *Test delete by id avec id correct
	 * Retourne true
	 */
	@Override
	@Test
	@Sql (statements = "INSERT INTO retention (id,sol,coeff_remplissage) VALUES (1,4,0.5)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testDeletingValidId_shouldReturnTrue() {
		assertThat(iService.deleteById(1)).isNotNull().isTrue();
		
	}

	/**
	 * Test delete by Id avec Id incorrect
	 * retourne false
	 */
	@Override
	@Test
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertThat(iService.deleteById(1)).isNotNull().isFalse();
		
	}

	
	// ********************************************************************************
	// TEST READ ALL
	
	@Override
	public void testReadAllWithContent_shouldReturnPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testReadAllNoContent_shouldReturnEmptyPage() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	// ********************************************************************************
	// TEST READ BY ID
	

	@Sql (statements = "INSERT INTO retention (id,sol,coeff_remplissage) VALUES (11,4,0.5)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM retention", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testReadByIdValidId_shouldReturnEntity() {
		ServiceResponse<Retention> resp = iService.readById(1);
		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("sol", Sol.Argileux);
		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("coeff_remplissage", 0.5);
	}

	@Override
	@Test
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertThat(iService.readById(100000)).isNull();
		
	}
	
	
	// ********************************************************************************
	// TEST EXISTS BY ID

	@Override
	public void testExistsByIdValidId_ShouldReturnTrue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		// TODO Auto-generated method stub
		
	}

}
