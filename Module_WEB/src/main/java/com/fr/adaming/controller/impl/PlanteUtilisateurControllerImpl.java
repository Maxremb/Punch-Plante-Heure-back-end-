package com.fr.adaming.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.PlanteUtilisateurCreateDto;
import com.fr.adaming.dto.PlanteUtilisateurUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.service.impl.PlanteUtilisateurServiceImpl;

@RestController
public class PlanteUtilisateurControllerImpl extends AbstractController<PlanteUtilisateurCreateDto, PlanteUtilisateurUpdateDto, PlanteUtilisateur> {

	@Autowired
	private PlanteUtilisateurServiceImpl service;
	
	
	public ResponseEntity<ResponseDto<PlanteUtilisateurUpdateDto>> findByJardin() {
		return null;
		
	}
	
}
