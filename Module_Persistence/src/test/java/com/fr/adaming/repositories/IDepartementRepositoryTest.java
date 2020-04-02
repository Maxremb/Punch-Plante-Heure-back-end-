package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
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
	 * Cette méthode teste la recherche d'un département dans la BD par son nom  via couche repository
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (2, '2020-02-21', 0, 15, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingDepartementByNom_shouldReturnListOfOneDepartement() {
		//préparer les inputs
		Integer numeroInput = 1 ;
		String nomInput = "nom4Test";
		
		//invoquer l'appli
		List<Departement> result = depRepo.findDepartementByNom(nomInput);
		
		//assertion
	    assertThat(result).isNotNull().asList().isNotEmpty().hasSize(1);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("nom", nomInput);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("numeroDep", 1);
	}
	
	/**
	 * Cette méthode teste la recherche des données météo pour un département (via numéro département) via couche repository
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (2, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingMeteoByDepartement_shouldReturnListOfMeteo() {
		//préparer les inputs
		int idMeteo1 = 1 ;
		int idMeteo2 = 2 ;
		LocalDate date = LocalDate.parse("2020-02-20");
		int pluie = 5 ; 
		double temperature = 20 ; 
		int idDep = 1 ;
				
		//invoquer l'appli
		List<Meteo> result = depRepo.findMeteoByNumeroDep(idDep);
		
		//assertion
	    assertThat(result).isNotNull().asList().isNotEmpty().hasSize(2);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("id", idMeteo1);
	    assertThat(result.get(1)).hasFieldOrPropertyWithValue("id", idMeteo2);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("date", date);
	    assertThat(result.get(1)).hasFieldOrPropertyWithValue("date", date);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("pluie", pluie);
	    assertThat(result.get(1)).hasFieldOrPropertyWithValue("pluie", pluie);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("temperature", temperature);
	    assertThat(result.get(1)).hasFieldOrPropertyWithValue("temperature", temperature);
	
	}

}
