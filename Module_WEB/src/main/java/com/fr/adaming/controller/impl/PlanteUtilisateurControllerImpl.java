package com.fr.adaming.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.PageResponseDto;
import com.fr.adaming.dto.PlanteUtilisateurCreateDto;
import com.fr.adaming.dto.PlanteUtilisateurUpdateDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.service.IPlanteUtilisateurService;

/**
 * <p>
 * Controller pour l'entite Plante Utilisateur<br>
 * Etend la classe AbstractController
 * </p>
 * 
 * @author lucie
 * @since 0.0.1
 *
 */
@RestController
@RequestMapping(path = "/planteUtilisateur")
@CrossOrigin
public class PlanteUtilisateurControllerImpl
		extends AbstractController<PlanteUtilisateurCreateDto, PlanteUtilisateurUpdateDto, PlanteUtilisateur> {

	@Autowired
	private IPlanteUtilisateurService planteUtilisateurService;

	/**
	 * <p>
	 * Methode d'affichage de la Liste des Plante Utilisateur d'un Jardin
	 * 
	 * @param idJardin
	 * @return List<PlanteUtilisateur>
	 */
	@GetMapping(path = "/jardin/{idJardin}")
	public ResponseEntity<PageResponseDto<Page<PlanteUtilisateurUpdateDto>>> findByJardin(@PathVariable int idJardin, int page) {

		ServiceResponse<Page<PlanteUtilisateur>> serviceResponse1 = planteUtilisateurService.readByJardin(idJardin, page);

		return makeUpdateDtoPageResponse(serviceResponse1);
	}

}
