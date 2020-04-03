package com.fr.adaming.controller;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.dto.PageResponseDto;
import com.fr.adaming.dto.ResponseDto;

//TODO ajouter une responseDto

/**
 * @author Gregoire
 * @author Jeanne-Marie
 * @author Isaline<br>
 * 
 *         <b>Description : </b>
 *         <p>
 *         Interface pour les Controllers, implemente par la classe abstraite
 *         AbstractController Definis les méthodes CRUD : Create, Update, Read
 *         (all et ByID) et Delete.
 *         </p>
 *
 * @param <C> CreateDto
 * @param <U> UpdateDto
 * 
 */

@RequestMapping
public interface IController<C, U> {

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour creer une Entite.
	 * </p>
	 * 
	 * @param dto : CreateDTO de l'Entité
	 * @return ResponseDto : null ou objet CreateDto
	 */
	@PostMapping
	public ResponseEntity<ResponseDto<U>> create(@RequestBody @Valid C dto);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour supprimer une Entite via son id.
	 * </p>
	 * 
	 * @param id : id de l'entite
	 * @return boolean true or false
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDto<?>> deleteById(@PathVariable("id") @Positive int id);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour modifer une Entite.
	 * </p>
	 * 
	 * @param dto : Updatedto de l'entite
	 * @return boolean true or false
	 */
	@PutMapping
	public ResponseEntity<ResponseDto<U>> update(@RequestBody @Valid U dto);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour afficher une Entite via son id.
	 * </p>
	 * 
	 * @param id : id de l'entite
	 * @return UpdateDto
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseDto<U>> readById(@PathVariable("id") @Positive int id);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour afficher la page p de la liste complète des instances d'une entité
	 * </p>
	 * @param p le numéro de la page souhaitée
	 * @return page d'entité
	 */
	@GetMapping(path = "/all/{p}")
	public ResponseEntity<PageResponseDto<Page<U>>> readAll(@PathVariable("p") int p);

}
