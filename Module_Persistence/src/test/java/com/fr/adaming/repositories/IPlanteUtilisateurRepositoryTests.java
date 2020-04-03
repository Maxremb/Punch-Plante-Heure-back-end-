package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

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
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.enums.EtatPlante;
import com.fr.adaming.enums.EtatSante;

/**
 * <p>
 * Classe de Tests pour la Repository de l'entite Plante Utilisateur
 * </p>
 * 
 * @author Lucie
 * @since 0.0.1
 *
 */
@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IPlanteUtilisateurRepositoryTests {

	@Autowired
	public IPlanteUtilisateurRepository repo;

	/**
	 * Méthode de test pour afficher la liste des Plante Utilisateur par Jardin.
	 */
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
	public void testfetchingPlanteUtiliateurByIdJardin_shouldreturnListPlanteUtiliateur() {
		// Préparer les inputs
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

		// Invoquer l'appli
		Pageable pageable = PageRequest.of(0, 6, Sort.by("planteModel"));
		Page<PlanteUtilisateur> result = repo.findByJardin(1, pageable); //problème

		// assertion
		assertThat(result).isNotNull();
		assertThat(result.getSize()).isEqualTo(3);
		assertThat(result.getNumber()).isEqualTo(0);

	}

	/**
	 * Methode de test pour afficher une liste vide si le Jardin n'existe pas.
	 */
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
	public void testfetchingPlanteUtiliateurByNotExistingIdJardin_shouldreturnNull() {
		Page<PlanteUtilisateur> result = repo.findByJardin(3, null);
		assertThat(result).isEmpty();

	}

}
