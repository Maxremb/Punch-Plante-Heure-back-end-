package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.service.IAdminService;
import com.fr.adaming.service.IService;
import com.fr.adaming.service.IServiceTests;

/**
 * Test de la couche Service Admin qui implemente une interface pour les
 * méthodes CRUD (read All, read by Id, exist by Id, delete by Id) et définit
 * ses propres méthodes de test pour les autres.
 * 
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@SpringBootTest(classes = ModuleServiceApplication.class)
public class AdminServiceTest implements IServiceTests {
	
	@Autowired
	private IAdminService adminService ;
	
	@Autowired
	private IService<Admin> service;
	
	// paramètres admin
	private static final String pseudo = "extra terrestre";
	private static final String email = "kiki@trail.fr";
	private static final String mdp = "4TEST";
	// ID EMAIL MDP PSEUDO NON NULLABLE
	
	//****************************************************
	// Test DELETE BY ID

	@Override
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeletingValidId_shouldReturnTrue() {
		assertThat(service.deleteById(10)).isTrue();

	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertThat(service.deleteById(20)).isFalse();

	}

	
	//****************************************************
		// Test READ ALL
	
	
	@Override
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (20,'françois@trail.fr','4TEST4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadAllWithContent_shouldReturnPage() {
		assertThat(service.readAll(0).getBody().toList().size() == 2).isTrue();
		assertThat(service.readAll(0).getBody().toList().get(0)).hasFieldOrPropertyWithValue("email", "kiki@trail.fr");
		assertThat(service.readAll(0).getBody().toList().get(1)).hasFieldOrPropertyWithValue("email", "françois@trail.fr");
	}

	@Override
	@Test
	public void testReadAllNoContent_shouldReturnEmptyPage() {
		assertThat(service.readAll(0).getBody().isEmpty()).isTrue();

	}
	
	//****************************************************
		// Test READ BY ID

	@Override
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByIdValidId_shouldReturnEntity() {
		assertThat(service.readById(10).getBody()).hasFieldOrPropertyWithValue("mdp", "4TEST");

	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertThat(service.readById(1).getBody()).isNull();

	}
	
	//****************************************************
		// Test EXISTS BY ID

	@Override
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertThat(service.existsById(10)).isTrue();

	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		assertThat(service.existsById(20)).isFalse();

	}
	
	//****************************************************
			// Test READ BY PSEUDONYME
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByPseudonymeWithValidParam_ShouldReturnEntiteBody () {
		assertThat(adminService.readByPseudonyme(pseudo).getBody()).hasFieldOrPropertyWithValue("email", "kiki@trail.fr");
	}
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByPseudonymeWithInvalidParam_ShouldReturnNullBody () {
		assertThat(adminService.readByPseudonyme("terrestre").getBody()).isNull();
	}
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByPseudonymeWithNullParam_ShouldReturnNullBody () {
		assertThat(adminService.readByPseudonyme(null).getBody()).isNull();
	}
	
	@Test
	public void TestReadByPseudonymeWithNoDb_ShouldReturnNullBody () {
		assertThat(adminService.readByPseudonyme(pseudo).getBody()).isNull();
	}
	
	
	
	//****************************************************
			// Test READ BY EMAIL
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByEmailWithValidParam_ShouldReturnEntiteBody () {
		assertThat(adminService.readByEmail(email).getBody()).hasFieldOrPropertyWithValue("email", "kiki@trail.fr");
	}
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByEmailWithInvalidParam_ShouldReturnNullBody () {
		assertThat(adminService.readByEmail("max@max.fr").getBody()).isNull();
	}
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByEmailWithNullParam_ShouldReturnNullBody () {
		assertThat(adminService.readByEmail(null).getBody()).isNull();
	}
	
	@Test
	public void TestReadByEmailWithNoDb_ShouldReturnNullBody () {
		assertThat(adminService.readByEmail(email).getBody()).isNull();
	}
	
	
	//****************************************************
			// Test EXISTS BY EMAIL
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailWithValidParam_ShouldReturnTrue () {
		assertThat(adminService.existsByEmail(email)).isTrue();
	}
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailWithInvalidParam_ShouldReturnFalse () {
		assertThat(adminService.existsByEmail("max@max.fr")).isFalse();
	}
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailWithNullParam_ShouldReturnFalse () {
		assertThat(adminService.existsByEmail(null)).isFalse();
	}
	
	@Test
	public void TestExistsByEmailWithNoDb_ShouldReturnFalse() {
		assertThat(adminService.existsByEmail(email)).isFalse();
	}
	
	
	//****************************************************
			// Test EXISTS BY PSEUDONYME
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByPseudonymeWithValidParam_ShouldReturnTrue () {
		assertThat(adminService.existsByPseudonyme(pseudo)).isTrue();
	}
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByPseudonymeWithInvalidParam_ShouldReturnFalse () {
		assertThat(adminService.existsByPseudonyme("ironman")).isFalse();
	}
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByPseudonymeWithNullParam_ShouldReturnFalse () {
		assertThat(adminService.existsByPseudonyme(null)).isFalse();
	}
	
	@Test
	public void TestExistsByPseudonymeWithNoDb_ShouldReturnFalse() {
		assertThat(adminService.existsByPseudonyme(pseudo)).isFalse();
	}
	
	//****************************************************
			// Test EXISTS BY EMAIL EN MDP
	
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailAndMdpWithValidParam_ShouldReturnEntiteBody() {
		assertThat(adminService.existsByEmailAndMdp(email, mdp).getBody()).hasFieldOrPropertyWithValue("pseudonyme", "extra terrestre");
	}

	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailAndMdpWithNullParam_ShouldReturnNullBody() {
		assertThat(adminService.existsByEmailAndMdp(email, null).getBody()).isNull();
	}

	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailAndMDpWithOneNullParam_ShouldReturnNullBody() {
		assertThat(adminService.existsByEmailAndMdp(null, null).getBody()).isNull();
	}

	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (20,'françois@trail.fr','4TEST4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailAndMdpWithInvalidParam_ShouldReturnNullBody() {
		assertThat(adminService.existsByEmailAndMdp(email, "4TEST4TEST").getBody()).isNull();
	}

	@Test
	public void testByEmailAndMdpWithNoDb_ShouldReturnNullBody() {
		assertThat(adminService.existsByEmailAndMdp(email, mdp).getBody()).isNull();
	}

}
