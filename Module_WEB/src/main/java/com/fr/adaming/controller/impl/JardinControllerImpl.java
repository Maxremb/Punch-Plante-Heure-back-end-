package com.fr.adaming.controller.impl;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.JardinCreateDto;
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.metier.ICalculMetier;
import com.fr.adaming.service.IJardinService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe de la couche Controller pour l'entité Jardin Elle étend la classe
 * abstraite AbstractController
 * 
 * @author Clara Cadet et Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@RequestMapping(path = "/jardin")
@RestController
@CrossOrigin
@Slf4j
public class JardinControllerImpl extends AbstractController<JardinCreateDto, JardinUpdateDto, Jardin> {

	@Autowired
	private IJardinService serviceJardin;

	@Autowired
	private ICalculMetier calculMetier;

	/**
	 * 
	 * Méthode visant à récupérer un jardin par son nom
	 * 
	 * @param page Page que l'utilisateur veut afficher
	 * @param nom  Nom du jardin en question
	 * @return ResponseEntity contenant un ResponseDto de type Page de
	 *         JardinUpdateDto
	 * @author Clara Cadet
	 */
	@GetMapping(path = "/name")
	public ResponseEntity<ResponseDto<Page<JardinUpdateDto>>> readByName(@RequestParam(name = "page") int page,
			@RequestParam(name = "nom") @NotBlank String nom) {
		log.info("Controller Jardin : méthode read by name appelée");

		try {
			ServiceResponse<Page<Jardin>> resp = serviceJardin.readByNom(page, nom);

			return makeUpdateDtoPageResponse(resp);
		} catch (Exception e) {
			log.warn("Erreur méthode Jardin Controller readByName" + e.getMessage());
			return null;
		}

	}

	/**
	 * Méthode visant à rechercher tout les jardins d'un utilisateur
	 * 
	 * @param page       Page que l'utilisateur veut afficher
	 * @param identifier Id de l'utilisateur en question
	 * @return ResponseEntity contenant un ResponseDto de type Page de
	 *         JardinUpdateDto
	 * @author Clara Cadet
	 */
	@GetMapping(path = "/user")
	public ResponseEntity<ResponseDto<Page<JardinUpdateDto>>> readByUser(@RequestParam(name = "page") int page,
			@RequestParam(name = "identifier") @Positive Integer identifier) {
		log.info("Controller Jardin : méthode read by User appelée");
		try {
			ServiceResponse<Page<Jardin>> resp = serviceJardin.readByUtilisateur(page, identifier);

			return makeUpdateDtoPageResponse(resp);
		} catch (Exception e) {
			log.warn("Erreur méthode Jardin Controller readByUser" + e.getMessage());
			return null;
		}

	}

	/**
	 * Méthdoe visant à récupérer tout les jardins d'un départemnt
	 * 
	 * @param page   Page que l'utilisateur veut afficher
	 * @param numDep Numéro du département en question
	 * @return ResponseEntity contenant un ResponseDto de type Page de
	 *         JardinUpdateDto
	 * @author Maxime Rembert
	 */
	@GetMapping(path = "/departement")
	public ResponseEntity<ResponseDto<Page<JardinUpdateDto>>> readByDep(@RequestParam(name = "page") int page,
			@RequestParam(name = "numDep") @Positive Integer numDep) {
		log.info("Controller Jardin : méthode read by département appelée");
		try {
			ServiceResponse<Page<Jardin>> resp = serviceJardin.readByDepartement(page, numDep);

			return makeUpdateDtoPageResponse(resp);
		} catch (Exception e) {
			log.warn("Erreur méthode Jardin Controller readByDep" + e.getMessage());
			return null;
		}
	}

	/**
	 * Méthode visant à ré-initialiser la réserve utile d'un jardin par arrossage.
	 * 
	 * @param id Id du jardin en question
	 * @return ResponseEntity contenant un ResponseDto de type Page de
	 *         JardinUpdateDto
	 * @author Maxime Rembert
	 */
	@GetMapping(path = "/arrosage")
	public ResponseEntity<ResponseDto<JardinUpdateDto>> reinitArrossJardin(@RequestParam(name = "id") Integer id) {
		log.info("Controller Jardin : méthode ré-intitalition arrossage appelée");
		try {
			ServiceResponse<Jardin> resp = calculMetier.reinitArrosJardin(id);

			return makeUpdateDtoResponse(resp);
		} catch (Exception e) {
			log.warn("Erreur méthode Jardin Controller reinitArrossJardin" + e.getMessage());
			return null;
		}
	}

}
