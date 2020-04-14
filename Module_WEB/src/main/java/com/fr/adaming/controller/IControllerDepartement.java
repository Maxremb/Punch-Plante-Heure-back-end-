package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.fr.adaming.dto.ResponseDto;

//TODO ajouter une responseDto

/**
 *
 * @author Isaline MILLET
 * 
 *         <b>Description : </b>
 *         <p>
 *         Interface pour le Controller département, implémente une classe abstraite
 *         AbstractControllerDepartement
 *         Définit les méthodes CRUD : Create, Update, Read
 *         (all, ByID, ByName et MeteoByNumDep) et Delete.
 *         </p>
 *
 * @param <D> DepartementDto
 * @param <MU> MeteoUpdateDto
 * 
 */
@CrossOrigin
@RequestMapping(path = "/departement")
public interface IControllerDepartement<D, MU> {

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour creer un département.
	 * </p>
	 * 
	 * @param dto : DepartementDto
	 * @return ResponseDto : erreur true or false / body null ou objet DepartementDto / message
	 */
	@PostMapping
	public ResponseEntity<ResponseDto<D>> create(@RequestBody @Valid D dto);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour supprimer un département via son id.
	 * </p>
	 * 
	 * @param id : id du département
	 * @return ResponseDto : error true or false / body null / Message
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDto<D>> deleteById(@PathVariable("id") @Positive int id);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour modifer un département.
	 * </p>
	 * 
	 * @param dto : DepartementDto de l'entite
	 * @return ResponseDto : erreur true or false / body null ou objet DepartementDto / message
	 */
	@PutMapping
	public ResponseEntity<ResponseDto<D>> update(@RequestBody @Valid D dto);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour afficher un département via son id.
	 * </p>
	 * 
	 * @param id : id de l'entite
	 * @return ResponseDto : erreur true or false / body null ou objet DepartementDto / message
	 */
	@GetMapping(path = "/one/{id}")
	public ResponseEntity<ResponseDto<D>> readById(@PathVariable("id") @Positive int id);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour afficher la page p de la liste complete des instances de l'entite département
	 * </p>
	 * 
	 * @param p le numero de la page souhaitee
	 * @return ResponseDto : erreur true or false / body null ou objet page DepartementDto / message
	 */
	@GetMapping(path = "/all/{p}")
	public ResponseEntity<ResponseDto<Page<D>>> readAll(@PathVariable("p") int p);
	
	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour afficher la liste complete des instances de l'entite département
	 * </p>
	 * 
	 * @return ResponseDto : erreur true or false / body null ou objet liste DepartementDto / message
	 */
	@GetMapping(path = "/all/list")
	public ResponseEntity<ResponseDto<List<D>>> readAllList();
	
	
	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour afficher un département via son nom
	 * </p>
	 * 
	 * @param name le nom du département recherché
	 * @return ResponseDto : erreur true or false / body null ou objet DepartementDto / message
	 */
	@GetMapping(path = "/{name}")
	public ResponseEntity<ResponseDto<D>> readByName(@PathVariable("name") @NotNull String name);
	
	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour afficher des conditions météo d'un département après recherche via le numéro du département
	 * </p>
	 * 
	 * @param id le numéro du département
	 * @return ResponseDto : erreur true or false / body null ou objet liste MeteoUpdateDto / message
	 */
	@GetMapping(path = "/meteo")
	public ResponseEntity<ResponseDto<Page<MU>>> readMeteoByNumDep(@RequestParam(name = "id") @Positive int id, @RequestParam(name = "page") int page, @RequestParam(name = "elemsPerPage") int elementsPerPage, @RequestParam(name = "sortName") String sortName);

}
