package com.fr.adaming.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<ResponseDto<List<PlanteUtilisateurUpdateDto>>> findByJardin(@PathVariable int idJardin, Pageable pageable) {

		ServiceResponse<List<PlanteUtilisateur>> serviceResponse1 = planteUtilisateurService.readByJardin(idJardin, pageable);

		return makeUpdateDtoListResponse(serviceResponse1);
	}

}
