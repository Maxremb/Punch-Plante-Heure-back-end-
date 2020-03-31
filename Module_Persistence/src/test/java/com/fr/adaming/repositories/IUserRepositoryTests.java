package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;
import com.fr.adaming.entity.User;

@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IUserRepositoryTests {

	@Autowired
	private IUserRepository repo;
	
	@Sql(statements = "insert into user values (1, 'nom4Test', 'prenom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from user where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFetchingUsersByNomAndPrenom_shouldReturnListOfOneUser() {
		//préparer les inputs
		String nomInput = "nom4Test";
		String prenomInput = "prenom4Test";
		
		//invoquer l'appli
		List<User> result = repo.findByNomAndPrenom(nomInput, prenomInput);
		
		//assertion
	    assertThat(result).isNotNull().asList().isNotEmpty().hasSize(1);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("nom", nomInput);
	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("prenom", prenomInput);
	}
	
}
