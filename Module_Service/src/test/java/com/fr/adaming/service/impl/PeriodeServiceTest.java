package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Periode;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.enums.EtatPlante;
import com.fr.adaming.enums.EtatSante;
import com.fr.adaming.enums.TypePeriod;
import com.fr.adaming.service.IPeriodeService;
import com.fr.adaming.service.IService;
import com.fr.adaming.service.IServiceTests;

/**
 * Classe implémentant l'interface IServiceTests visant à tester les méthodes de PeriodeService
 * @author Isaline MILLET
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest(classes = ModuleServiceApplication.class)
public class PeriodeServiceTest implements IServiceTests {

	@Autowired
	private IPeriodeService servicePeriode;
	
	@Autowired
	private IService<Periode> service;

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeletingValidId_shouldReturnTrue() {
		assertThat(service.deleteById(1)).isTrue();
	}

	@Override
	@Test
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertThat(service.deleteById(10)).isFalse();	
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadAllWithContent_shouldReturnPage() {
		assertThat(service.readAll(0).getMessage()).isEqualTo("Success");
		assertThat(service.readAll(0).getBody().toList().size() == 1).isTrue();
		assertThat(service.readAll(0).getBody().toList().get(0)).hasFieldOrPropertyWithValue("id", 1);
		assertThat(service.readAll(0).getBody().toList().get(0)).hasFieldOrPropertyWithValue("dateDebut", LocalDate.parse("2020-04-01"));
	}

	@Override
	@Test
	public void testReadAllNoContent_shouldReturnEmptyPage() {
		assertThat(service.readAll(0).getMessage()).isEqualTo("Success");
		assertThat(service.readAll(0).getBody().toList()).isEmpty();
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByIdValidId_shouldReturnEntity() {
		assertThat(service.readById(1).getMessage()).isEqualTo("Success");
		assertThat(service.readById(1).getBody()).hasFieldOrPropertyWithValue("id", 1);
		assertThat(service.readById(1).getBody()).hasFieldOrPropertyWithValue("dateDebut", LocalDate.parse("2020-04-01"));
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertThat(service.readById(2).getMessage()).isEqualTo("Une entité avec cet ID n'existe pas dans la base de données");
		assertThat(service.readById(2).getBody()).isNull();
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertTrue(service.existsById(1));
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		assertFalse(service.existsById(2));
	}
	
	/**
	 * Cette méthode teste la récupération d'une page de périodes via numéro departement - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByDepIdValidId_shouldReturnPage() {
		assertThat(servicePeriode.readByDepartementId(0, 69).getMessage()).isEqualTo("Success");
		assertTrue(servicePeriode.readByDepartementId(0, 69).getBody().toList().size() == 1);
		assertThat(servicePeriode.readByDepartementId(0, 69).getBody().toList().get(0)).hasFieldOrPropertyWithValue("id", 1);
		assertThat(servicePeriode.readByDepartementId(0, 69).getBody().toList().get(0)).hasFieldOrPropertyWithValue("dateDebut", LocalDate.parse("2020-04-01"));
	}

	/**
	 * Cette méthode teste la récupération d'une page de périodes via numéro departement - conditions invalides (dep inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByDepIdInvalidId_shouldReturnEmptyPage() {
		assertThat(servicePeriode.readByDepartementId(0, 125).getMessage()).isEqualTo("Success");
		assertTrue(servicePeriode.readByDepartementId(0, 125).getBody().toList().isEmpty());
	}
	
	/**
	 * Cette méthode teste la récupération d'une liste de périodes via id jardin et num dep - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByJardinIdAndDepIdValid_shouldReturnList() {
		assertThat(servicePeriode.readByJardinAndDep(69, 1).getMessage()).isEqualTo("Succes");
		assertTrue(servicePeriode.readByJardinAndDep(69, 1).getBody().size() == 1);
		assertThat(servicePeriode.readByJardinAndDep(69, 1).getBody().get(0)).hasFieldOrPropertyWithValue("id", 1);
		assertThat(servicePeriode.readByJardinAndDep(69, 1).getBody().get(0)).hasFieldOrPropertyWithValue("dateDebut", LocalDate.parse("2020-04-01"));
	}

	/**
	 * Cette méthode teste la récupération d'une liste de périodes via id jardin et num dep - conditions invalides (jardin inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByJardinInvalidIdAndDepId_shouldReturnEmptyList() {
		assertThat(servicePeriode.readByJardinAndDep(69, 2).getMessage()).isEqualTo("Succes");
		assertTrue(servicePeriode.readByJardinAndDep(69, 2).getBody().isEmpty());
	}
	
	/**
	 * Cette méthode teste la récupération d'une liste de périodes via id jardin et num dep - conditions invalides (jardin inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByJardinIdAndDepInvalidId_shouldReturnEmptyList() {
		assertThat(servicePeriode.readByJardinAndDep(125, 1).getMessage()).isEqualTo("Succes");
		assertTrue(servicePeriode.readByJardinAndDep(125, 1).getBody().isEmpty());
	}
	
	/**
	 * Cette méthode teste la récupération d'une liste de périodes via id jardin et num dep - conditions invalides (jardin et dep inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByJardinInvalidIdAndDepInvalidId_shouldReturnEmptyList() {
		assertThat(servicePeriode.readByJardinAndDep(125, 2).getMessage()).isEqualTo("Succes");
		assertTrue(servicePeriode.readByJardinAndDep(125, 2).getBody().isEmpty());
	}
	
	/**
	 * Cette méthode teste la récupération d'une liste de périodes via plante modèle id - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByPlanteModelIdValid_shouldReturnPage() {
		assertThat(servicePeriode.readByPlanteModelId(0, 1).getMessage()).isEqualTo("Success");
		assertTrue(servicePeriode.readByPlanteModelId(0, 1).getBody().toList().size() == 1);
		assertThat(servicePeriode.readByPlanteModelId(0, 1).getBody().toList().get(0)).hasFieldOrPropertyWithValue("id", 1);
		assertThat(servicePeriode.readByPlanteModelId(0, 1).getBody().toList().get(0)).hasFieldOrPropertyWithValue("dateDebut", LocalDate.parse("2020-04-01"));
	}

	/**
	 * Cette méthode teste la récupération d'une liste de périodes via plante modèle id - conditions invalides (plante modèle inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByPlanteModelInvalidId_shouldReturnEmptyPage() {
		assertThat(servicePeriode.readByPlanteModelId(0, 2).getMessage()).isEqualTo("Success");
		assertTrue(servicePeriode.readByPlanteModelId(0, 2).getBody().toList().isEmpty());
	}
	
	/**
	 * Cette méthode teste la récupération d'une liste de périodes via numéro departement et id plante modèle - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByDepValidIdAndPlanteModelValidId_shouldReturnList() {
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelId(69, 1).getMessage()).isEqualTo("Success");
		assertTrue(servicePeriode.readByDepartementIdAndPlanteModelId(69, 1).getBody().size() == 1);
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelId(69, 1).getBody().get(0)).hasFieldOrPropertyWithValue("id", 1);
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelId(69, 1).getBody().get(0)).hasFieldOrPropertyWithValue("dateDebut", LocalDate.parse("2020-04-01"));
	}

	/**
	 * Cette méthode teste la récupération d'une liste de périodes via numéro departement et id plante modèle - conditions invalides (dep inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByDepInvalidIdAndPlanteModelValidId_shouldReturnEmptyList() {
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelId(125, 1).getMessage()).isEqualTo("Success");
		assertTrue(servicePeriode.readByDepartementIdAndPlanteModelId(125, 1).getBody().isEmpty());
	}
	
	/**
	 * Cette méthode teste la récupération d'une liste de périodes via numéro departement et id plante modèle - conditions invalides (plante id inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByDepValidIdAndPlanteModelInvalidId_shouldReturnEmptyList() {
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelId(69, 2).getMessage()).isEqualTo("Success");
		assertTrue(servicePeriode.readByDepartementIdAndPlanteModelId(69, 2).getBody().isEmpty());
	}
	
	/**
	 * Cette méthode teste la récupération d'une liste de périodes via numéro departement et id plante modèle - conditions invalides (both inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByDepInvalidIdAndPlanteModelInvalidId_shouldReturnEmptyList() {
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelId(125, 2).getMessage()).isEqualTo("Success");
		assertTrue(servicePeriode.readByDepartementIdAndPlanteModelId(125, 2).getBody().isEmpty());
	}
	
	/**
	 * Cette méthode teste la récupération d'une période via numéro departement, id plante modèle et type - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByDepPlanteTypeValid_shouldReturnEntity() {
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelIdAndType(69, 1, TypePeriod.FLORAISON).getMessage()).isEqualTo("Success");
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelIdAndType(69, 1, TypePeriod.FLORAISON).getBody()).hasFieldOrPropertyWithValue("id", 1);
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelIdAndType(69, 1, TypePeriod.FLORAISON).getBody()).hasFieldOrPropertyWithValue("dateDebut", LocalDate.parse("2020-04-01"));
	}

	/**
	 * Cette méthode teste la récupération d'une période via numéro departement, id plante modèle et type - conditions invalides (dep inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByInvalidDepPlanteType_shouldReturnNull() {
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelIdAndType(125, 1, TypePeriod.FLORAISON).getMessage()).isEqualTo("Success");
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelIdAndType(125, 1, TypePeriod.FLORAISON).getBody()).isNull();
	}
	
	/**
	 * Cette méthode teste la récupération d'une période via numéro departement, id plante modèle et type - conditions invalides (plante inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByDepInvalidPlanteType_shouldReturnNull() {
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelIdAndType(69, 2, TypePeriod.FLORAISON).getMessage()).isEqualTo("Success");
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelIdAndType(69, 2, TypePeriod.FLORAISON).getBody()).isNull();
	}
	
	/**
	 * Cette méthode teste la récupération d'une période via numéro departement, id plante modèle et type - conditions invalides (type inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByDepPlanteInvalidType_shouldReturnNull() {
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelIdAndType(69, 1, TypePeriod.TAILLE).getMessage()).isEqualTo("Success");
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelIdAndType(69, 1, TypePeriod.TAILLE).getBody()).isNull();
	}
	
	/**
	 * Cette méthode teste la récupération d'une période via numéro departement, id plante modèle et type - conditions invalides (tous inexistants)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByInvalidDepInvalidPlanteInvalidType_shouldReturnNull() {
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelIdAndType(125, 2, TypePeriod.TAILLE).getMessage()).isEqualTo("Success");
		assertThat(servicePeriode.readByDepartementIdAndPlanteModelIdAndType(125, 2, TypePeriod.TAILLE).getBody()).isNull();
	}
		
	/**
	 * Cette méthode teste la création d'une période - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreateValid_shouldReturnEntity() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setDateDebut(LocalDate.parse("2020-04-01"));
		periode.setDateFin(LocalDate.parse("2020-04-01"));
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(dep);
		periode.setPlanteModel(plante);
		
		ServiceResponse<Periode> resp = service.create(periode);
		
		assertThat(resp.getBody()).isNotNull();
		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("dateDebut", LocalDate.parse("2020-04-01"));
		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("dateFin", LocalDate.parse("2020-04-01"));
		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("type", TypePeriod.FLORAISON);
		assertThat(resp.getBody().getDepartement()).isNotNull().hasFieldOrPropertyWithValue("nom", "rhone");
		assertThat(resp.getBody().getPlanteModel()).isNotNull().hasFieldOrPropertyWithValue("nomCommun", "nomCommun");
		assertThat(resp.getMessage()).isEqualTo("Success");
	}
	
	/**
	 * Cette méthode teste la création d'une période nulle - conditions invalides
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreateInvalidNull_shouldReturnNull() {		
		ServiceResponse<Periode> resp = service.create(null);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Tentative de création d'une entité null");
	}
	
	/**
	 * Cette méthode teste la création d'une période - conditions invalides (id utilisé)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-02', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreateInvalidID_shouldReturnEntity() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setId(1);
		periode.setDateDebut(LocalDate.parse("2020-04-01"));
		periode.setDateFin(LocalDate.parse("2020-04-01"));
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(dep);
		periode.setPlanteModel(plante);
		
		ServiceResponse<Periode> resp = service.create(periode);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Une entité avec cet ID existe déjà dans la base dde données");
	}
	
	/**
	 * Cette méthode teste la création d'une période - conditions invalides (sans date début)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreateInvalidSansDebut_shouldReturnEntity() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setDateDebut(null);
		periode.setDateFin(LocalDate.parse("2020-04-01"));
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(dep);
		periode.setPlanteModel(plante);
		
		ServiceResponse<Periode> resp = service.create(periode);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Les dates de début et de fin doivent être spécifiés");
	}
	
	/**
	 * Cette méthode teste la création d'une période - conditions invalides (sans date fin)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreateInvalidSansFin_shouldReturnEntity() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setDateDebut(LocalDate.parse("2020-04-01"));
		periode.setDateFin(null);
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(dep);
		periode.setPlanteModel(plante);
		
		ServiceResponse<Periode> resp = service.create(periode);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Les dates de début et de fin doivent être spécifiés");
	}
	
	/**
	 * Cette méthode teste la création d'une période - conditions invalides (dep null)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreateInvalidSansDep_shouldReturnEntity() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setDateDebut(LocalDate.parse("2020-04-01"));
		periode.setDateFin(LocalDate.parse("2020-04-01"));
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(null);
		periode.setPlanteModel(plante);
		
		ServiceResponse<Periode> resp = service.create(periode);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Les periodes doivent être liés à un département et à une plante");
	}
	
	/**
	 * Cette méthode teste la création d'une période - conditions invalides (plante modele null)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreateInvalidSansPlanteModel_shouldReturnEntity() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setDateDebut(LocalDate.parse("2020-04-01"));
		periode.setDateFin(LocalDate.parse("2020-04-01"));
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(dep);
		periode.setPlanteModel(null);
		
		ServiceResponse<Periode> resp = service.create(periode);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Les periodes doivent être liés à un département et à une plante");
	}
	
	/**
	 * Cette méthode teste la création d'une période - conditions invalides (date debut après date fin)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreateInvalidDates_shouldReturnEntity() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setDateDebut(LocalDate.parse("2020-04-05"));
		periode.setDateFin(LocalDate.parse("2020-04-01"));
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(dep);
		periode.setPlanteModel(plante);
		
		ServiceResponse<Periode> resp = service.create(periode);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("La date de fin est avant la date de début pour cette periode");
	}
	
	/**
	 * Cette méthode teste la modification d'une période - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-02', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateValid_shouldReturnEntity() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setId(1);
		periode.setDateDebut(LocalDate.parse("2020-04-01"));
		periode.setDateFin(LocalDate.parse("2020-04-02"));
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(dep);
		periode.setPlanteModel(plante);
		
		ServiceResponse<Periode> resp = service.update(periode);
		
		assertThat(resp.getBody()).isNotNull().hasFieldOrPropertyWithValue("dateFin", LocalDate.parse("2020-04-02"));
		assertThat(resp.getMessage()).isEqualTo("Success");
	}
	
	/**
	 * Cette méthode teste la modification d'une période - conditions invalides (null)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-02', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateInvalidNull_shouldReturnNull() {
		ServiceResponse<Periode> resp = service.update(null);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Tentative de modification avec une entité null");
	}
	
	/**
	 * Cette méthode teste la modification d'une période - conditions invalides (sans date début)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-02', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateInvalidDebut_shouldReturnNull() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setId(1);
		periode.setDateDebut(null);
		periode.setDateFin(LocalDate.parse("2020-04-02"));
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(dep);
		periode.setPlanteModel(plante);
		
		ServiceResponse<Periode> resp = service.update(periode);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Les dates de début et de fin doivent être spécifiés");
	}
	
	/**
	 * Cette méthode teste la modification d'une période - conditions invalides (sans date fin)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-02', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateInvalidFin_shouldReturnNull() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setId(1);
		periode.setDateDebut(LocalDate.parse("2020-04-02"));
		periode.setDateFin(null);
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(dep);
		periode.setPlanteModel(plante);
		
		ServiceResponse<Periode> resp = service.update(periode);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Les dates de début et de fin doivent être spécifiés");
	}
	
	/**
	 * Cette méthode teste la modification d'une période - conditions invalides (sans dep)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-02', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateInvalidDep_shouldReturnNull() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setId(1);
		periode.setDateDebut(LocalDate.parse("2020-04-02"));
		periode.setDateFin(LocalDate.parse("2020-04-02"));
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(null);
		periode.setPlanteModel(plante);
		
		ServiceResponse<Periode> resp = service.update(periode);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Les periodes doivent être liés à un département et à une plante");
	}
	
	/**
	 * Cette méthode teste la modification d'une période - conditions invalides (sans plante)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-02', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateInvalidPlante_shouldReturnNull() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setId(1);
		periode.setDateDebut(LocalDate.parse("2020-04-02"));
		periode.setDateFin(LocalDate.parse("2020-04-02"));
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(dep);
		periode.setPlanteModel(null);
		
		ServiceResponse<Periode> resp = service.update(periode);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Les periodes doivent être liés à un département et à une plante");
	}
	
	/**
	 * Cette méthode teste la modification d'une période - conditions invalides (date debut après date fin)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-02', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateInvalidDates_shouldReturnNull() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setId(1);
		periode.setDateDebut(LocalDate.parse("2020-04-05"));
		periode.setDateFin(LocalDate.parse("2020-04-02"));
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(dep);
		periode.setPlanteModel(plante);
		
		ServiceResponse<Periode> resp = service.update(periode);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("La date de fin est avant la date de début pour cette periode");
	}
	
	/**
	 * Cette méthode teste la modification d'une période - conditions invalides (id periode inexistant)
	 */
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep) VALUES (1, 'nomJardin', 69)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into plante_utilisateur (id, etat_plante, etat_sante, jardin_id, plante_model_id) values(1, 3, 0, 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-02', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Utilisateur", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateInvalidID_shouldReturnNull() {
		Departement dep = new Departement();
		dep.setNom("rhone");
		dep.setNumeroDep(69);
		
		Jardin jardin = new Jardin();
		jardin.setId(1);
		jardin.setNom("nomJardin");
		jardin.setDepartement(dep);
		
		PlanteModel plante = new PlanteModel();
		plante.setId(1);
		plante.setNomCommun("nomCommun");
		plante.setNomScientifique("nomScientifique");
		
		PlanteUtilisateur planteUtil = new PlanteUtilisateur();
		planteUtil.setId(1);
		planteUtil.setEtatPlante(EtatPlante.recolte);
		planteUtil.setEtatSante(EtatSante.bonneSante);
		planteUtil.setJardin(jardin);
		planteUtil.setPlanteModel(plante);
		
		Periode periode = new Periode();
		periode.setId(2);
		periode.setDateDebut(LocalDate.parse("2020-04-05"));
		periode.setDateFin(LocalDate.parse("2020-04-02"));
		periode.setType(TypePeriod.FLORAISON);
		periode.setDepartement(dep);
		periode.setPlanteModel(plante);
		
		ServiceResponse<Periode> resp = service.update(periode);
		
		assertThat(resp.getBody()).isNull();
		assertThat(resp.getMessage()).isEqualTo("Une entité avec cet ID n'existe pas dans la base dde données");
	}	
}
