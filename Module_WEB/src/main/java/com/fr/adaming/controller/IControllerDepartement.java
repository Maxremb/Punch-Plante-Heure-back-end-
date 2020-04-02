package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.dto.ResponseDto;
import com.sun.istack.NotNull;

//TODO ajouter une responseDto

/**
 *
 * @author Isaline MILLET
 * 
 *         <b>Description : </b>
 *         <p>
 *         Interface pour le Controller département, implemente une classe abstraite
 *         AbstractControllerDepartement
 *         Définit les méthodes CRUD : Create, Update, Read
 *         (all et ByID) et Delete.
 *         </p>
 *
 * @param <D> DepartementDto
 * @param <MU> MeteoUpdateDto
 * 
 */
@RequestMapping(path = "/departement")
public interface IControllerDepartement<D, MU> {

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
	public ResponseEntity<ResponseDto<D>> create(@RequestBody @Valid D dto);

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
	public ResponseEntity<ResponseDto<D>> update(@RequestBody @Valid D dto);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour afficher une Entite via son id.
	 * </p>
	 * 
	 * @param id : id de l'entite
	 * @return UpdateDto
	 */
	@GetMapping(path = "/one/{id}")
	public ResponseEntity<ResponseDto<D>> readById(@PathVariable("id") @Positive int id);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour afficher la liste des donnees d'une entite.
	 * </p>
	 * 
	 * @return listDto
	 */
	@GetMapping(path = "/all")
	public ResponseEntity<ResponseDto<List<D>>> readAll();
	
	@GetMapping(path = "/{name}")
	public ResponseEntity<ResponseDto<List<D>>> readByName(@PathVariable("name") @NotNull String name);
	
	@GetMapping(path = "/meteo/{id}")
	public ResponseEntity<ResponseDto<List<MU>>> readMeteoByNumDep(@PathVariable("id") @Positive int id);

}
