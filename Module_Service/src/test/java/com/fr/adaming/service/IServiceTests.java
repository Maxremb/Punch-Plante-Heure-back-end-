package com.fr.adaming.service;

/**
 * Interface IServiceTest definissant les méthodes obligatoires dans les tests de la couche service
 * @author Lea
 *
 */

public interface IServiceTests {
	
	/**
	 * Methode permettant de tester la suppression d'un objet avec un Id existant dans la bd
	 * le resultat du test devrait etre positif
	 */
	public void testDeletingValidId_shouldReturnTrue();
	
	/**
	 * Methode permettant de tester la suppression d'un objet avec un Id invalide
	 * le resultat du test devrait etre negatif
	 */
	public void testDeletingInvalidId_shouldReturnFalse();
	
	/**
	 * Methode permettant de tester l'impression de tous les objets d'une table contenant des lignes
	 * le resultat du test devrait etre une liste d'objet
	 */
	public void testReadAllWithContent_shouldReturnList();
	
	/**
	 * Methode permettant de tester l'impression de tous les objets d'une table vide
	 * le resultat du test devrait etre une liste vide
	 */
	public void testReadAllNoContent_shouldReturnEmptyList();
	
	/**
	 * Methode permettant de tester la recherche par Id avec un parametre valide
	 * le resultat du test devrait etre un objet
	 */
	public void testReadByIdValidId_shouldReturnEntity();
	
	/**
	 * Methode permettant de tester la recherche par Id avec un parametre invalide
	 * le resultat du test devrait etre null
	 */
	public void testReadByIdInvalidId_shouldReturnNull();
	
	/**
	 * Methode permettant de tester l'existance ud'une ligne dans une table par id, avec un id valide
	 * le resultat du test devrait etre positif
	 */
	public void testExistsByIdValidId_ShouldReturnTrue();
	
	/**
	 * Methode permettant de tester l'existance ud'une ligne dans une table par id, avec un id non valide
	 * le resultat du test devrait etre negatif
	 */
	public void testExistsByIdInValidId_ShouldReturnFalse();

}
