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
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.entity.User;
import com.fr.adaming.enums.EtatPlante;
import com.fr.adaming.enums.EtatSante;

@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IPlanteUtilisateurRepositoryTests {

	@Autowired
	public IPlanteUtilisateurRepository repo;
	
	
	@Sql(statements = "insert into jardin (id, nom) values(1, 'Jardin1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur values(1,'2020-04-01', '2020-04-01', 'semis', 'bonneSante', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from plante_utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testfetchingPlanteUtiliateurByIdJardin_shouldreturnListPlanteUtiliateur(){
		//Préparer les inputs
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("Jardin1");
		
		PlanteUtilisateur planteUtilisateur = new PlanteUtilisateur();
		planteUtilisateur.setId(1);
		String date = "2020-04-01";
		LocalDate localDate = LocalDate.parse(date);
		planteUtilisateur.setDatePlantation(localDate);
		planteUtilisateur.setDateSemis(localDate);
		planteUtilisateur.setEtatPlante(EtatPlante.semis);
		planteUtilisateur.setEtatSante(EtatSante.bonneSante);
		planteUtilisateur.setJardin(jardin);
		
		//Invoquer l'appli
		List<PlanteUtilisateur> result = repo.findByJardin(1);
		
		//assertion
		assertThat(result).isNotNull().asList().isNotEmpty();
		
		
		
		
		
	}
	
//	@Sql(statements = "insert into user values (1, 'nom4Test', 'prenom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "delete from user where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Test
//	public void testFetchingUsersByNomAndPrenom_shouldReturnListOfOneUser() {
//		//préparer les inputs
//		String nomInput = "nom4Test";
//		String prenomInput = "prenom4Test";
//		
//		//invoquer l'appli
//		List<User> result = repo.findByNomAndPrenom(nomInput, prenomInput);
//		
//		//assertion
//	    assertThat(result).isNotNull().asList().isNotEmpty().hasSize(1);
//	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("nom", nomInput);
//	    assertThat(result.get(0)).hasFieldOrPropertyWithValue("prenom", prenomInput);
//	}
	
}
