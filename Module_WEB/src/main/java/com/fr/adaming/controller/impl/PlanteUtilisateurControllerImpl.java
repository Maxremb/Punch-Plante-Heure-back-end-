package com.fr.adaming.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.converter.JardinConverter;
import com.fr.adaming.converter.PlanteUtilisateurConverter;
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.PlanteUtilisateurCreateDto;
import com.fr.adaming.dto.PlanteUtilisateurUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.service.IPlanteUtilisateurService;
import com.fr.adaming.service.impl.JardinServiceImpl;
import com.fr.adaming.service.impl.PlanteUtilisateurServiceImpl;

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

	@Autowired
	private PlanteUtilisateurConverter planteUtilisateurConverter;

	/**
	 * <p>
	 * Methode d'affichage de la Liste des Plante Utilisateur d'un Jardin
	 * 
	 * @param idJardin
	 * @return List<PlanteUtilisateur>
	 */
	@GetMapping(path = "/jardin/{idJardin}")
	public ResponseEntity<ResponseDto<List<PlanteUtilisateurUpdateDto>>> findByJardin(@PathVariable int idJardin) {

		ServiceResponse<List<PlanteUtilisateur>> serviceResponse1 = planteUtilisateurService.findByJardin(idJardin);
		List<PlanteUtilisateurUpdateDto> planteUtilisateurDtoListe = planteUtilisateurConverter
				.convertListEntityToUpdateDto(serviceResponse1.getBody());

		ResponseDto<List<PlanteUtilisateurUpdateDto>> responseDto = new ResponseDto<List<PlanteUtilisateurUpdateDto>>();
		responseDto.setError(false);
		responseDto.setMessage(serviceResponse1.getMessage());
		responseDto.setBody(planteUtilisateurDtoListe);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

}
