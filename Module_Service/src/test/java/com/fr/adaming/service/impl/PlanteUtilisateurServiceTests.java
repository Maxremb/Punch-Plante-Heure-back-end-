package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import com.fr.adaming.enums.EtatPlante;
import com.fr.adaming.enums.EtatSante;
import com.fr.adaming.service.IServiceTests;

/**
 * <p>
 * Classe de Tests pour le Service de l'entite Plante Utilisateur
 * </p>
 * 
 * @author Lucie Martinez / Isaline Millet
 * @since 0.0.1
 *
 */
@SpringBootTest(classes = ModuleServiceApplication.class)
public class PlanteUtilisateurServiceTests implements IServiceTests {

	@Autowired
	private PlanteUtilisateurServiceImpl planteUtilisateurService;

	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id)  values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testReadAllWithContent_shouldReturnPage() {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(1);
		utilisateur.setNom("Stark");
		utilisateur.setEmail("stark@stark.fr");
		utilisateur.setMdp("4TEST");
		utilisateur.setPrenom("tony");
		utilisateur.setPseudonyme("IronMan");

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
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id)  values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
	}

	@Test
	@Override
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertThat(planteUtilisateurService.readById(1).getBody()).isNull();
		assertThat(planteUtilisateurService.readById(1).getMessage())
		.isEqualTo("Une entité avec cet ID n'existe pas dans la base de données");

	}

	@Test
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id)  values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id) values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
		utilisateur.setNom("Stark");
		utilisateur.setEmail("stark@stark.fr");
		utilisateur.setMdp("4TEST");
		utilisateur.setPrenom("tony");
		utilisateur.setPseudonyme("IronMan");

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
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id)  values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id)  values(2,'2020-04-03', '2020-04-03', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingEntityWithExistingId_shouldReturnNull() {
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(1);
		utilisateur.setNom("Stark");
		utilisateur.setEmail("stark@stark.fr");
		utilisateur.setMdp("4TEST");
		utilisateur.setPrenom("tony");
		utilisateur.setPseudonyme("IronMan");

		
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
		
		assertThat(response.getBody()).isNull();
		
	}
	
	
	/**
	 * <p>Cette méthode test la modification d'une Plante Utiisateur avec les conditions optimales.</p>
	 */
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id)  values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id)  values(2,'2020-04-03', '2020-04-03', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateEntity_ShouldReturnEntity() {
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(1);
		utilisateur.setNom("Stark");
		utilisateur.setEmail("stark@stark.fr");
		utilisateur.setMdp("4TEST");
		utilisateur.setPrenom("tony");
		utilisateur.setPseudonyme("IronMan");
		
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
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id)  values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateWithNotExistingId_shouldReturnNull() {
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(1);
		utilisateur.setNom("Stark");
		utilisateur.setEmail("stark@stark.fr");
		utilisateur.setMdp("4TEST");
		utilisateur.setPrenom("tony");
		utilisateur.setPseudonyme("IronMan");


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
	
	/**
	 * <p>Cette méthode test la récupération d'une page de Plantes Utilisateur via id jardin - conditions valides</p>
	 */
	@Test
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id)  values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByJardin_shouldReturnPage() {		
		assertTrue(planteUtilisateurService.readByJardin(1, 0).getBody().toList().size() == 1);
		assertThat(planteUtilisateurService.readByJardin(1, 0).getMessage()).isEqualTo("Succes");
		assertThat(planteUtilisateurService.readByJardin(1, 0).getBody().toList().get(0)).isNotNull().hasFieldOrPropertyWithValue("dateSemis", LocalDate.parse("2020-04-01"));
		assertThat(planteUtilisateurService.readByJardin(1, 0).getBody().toList().get(0)).isNotNull().hasFieldOrPropertyWithValue("datePlantation", LocalDate.parse("2020-04-01"));
	}
	
	/**
	 * <p>Cette méthode test la récupération d'une page de Plantes Utilisateur via id jardin - conditions invalides (id jardin inexistant)</p>
	 */
	@Test
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id)  values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByJardinInexistingId_shouldReturnNull() {		
		assertNull(planteUtilisateurService.readByJardin(2, 0).getBody());
		assertThat(planteUtilisateurService.readByJardin(2, 0).getMessage()).isEqualTo("Jardin inexistant");
	}
	
	/**
	 * <p>Cette méthode test la récupération d'une liste de Plantes Utilisateur via id jardin - conditions valides</p>
	 */
	@Test
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id)  values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadListByJardin_shouldReturnList() {		
		assertTrue(planteUtilisateurService.readByJardin(1).getBody().size() == 1);
		assertThat(planteUtilisateurService.readByJardin(1).getMessage()).isEqualTo("Succes");
		assertThat(planteUtilisateurService.readByJardin(1).getBody().get(0)).isNotNull().hasFieldOrPropertyWithValue("dateSemis", LocalDate.parse("2020-04-01"));
		assertThat(planteUtilisateurService.readByJardin(1).getBody().get(0)).isNotNull().hasFieldOrPropertyWithValue("datePlantation", LocalDate.parse("2020-04-01"));
	}
	
	/**
	 * <p>Cette méthode test la récupération d'une liste de Plantes Utilisateur via id jardin - conditions invalides (id jardin inexistant)</p>
	 */
	@Test
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, date_plantation, date_semis, etat_plante, etat_sante, jardin_id, plante_model_id)  values(1,'2020-04-01', '2020-04-01', 0, 1, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadListByJardinInexistingId_shouldReturnEmptyList() {		
		assertThat(planteUtilisateurService.readByJardin(2).getBody()).isEmpty();
		assertThat(planteUtilisateurService.readByJardin(2).getMessage()).isEqualTo("Jardin inexistant");
	}
	
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeletingByJardinValidId_shouldReturnTrue() {
		assertTrue(planteUtilisateurService.deleteByJardin(1));
	}

	
	@Test
	@Sql(statements = "insert into utilisateur (id, email,mdp,nom,prenom,pseudonyme) values(1,'stark@stark.fr','4TEST','Stark','tony','IronMan')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into departement (numero_dep, nom) values(69,'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep, utilisateur_id) values(1,'Jardin1', 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values(2,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeletingByJardinInvalidId_shouldReturnFalse() {
		assertFalse(planteUtilisateurService.deleteByJardin(2));	
	}
	
}
