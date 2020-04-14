package com.fr.adaming.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.PlanteUtilisateurCreateDto;
import com.fr.adaming.dto.PlanteUtilisateurUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.service.IPlanteUtilisateurService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Controller pour l'entite Plante Utilisateur<br>
 * Etend la classe AbstractController
 * </p>
 * 
 * @author Lucie
 * @since 0.0.1
 *
 */
@RestController
@RequestMapping(path = "/planteUtilisateur")
@CrossOrigin
@Slf4j
public class PlanteUtilisateurControllerImpl
		extends AbstractController<PlanteUtilisateurCreateDto, PlanteUtilisateurUpdateDto, PlanteUtilisateur> {

	@Autowired
	private IPlanteUtilisateurService planteUtilisateurService;

	/**
	 * 
	 * Methode d'affichage des plantes utilisateur d'un jardin par page
	 * 
	 * @param idJardin Id du Jardin en question
	 * @return ResponseEntity contenant un ResponseDto de type Liste de
	 *         PlanteUtilisateurUpdateDto
	 */
	@GetMapping(path = "/jardin/{idJardin}")
	public ResponseEntity<ResponseDto<Page<PlanteUtilisateurUpdateDto>>> findByJardin(@PathVariable int idJardin,
			int page) {
		log.info("Controller Plante Utilisateur : méthode find by jardin appelée (page)");
		try {
			ServiceResponse<Page<PlanteUtilisateur>> serviceResponse1 = planteUtilisateurService.readByJardin(idJardin,
					page);

			return makeUpdateDtoPageResponse(serviceResponse1);
		} catch (Exception e) {
			log.warn("Erreur méthode PlanteUtilisateur Controller findByJardin(idJardin, page)" + e.getMessage());
			return null;
		}
	}

	/**
	 * Methode d'affichage de la Liste des plantes utilisateur d'un jardin
	 * 
	 * @param idJardin Id du Jardin en question
	 * @return ResponseEntity contenant un ResponseDto de type Liste de
	 *         PlanteUtilisateurUpdateDto
	 */
	@GetMapping(path = "/jardin/liste/{idJardin}")
	public ResponseEntity<ResponseDto<List<PlanteUtilisateurUpdateDto>>> findByJardin(@PathVariable int idJardin) {
		log.info("Controller Plante Utilisateur : méthode find by jardin appelée (liste)");
		try {
			ServiceResponse<List<PlanteUtilisateur>> serviceResponse1 = planteUtilisateurService.readByJardin(idJardin);

			return makeUpdateDtoListResponse(serviceResponse1);
		} catch (Exception e) {
			log.warn("Erreur méthode PlanteUtilisateur Controller findByJardin(idJardin)" + e.getMessage());
			return null;
		}
	}

	/**
	 * Méthode permettant de supprimer tout les plantes utilisateurs d'un jardin
	 * 
	 * @param idJardin Id du Jardin en question
	 * @return ResponseEntity contenant un ResponseDto de type Liste de
	 *         PlanteUtilisateurUpdateDto
	 */
	@DeleteMapping(path = "/jardin/{idJardin}")
	public ResponseEntity<ResponseDto<PlanteUtilisateurUpdateDto>> deleteAllByJardin(@PathVariable int idJardin) {
		log.info("Controller: méthode DELETE ALL BY JARDIN appelée");

		try {
			boolean result = planteUtilisateurService.deleteByJardin(idJardin);
		
		ResponseDto<PlanteUtilisateurUpdateDto> responseDto = new ResponseDto<>();

		if (result) {
			responseDto.setError(false);
			responseDto.setMessage("Suppression réussie");
			responseDto.setBody(null);
			log.info("Controller: méthode DELETE ALL BY JARDIN - Succes");
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			responseDto.setMessage("Erreur pendant la suppression de l'entité: " + idJardin);
			responseDto.setBody(null);
			log.info("Controller: méthode DELETE ALL BY JARDIN - Erreur");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		} catch (Exception e) {
			log.warn("Erreur méthode PlanteUtilisateur Controller deleteAllByJardin" + e.getMessage());
			return null;
		}
	}

}
