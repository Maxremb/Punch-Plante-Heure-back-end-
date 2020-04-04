package com.fr.adaming.controller;

/**Interface pour les testes de controlleurs. Toute Méthode controlleur retourne un objet de type responseDto.
 * @author Jeanne-Marie
 * @see IController
 *
 */

public interface IControllerTests {

		
		/**
		 * Teste de la méthode create avec entité valide. Doit retourner statut 200. ResponseDto doit contenir la createDto de l'entité dans son body.
		 */
		public void testCreatingEntityWithValidBody_shouldReturn200()throws Exception;
		
		/**
		 * Teste de la méthode create avec entité invalide. Doit retourner le statut 400. Le body de responseDto doit contenir une liste vide.
		 */
		public void testCreatingEntityWithInvalidBody_shouldReturn400()throws Exception;
		
		/**
		 * Teste de la méthode deleteById avec un id qui existe dans la base de données. Doit retourner un statut 200. responseDto doit avoir un body null.
		 */
		public void testDeletingEntityWithValidId_shouldReturn200()throws Exception;
		
		/**
		 * Teste de la méthode deleteById avec un id qui n'existe pas dans la base de données. Doit retourner un statut 400. Le body de responseDto doit être null.
		 */
		public void testDeletingEntityWithInvalidId_shouldReturn400()throws Exception;
		
		/**
		 * Teste de la méthode deleteById avec un id négatif. Doit retourner un statut 400. Le body de responseDto doit être null.
		 */
		public void testDeletingEntityWithNegativeId_shouldReturn400()throws Exception;
		
		/**
		 * Teste de la méthode update avec entité valide (id existant). Doit retourner un statut 200. Le body de responseDto doit contenir un objet de type UpdateDto
		 */
		public void testUpdatingEntityWithValidId_shouldReturn200()throws Exception;
		
		/**
		 * Teste de la méthode update avec entité avec un id qui n'exite pas dans la base de données. Doit retourner un statut 400. Le body de responseDto doit être null.
		 */
		public void testUpdatingEntityWithInvalidId_shouldReturn400()throws Exception;
		
		/**
		 * Teste de la méthode readById avec un id qui exite pas dans la base de données. Doit retourner un statut 200. Le body de responseDto doit contenir un objet de type updateDto.
		 */
		public void testReadingEntityWithValidId_shouldReturn200()throws Exception;
		
		/**
		 * Teste de la méthode readById avec un id qui n'exite pas dans la base de données. Doit retourner un statut 400. Le body de responseDto doit être null.
		 */
		public void testReadingEntityWithInvalidId_shouldReturn400()throws Exception;
		
		/**
		 * Teste de la méthode readById avec un id negatif. Doit retourner un statut 400. Le body de responseDto doit être null.
		 */
		public void testReadingEntityWithNegativeId_shouldReturn400()throws Exception;
		
		/**
		 * Teste de la méthode readAll. Doit retourner un statut 200. Le body de responseDto doit contenir une liste de UpdateDto
		 */
		public void testReadingAllEntity_shouldReturn200()throws Exception;

}
