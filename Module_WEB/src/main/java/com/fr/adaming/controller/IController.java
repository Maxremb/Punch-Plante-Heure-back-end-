package com.fr.adaming.controller;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.dto.ResponseDto;

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
@CrossOrigin( allowCredentials = "true", origins = "http://localhost:4200")
@RequestMapping
public interface IController<C, U> {

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour creer une Entite.
	 * </p>
	 * 
	 * @param dto : CreateDTO de l'Entité
	 * @return Response entity avec ResponseDto. Body : null ou objet UpdateDto
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
	 * @return ResponseEntity avec ResponseDto. Body: boolean true or false
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDto<U>> deleteById(@PathVariable("id") @Positive int id);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour modifer une Entite.
	 * </p>
	 * 
	 * @param dto : Updatedto de l'entite
	 * @return ResponseEntity avec ResponseDto. Body: UpdateDto
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
	 * @return ResponseEntity avec ResponseDto. Body: UpdateDto
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseDto<U>> readById(@PathVariable("id") @Positive int id);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour afficher la page p de la liste complète des instances d'une entité
	 * </p>
	 * @param p le numéro de la page souhaitée
	 * @return ResponseEntity avec ResponseDto. Body: page d'entité
	 */
	@GetMapping(path = "/all/{p}")
	public ResponseEntity<ResponseDto<Page<U>>> readAll(@PathVariable("p") int p);

}
