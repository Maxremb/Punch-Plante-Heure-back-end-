package com.fr.adaming.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.PlanteModelCreateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.service.IPlanteModelService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Classe de la couche Controller pour l'entité Plante Model Elle étend la
 * classe abstraite AbstractController
 * 
 * @author Gregoire
 * @since 0.0.1-SNAPSHOT
 *
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/plantemodel")
@Slf4j
public class PlantModelControllerImpl
		extends AbstractController<PlanteModelCreateDto, PlanteModelUpdateDto, PlanteModel> {

	@Autowired
	private IPlanteModelService servicePM;

	@Override
	public ResponseEntity<ResponseDto<Page<PlanteModelUpdateDto>>> readAll(int p) {
		log.info("Controller Plante Modele : méthode read all appelée");
		try {
			ServiceResponse<Page<PlanteModel>> serviceResponse = servicePM.readAllReduced(p);

			return makeUpdateDtoPageResponse(serviceResponse);
		} catch (Exception e) {
			log.warn("Erreur méthode PlanteModel Controller readAll" + e.getMessage());
			return null;
		}
	}

	/**
	 * Controller pour recuperer des planteModel qui comportent le string "nom" dans
	 * leurs noms commun ou scientifique
	 * 
	 * @param page La page demandée
	 * @param nom  Le nom recherché
	 * @return ResponseEntity avec ResponseDto contenant une page de
	 *         PlanteModelUpdateDto
	 * @author Gregoire
	 */
	@GetMapping(path = "/nom")
	public ResponseEntity<ResponseDto<Page<PlanteModelUpdateDto>>> readByNom(@RequestParam(name = "page") int page,
			@RequestParam(name = "nom") String nom) {
		log.info("Controller Plante Modele : méthode read by nom appelée");
		try {
			ServiceResponse<Page<PlanteModel>> serviceResponse = servicePM.findByNom(page, nom);

			return makeUpdateDtoPageResponse(serviceResponse);
		} catch (Exception e) {
			log.warn("Erreur méthode PlanteModel Controller readByNom" + e.getMessage());
			return null;
		}
	}

}
