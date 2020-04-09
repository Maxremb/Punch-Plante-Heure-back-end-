package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	
	
	/**
	 * Test update de l'objet retention avec un objet null en entrée
	 * Retourne un obejt ServiceResponse avec un body = null
	 */
	@Test
	public void TestUpdateWithNullEntite_shouldReturnNullBody () {
		assertThat(iService.update(null).getBody()).isNull();
		
	}
	
	/**
	 * Test update de l'objet retention avec un objet non existant dans la DB
	 * Retourne un obejt ServiceResponse avec un body = null
	 */
	@Test
	public void TestUpdateWithNoExistingEntite_SHouldReturnNullBody() {
		Retention retention = new Retention();
		retention.setId(10);
		retention.setSol(Sol.Sableux);
		assertThat(iService.update(retention).getBody()).isNull();
	}
	
	/**
	 * Test update de l'objet retention avec un objet existant dans la DB et non null
	 * Retourne l'objet en question 
	 */
	@Test
	@Sql (statements = "INSERT INTO retention (id,sol,coeff_remplissage) VALUES (10,4,0.5)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM retention", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestUpdateWithCorrectEntite_ShouldReturnRightEntite() {
		Retention retention = new Retention();
		retention.setId(10);
		retention.setSol(Sol.Argileux);
		retention.setCoeffRemplissage(10.0);
		ServiceResponse<Retention> resp = iService.update(retention);
		assertThat(resp.getBody()).hasFieldOrPropertyWithValue("sol", Sol.Argileux);
		assertThat(resp.getBody()).hasFieldOrPropertyWithValue("coeffRemplissage", retention.getCoeffRemplissage());
		
	}
	
	
	// ********************************************************************************
	// TEST READ BY SOL
	
	/**
	 * Test de la méthode ReadBySol avec un null en entrée
	 * Retourne un objet ServiceResponse avec un body = null
	 */
	@Test
	public void TestReadBySolWithNullEntite_ShouldReturnNullBody() {
		assertThat(retentionService.readBySol(null).getBody()).isNull();
	}
	
	
	/**
	 * Test de la méthode ReadBySol avec un objet en entrée mais DB vide
	 *  Retourne un objet ServiceResponse avec un body = null
	 */
	@Test
	public void TestReadBySolWithNoDB_shouldReturnNullBody() {
		
		assertThat(retentionService.readBySol(Sol.ArgiloSableux).getBody()).isNull();
	}
	
	/**
	 * Test de la méthode ReadBySol avec un paramètre correct et existant dans la DB
	 *  Retourne l'entite recherché
	 */
	@Test
	@Sql (statements = "INSERT INTO retention (id,sol,coeff_remplissage) VALUES (10,4,0.5)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM retention", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadBySolWithCorrectSol_ShouldReturnEntite () {
	
		ServiceResponse<Retention> resp = retentionService.readBySol(Sol.Argileux);
		assertThat(resp.getBody()).hasFieldOrPropertyWithValue("id", 10);
		assertThat(resp.getBody()).hasFieldOrPropertyWithValue("coeffRemplissage", 0.5);
		
	}
	
	
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
	@Test
	@Sql (statements = "INSERT INTO retention (id,sol,coeff_remplissage) VALUES (11,4,0.5)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "INSERT INTO retention (id,sol,coeff_remplissage) VALUES (10,0,10)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM retention", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadAllWithContent_shouldReturnPage() {
		assertTrue(iService.readAll(0).getBody().toList().size() == 2);
		assertThat(iService.readAll(0).getBody().toList().get(0)).isNotNull();
		
	}

	@Override
	@Test
	public void testReadAllNoContent_shouldReturnEmptyPage() {
		assertTrue(iService.readAll(0).getBody().toList().isEmpty());
		
	}
	
	
	
	// ********************************************************************************
	// TEST READ BY ID
	

	/**
	 *Test du readById d'un objet retention avec des paramètres correcte
	 *Retourne le bon objet
	 */
	@Sql (statements = "INSERT INTO retention (id,sol,coeff_remplissage) VALUES (11,4,0.5)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM retention", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testReadByIdValidId_shouldReturnEntity() {
		ServiceResponse<Retention> resp = iService.readById(11);
		assertThat(resp.getBody()).hasFieldOrPropertyWithValue("sol", Sol.Argileux);
		assertThat(resp.getBody()).hasFieldOrPropertyWithValue("coeffRemplissage", 0.5);
	}

	/**
	 *Test du readById d'un objet retention avec un Id incorrect
	 *Retourne un objet Service Response avec un body = null
	 */
	@Override
	@Test
	public void testReadByIdInvalidId_shouldReturnNull() {
		ServiceResponse<Retention> resp = iService.readById(100000);
		assertThat(resp.getBody()).isNull();
		
	}
	
	
	// ********************************************************************************
	// TEST EXISTS BY ID

	@Override
	@Test
	@Sql (statements = "INSERT INTO retention (id,sol,coeff_remplissage) VALUES (11,4,0.5)",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "DELETE FROM retention", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertThat(iService.existsById(11)).isTrue();
		
	}

	@Override
	@Test
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		assertThat(iService.existsById(1000000)).isFalse();
		
	}

}
