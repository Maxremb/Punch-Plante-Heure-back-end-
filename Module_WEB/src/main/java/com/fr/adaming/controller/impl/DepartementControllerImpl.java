package com.fr.adaming.controller.impl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractControllerDepartement;
import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.MeteoCreateDto;
import com.fr.adaming.dto.MeteoUpdateDto;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;

@RestController
@CrossOrigin
public class DepartementControllerImpl extends AbstractControllerDepartement<DepartementDto, MeteoUpdateDto, Meteo, MeteoCreateDto, Departement> {
	
}
