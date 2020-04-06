package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.enums.EtatPlante;
import com.fr.adaming.enums.EtatSante;
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
	public void testReadAllWithContent_shouldReturnPage() {
		
		
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
		
		
		assertTrue(list.getBody().toList().size() == 1);
		assertThat(list.getBody().toList().get(0)).isNotNull().hasFieldOrPropertyWithValue("dateSemis", LocalDate.parse("2020-04-01"));
		assertThat(list.getBody().toList().get(0)).isNotNull().hasFieldOrPropertyWithValue("datePlantation", LocalDate.parse("2020-04-01"));
	}
	

	@Test
	@Override
	public void testReadAllNoContent_shouldReturnEmptyPage() {
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
	
	
	/**
	 * <p>Cette méthode teste la création d'une Plante Utilisateur avec des conditions valides.</p>
	 */
	@Sql(statements = "insert into utilisateur (id, nom) values(1,'Martinez')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingValidEntity_ShouldReturnEntity() {
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
		
		PlanteUtilisateur planteUtilisateur = new PlanteUtilisateur();
		planteUtilisateur.setId(1);
		String date = "2020-04-01";
		LocalDate localDate = LocalDate.parse(date);
		planteUtilisateur.setDatePlantation(localDate);
		planteUtilisateur.setDateSemis(localDate);
		planteUtilisateur.setEtatPlante(EtatPlante.semis);
		planteUtilisateur.setEtatSante(EtatSante.bonneSante);
		planteUtilisateur.setJardin(jardin);
		planteUtilisateur.setPlanteModel(planteModel);
		
		ServiceResponse<PlanteUtilisateur> response = planteUtilisateurService.create(planteUtilisateur);
		
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody()).isNotNull().hasFieldOrPropertyWithValue("dateSemis", LocalDate.parse("2020-04-01"));
	}
	
	/**
	 * <p>Cette méthode test la création d'une Plante Utiisateur Nulle</p>
	 */
	@Test
	public void testCreatingNullEntity_shouldReturnNull() {
		ServiceResponse<PlanteUtilisateur> response = planteUtilisateurService.create(null);
		
		assertThat(response.getBody()).isNull();
	}
	
	
	
	/**
	 * <p>Cette méthode test la création d'une Plante Utiisateur avec un Id déjà existant.</p>
	 */
	@Sql(statements = "insert into utilisateur (id, nom) values(1,'Martinez')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur values(2,'2020-04-03', '2020-04-03', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingEntityWithExistingId_shouldReturnNull() {
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(1);
		utilisateur.setNom("Martinez");

		Meteo meteo = new Meteo();
		meteo.setId(1);
		meteo.setPluie(5);
		meteo.setDate(LocalDate.parse("2020-02-20"));
		meteo.setTemperature(20);
		
		Departement dep = new Departement();
		dep.setNumeroDep(69);
		dep.setNom("Rhone");
		List<Meteo> listMeteo = new ArrayList<Meteo>();
		listMeteo.add(meteo);
		dep.setMeteoDep(listMeteo);

		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("Jardin1");
		jardin.setDepartement(dep);
		jardin.setUtilisateur(utilisateur);
		
		PlanteModel planteModel = new PlanteModel();
		planteModel.setId(2);
		planteModel.setNomScientifique("Hibiscus");
		
		PlanteUtilisateur planteUtilisateur = new PlanteUtilisateur();
		planteUtilisateur.setId(1);
		String date = "2020-04-01";
		LocalDate localDate = LocalDate.parse(date);
		planteUtilisateur.setDatePlantation(localDate);
		planteUtilisateur.setDateSemis(localDate);
		planteUtilisateur.setEtatPlante(EtatPlante.semis);
		planteUtilisateur.setEtatSante(EtatSante.bonneSante);
		planteUtilisateur.setJardin(jardin);
		planteUtilisateur.setPlanteModel(planteModel);
		
		ServiceResponse<PlanteUtilisateur> response = planteUtilisateurService.create(planteUtilisateur);
		
		assertThat(response.getBody()).isNull();
		
	}
	
	
	/**
	 * <p>Cette méthode test la modification d'une Plante Utiisateur avec les conditions optimales.</p>
	 */
	@Sql(statements = "insert into utilisateur (id, nom) values(1,'Martinez')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur values(2,'2020-04-03', '2020-04-03', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateEntity_ShouldReturnEntity() {
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(1);
		utilisateur.setNom("Martinez");

		Meteo meteo = new Meteo();
		meteo.setId(1);
		meteo.setPluie(5);
		meteo.setDate(LocalDate.parse("2020-02-20"));
		meteo.setTemperature(20);
		
		Departement dep = new Departement();
		dep.setNumeroDep(69);
		dep.setNom("Rhone");
		List<Meteo> listMeteo = new ArrayList<Meteo>();
		listMeteo.add(meteo);
		dep.setMeteoDep(listMeteo);

		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("Jardin1");
		jardin.setDepartement(dep);
		jardin.setUtilisateur(utilisateur);
		
		PlanteModel planteModel = new PlanteModel();
		planteModel.setId(2);
		planteModel.setNomScientifique("Hibiscus");
		
		PlanteUtilisateur planteUtilisateur = new PlanteUtilisateur();
		planteUtilisateur.setId(1);
		String date = "2020-04-01";
		LocalDate localDate = LocalDate.parse(date);
		planteUtilisateur.setDatePlantation(localDate);
		planteUtilisateur.setDateSemis(localDate);
		planteUtilisateur.setEtatPlante(EtatPlante.jeunePousse);
		planteUtilisateur.setEtatSante(EtatSante.bonneSante);
		planteUtilisateur.setJardin(jardin);
		planteUtilisateur.setPlanteModel(planteModel);
		
		ServiceResponse<PlanteUtilisateur> response = planteUtilisateurService.update(planteUtilisateur);
		
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getMessage()).isEqualTo("Succes");
		assertThat(response.getBody()).hasFieldOrPropertyWithValue("etatPlante", planteUtilisateur.getEtatPlante());
		
	}
	
	
	/**
	 * <p>Cette méthode test la modification d'une Plante Utiisateur en modifiant l'id avec un id avec un déjà existant.</p>
	 */
	@Sql(statements = "insert into utilisateur (id, nom) values(1,'Martinez')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateWithNotExistingId_shouldReturnNull() {
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(1);
		utilisateur.setNom("Martinez");


		Meteo meteo = new Meteo();
		meteo.setId(1);
		meteo.setPluie(5);
		meteo.setDate(LocalDate.parse("2020-02-20"));
		meteo.setTemperature(20);
		
		Departement dep = new Departement();
		dep.setNumeroDep(69);
		dep.setNom("Rhone");
		List<Meteo> listMeteo = new ArrayList<Meteo>();
		listMeteo.add(meteo);
		dep.setMeteoDep(listMeteo);

		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("Jardin1");
		jardin.setDepartement(dep);
		jardin.setUtilisateur(utilisateur);
		
		PlanteModel planteModel = new PlanteModel();
		planteModel.setId(2);
		planteModel.setNomScientifique("Hibiscus");
		
		PlanteUtilisateur planteUtilisateur = new PlanteUtilisateur();
		planteUtilisateur.setId(2);
		String date = "2020-04-01";
		LocalDate localDate = LocalDate.parse(date);
		planteUtilisateur.setDatePlantation(localDate);
		planteUtilisateur.setDateSemis(localDate);
		planteUtilisateur.setEtatPlante(EtatPlante.jeunePousse);
		planteUtilisateur.setEtatSante(EtatSante.bonneSante);
		planteUtilisateur.setJardin(jardin);
		planteUtilisateur.setPlanteModel(planteModel);
		
		ServiceResponse<PlanteUtilisateur> response = planteUtilisateurService.update(planteUtilisateur);
		
		assertNull(response.getBody());
	}
	
	
	/**
	 * <p>Cette méthode test la modification d'une Plante Utilisateur en modifiant l'entite avec un null.</p>
	 */
	@Test
	public void testUpdateNullEntity_shouldReturnNull() {
		ServiceResponse<PlanteUtilisateur> response =planteUtilisateurService.update(null);
		
		assertNull(response.getBody());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
