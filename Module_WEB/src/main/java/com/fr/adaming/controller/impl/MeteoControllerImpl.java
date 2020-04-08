package com.fr.adaming.controller.impl;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
 * Classe de la couche Controller pour l'entité Meteo
 * Elle implémente la classe abstraite AbstractController
 * @author Jeanne-Marie MATHEVET
 * @since 0.0.1-SNAPSHOT
 */
@RestController
@CrossOrigin
@RequestMapping (path= "/meteo")
public class MeteoControllerImpl extends AbstractController<MeteoCreateDto, MeteoUpdateDto, Meteo>{
	
	
	@Autowired
	private IMeteoService service;
	
	/**
	 * Méthode visant à récupérer la meteo de tous les departements à une date donnée en paramètre
	 * @param date date à laquelle on veut connaitre la météo
	 * @param page page que l'utilisateur veut afficher
	 * @return ResponseEntity contenant un ResponseDto de type Page de MeteoUpdateDto
	 */
	@GetMapping (path= "/date")
	public ResponseEntity<ResponseDto<Page<MeteoUpdateDto>>> readByDate(@RequestParam(name = "date") @NotBlank String date, @RequestParam(name = "page") @Positive int page) {

		ServiceResponse<Page<Meteo>> response = service.readByDate(LocalDate.parse(date), page);
		return makeUpdateDtoPageResponse(response);
		
	}
	
	/**
	 * Methode visant à récupérer la météo d'un département à une date donnée
	 * @param date date à laquelle on veut connaitre la météo
	 * @param numDepartement numéro de departement duquel on veut connaitre la météo
	 * @return ResponseEntity contenant ResponseDto de MeteoUpdateDto
	 */
	@GetMapping(path= "/datedepartement")
	public ResponseEntity<ResponseDto<MeteoUpdateDto>> readByDateAndDepartement (@RequestParam(name="date") @NotBlank String date, @RequestParam(name= "numero") int numDepartement) {

		ServiceResponse<Meteo> response = service.readByDateAndDepartement(LocalDate.parse(date), numDepartement);
		return makeUpdateDtoResponse(response);
	
	}
	
	/** Done une liste de meteo par mois et departement.
	 * @param mois Le numéro du mois
	 * @param numDepartement Le nuléro du département
	 * @return ResponseEntity avec ResponseDto contenant une liste de meteoUpdateDto
	 * @author Gregoire
	 */
	@GetMapping(path = "/mois-departement")
	public ResponseEntity<ResponseDto<List<MeteoUpdateDto>>> readByMoisAndDepartement(@RequestParam(name = "annee") int annee, @RequestParam(name = "mois") int mois, @RequestParam(name = "depNum") int numDepartement){
				
		return makeUpdateDtoListResponse(service.readByMonthAndDepartement(annee, mois, numDepartement));
		
	}
}
