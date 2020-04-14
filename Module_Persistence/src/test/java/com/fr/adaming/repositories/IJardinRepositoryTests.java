package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import com.fr.adaming.ModulePersistenceApplication;
import com.fr.adaming.entity.Jardin;

/**
 * Classe responsable des tests repository pour l'entité Jardin
 * @author Clara Cadet / Isaline MILLET
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IJardinRepositoryTests {

	@Autowired
	private IJardinRepository repo;
	
	/**
	 * Permet de tester la recherche par un nom existant dans la base de données
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nom', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByNom_shouldReturnListOfOneJardin() {
		Pageable pageable = PageRequest.of(0, 6);
		Page<Jardin> result = repo.findByNom(pageable, "nom");
		
		assertThat(result).isNotNull();
	    assertThat(result.toList()).isNotEmpty().hasSize(1);
	    assertThat(result.toList().get(0)).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(result.toList().get(0)).hasFieldOrPropertyWithValue("nom", "nom");
	    
	}
	
	/**
	 * Permet de tester la recherche par un nom non existant dans la base de données
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nom', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByNomInvalid_shouldReturnAListEmpty() {
		Pageable pageable = PageRequest.of(0, 6);
		Page<Jardin> result = repo.findByNom(pageable, "nom_invalide");
		
		assertThat(result).isNotNull();
	    assertThat(result.toList()).isEmpty();
	}
	
	// TEST UTILISATEUR
	
	/**
	 * Permet de tester la recherche par un identifiant utilisateur existant dans la base de données
	 */
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByUtilisateur_shouldReturnListOfOneJardin() {
		//préparer les inputs
		String nomInput = "nom4Test";
		
		//invoquer l'appli
		Pageable pageable = PageRequest.of(0, 6);
		Page<Jardin> result = repo.trouveParUtilisateur(pageable, 1);
		
		//assertion
		assertThat(result).isNotNull();
	    assertThat(result.toList()).isNotEmpty().hasSize(1);
	    assertThat(result.toList().get(0)).hasFieldOrPropertyWithValue("nom", nomInput);
	}
	
	/**
	 * Permet de tester la recherche par un identifiant utilisateur non existant dans la base de données
	 */
	@Sql(statements = "INSERT INTO utilisateur (id,nom,prenom,email,mdp,pseudonyme) VALUES (1,'jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nomTestDepartement')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, utilisateur_id) VALUES (1, 'nom4Test', 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByNotExistingUtilisateur_shouldReturnEmptyList() {
		//invoquer l'appli
		Pageable pageable = PageRequest.of(0, 6);
		Page<Jardin> result = repo.trouveParUtilisateur(pageable, 2);
		
		//assertion
		assertThat(result).isNotNull();
	    assertThat(result.toList()).isEmpty();
	}
	
	/**
	 * Permet de tester la recherche par un numero de departement dans la base de données
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nom', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByNumDepartement_shouldReturnPageOfOneJardin() {
		Pageable pageable = PageRequest.of(0, 6);
		Page<Jardin> result = repo.trouveParDepartement(pageable, 69);
		
		assertThat(result).isNotNull();
	    assertThat(result.toList()).isNotEmpty().hasSize(1);
	    assertThat(result.toList().get(0)).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(result.toList().get(0)).hasFieldOrPropertyWithValue("nom", "nom");
	    
	}
	
	/**
	 * Permet de tester la recherche par un numero de departement si numero invalide dans la base de données
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nom', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByNumDepartementInvalid_shouldReturnPageEmpty() {
		Pageable pageable = PageRequest.of(0, 6);
		Page<Jardin> result = repo.trouveParDepartement(pageable, 75);
		
		assertThat(result).isNotNull();
	    assertThat(result.toList()).isEmpty();	    
	}
	
	/**
	 * Permet de tester la recherche par un numero de departement dans la base de données - liste
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nom', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByNumDepartement_shouldReturnListOfOneJardin() {
		List<Jardin> result = repo.trouveListJardinParDepartement(69);
		
		assertThat(result).isNotNull();
	    assertThat(result).isNotEmpty().hasSize(1);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("nom", "nom");
	}
	
	/**
	 * Permet de tester la recherche par un numero de departement si numero invalide dans la base de données - liste
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nom', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingJardinByNumDepartementInvalid_shouldReturnListEmpty() {
		List<Jardin> result = repo.trouveListJardinParDepartement(125);
		
		assertThat(result).isNotNull();
	    assertThat(result).isEmpty();	    
	}
	
}
