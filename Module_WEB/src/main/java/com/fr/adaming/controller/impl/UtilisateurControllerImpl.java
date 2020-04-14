package com.fr.adaming.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.dto.UtilisateurCreateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.service.IUtilisateurService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe de la couche Controller pour l'entité Utilisateur Elle étend la classe
 * abstraite AbstractController
 * 
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@RequestMapping(path = "/utilisateur")
@RestController
@CrossOrigin
@Slf4j
public class UtilisateurControllerImpl
		extends AbstractController<UtilisateurCreateDto, UtilisateurUpdateDto, Utilisateur> {

	@Autowired
	private IUtilisateurService userService;

	/**
	 * Méthode visant à récupérer un utilisateur par nom et prénom
	 * 
	 * @param nom    Nom de l'utilisateur en question
	 * @param prenom Prénom de l'utilisateur en question
	 * @return ResponseEntity contenant un ResponseDto de type Utilisateur UpdateDto
	 */
	@GetMapping(path = "/nomEtPrenom")
	public ResponseEntity<ResponseDto<UtilisateurUpdateDto>> readByNomAndPrenom(@RequestParam(name = "nom") String nom,
			@RequestParam(name = "prenom") String prenom) {
		log.info("Controlelr utilisateur : méthode read by Nom and Prenom appelée");
		try {
			ServiceResponse<Utilisateur> resp = userService.readByNomAndPrenom(nom, prenom);
			return makeUpdateDtoResponse(resp);
		} catch (Exception e) {
			log.warn("Erreur méthode Utilisateur Controller readByNomAndPrenom" + e.getMessage());
			return null;
		}

	}

	/**
	 * Méthode visant à savoir si un utilisateur est actif ou non
	 * 
	 * @param pseudonyme Pseudonyme de l'utilisateur en question
	 * @return ResponseEntity contenant un ResponseDto de type Utilisateur UpdateDto
	 */
	@GetMapping(path = "/actif")
	public ResponseEntity<ResponseDto<Boolean>> isActif(@RequestParam(name = "pseudonyme") String pseudonyme) {
		log.info("Controller utilisateur : méthode isActif appelée");

		try {
			boolean result = userService.isActif(pseudonyme);

			ResponseDto<Boolean> responseDto = new ResponseDto<>();

			if (result) {
				responseDto.setError(false);
				responseDto.setMessage("Utilisateur actif");
				responseDto.setBody(true);
				return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			} else if (!result) {
				responseDto.setError(false);
				responseDto.setMessage("Utilisateur non actif");
				responseDto.setBody(false);
				return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			} else {
				responseDto.setError(true);
				responseDto.setMessage("Aucun utilisateur n'existe avec pseudo : " + pseudonyme);
				responseDto.setBody(null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
		} catch (Exception e) {
			log.warn("Erreur méthode Utilisateur Controller isActif" + e.getMessage());
			return null;
		}

	}

	/**
	 * Méthode visant à désactiver un utilisateur
	 * 
	 * @param id Id de l'utilisateur en question
	 * @return ResponseEntity contenant un ResponseDto de type booléen
	 */
	@GetMapping(path = "/desactivate")
	public ResponseEntity<ResponseDto<Boolean>> desactivateUtilisateur(@RequestParam(name = "id") Integer id) {
		log.info("Controller utilisateur : méthode desactivateUser appelée");

		try {
			boolean result = userService.desactivateUser(id);
			ResponseDto<Boolean> responseDto = new ResponseDto<>();

			if (result) {
				responseDto.setError(false);
				responseDto.setMessage("L'utilisateur à bien été désactivé");
				responseDto.setBody(null);
				return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			} else {
				responseDto.setError(true);
				responseDto.setMessage(
						"Utilisateur déjà désactivé / erreur lors de la requête / id null ou non existant dans la DB (id : "
								+ id + " )");
				responseDto.setBody(null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
		} catch (Exception e) {
			log.warn("Erreur méthode Utilisateur Controller desactivateUtilisateur" + e.getMessage());
			return null;
		}
	}

	/**
	 * Méthdoe visant à activer un utilisateur
	 * 
	 * @param id Id de l'utilisateur en question
	 * @return ResponseEntity contenant un ResponseDto de type booléen
	 */
	@GetMapping(path = "/activate")
	public ResponseEntity<ResponseDto<Boolean>> activateUtilisateur(@RequestParam(name = "id") Integer id) {
		log.info("Controller utilisateur : méthode activateUser appelée");

		try {
			boolean result = userService.activateUser(id);

			ResponseDto<Boolean> responseDto = new ResponseDto<>();

			if (result) {
				responseDto.setError(false);
				responseDto.setMessage("L'utilisateur à bien été activé");
				responseDto.setBody(null);
				return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			} else {
				responseDto.setError(true);
				responseDto.setMessage(
						"Utilisateur déjà activé / erreur lors de la requête / id null ou non existant dans la DB (id : "
								+ id + " )");
				responseDto.setBody(null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
		} catch (Exception e) {
			log.warn("Erreur méthode Utilisateur Controller activateUtilisateur" + e.getMessage());
			return null;
		}
	}
}
