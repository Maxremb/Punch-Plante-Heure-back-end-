package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
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
	public void testFetchingDepartementByNom_shouldReturnOneDepartement() {
		//préparer les inputs
		Integer numeroInput = 1 ;
		String nomInput = "nom4Test";
		
		//invoquer l'appli
		Departement result = depRepo.findDepartementByNom(nomInput);
		
		//assertion
		assertThat(result).isNotNull().hasFieldOrPropertyWithValue("nom", nomInput);
		assertThat(result).isNotNull().hasFieldOrPropertyWithValue("numeroDep", 1);
	}
	
//	/**
//	 * Cette méthode teste la recherche des données météo pour un département (via numéro département) via couche repository
//	 */
//	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (1, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Meteo (id, date, pluie, temperature, departement_id) VALUES (2, '2020-02-20', 5, 20, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Test
//	public void testFetchingMeteoByDepartement_shouldReturnPageOfMeteo() {
//		//préparer les inputs
//		int idMeteo1 = 1 ;
//		int idMeteo2 = 2 ;
//		LocalDate date = LocalDate.parse("2020-02-20");
//		int pluie = 5 ; 
//		double temperature = 20 ; 
//		int idDep = 1 ;
//				
//		Pageable pageable = PageRequest.of(0, 20);
//		
//		//invoquer l'appli
//		Page<Meteo> resultPage = depRepo.findMeteoByNumeroDep(pageable, idDep);
//		
//		//assertion
//	    assertThat(resultPage).isNotNull().asList().isNotEmpty().hasSize(2);
//	    List<Meteo> resultList = resultPage.getContent();
//	    assertThat(resultList.get(0)).hasFieldOrPropertyWithValue("id", idMeteo1);
//	    assertThat(resultList.get(1)).hasFieldOrPropertyWithValue("id", idMeteo2);
//	    assertThat(resultList.get(0)).hasFieldOrPropertyWithValue("date", date);
//	    assertThat(resultList.get(1)).hasFieldOrPropertyWithValue("date", date);
//	    assertThat(resultList.get(0)).hasFieldOrPropertyWithValue("pluie", pluie);
//	    assertThat(resultList.get(1)).hasFieldOrPropertyWithValue("pluie", pluie);
//	    assertThat(resultList.get(0)).hasFieldOrPropertyWithValue("temperature", temperature);
//	    assertThat(resultList.get(1)).hasFieldOrPropertyWithValue("temperature", temperature);
//	}

}
