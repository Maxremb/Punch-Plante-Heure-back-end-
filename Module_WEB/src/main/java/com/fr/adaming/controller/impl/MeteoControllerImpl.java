package com.fr.adaming.controller.impl;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.MeteoCreateDto;
import com.fr.adaming.dto.MeteoUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.service.IMeteoService;

/**
 * @author Jeanne-Marie MATHEVET
 *
 */
@RestController
@CrossOrigin
@RequestMapping (path= "/meteo")
public class MeteoControllerImpl extends AbstractController<MeteoCreateDto, MeteoUpdateDto, Meteo>{
	
	
	@Autowired
	private IMeteoService service;
	
	/**
	 * Méthode visant à récupérer la meteo à une date donnée en paramètre
	 * @param date date à laquelle on veut connaitre la météo
	 * @return ResponseEntity de type ResponseDto de type MeteoUpdateDto
	 */
	@GetMapping (path= "/date")
	public ResponseEntity<ResponseDto<MeteoUpdateDto>> readByDate(@RequestParam(name = "date") @NotBlank LocalDate date) {

		ServiceResponse<Meteo> response = service.readByDate(date);
		
		return makeUpdateDtoResponse(response);
		
	}

	
	

}
