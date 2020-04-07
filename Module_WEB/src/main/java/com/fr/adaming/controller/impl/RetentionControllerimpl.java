package com.fr.adaming.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.RetentionCreateDto;
import com.fr.adaming.dto.RetentionUpdateDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Retention;
import com.fr.adaming.enums.Sol;
import com.fr.adaming.service.IRetentionService;

/**
 * Classe de la couche Controller pour l'entité Retention Elle implémente la
 * classe abstraite AbstractController
 * 
 * @author maxime
 * @since 0.0.1-SNAPSHOT
 */
@RequestMapping(path = "/retention")
@RestController
@CrossOrigin
public class RetentionControllerimpl extends AbstractController<RetentionCreateDto, RetentionUpdateDto, Retention> {

	@Autowired
	private IRetentionService serviceRetention;

	/**
	 * Méthode visant à récuprer un objet de type Retention à partir de son type de
	 * sol
	 * 
	 * @param sol correspond au type de sol voulu
	 * @return ResponseEntity contenant ResponseDto de RetentionUpdateDto
	 */
	public ResponseEntity<ResponseDto<RetentionUpdateDto>> readBySol(@RequestParam(name = "sol") Sol sol) {

		ServiceResponse<Retention> resp = serviceRetention.readBySol(sol);

		return makeUpdateDtoResponse(resp);
	}

}
