package com.fr.adaming.metier;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Meteo;

@SpringBootTest(classes = ModuleServiceApplication.class)
public class CalculMetierTest implements ICalculMetierTest {


	@Autowired
	private ICalculMetier calcul;

	// paramètres meteo
	private static final int identifier = 1;
	private static final double tempMin = 10.5;
	private static final double tempMax = 20.2;
	private static final double pluie = 0.5;
	private static final double ensoleillement = 666.66;
	private static final double evp = 2.3;
	private static final LocalDate date = (LocalDate.parse("2020-02-01"));

	// Parametres pour departement
	private static final int depNum = 74;
	private static final String depName = "Haute-Savoie";
	private static final String depNameSql = "'" + depName + "'";

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (1,0.5,10,3.1,5.2,'Jardin de mamy',0.5,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (2,0.5,10,3.1,5.2,'Jardin de mamy',1.6,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestCalculRuWithValidMeteo_ShouldReturnFullSetJardin() {
		Meteo meteo = makeNewUpdateDto();
		meteo.setEvapoTranspirationPotentielle(0.5);

		Set<Jardin> setJardins = calcul.calculRU(meteo);

		assertThat(setJardins).isNotNull().hasSize(2);

	}

	
	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (1,0.5,10,3.1,5.2,'Jardin de mamy',10,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (2,0.5,10,3.1,5.2,'Jardin de mamy',2.3,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestCalculRuWithOneGardenToArose_shouldReturnSetOneGarden() {
		Meteo meteo = makeNewUpdateDto();

		meteo.setEvapoTranspirationPotentielle(2.0);
		
		Set<Jardin> setJardins = calcul.calculRU(meteo);

		assertThat(setJardins).isNotNull().hasSize(1);
		
	}

	
	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (73,'Savoie')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (1,0.5,0,3.1,5.2,'Jardin de mamy',0,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (2,0.5,0,3.1,5.2,'Jardin de mamy',0,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestCalculRuWithNullDeptOnMeteo_ShouldReturnEmptySet() {
		Meteo meteo = makeNewUpdateDto();

		meteo.setDepartement(null);

		Set<Jardin> setJardins = calcul.calculRU(meteo);

		assertThat(setJardins).isEmpty();

	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (73,'Savoie')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (1,0.5,0,3.1,5.2,'Jardin de mamy',0,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (2,0.5,0,3.1,5.2,'Jardin de mamy',0,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestCalculRuWith0EvpMeteo_ShouldReturnEmptySet() {
		Meteo meteo = makeNewUpdateDto();

		meteo.setEvapoTranspirationPotentielle(0);

		Set<Jardin> setJardins = calcul.calculRU(meteo);

		assertThat(setJardins).isEmpty();

	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (73,'Savoie')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (1,0.5,0,3.1,5.2,'Jardin de mamy',0,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (2,0.5,0,3.1,5.2,'Jardin de mamy',0,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestCalculRuWithNoJardinInDept_ShouldReturnEmptySet() {
		Meteo meteo = makeNewUpdateDto();

		Departement dept = new Departement();
		dept.setNom("Savoie");
		dept.setNumeroDep(73);

		meteo.setDepartement(dept);

		Set<Jardin> setJardins = calcul.calculRU(meteo);

		assertThat(setJardins).isEmpty();

	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (73,'Savoie')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (1,0.5,0,3.1,5.2,'Jardin de mamy',0,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (2,0.5,0,3.1,5.2,'Jardin de mamy',0,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestCalulRuWithInteriorDept_ShouldReturnEmptySet() {
		Meteo meteo = makeNewUpdateDto();

		Departement dept = new Departement();
		dept.setNom("Intérieur");
		dept.setNumeroDep(0);

		meteo.setDepartement(dept);

		Set<Jardin> setJardins = calcul.calculRU(meteo);

		assertThat(setJardins).isEmpty();

	}

	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
			+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (73,'Savoie')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (1,0.5,10,3.1,5.2,'Jardin de mamy',10,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (2,0.5,10,3.1,5.2,'Jardin de mamy',10,4,"
			+ depNum + ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestCalculRuWithFullReserveJardin_ShouldReturnEmptySet() {
		Meteo meteo = makeNewUpdateDto();

		meteo.setEvapoTranspirationPotentielle(2.0);
		
		Set<Jardin> setJardins = calcul.calculRU(meteo);

		assertThat(setJardins).isEmpty();

	}

	// *** Méthodes privés ***

	private Meteo makeNewUpdateDto() {
		// Creation du dto qu'on va utiliser pour la requete et aussi la comparaison

		Departement dept = new Departement();
		dept.setNom(depName);
		dept.setNumeroDep(depNum);

		Meteo meteo = new Meteo();
		meteo.setDate(date);
		meteo.setDepartement(dept);
		meteo.setEnsoleillement(ensoleillement);
		meteo.setEvapoTranspirationPotentielle(evp);
		meteo.setId(identifier);
		meteo.setPluie(pluie);
		meteo.setTemperatureMax(tempMax);
		meteo.setTemperatureMin(tempMin);

		return meteo;
	}


	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, longueur, largeur, prof_sol, reserve_utile, reserve_max_eau) VALUES (1, 'nomJardin', 69, 10, 10, 10, 5, 10)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)	
	public void TestReinitValid_ShouldReturnEntity() {
		ServiceResponse<Jardin> resp = calcul.reinitArrosJardin(1);
		
		assertThat(resp.getMessage()).isEqualTo("Success");
		assertThat(resp.getBody()).isNotNull();
		assertThat(resp.getBody()).hasFieldOrPropertyWithValue("reserveUtile", (double)10);
	}


	@Override
	@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (69, 'rhone')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Jardin (id, nom, departement_numero_dep, longueur, largeur, prof_sol, reserve_utile, reserve_max_eau) VALUES (1, 'nomJardin', 69, 10, 10, 10, 5, 10)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Jardin", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)	
	public void TestReinitInvalidJardinNull_ShouldReturnNull() {
//		ServiceResponse<Jardin> resp = calcul.reinitArrosJardin(null);
//		
//		assertThat(resp.getMessage()).isEqualTo("Success");
//		assertThat(resp.getBody()).isNull();
	}


	@Override
	public void TestReinitInvalidJardinInexistant_ShouldReturnNull() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void TestReinitInvalidJardinNoLongueur_ShouldReturnNull() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void TestReinitInvalidJardinNoLargeur_ShouldReturnNull() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void TestReinitInvalidJardinNoProfondeur_ShouldReturnNull() {
		// TODO Auto-generated method stub
		
	}
	
	
	


}
