package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;

/**
 * Cette classe teste la couche repository de l'entité Département
 * @author Isaline MILLET
 * @since 0.0.1
 */
@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IDepartementRepositoryTest {
	
	@Autowired
	private IDepartementRepository depRepo;
	
	/**
	 * Cette méthode teste la recherche d'un département dans la BD par son nom 
	 */
	@Sql(statements = "INSERT INTO Departement (numeroDep, nom) VALUES (001, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, departement_id) VALUES (1, 001)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingDepartementByNom_shouldReturnListOfOneDepartement() {
		//préparer les inputs
		Integer numeroInput = 001 ;
		String nomInput = "nom4Test";
		
		//invoquer l'appli
		List<Departement> result = depRepo.findDepartementByNom(nomInput);
		
		//assertion
	    assertThat(result).isNotNull().asList().isNotEmpty().hasSize(1);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("nom", nomInput);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("numeroDep", 001);
	}
	
	/**
	 * Cette méthode teste la recherche des données météo pour un département (via numéro département)
	 */
	@Sql(statements = "INSERT INTO Departement (numeroDep, nom) VALUES (001, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, departement_id) VALUES (1, 001)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, departement_id) VALUES (2, 001)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)	
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingMeteoByDepartement_shouldReturnListOfMeteo() {
		//préparer les inputs
		Integer numeroInput = 001 ;
		
		
		//invoquer l'appli
		List<Meteo> result = depRepo.findMeteoByNumeroDep(numeroInput);
		
		//assertion
	    assertThat(result).isNotNull().asList().isNotEmpty().hasSize(2);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("id", 1);
	    assertThat(result.get(1)).hasFieldOrPropertyWithValue("id", 2);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("departement_id", 001);
	    assertThat(result.get(1)).hasFieldOrPropertyWithValue("departement_id", 001);
	}

}
