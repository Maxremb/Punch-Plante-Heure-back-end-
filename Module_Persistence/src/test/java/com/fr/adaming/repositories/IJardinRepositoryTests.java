package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;
import com.fr.adaming.entity.Jardin;

/**
 * Classe responsable des test repository pour l'entité Jardin
 * @author Clara Cadet
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IJardinRepositoryTests {

	@Autowired
	private IJardinRepository repo;
	
	/**
	 * Permet de tester la recherche par un nom existant dans la base de données
	 */
	@Sql(statements = "insert into jardin (id, nom) values (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from user where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByNom_shouldReturnListOfOneJardin() {
		//préparer les inputs
		String nomInput = "nom4Test";
		
		//invoquer l'appli
		List<Jardin> result = repo.findByNom(nomInput);
		
		//assertion
	    assertThat(result).isNotNull().asList().isNotEmpty().hasSize(1);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("nom", nomInput);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("id", 1);
	}
	
	/**
	 * Permet de tester la recherche par un nom non existant dans la base de données
	 */
	@Test
	public void testFetchingJardinByNotExistingNom_shouldReturnEmptyList() {
		//préparer les inputs
		String nomInput = "nom4Test";
		
		//invoquer l'appli
		List<Jardin> result = repo.findByNom(nomInput);
		
		//assertion
	    assertThat(result).isNotNull().asList().isNotEmpty().isEmpty();
	}
	
	/**
	 * Permet de tester la recherche par un identifiant utilisateur existant dans la base de données
	 */
	@Sql(statements = "insert into utilisateur (id, nom) values (1, 'Jean-Eudes')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, utilisateur_id) values (1, 'nom4Test', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from utilisateur where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByUtilisateur_shouldReturnListOfOneJardin() {
		//préparer les inputs
		String nomInput = "nom4Test";
		
		//invoquer l'appli
		List<Jardin> result = repo.findByUtilisateur(1);
		
		//assertion
	    assertThat(result).isNotNull().asList().isNotEmpty().hasSize(1);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("nom", nomInput);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("id", 1);
	}
	
	/**
	 * Permet de tester la recherche par un identifiant utilisateur non existant dans la base de données
	 */
	@Test
	public void testFetchingJardinByNotExistingUtilisateur_shouldReturnEmptyList() {
		//préparer les inputs
		String nomInput = "nom4Test";
		
		//invoquer l'appli
		List<Jardin> result = repo.findByUtilisateur(1);
		
		//assertion
	    assertThat(result).isNotNull().asList().isNotEmpty().isEmpty();
	}
	
	/**
	 * Permet de tester la recherche par un numéro de département existant dans la base de données
	 */
	@Sql(statements = "insert into departement (numeroDep, nom) values (69, 'Rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into jardin (id, nom, numeroDep) values (1, 'nom4Test', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from departement where numeroDep = 69", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from jardin where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByDepartement_shouldReturnListOfOneJardin() {
		//préparer les inputs
		String nomInput = "nom4Test";
		
		//invoquer l'appli
		List<Jardin> result = repo.findByDepartement(69);
		
		//assertion
	    assertThat(result).isNotNull().asList().isNotEmpty().hasSize(1);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("nom", nomInput);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(result.get(0).getDepartement()).hasFieldOrPropertyWithValue("nom", "Rhone");
	}
	
	/**
	 * Permet de tester la recherche par un numéro de département non existant dans la base de données
	 */
	@Test
	public void testFetchingJardinByNotExistingDepartement_shouldReturnListOfOneJardin() {
		//préparer les inputs
		String nomInput = "nom4Test";
		
		//invoquer l'appli
		List<Jardin> result = repo.findByDepartement(69);
		
		//assertion
	    assertThat(result).isNotNull().asList().isNotEmpty().isEmpty();
	}
	
}
