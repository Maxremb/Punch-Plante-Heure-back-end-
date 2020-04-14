package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;
import com.fr.adaming.entity.Departement;

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
	 * 
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingDepartementByNom_shouldReturnOneDepartement() {
		Departement result = depRepo.findDepartementByNom("rhone");
		
		assertThat(result).isNotNull().hasFieldOrPropertyWithValue("nom", "rhone");
		assertThat(result).isNotNull().hasFieldOrPropertyWithValue("numeroDep", 69);
	}

}
