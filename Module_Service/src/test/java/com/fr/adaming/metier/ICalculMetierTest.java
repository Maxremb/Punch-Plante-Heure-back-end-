package com.fr.adaming.metier;


/**
 * Interface ICalculMetier definissant les méthodes obligatoires dans les tests de la couche metier
 * @author Maxime Rembert / Isaline Millet
 * @since 0.0.1-SNAPSHOT
 */
public interface ICalculMetierTest {
	
	/**
	 * Test de la méthode calcul de réserve utile avec une meteo valid
	 * retourne un set de jardin
	 */
	public void TestCalculRuWithValidMeteo_ShouldReturnFullSetJardin() ;
	
	/**
	 * Test de la méthode calcul de réserve utile avec un seul jardin en état critique
	 * retourne un set de un jardin
	 */
	public void TestCalculRuWithOneGardenToArose_shouldReturnSetOneGarden();
	
	/**
	 * Test de la méthode calcul de réserve utile avec une meteo sans departement
	 * retourne un set de jardin vide
	 */
	public void TestCalculRuWithNullDeptOnMeteo_ShouldReturnEmptySet() ;
	
	/**
	 * Test de la méthode calcul de réserve utile avec une meteo sans evapotranspiration
	 * retourne un set de jardin vide
	 */
	public void TestCalculRuWith0EvpMeteo_ShouldReturnEmptySet() ;

	/**
	 * Test de la méthode calcul de réserve utile avec 0 jardins dans le departement de la méteo
	 * retourne un set de jardin vide
	 */
	public void TestCalculRuWithNoJardinInDept_ShouldReturnEmptySet();
	
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
	
	/**
	 * Test de la méthode réinitialisation de réserve utile - conditions valides
	 * retourne un jardin 
	 */
	public void TestReinitValid_ShouldReturnEntity();
	
	/**
	 * Test de la méthode réinitialisation de réserve utile - conditions invalides (jardin null)
	 * retourne null 
	 */
	public void TestReinitInvalidJardinNull_ShouldReturnNull();
	
	/**
	 * Test de la méthode réinitialisation de réserve utile - conditions invalides (jardin inexistant)
	 * retourne null 
	 */
	public void TestReinitInvalidJardinInexistant_ShouldReturnNull();
	
	/**
	 * Test de la méthode réinitialisation de réserve utile - conditions invalides (jardin sans longueur)
	 * retourne null 
	 */
	public void TestReinitInvalidJardinNoLongueur_ShouldReturnNull();
	
	/**
	 * Test de la méthode réinitialisation de réserve utile - conditions invalides (jardin sans largeur)
	 * retourne null 
	 */
	public void TestReinitInvalidJardinNoLargeur_ShouldReturnNull();
	
	/**
	 * Test de la méthode réinitialisation de réserve utile - conditions invalides (jardin sans profondeur)
	 * retourne null 
	 */
	public void TestReinitInvalidJardinNoProfondeur_ShouldReturnNull();
	
	
	
	
	
}
