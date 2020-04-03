package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.service.IServiceTests;

/**
 * <p>
 * Classe de Tests pour le Service de l'entite Plante Utilisateur
 * </p>
 * 
 * @author Lucie
 * @since 0.0.1
 *
 */
@SpringBootTest(classes = ModuleServiceApplication.class)
public class PlanteUtilisateurServiceTests implements IServiceTests {

	@Autowired
	private PlanteUtilisateurServiceImpl planteUtilisateurService;

	@Sql(statements = "insert into utilisateur (id, nom) values(1,'Martinez')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingValidId_shouldReturnTrue() {
		assertTrue(planteUtilisateurService.deleteById(1));
	}

	
	@Test
	@Sql(statements = "insert into utilisateur (id, nom) values(1,'Martinez')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertFalse(planteUtilisateurService.deleteById(2));
		
	}

	
	@Test
	@Sql(statements = "insert into utilisateur (id, nom) values(1,'Martinez')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testReadAllWithContent_shouldReturnList() {
		
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(1);
		utilisateur.setNom("Martinez");

		Departement dep = new Departement();
		dep.setNumeroDep(69);
		dep.setNom("Rhone");

		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("Jardin1");
		jardin.setDepartement(dep);
		jardin.setUtilisateur(utilisateur);
		
		PlanteModel planteModel = new PlanteModel();
		planteModel.setId(2);
		planteModel.setNomScientifique("Hibiscus");
		
		ServiceResponse<Page<PlanteUtilisateur>> list = planteUtilisateurService.readAll(0);
		
		
		assertTrue(planteUtilisateurService.readAll(0).getBody().toList().size() == 1);
		assertThat(planteUtilisateurService.readAll(0).getBody().toList().get(0)).isNotNull().hasFieldOrPropertyWithValue("dateSemis", LocalDate.parse("2020-04-01"));
		assertThat(planteUtilisateurService.readAll(0).getBody().toList().get(0)).isNotNull().hasFieldOrPropertyWithValue("datePlantation", LocalDate.parse("2020-04-01"));
//		assertThat(planteUtilisateurService.readAll(0).getBody().toList().get(0)).isNotNull().hasFieldOrPropertyWithValue("etatPlante", 0);
//		assertThat(planteUtilisateurService.readAll(0).getBody().toList().get(0)).isNotNull().hasFieldOrPropertyWithValue("etatSante", 1);
		
//		assertThat(planteUtilisateurService.readAll(0).getBody().toList().get(0).getJardin()).isNotNull().hasFieldOrPropertyWithValue("jardin", jardin);
//		assertThat(planteUtilisateurService.readAll(0).getBody().toList().get(0).getPlanteModel()).isNotNull().hasFieldOrPropertyWithValue("planteModel", planteModel);
		
		
//		PlanteUtilisateur planteUtilisateur = new PlanteUtilisateur();
//		planteUtilisateur.setId(1);
//		String date = "2020-04-01";
//		LocalDate localDate = LocalDate.parse(date);
//		planteUtilisateur.setDatePlantation(localDate);
//		planteUtilisateur.setDateSemis(localDate);
//		planteUtilisateur.setEtatPlante(EtatPlante.semis);
//		planteUtilisateur.setEtatSante(EtatSante.bonneSante);
//		
//		List<PlanteUtilisateur> expectedList = new ArrayList();
//		
//		expectedList.add(planteUtilisateur);
//		
//		ServiceResponse<List<PlanteUtilisateur>> list = planteUtilisateurService.readAll();
//		assertEquals(expectedList, list);
	}
	

	@Test
	@Override
	public void testReadAllNoContent_shouldReturnEmptyList() {
		ServiceResponse<Page<PlanteUtilisateur>> list = planteUtilisateurService.readAll(0);
		assertTrue(list.getBody().toList().isEmpty());

	}

	@Test
	@Sql(statements = "insert into utilisateur (id, nom) values(1,'Martinez')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testReadByIdValidId_shouldReturnEntity() {
		String date = "2020-04-01";
		LocalDate localDate = LocalDate.parse(date);
		assertThat(planteUtilisateurService.readById(1).getBody()).isNotNull().hasFieldOrPropertyWithValue("dateSemis", localDate);
		
//		PlanteUtilisateur planteUtilisateur = new PlanteUtilisateur();
//		planteUtilisateur.setId(1);
//		String date = "2020-04-01";
//		LocalDate localDate = LocalDate.parse(date);
//		planteUtilisateur.setDatePlantation(localDate);
//		planteUtilisateur.setDateSemis(localDate);
//		planteUtilisateur.setEtatPlante(EtatPlante.semis);
//		planteUtilisateur.setEtatSante(EtatSante.bonneSante);
//		
//		assertEquals(planteUtilisateur, planteUtilisateurService.readById(1));
	}

	@Test
	@Override
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertThat(planteUtilisateurService.readById(1).getBody()).isNull();
		assertThat(planteUtilisateurService.readById(1).getMessage())
		.isEqualTo("Une entité avec cet ID n'existe pas dans la base de données");

	}

	@Test
	@Sql(statements = "insert into utilisateur (id, nom) values(1,'Martinez')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertTrue(planteUtilisateurService.existsById(1));
		
	}

	@Test
	@Sql(statements = "insert into utilisateur (id, nom) values(1,'Martinez')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		assertFalse(planteUtilisateurService.existsById(2));

	}
	
	
	@Test
	public void testCreateValid_ShouldReturnEntity() {
		
	}
}
