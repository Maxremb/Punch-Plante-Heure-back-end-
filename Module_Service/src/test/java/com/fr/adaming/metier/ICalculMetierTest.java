package com.fr.adaming.metier;


/**
 * Interface ICalculMetier definissant les méthodes obligatoires dans les tests de la couche metier
 * @author maxime
 * @since 0.0.1-SNAPSHOT
 */
public interface ICalculMetierTest {
	
	/**
	 * Test de la méthode calcul de réserve utile avec une meteo valid
	 * retourne un set de jardin
	 */
	public void TestCalculRuWithValidMeteo_ShouldReturnFullSetJardin() ;
	
	/**
	 * Test de la méthode calcul de réserve utile avec une meteo sans departement
	 * retourne un set de jardin vide
	 */
	public void TestCalculRuWithNullDeptOnMeteo_ShouldReturnEmptySet() ;
	
	/**
	 * Test de la méthode calcul de réserve utile avec une meteo sans evapotranspiration
	 * retourne un set de jardin vide
	 */
	public void TestCalculRuWithNullEvpMeteo_ShouldReturnEmptySet() ;

	/**
	 * Test de la méthode calcul de réserve utile avec 0 jardins dans le departement de la méteo
	 * retourne un set de jardin vide
	 */
	public void TestCalculRyWithNoJardinInDept_ShouldReturnEmptySet();
	
	/**
	 * Test de la méthode calcul de réserve utile avec une meteo avec departement = 0 (jardin d'interieur)
	 * retourne un set de jardin vide
	 */
	public void TestCalulRuWithInteriorDept_ShouldReturnEmptySet() ;
	
	/**
	 * Test de la méthode calcul de réserve utile avec reserve utile supereir à 20% de la reserve maximum
	 * retourne un set de jardin vide
	 */
	public void TestCalculRuWithFullReserveJardin_ShouldReturnEmptySet();
	
	
	
}
