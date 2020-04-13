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
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Periode;
import com.fr.adaming.entity.PlanteModel;
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
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertThat(service.readById(2).getMessage()).isEqualTo("Une entité avec cet ID n'existe pas dans la base de données");
		assertThat(service.readById(2).getBody()).isNull();
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Plante_Model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertTrue(service.existsById(1));
	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql(statements = "INSERT INTO plante_model (id, nom_commun, nom_scientifique) VALUES (1, 'nomCommun', 'nomScientifique')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO periode(id, date_debut, date_fin, type, departement_numero_dep, plante_model_id) VALUES (1, '2020-04-01', '2020-04-01', 1, 69, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM periode", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	
	//Méthodes spécifiques : findByDepAndPlanteModel findByDepAndPlanteModelAndType 
		// Create et update
}
