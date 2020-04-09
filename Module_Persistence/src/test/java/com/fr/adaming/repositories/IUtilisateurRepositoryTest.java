package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;
import com.fr.adaming.entity.Utilisateur;

/**
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@SpringBootTest (classes = ModulePersistenceApplication.class)
public class IUtilisateurRepositoryTest {
	
	@Autowired
	private IUtilisateurRepository repo;
	
	//paramètres utilisateur
	private static final String nom = "jornet";
	private static final String prenom = "kilian";
	private static final String pseudo ="extra terrestre";
	// ID EMAIL MDP PSEUDO NOM PRENOM  NON NULLABLE
	
	
	//***************************************************************
	// FIND BY NOM AND PRENOM
	@Test
	@Sql (statements = "INSERT INTO utilisateur (nom,prenom,email,mdp,pseudo) VALUES ('jornet','kilian','kiki@trail.fr','4TEST','extra terrestre') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void TestReadByNomAndPrenomWithValidParam_ShouldreturnEntite() {
		Utilisateur user = repo.findByNomAndPrenom(nom, prenom);
		
		assertThat(user);
	}
	
	public void TestReadByEmailAndMdpWithNullParam_ShouldReturnNull () {
		
	}
	
	public void TestReadByEmailAndMdpWithOneNullParam_ShouldReturnNull() {
		
	}
	
	//***************************************************************
	//  ACTIF

}
