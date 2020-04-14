package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;
import com.fr.adaming.entity.PlanteUtilisateur;

/**
 * <p>
 * Classe de Tests pour la Repository de l'entite Plante Utilisateur
 * </p>
 * 
 * @author Lucie Martinez
 * @since 0.0.1
 *
 */
@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IPlanteUtilisateurRepositoryTests {

	@Autowired
	public IPlanteUtilisateurRepository repo;

	/**
	 * Méthode de test pour afficher la liste des Plantes Utilisateur par Jardin pour un id de jardin valide
	 */
	@Sql(statements = "insert into departement (numero_dep, nom) values (69,'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep) values (1,'nomjardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values (1,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, jardin_id, plante_model_id) values (1, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingPlanteUtilisateurByIdJardin_shouldReturnListPlanteUtilisateur() {
		Pageable pageable = PageRequest.of(0, 6);
		Page<PlanteUtilisateur> result = repo.findByJardin(1, pageable); 

		assertThat(result).isNotNull();
		assertThat(result.getSize()).isEqualTo(6);
		assertThat(result.getNumber()).isEqualTo(0);
		assertThat(result.getContent().get(0)).hasFieldOrPropertyWithValue("id", 1);

	}

	/**
	 * Méthode de test pour afficher la liste des Plantes Utilisateur par Jardin pour un id de jardin invalide
	 */
	@Sql(statements = "insert into departement (numero_dep, nom) values (69,'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, departement_numero_dep) values (1,'nomjardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_model (id, nom_scientifique) values (1,'Hibiscus')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, jardin_id, plante_model_id) values (1, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingPlanteUtilisateurByIdJardinInvalid_shouldReturnListPlanteUtilisateurEmpty() {
		Pageable pageable = PageRequest.of(0, 6);
		Page<PlanteUtilisateur> result = repo.findByJardin(2, pageable); 

		assertThat(result).isNotNull();
		assertThat(result.getContent()).isEmpty();

	}


}
