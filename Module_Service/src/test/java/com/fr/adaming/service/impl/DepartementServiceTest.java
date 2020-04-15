package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.service.IDepartementService;
import com.fr.adaming.service.IService;
import com.fr.adaming.service.IServiceTests;

/**
 * Cette classe teste la couche service de l'entité département Elle implémente
 * une interface pour le test des méthodes CRUD (read All, read by Id, exist by
 * Id, delete by Id) et définit ses propres méthodes de test pour les autres.
 * 
 * @author Isaline MILLET
 * @since 0.0.1
 *
 */
@SpringBootTest(classes = ModuleServiceApplication.class)
public class DepartementServiceTest implements IServiceTests {

	@Autowired
	private IDepartementService<Departement, Meteo> serviceDep;

	@Autowired
	private IService<Departement> service;

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingValidId_shouldReturnTrue() {
		assertTrue(service.deleteById(69));

	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertFalse(service.deleteById(2));
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadAllWithContent_shouldReturnPage() {

		assertTrue(service.readAll(0).getBody().toList().size() == 1);
		assertThat(service.readAll(0).getBody().toList().get(0)).isNotNull().hasFieldOrPropertyWithValue("nom",
				"nom4Test");
		assertThat(service.readAll(0).getBody().toList().get(0)).isNotNull().hasFieldOrPropertyWithValue("numeroDep",
				1);
	}

	@Test
	@Override
	public void testReadAllNoContent_shouldReturnEmptyPage() {
		assertTrue(service.readAll(0).getBody().toList().isEmpty());
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadByIdValidId_shouldReturnEntity() {
		assertThat(service.readById(1).getBody()).isNotNull().hasFieldOrPropertyWithValue("numeroDep", 1);
		assertThat(service.readById(1).getBody()).isNotNull().hasFieldOrPropertyWithValue("nom", "nom4Test");

	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertThat(service.readById(2).getBody()).isNull();
		assertThat(service.readById(2).getMessage())
				.isEqualTo("Une entité avec cet ID n'existe pas dans la base de données");
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertTrue(service.existsById(1));
	}

	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		assertFalse(service.existsById(2));
	}

	/**
	 * Cette méthode teste la récupération d'un département via son nom - conditions
	 * valides
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByNomValid_ShouldReturnEntity() {
		assertThat(serviceDep.readDepartementByNom("nom4Test").getBody()).isNotNull()
				.hasFieldOrPropertyWithValue("numeroDep", 1);
		assertThat(serviceDep.readDepartementByNom("nom4Test").getBody()).isNotNull().hasFieldOrPropertyWithValue("nom",
				"nom4Test");
	}

	/**
	 * Cette méthode teste la récupération d'un département via son nom - conditions
	 * invalides (nom inexistant)
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByNomInvalid_ShouldReturnNull() {
		assertThat(serviceDep.readDepartementByNom("nomInvalid").getBody()).isNull();
		assertThat(serviceDep.readDepartementByNom("nomInvalid").getMessage())
				.isEqualTo("Récupération d'un département après recherche par nom");
	}

	/**
	 * Cette méthode teste la récupération d'un département via son nom - conditions
	 * invalides (nom null)
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByNomNull_ShouldReturnNull() {
		assertThat(serviceDep.readDepartementByNom(null).getBody()).isNull();
		assertThat(serviceDep.readDepartementByNom(null).getMessage())
				.isEqualTo("Tentative de récupération d'un département après recherche via nom NULL");
	}

	/**
	 * Cette méthode teste la création d'un département - conditions valides
	 */
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateValid_ShouldReturnEntity() {

		Departement dep = new Departement();
		dep.setNumeroDep(1);
		dep.setNom("nom4Test");

		ServiceResponse<Departement> resp = service.create(dep);

		assertThat(resp.getBody()).isNotNull();
		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("nom", "nom4Test");
		assertThat(resp.getMessage()).isEqualTo("Création d'un département via couche service OK");
	}

	/**
	 * Cette méthode teste la création d'un département - conditions invalides
	 * (département NULL)
	 */
	@Test
	public void testCreateNull_ShouldReturnNull() {
		ServiceResponse<Departement> resp = service.create(null);
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Tentative de création d'un département NULL");
	}

	/**
	 * Cette méthode teste la création d'un département - conditions invalides (id
	 * déjà utilisé)
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateExistingId_ShouldReturnNull() {

		Departement dep = new Departement();
		dep.setNumeroDep(1);
		dep.setNom("nom2Test");

		ServiceResponse<Departement> resp = service.create(dep);

		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Un département est déjà enregistré avec cet ID");
	}

	/**
	 * Cette méthode teste la création d'un département - conditions invalides (nom
	 * déjà utilisé)
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateExistingName_ShouldReturnNull() {

		Departement dep = new Departement();
		dep.setNumeroDep(2);
		dep.setNom("nom4Test");

		ServiceResponse<Departement> resp = service.create(dep);

		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Tentative échouée de création d'un département");
	}

	/**
	 * Cette méthode teste la modification d'un département - conditions valides
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateExistingDep_ShouldReturnEntite() {

		Departement dep = new Departement();
		dep.setNumeroDep(1);
		dep.setNom("nom4Test");

		ServiceResponse<Departement> resp = service.update(dep);

		assertThat(resp.getMessage()).isEqualTo("Modification d'un département via couche service OK");
		assertThat(resp.getBody()).hasFieldOrPropertyWithValue("nom", "nom4Test");
	}

	/**
	 * Cette méthode teste la modification d'un département - conditions invalides
	 * (id non existant dans BD)
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateNotExistingDep_ShouldReturnNull() {

		Departement dep = new Departement();
		dep.setNumeroDep(2);
		dep.setNom("nom4Test");

		ServiceResponse<Departement> resp = service.update(dep);

		assertNull(resp.getBody());
		assertThat(resp.getMessage()).isEqualTo("Aucun département n'existe avec cet ID");
	}

	/**
	 * Cette méthode teste la modification d'un département - conditions invalides
	 * (nouveau nom déjà utilisé dans BD)
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (2, 'nomUtilise')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateDepWithExistingName_ShouldReturnEntite() {

		Departement dep = new Departement();
		dep.setNumeroDep(1);
		dep.setNom("nomUtilise");

		ServiceResponse<Departement> resp = service.update(dep);

		assertNull(resp.getBody());
		assertThat(resp.getMessage()).isEqualTo("Tentative de modification d'un département avec nom déjà utilisé");
	}

	/**
	 * Cette méthode teste la modification d'un département - conditions invalides
	 * (departement null)
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateNullDep_ShouldReturnNull() {
		ServiceResponse<Departement> resp = service.update(null);
		assertNull(resp.getBody());
		assertThat(resp.getMessage()).isEqualTo("Tentative de modification d'un département NULL");
	}

	/**
	 * Cette méthode teste la récupération des départements sous forme de liste -
	 * conditions valides (departement null)
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadAllListWithContent_shouldReturnList() {
		assertTrue(serviceDep.readAllList().getBody().size() == 1);
		assertThat(serviceDep.readAllList().getBody().get(0)).hasFieldOrPropertyWithValue("numeroDep", 1);
		assertThat(serviceDep.readAllList().getBody().get(0)).hasFieldOrPropertyWithValue("nom", "nom4Test");
		assertThat(serviceDep.readAllList().getMessage()).isEqualTo("Succes");
	}

	/**
	 * Cette méthode teste la récupération des départements sous forme de liste -
	 * conditions invalides (db vide)
	 */
	@Test
	public void testReadAllListWithNoContent_shouldReturnEmptyList() {
		assertTrue(serviceDep.readAllList().getBody().size() == 0);
		assertThat(serviceDep.readAllList().getMessage()).isEqualTo("Succes");
	}

	/**
	 * Cette méthode teste la récupération des départements sous forme de liste - conditions valides
	 */
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (1, 'nom4Test')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo(id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 10, 10, 10, 10, 10, '2020-02-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadMeteoByNumeroDep_shouldReturnPage() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("date"));
		assertThat(serviceDep.readMeteoByNumeroDep(pageable, 1).getBody()).isNotNull();
		assertTrue(serviceDep.readMeteoByNumeroDep(pageable, 1).getBody().toList().size() == 1);
		assertThat(serviceDep.readMeteoByNumeroDep(pageable, 1).getBody().toList().get(0)).hasFieldOrPropertyWithValue("id",
				1);
		assertThat(serviceDep.readMeteoByNumeroDep(pageable, 1).getBody().toList().get(0))
				.hasFieldOrPropertyWithValue("temperatureMax", (double) 10);
		assertThat(serviceDep.readMeteoByNumeroDep(pageable, 1).getBody().toList().get(0))
				.hasFieldOrPropertyWithValue("temperatureMin", (double) 10);
		assertThat(serviceDep.readMeteoByNumeroDep(pageable, 1).getBody().toList().get(0)).hasFieldOrPropertyWithValue("pluie",
				(double) 10);
		assertThat(serviceDep.readMeteoByNumeroDep(pageable, 1).getBody().toList().get(0))
				.hasFieldOrPropertyWithValue("ensoleillement", (double) 10);
		assertThat(serviceDep.readMeteoByNumeroDep(pageable, 1).getBody().toList().get(0))
				.hasFieldOrPropertyWithValue("evapoTranspirationPotentielle", (double) 10);
		assertThat(serviceDep.readMeteoByNumeroDep(pageable, 1).getBody().toList().get(0)).hasFieldOrPropertyWithValue("date",
				LocalDate.parse("2020-02-01"));
		assertThat(serviceDep.readMeteoByNumeroDep(pageable, 1).getMessage()).isEqualTo("Récupération d'une liste de conditions météo par département");
	}
	
	/**
	 * Cette méthode teste la récupération des départements sous forme de liste - conditions invalides (departement inexistant)
	 */
	@Test
	public void testReadMeteoByNumeroDepInexistant_shouldReturnPage() {

		Pageable pageable = PageRequest.of(0, 20, Sort.by("date"));
		assertNull(serviceDep.readMeteoByNumeroDep(pageable, 1).getBody());
		assertThat(serviceDep.readMeteoByNumeroDep(pageable, 1).getMessage()).isEqualTo("Tentative récupération d'une liste de conditions météo par département inexistant");
	}
	
	/**
	 * Cette méthode teste la récupération des départements sous forme de liste - conditions invalides (departement null)
	 */
	@Test
	public void testReadMeteoByNumeroDepNull_shouldReturnPage() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("date"));
		assertNull(serviceDep.readMeteoByNumeroDep(pageable, null).getBody());
		assertThat(serviceDep.readMeteoByNumeroDep(pageable, null).getMessage()).isEqualTo("Problème readMeteoByNumDep");
	}

}
