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

@RequestMapping(path = "/utilisateur")
@RestController
@CrossOrigin
@Slf4j
public class UtilisateurControllerImpl
		extends AbstractController<UtilisateurCreateDto, UtilisateurUpdateDto, Utilisateur> {

	@Autowired
	private IUtilisateurService userService;


	@GetMapping(path = "/nomEtPrenom")
	public ResponseEntity<ResponseDto<UtilisateurUpdateDto>> readByNomAndPrenom(@RequestParam(name = "nom") String nom,
			@RequestParam(name = "prenom") String prenom) {

		ServiceResponse<Utilisateur> resp = userService.readByNomAndPrenom(nom, prenom);
		return makeUpdateDtoResponse(resp);

	}

	@GetMapping(path = "/actif")
	public ResponseEntity<ResponseDto<Boolean>> isActif(@RequestParam(name = "pseudonyme") String pseudonyme) {
		log.info("Controller: méthode isActif appelée");

		boolean result = userService.isActif(pseudonyme);
		ResponseDto<Boolean> responseDto = new ResponseDto<>();

		if (result) {
			responseDto.setError(false);
			responseDto.setMessage("Un utilisateur existe avec ce pseudonyme");
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			responseDto.setMessage("Aucun utilisateur n'existe avec pseudo : " + pseudonyme);
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}

	}

	@GetMapping(path = "/desactivate")
	public ResponseEntity<ResponseDto<Boolean>> desactivateUtilisateur(@RequestParam(name = "id") Integer id) {
		log.info("Controller: méthode desactivateUser appelée");

		boolean result = userService.desactivateUser(id);
		ResponseDto<Boolean> responseDto = new ResponseDto<>();

		if (result) {
			responseDto.setError(false);
			responseDto.setMessage("L'utilisateur à bien été désactivé");
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			responseDto.setMessage("Aucun utilisateur n'existe avec id : " + id);
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
	}
	
	@GetMapping(path = "/activate")
	public ResponseEntity<ResponseDto<Boolean>> activateUtilisateur(@RequestParam(name = "id") Integer id){
		log.info("Controller: méthode activateUser appelée");

		boolean result = userService.activateUser(id);
		ResponseDto<Boolean> responseDto = new ResponseDto<>();

		if (result) {
			responseDto.setError(false);
			responseDto.setMessage("L'utilisateur à bien été activé");
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			responseDto.setMessage("Aucun utilisateur n'existe avec id : " + id);
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
	}

}
