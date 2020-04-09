package com.fr.adaming.converter;

/**
 * @author Léa Coston/ Isaline Millet<br>
 * 
 * <b>Description : </b>
 * Interface pour les tests sur le Converter Département
 *
 */
public interface IConverterDepartementTests {

	/**
	 * <b>Description : </b>
	 * <p>Test méthode pour convertir une Dto en Entite</p>
	 */
	public void testConvertingDtoToEntity_shouldReturnEntity();
	
	/**
	 * <b>Description : </b>
	 * <p>Test méthode pour convertir une Dto Null en Entite, doit retourner null</p>
	 */
	public void testConvertingNullDtoToEntity_shouldReturnNullEntity();
	
	/**
	 * <b>Description : </b>
	 * <p>Test méthode pour convertir une Entite en Dto</p>
	 */
	public void testConvertingEntityToDto_shouldReturnDto();
	
	/**
	 * <b>Description : </b>
	 * <p>Test méthode pour convertir une Entite Null en Dto, doit retourner null</p>
	 */
	public void testConvertingNullEntityToDto_shouldReturnNullDto();
	
	/**
	 * <b>Description : </b>
	 * <p>Test méthode pour convertir une Liste d'Entite en Liste de Dto</p>
	 */
	public void testConvertingListEntityToDto_shouldReturnDtoList();
	
	/**
	 * <b>Description : </b>
	 * <p>Test méthode pour convertir une Liste d'Entite vide en Liste de Dto, doit retourner une liste vide</p>
	 */
	public void testConvertingEmptyListEntityToDto_shouldReturnEmptyList();
	
	/**
	 * <b>Description : </b>
	 * <p>Test méthode pour convertir une Liste de Dto en Liste d'Entite</p>
	 */
	public void testConvertingListDtoToEntity_shouldReturnEntityList ();
	
	/**
	 * <b>Description : </b>
	 * <p>Test méthode pour convertir une Liste de Dto vide en Liste d'Entite, doit retourner une liste vide</p>
	 */
	public void testConvertingEmptyListDtoToEntity_shouldReturnEmptyList() ;
	
	/**
	 * <b>Description : </b>
	 * <p>Test méthode pour convertir une Page de Dto en Page d'Entites, doit retourner une page d'entités</p>
	 */
	public void testConvertingPageDtoToEntity_shouldReturnPageOfEntities();

	/**
	 * <b>Description : </b>
	 * <p>Test méthode pour convertir une Page d'entites en Page de Dto, doit retourner une page de Dto</p>
	 */
	public void testConvertingPageEntityToDto_shouldReturnPageOfDtos();
	
}
