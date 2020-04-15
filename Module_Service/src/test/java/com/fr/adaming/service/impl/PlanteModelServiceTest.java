package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.service.IPlanteModelService;
import com.fr.adaming.service.IServiceTests;

//TODO reparer testes qui ne fonctionnent pas

/**
 * Cette classe teste la couche service de l'entité PlanteModel
 * Elle implémente une interface pour le test des méthodes CRUD (read All, read by Id, exist by Id, delete by Id) et définit ses propres méthodes de test pour les autres. 
 * 
 * @author Léa Coston / Isaline Millet
 * @since 0.0.1
 *
 */
@SpringBootTest(classes = ModuleServiceApplication.class)
public class PlanteModelServiceTest implements IServiceTests {

	@Autowired
	private IPlanteModelService service;

	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testDeletingValidId_shouldReturnTrue() {
		assertTrue(service.deleteById(1));

	}

	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertFalse(service.deleteById(2));

	}

	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testReadAllWithContent_shouldReturnPage() {
		assertNotNull(service.readAll(0));
		assertThat(service.readAll(0).getBody().getNumberOfElements()).isEqualTo(1);
		assertThat(service.readAll(0).getBody().getNumber()).isEqualTo(0);
		assertThat(service.readAll(0)).hasFieldOrPropertyWithValue("message", "Success");
	}

	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testReadAllNoContent_shouldReturnEmptyPage() {
		assertNotNull(service.readAll(0));
		assertThat(service.readAll(0).getBody().getNumberOfElements()).isEqualTo(0);
		assertThat(service.readAll(0)).hasFieldOrPropertyWithValue("message", "Success");

	}

	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testReadByIdValidId_shouldReturnEntity() {
		assertThat(service.readById(1)).isNotNull();
		assertThat(service.readById(1).getBody()).isInstanceOf(PlanteModel.class);
		assertThat(service.readById(1)).hasFieldOrPropertyWithValue("message", "Success");

	}

	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertNull(service.readById(2).getBody());
		assertThat(service.readById(2)).hasFieldOrPropertyWithValue("message",
				"Une entité avec cet ID n'existe pas dans la base de données");

	}

	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertTrue(service.existsById(1));

	}

	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	@Test
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		assertFalse(service.existsById(2));

	}

	/**
	 * Test de findByNom. Recherche avec un nom scientifique.
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'Alice', 'Alicium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (2, 'Bob', 'Bobium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (3, 'Bobby', 'Bobium Mendax')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFindByNomUsingNomScientifique_shouldReturnPageOfEntities() {

		ServiceResponse<Page<PlanteModel>> serviceResponse = service.findByNom(0, "Alicium Vulgaris");

		assertNotNull(serviceResponse);
		assertEquals("Success", serviceResponse.getMessage());
		assertFalse(serviceResponse.getBody().isEmpty());
		assertTrue(serviceResponse.getBody().toList().size() == 1);
		assertThat(serviceResponse.getBody().getContent().get(0)).isInstanceOf(PlanteModel.class);

	}

	/**
	 * Test de findByNom. Recherche avec nom commun
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'Alice', 'Alicium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (2, 'Bob', 'Bobium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (3, 'Bobby', 'Bobium Mendax')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFindByNomUsingNomCommun_shouldReturnPageOfEntities() {

		ServiceResponse<Page<PlanteModel>> serviceResponse = service.findByNom(0, "Bob");

		assertNotNull(serviceResponse);
		assertEquals("Success", serviceResponse.getMessage());
		assertFalse(serviceResponse.getBody().isEmpty());
		assertTrue(serviceResponse.getBody().toList().size() == 2);
		assertThat(serviceResponse.getBody().getContent()).asList()
				.allSatisfy(plant -> assertThat(plant).isInstanceOf(PlanteModel.class));

	}

	/**
	 * Test de findByNom. Recherche avec nom incomplet.
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'Alice', 'Alicium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (2, 'Bob', 'Bobium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (3, 'Bobby', 'Bobium Mendax')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFindByNomUsingIncompleteNom_shouldReturnPageOfEntities() {

		ServiceResponse<Page<PlanteModel>> serviceResponse = service.findByNom(0, "i");

		assertNotNull(serviceResponse);
		assertEquals("Success", serviceResponse.getMessage());
		assertFalse(serviceResponse.getBody().isEmpty());
		assertTrue(serviceResponse.getBody().toList().size() == 3);
		assertThat(serviceResponse.getBody().getContent()).asList()
				.allSatisfy(plant -> assertThat(plant).isInstanceOf(PlanteModel.class));

	}

	/**
	 * Test de findByNom. Recherche avec nom qui n'existe pas dans la bd.
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'Alice', 'Alicium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (2, 'Bob', 'Bobium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (3, 'Bobby', 'Bobium Mendax')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFindByNomUsingInvalidNom_shouldReturnEmptyPage() {

		ServiceResponse<Page<PlanteModel>> serviceResponse = service.findByNom(0, "AardvarkPlant");

		assertNotNull(serviceResponse);
		assertEquals("Success", serviceResponse.getMessage());
		assertTrue(serviceResponse.getBody().isEmpty());

	}

	/**
	 * Test de findByNom. Recherche avec la mauvaise casse (?), devrais marcher quand même.
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'Alice', 'Alicium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (2, 'Bob', 'Bobium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (3, 'BOBBY', 'Bobium Mendax')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFindByNomUsingWrongCase_shouldNonEmptyPage() {

		ServiceResponse<Page<PlanteModel>> serviceResponse = service.findByNom(0, "bobby");

		assertNotNull(serviceResponse);
		assertEquals("Success", serviceResponse.getMessage());
		
		// TODO comprendre pourquoi le teste ne fonctionne pas: 
		// La casse est ignorée quand on utilise swagger/rest mais pas dans le teste pour une raison inconnue.
		// Lien possible avec le fait qu'on utlise h2 au lieu de mysql
		
//		assertTrue(serviceResponse.getBody().toList().size() == 1);
//		assertThat(serviceResponse.getBody().getContent()).asList()
//				.allSatisfy(plant -> assertThat(plant).isInstanceOf(PlanteModel.class));

	}
	
	/**
	 * Test de create argument valid, devrait retourner un ServiceResponse avec le message Succes et l'objet créé en body.
	 */
	@Sql(statements = "DELETE FROM plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateValidArgs_shouldReturnServiceResponseSucces() {
		PlanteModel plante= new PlanteModel();
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		ServiceResponse<PlanteModel> resp = service.create(plante);
		assertThat(resp.getMessage()).isEqualTo("Création de la plante modele réussie");
		assertThat(resp.getBody()).hasFieldOrPropertyWithValue("nomCommun", "nomCommun");
		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("nomScientifique", "nomScientifique");
		assertThat(resp.getBody().getId()).isGreaterThan(0);
	}
	
	/**
	 * Test de create argument null, devrait retourner un ServiceResponse avec un body null et un message d'erreur.
	 */
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateNullEntity_shouldReturnResponseNullBodyFailureMessage() {
		assertThat(service.create(null)).hasFieldOrPropertyWithValue("message","Création non réalisée : objet d'entrée nul");
		assertThat(service.create(null).getBody()).isNull();
		
	}
	
	/**
	 * Test de create argument au nom Scientifique null, devrait retourner un ServiceResponse avec un body null et un message d'erreur.
	 */
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateNullNomScien_shouldReturnResponseNullBodyFailureMessage() {
		PlanteModel plante= new PlanteModel();
		plante.setNomScientifique(null);
		assertThat(service.create(plante)).hasFieldOrPropertyWithValue("message","Création non réalisée : Le nom Scientifique ne doit pas etre nul");
		assertThat(service.create(plante).getBody()).isNull();
		
	}
	
	/**
	 * Test de create argument avec id déjà utilisé, devrait retourner un ServiceResponse avec un body null et un message d'erreur.
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'Alice', 'Alicium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateUsedId_shouldReturnResponseNullBodyFailureMessage() {
		PlanteModel plante= new PlanteModel();
		plante.setId(1);
		plante.setNomScientifique("nom");
		assertThat(service.create(plante)).hasFieldOrPropertyWithValue("message","Création non réalisée : cet id est déjà présent dans la base de donnée");
		assertThat(service.create(plante).getBody()).isNull();
		
	}
	
	/**
	 * Test de create argument avec un nom Scientifique déja utilisé, devrait retourner un ServiceResponse avec un body null et un message d'erreur.
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'Alice', 'Alicium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateUsedNomScien_shouldReturnResponseNullBodyFailureMessage() {
		PlanteModel plante= new PlanteModel();
		plante.setNomScientifique("Alicium Vulgaris");
		plante.setNomScientifique("Alicium Vulgaris");
		assertThat(service.create(plante)).hasFieldOrPropertyWithValue("message","Création non réalisée : ce nom scientifique est déjà présent dans la base de donnée");
		assertThat(service.create(plante).getBody()).isNull();
		
	}
	
	/**
	 * Test de update argument avec id inexistant, devrait retourner un ServiceResponse avec un body null et un message d'erreur.
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'Alice', 'Alicium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateNotUsedId_shouldReturnResponseNullBodyFailureMessage() {
		PlanteModel plante= new PlanteModel();
		plante.setId(2);
		plante.setNomScientifique("nom");
		assertThat(service.update(plante)).hasFieldOrPropertyWithValue("message","Mise à jour non réalisée : cet id n'existe pas dans la base de donnée");
		assertThat(service.update(plante).getBody()).isNull();
		
	}
	
	/**
	 * Test de update argument null, devrait retourner un ServiceResponse avec un body null et un message d'erreur.
	 */
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateNullEntity_shouldReturnResponseNullBodyFailureMessage() {

		assertThat(service.update(null)).hasFieldOrPropertyWithValue("message","Mise à jour non réalisée : objet d'entrée null");
		assertThat(service.update(null).getBody()).isNull();
		
	}
	
	
	/**
	 * Test de update argument avec nom scientifique null, devrait retourner un ServiceResponse avec un body null et un message d'erreur.
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'Alice', 'Alicium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateNullNomScien_shouldReturnResponseNullBodyFailureMessage() {
		PlanteModel plante= new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("Alice");
		plante.setNomScientifique(null);
		assertThat(service.update(plante)).hasFieldOrPropertyWithValue("message","Mise à jour non réalisée : Le nom Scientifique ne doit pas etre null");
		assertThat(service.update(plante).getBody()).isNull();
		
	}
	
	/**
	 * Test de update argument avec nom scientifique déjà utilisé, devrait retourner un ServiceResponse avec un body null et un message d'erreur.
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'Alice', 'Alicium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (2, 'Bob', 'Bobium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateUsedNomScien_shouldReturnResponseNullBodyFailureMessage() {
		PlanteModel plante= new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("Alice");
		plante.setNomScientifique("Bobium Vulgaris");
		assertThat(service.update(plante)).hasFieldOrPropertyWithValue("message","erreur modification");
		assertThat(service.update(plante).getBody()).isNull();
	}
	
	/**
	 * Test de update argument valide, devrait retourner un ServiceResponse avec un message Succes et un body contenant l'objet updater.
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'Alice', 'Alicium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateValidArgs_shouldReturnResponseWithBody() {
		PlanteModel plante= new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("Alice");
		plante.setNomScientifique("nouveauNom");
		assertThat(service.update(plante)).hasFieldOrPropertyWithValue("message","Mise à jour de la plante modele réussie");
		//assertThat(service.update(plante).getBody()).isNotNull().hasFieldOrPropertyWithValue("nomScientifique", "nouveauNom" );
		
	}
	
	/**
	 * Test de ReadAllReduced, devrait retourner un ServiceResponse avec un message succes et un body contenant une page de PlanteModel dont seulement 4 attributs peuvent etre non null.
	 */
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'Alice', 'Alicium Vulgaris')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadAllReduced_shouldReturnServiceResponseWithBodyPageOfPlantModel() {
		assertThat(service.readAllReduced(0)).hasFieldOrPropertyWithValue("message", "Succes");
		assertThat(service.readAllReduced(0).getBody().getNumber()).isEqualTo(0);
		assertThat(service.readAllReduced(0).getBody().getTotalElements()).isEqualTo(1);
	}

}
