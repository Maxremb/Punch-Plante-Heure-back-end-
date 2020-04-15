package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.service.IAdminService;
import com.fr.adaming.service.IService;
import com.fr.adaming.service.IServiceTests;

/**
 * Test de la couche Service Admin qui implemente une interface pour les
 * méthodes CRUD (read All, read by Id, exist by Id, delete by Id) et définit
 * ses propres méthodes de test pour les autres.
 * 
 * @author Maxime Rembert / Isaline MILLET
 * @since 0.0.1-SNAPSHOT
 *
 */
@SpringBootTest(classes = ModuleServiceApplication.class)
public class AdminServiceTest implements IServiceTests {

	@Autowired
	private IAdminService adminService;

	@Autowired
	private IService<Admin> service;

	// paramètres admin
	private static final String pseudo = "extra terrestre";
	private static final String email = "kiki@trail.fr";
	private static final String mdp = "4TEST";
	// ID EMAIL MDP PSEUDO NON NULLABLE

	// ****************************************************
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

	// ****************************************************
	// Test READ ALL

	@Override
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (20,'françois@trail.fr','4TEST4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadAllWithContent_shouldReturnPage() {
		assertThat(service.readAll(0).getBody().toList().size() == 2).isTrue();
		assertThat(service.readAll(0).getBody().toList().get(0)).hasFieldOrPropertyWithValue("email", "kiki@trail.fr");
		assertThat(service.readAll(0).getBody().toList().get(1)).hasFieldOrPropertyWithValue("email",
				"françois@trail.fr");
	}

	@Override
	@Test
	public void testReadAllNoContent_shouldReturnEmptyPage() {
		assertThat(service.readAll(0).getBody().isEmpty()).isTrue();

	}

	// ****************************************************
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

	// ****************************************************
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

	// ****************************************************
	// Test READ BY PSEUDONYME

	/**
	 * Cette méthode teste la récupération d'un admin via son pseudo - conditions
	 * valides
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByPseudonymeWithValidParam_ShouldReturnEntiteBody() {
		assertThat(adminService.readByPseudonyme(pseudo).getBody()).hasFieldOrPropertyWithValue("email",
				"kiki@trail.fr");
	}

	/**
	 * Cette méthode teste la récupération d'un admin via son pseudo - conditions
	 * invalides (pseudo inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByPseudonymeWithInvalidParam_ShouldReturnNullBody() {
		assertThat(adminService.readByPseudonyme("terrestre").getBody()).isNull();
	}

	/**
	 * Cette méthode teste la récupération d'un admin via son pseudo - conditions
	 * invalides (admin null)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByPseudonymeWithNullParam_ShouldReturnNullBody() {
		assertThat(adminService.readByPseudonyme(null).getBody()).isNull();
	}

	/**
	 * Cette méthode teste la récupération d'un admin via son pseudo - conditions
	 * invalides
	 */
	@Test
	public void TestReadByPseudonymeWithNoDb_ShouldReturnNullBody() {
		assertThat(adminService.readByPseudonyme(pseudo).getBody()).isNull();
	}

	// ****************************************************
	// Test READ BY EMAIL

	/**
	 * Cette méthode teste la récupération d'un admin via son email - conditions
	 * valides
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByEmailWithValidParam_ShouldReturnEntiteBody() {
		assertThat(adminService.readByEmail(email).getBody()).hasFieldOrPropertyWithValue("email", "kiki@trail.fr");
	}

	/**
	 * Cette méthode teste la récupération d'un admin via son email - conditions
	 * invalides (email inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByEmailWithInvalidParam_ShouldReturnNullBody() {
		assertThat(adminService.readByEmail("max@max.fr").getBody()).isNull();
	}

	/**
	 * Cette méthode teste la récupération d'un admin via son email - conditions
	 * invalides (email null)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestReadByEmailWithNullParam_ShouldReturnNullBody() {
		assertThat(adminService.readByEmail(null).getBody()).isNull();
	}

	/**
	 * Cette méthode teste la récupération d'un admin via son email - conditions
	 * invalides (db nulle)
	 */
	@Test
	public void TestReadByEmailWithNoDb_ShouldReturnNullBody() {
		assertThat(adminService.readByEmail(email).getBody()).isNull();
	}

	// ****************************************************
	// Test EXISTS BY EMAIL

	/**
	 * Cette méthode teste l'existence d'un admin via son email - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailWithValidParam_ShouldReturnTrue() {
		assertThat(adminService.existsByEmail(email)).isTrue();
	}

	/**
	 * Cette méthode teste l'existence d'un admin via son email - conditions
	 * invalides (email inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailWithInvalidParam_ShouldReturnFalse() {
		assertThat(adminService.existsByEmail("max@max.fr")).isFalse();
	}

	/**
	 * Cette méthode teste l'existence d'un admin via son email - conditions
	 * invalides (null)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailWithNullParam_ShouldReturnFalse() {
		assertThat(adminService.existsByEmail(null)).isFalse();
	}

	/**
	 * Cette méthode teste l'existence d'un admin via son email - conditions
	 * invalides (db vide)
	 */
	@Test
	public void TestExistsByEmailWithNoDb_ShouldReturnFalse() {
		assertThat(adminService.existsByEmail(email)).isFalse();
	}

	// ****************************************************
	// Test EXISTS BY PSEUDONYME

	/**
	 * Cette méthode teste l'existence d'un admin via son pseudo - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByPseudonymeWithValidParam_ShouldReturnTrue() {
		assertThat(adminService.existsByPseudonyme(pseudo)).isTrue();
	}

	/**
	 * Cette méthode teste l'existence d'un admin via son pseudo - conditions invalides (pseudo inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByPseudonymeWithInvalidParam_ShouldReturnFalse() {
		assertThat(adminService.existsByPseudonyme("ironman")).isFalse();
	}

	/**
	 * Cette méthode teste l'existence d'un admin via son pseudo - conditions invalides (pseudo null)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByPseudonymeWithNullParam_ShouldReturnFalse() {
		assertThat(adminService.existsByPseudonyme(null)).isFalse();
	}

	/**
	 * Cette méthode teste l'existence d'un admin via son pseudo - conditions invalides (db vide)
	 */
	@Test
	public void TestExistsByPseudonymeWithNoDb_ShouldReturnFalse() {
		assertThat(adminService.existsByPseudonyme(pseudo)).isFalse();
	}

	// ****************************************************
	// Test EXISTS BY EMAIL ET MDP

	/**
	 * Cette méthode teste l'existence d'un admin via son email et mdp - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailAndMdpWithValidParam_ShouldReturnEntiteBody() {
		assertThat(adminService.existsByEmailAndMdp(email, mdp).getBody()).hasFieldOrPropertyWithValue("pseudonyme",
				"extra terrestre");
	}

	/**
	 * Cette méthode teste l'existence d'un admin via son email et mdp - conditions invalides (mdp null)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailAndMdpWithNullParam_ShouldReturnNullBody() {
		assertThat(adminService.existsByEmailAndMdp(email, null).getBody()).isNull();
	}

	/**
	 * Cette méthode teste l'existence d'un admin via son email et mdp - conditions invalides (email et mdp null)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailAndMDpWithOneNullParam_ShouldReturnNullBody() {
		assertThat(adminService.existsByEmailAndMdp(null, null).getBody()).isNull();
	}

	/**
	 * Cette méthode teste l'existence d'un admin via son email et mdp - conditions invalides (mdp inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (10,'kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (20,'françois@trail.fr','4TEST4TEST','vigneron') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestExistsByEmailAndMdpWithInvalidParam_ShouldReturnNullBody() {
		assertThat(adminService.existsByEmailAndMdp(email, "4TEST4TEST").getBody()).isNull();
	}

	/**
	 * Cette méthode teste l'existence d'un admin via son email et mdp - conditions invalides (db nulle)
	 */
	@Test
	public void testByEmailAndMdpWithNoDb_ShouldReturnNullBody() {
		assertThat(adminService.existsByEmailAndMdp(email, mdp).getBody()).isNull();
	}
	
	// ****************************************************
		// Test CREATE

	/**
	 * Cette méthode teste la création d'un admin - conditions valides
	 */
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateValid_ShouldReturnEntity() {

		Admin admin = new Admin();
		admin.setEmail("email4Test");
		admin.setMdp("mdp4Test");
		admin.setPseudonyme("pseudo4Test");

		ServiceResponse<Admin> resp = service.create(admin);

		assertThat(resp.getBody()).isNotNull();
		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("email", "email4Test");
		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("mdp", "mdp4Test");
		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("pseudonyme", "pseudo4Test");
		assertThat(resp.getMessage()).isEqualTo("Success");
	}

	/**
	 * Cette méthode teste la création d'un admin - conditions invalides (admin
	 * NULL)
	 */
	@Test
	public void testCreateNULL_ShouldReturnNull() {
		ServiceResponse<Admin> resp = service.create(null);

		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Objet d'entrée null");
	}

	/**
	 * Cette méthode teste la création d'un admin - conditions invalides (email
	 * existant)
	 */
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'email4Test','mdp4Test','pseudo4Test') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateExistingEmail_ShouldReturnNull() {
		Admin admin = new Admin();
		admin.setEmail("email4Test");
		admin.setMdp("mdp");
		admin.setPseudonyme("pseudo");

		ServiceResponse<Admin> resp = service.create(admin);

		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Email ou pseudo deja utilisé");
	}

	/**
	 * Cette méthode teste la création d'un admin - conditions invalides (pseudo
	 * existant)
	 */
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'email4Test','mdp4Test','pseudo4Test') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateExistingPseudo_ShouldReturnNull() {
		Admin admin = new Admin();
		admin.setEmail("email");
		admin.setMdp("mdp");
		admin.setPseudonyme("pseudo4Test");

		ServiceResponse<Admin> resp = service.create(admin);

		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Email ou pseudo deja utilisé");
	}
	
	// ****************************************************
		// Test UPDATE

	/**
	 * Cette méthode teste la modification d'un admin - conditions valides
	 */
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'email4Test','mdp4Test','pseudo4Test') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateExistingAdmin_ShouldReturnEntite() {
		Admin admin = new Admin();
		admin.setId(1);
		admin.setEmail("email");
		admin.setMdp("mdp");
		admin.setPseudonyme("pseudo");

		ServiceResponse<Admin> resp = service.update(admin);

		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("email", "email");
		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("mdp", "mdp");
		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("pseudonyme", "pseudo");
		assertThat(resp.getMessage()).isEqualTo("Success");
	}

	/**
	 * Cette méthode teste la modification d'un admin - conditions invalides (admin
	 * non existant)
	 */
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'email4Test','mdp4Test','pseudo4Test') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateExistingNotExistingAdmin_ShouldReturnNull() {
		Admin admin = new Admin();
		admin.setId(2);
		admin.setEmail("email4Test");
		admin.setMdp("mdp4Test");
		admin.setPseudonyme("pseudo4Test");

		ServiceResponse<Admin> resp = service.update(admin);

		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage())
				.isEqualTo("Modification non réalisée : id inconnu dans la database ou entité nulle");
	}

	/**
	 * Cette méthode teste la modification d'un admin - conditions invalides (modif
	 * par email existant)
	 */
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'email4Test','mdp4Test','pseudo4Test') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (2,'email','mdp','pseudo') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateExistingEmail_ShouldReturnNull() {
		Admin admin = new Admin();
		admin.setId(1);
		admin.setEmail("email");
		admin.setMdp("mdp2");
		admin.setPseudonyme("pseudo2");

		ServiceResponse<Admin> resp = service.update(admin);

		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Exception lors de la modification dans la DB");
	}

	/**
	 * Cette méthode teste la modification d'un admin - conditions invalides (modif
	 * par pseudo existant)
	 */
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (1,'email4Test','mdp4Test','pseudo4Test') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO admin (id,email,mdp,pseudonyme) VALUES (2,'email','mdp','pseudo') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM admin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateExistingPseudo_ShouldReturnNull() {
		Admin admin = new Admin();
		admin.setId(1);
		admin.setEmail("email2");
		admin.setMdp("mdp2");
		admin.setPseudonyme("pseudo");

		ServiceResponse<Admin> resp = service.update(admin);

		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Exception lors de la modification dans la DB");
	}

	/**
	 * Cette méthode teste la modification d'un admin - conditions invalides (null)
	 */
	@Test
	public void testUpdateNull_ShouldReturnNull() {

		ServiceResponse<Admin> resp = service.update(null);

		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage())
				.isEqualTo("Modification non réalisée : id inconnu dans la database ou entité nulle");
	}
}
