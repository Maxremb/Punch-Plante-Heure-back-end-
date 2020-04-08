package com.fr.adaming.metier;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModuleServiceApplication;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.enums.Sol;

@SpringBootTest(classes = ModuleServiceApplication.class)
public class CalculMetierTest implements ICalculMetierTest{
	
	// TODO : TEST A FAIRE
	
	
	@Autowired
	private ICalculMetier calcul;
	
	//paramètres meteo
	private static final int identifier =1;
	private static final double tempMin =10.5;
	private static final double tempMax =20.2;
	private static final double pluie = 10.3;
	private static final double ensoleillement = 666.66;
	private static final double evp = 2.3;
	private static final LocalDate date = (LocalDate.parse("2020-02-01"));
	
	// Parametres pour departement
	private static final int depNum = 74;
	private static final String depName = "Haute-Savoie";
	private static final String depNameSql = "'" + depName + "'";
	
	//paramètre jardin
	private static final String nom = "Jardin de mamy";
	private static final double maxReserve =0;
	private static final double utileReserve =0;
	private static final Float longueur = 5.2f;
	private static final Float largeur = 3.1f;
	private static final Float profSol = 0.5f;
	private static final Sol sol= Sol.Argileux;
	
	
	

	@Override
	//@Test
	@Sql(statements = "INSERT INTO Departement (numero_dep, nom) VALUES (" + depNum + ", " + depNameSql
	+ ")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Meteo (id, temperature_max, temperature_min, pluie, ensoleillement, evapo_transpiration_potentielle, date, departement_id) VALUES (1, 25, 20, 5, 120, 5, '2020-01-01'," + depNum + " )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (1,0.5,0,3.1,5.2,'Jardin de mamy',0,4,"+depNum+")",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "INSERT INTO jardin (id,prof_sol,reserve_max_eau,largeur,longueur,nom,reserve_utile,sol,departement_numero_dep) VALUES (2,0.5,0,3.1,5.2,'Jardin de mamy',0,4,"+depNum+")",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Departement", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql (statements = "DELETE FROM jardin",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Meteo", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestCalculRuWithValidMeteo_ShouldReturnFullSetJardin() {
		Meteo meteo = makeNewUpdateDto();
		
		Set<Jardin> setJardins = calcul.calculRU(meteo);
		
		assertThat(setJardins).isNotNull().hasSize(2);
		
	}

	@Override
	public void TestCalculRuWithNullDeptOnMeteo_ShouldReturnEmptySet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void TestCalculRuWithNullEvpMeteo_ShouldReturnEmptySet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void TestCalculRyWithNoJardinInDept_ShouldReturnEmptySet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void TestCalulRuWithInteriorDept_ShouldReturnEmptySet() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void TestCalculRuWithFullReserveJardin_ShouldReturnEmptySet() {
		// TODO Auto-generated method stub
		
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
//		
//		Jardin jardin = new Jardin();
//		jardin.setDepartement(dept);
//		jardin.setId(identifier);
//		jardin.setLargeur(largeur);
//		jardin.setLongueur(longueur);
//		jardin.setNom(nom);
//		jardin.setProfSol(profSol);
//		jardin.setRESERVE_MAX_EAU(maxReserve);
//		jardin.setReserveUtile(utileReserve);
//		jardin.setSol(sol);
		
		return meteo;

	}

}
