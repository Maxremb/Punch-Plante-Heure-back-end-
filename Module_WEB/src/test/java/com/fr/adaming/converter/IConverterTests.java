package com.fr.adaming.converter;

/**
 * @author Lea<br>
 * 
 * <b>Description : </b>
 * <p>Interface pour les tests sur les Converter, implemente par toutes les classes converter test</p>
 *
 */
public interface IConverterTests {

	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une CreateDto en Entite</p>
	 */
	public void testConvertingCreateDtoToEntity_shouldReturnEntity();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une CreateDto Null en Entite, doit retourner null</p>
	 */
	public void testConvertingNullCreateDto_shouldReturnNullEntity();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une UpdateDto en Entite</p>
	 */
	public void testConvertingUpdateDtoToEntity_shouldReturnEntity();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une UpdateDto Null en Entite, doit retourner null</p>
	 */
	public void testConvertingNullUpdateDto_shouldReturnNullEntity();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Entite en CreateDto</p>
	 */
	public void testConvertingEntityToCreateDto_shouldReturnCreateDto();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Entite Null en CreateDto, doit retourner null</p>
	 */
	public void testConvertingNullEntity_shouldReturnNullCreateDto();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Entite en UpdateDto</p>
	 */
	public void testConvertingEntityToUpdateDto_shouldReturnUpdateDto();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Entite Null en UpdateDto, doit retourner null</p>
	 */
	public void testConvertingNullEntity_shouldReturnNullUpdateDto();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste d'Entite en Liste de CreateDto</p>
	 */
	public void testConvertingListEntityToCreateDto_shouldReturnCreateDtoList();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste d'Entite Null en Liste de CreateDto, doit retourner une liste vide</p>
	 */
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste de CreateDto en Liste d'Entite</p>
	 */
	public void testConvertingListCreateDtoToEntity_shouldReturnEntityList ();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste de CreateDto Null en Liste d'Entite, doit retourner une liste vide</p>
	 */
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() ;
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste d'Entite en Liste de CreateDto</p>
	 */
	public void testConvertingListEntityToUpdateDto_shouldReturnUpdateDtoList();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste d'Entite Null en Liste de UpdateDto, doit retourner une liste vide</p>
	 */
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste de UpdateDto en Liste d'Entite, doit retourner une liste d'entités</p>
	 */
	public void testConvertingListUpdateDtoToEntity_shouldReturnEntityList ();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste de UpdateDto Null en Liste d'Entite, doit retourner une liste vide</p>
	 */
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() ;
	
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Page de CreateDto en Page d'Entites, doit retourner une page d'entités</p>
	 */
	public void testConvertingPageCreateDtoToEntity_shouldReturnPageOfEntities();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Page Null de CreateDto en Page d'Entites, doit retourner une page vide</p>
	 */
	public void testConvertingNullPageCreateDtoToEntity_shouldReturnEmptyPage();

	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Page d'entites en Page de createDto, doit retourner une page d'entités</p>
	 */
	public void testConvertingPageEntityToCreateDto_shouldReturnPageOfCreateDtos() ;
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Page null d'entites en Page de createDto, doit retourner une page vide</p>
	 */
	public void testConvertingNullPageEntityToCreateDto_shouldReturnEmptyPage();

	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Page de UpdateDto en Page d'Entites, doit retourner une page d'entités</p>
	 */
	public void testConvertingPageUpdateDtoToEntity_shouldReturnPageOfEntities();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Page null de UpdateDto en Page d'Entites, doit retourner une page vide</p>
	 */
	public void testConvertingNullPageUpdateDtoToEntity_shouldReturnEmptyPage();

	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Page de UpdateDto en Page d'Entites, doit retourner une liste d'entités</p>
	 */
	public void testConvertingPageEntityToUpdateDto_shouldReturnPageOfUpdateDtos();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Page de UpdateDto en Page d'Entites, doit retourner une liste d'entités</p>
	 */
	public void testConvertingNullPageEntityToUpdateDto_shouldReturnEmptyPage();
	
}
