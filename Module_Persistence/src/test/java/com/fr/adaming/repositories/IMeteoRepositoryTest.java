package com.fr.adaming.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.ModulePersistenceApplication;

/**
 * Classe visant à tester les méthodes de IMeteoRepositoryTest
 * @author Jeanne-Marie MATHEVET
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IMeteoRepositoryTest {
	
	@Autowired
	private IMeteoRepository repo;
	
	/**
	 * Methode visant à tester la recherche de météo à une date donnée
	 */
	@Test
	public void testFetchingMeteoByDate_shouldReturnAListOfMeteo() {
		// TO DO
	}

}
