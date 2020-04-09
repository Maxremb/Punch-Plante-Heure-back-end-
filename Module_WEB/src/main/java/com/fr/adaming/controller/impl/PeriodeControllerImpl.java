package com.fr.adaming.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.PeriodeCreateDto;
import com.fr.adaming.dto.PeriodeUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Periode;
import com.fr.adaming.enums.TypePeriod;
import com.fr.adaming.service.IPeriodeService;

/**
 * Contoller pour les periodes qui joignent les models de plantes et les
 * départements
 * 
 * @author Grégoire
 * @since 0.0.1
 */
@RestController
@RequestMapping(path = "/periode")
@CrossOrigin
public class PeriodeControllerImpl extends AbstractController<PeriodeCreateDto, PeriodeUpdateDto, Periode> {

	@Autowired
	IPeriodeService periodeService;

	/**
	 * Cherche dans la base de données toutes les periodes associées à un
	 * departement
	 * 
	 * @param id L'identifiant du département
	 * @return ResponseEntity contenant une ResponseDto avec une liste de dto
	 */
	@GetMapping(path = "/departement/{depId}")
	public ResponseEntity<ResponseDto<Page<PeriodeUpdateDto>>> readByDepartementId(@PathVariable("depId") int id, @RequestParam(name = "page") int page) {

		ServiceResponse<Page<Periode>> serviceResponse = periodeService.readByDepartementId(page, id);

		return makeUpdateDtoPageResponse(serviceResponse);

	}

	/**
	 * @param id L'identifiant de la plante
	 * @return
	 */
	@GetMapping(path = "/plant/{plantId}")
	public ResponseEntity<ResponseDto<Page<PeriodeUpdateDto>>> readByPlanteId(@PathVariable("plantId") int id, @RequestParam("page") int page) {

		ServiceResponse<Page<Periode>> serviceResponse = periodeService.readByPlanteModelId(page, id);

		return makeUpdateDtoPageResponse(serviceResponse);

	}

	/**
	 * Devrais retourner une liste avec 5 elements; un par type d'action.
	 * 
	 * @param idDep   L'identifiant du département
	 * @param idPlant L'identifiant du model de plante
	 * @return
	 */
	@GetMapping(path = "/alltypes")
	public ResponseEntity<ResponseDto<List<PeriodeUpdateDto>>> readByDepartementIdAndPlanteModelId(
			@RequestParam(name = "depId") int idDep, @RequestParam(name = "plantId") int idPlant) {

		ServiceResponse<List<Periode>> serviceResponse = periodeService.readByDepartementIdAndPlanteModelId(idDep,
				idPlant);

		return makeUpdateDtoListResponse(serviceResponse);

	}

	/**
	 * Retourne une period associé à un departement, une periode, et un type.
	 * Exemple d'URL: /periode/onetype?depId=69&plantId=12&type=REMPOTAGE
	 * 
	 * @param idDep   L'id du département
	 * @param idPlant L'id du model de plante
	 * @param type    le type d'action
	 * @return
	 */
	@GetMapping(path = "/onetype")
	public ResponseEntity<ResponseDto<PeriodeUpdateDto>> readByPlanteModelIdAndDepartementIdAndType(
			@RequestParam(name = "depId") int idDep, @RequestParam(name = "plantId") int idPlant,
			@RequestParam(name = "type") TypePeriod type) {

		ServiceResponse<Periode> serviceResponse = periodeService.readByDepartementIdAndPlanteModelIdAndType(idDep,
				idPlant, type);

		return makeUpdateDtoResponse(serviceResponse);

	}

}
